package chapter8;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Класс реализующий алгоритм сжатия данных кодом Хаффмана
 * выполняющий кодирование, декодирование, построение дерева, создание кодовой и частотной таблиц
 *
 * @author rassoll
 * @created 03.01.2018
 * @$Author$
 * @$Revision$
 */
class Huffman
{
	private static final char emptyNodeChar = 216;

	/**
	 * Создание частотной таблицы
	 *
	 * @param message сообщение
	 * @return частотная таблица
	 */
	static HashMap<Character, Integer> makeFrequencyTable(String message)
	{
		LinkedHashMap<Character, Integer> frequencyTable = new LinkedHashMap<>();
		for (char ch : message.toCharArray())
		{
			if (frequencyTable.containsKey(ch))
			{
				frequencyTable.computeIfPresent(ch, (k, v) -> v + 1);
			}
			else
			{
				frequencyTable.put(ch, 1);
			}
		}

		frequencyTable =
			frequencyTable.entrySet().stream()
				.sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

		return frequencyTable;
	}

	/**
	 * Создание кодовой таблицы
	 *
	 * @param message сообщение
	 * @return кодовая таблица
	 */
	static HashMap<Character, String> makeCodeTable(String message)
	{
		Tree huffmanTree = createEncodingTree(message);
		HashMap<Character, String> codeTable = new LinkedHashMap<>();

		for (char ch : message.toCharArray())
		{
			if (!codeTable.containsKey(ch))
			{
				codeTable.put(ch, getBinaryCharacterCode(huffmanTree.getRoot(), ch, new StringBuilder()));
			}
		}

		return codeTable;
	}

	/**
	 * Кодирование сообщения в двоичную форму
	 *
	 * @param message сообщение
	 * @return закодированное сообщение
	 */
	static String encode(String message)
	{
		Tree huffmanTree = createEncodingTree(message);
		StringBuilder encodedMessage = new StringBuilder();

		for (char ch : message.toCharArray())
		{
			encodedMessage.append(getBinaryCharacterCode(huffmanTree.getRoot(), ch, new StringBuilder()));
		}

		return encodedMessage.toString();
	}

	/**
	 * Декодирование сообщения из двоичной формы
	 *
	 * @param encodedMessage закодированное сообщение
	 * @param codeTable      таблица двоичных кодов
	 * @return декодированное сообщение
	 */
	static String decode(String encodedMessage, HashMap<Character, String> codeTable)
	{
		Tree huffmanDecodingTree = createDecodingTree(codeTable);
		StringBuilder decodedMessage = new StringBuilder();

		Node current = huffmanDecodingTree.getRoot();
		for (char ch : encodedMessage.toCharArray())
		{
			if (ch == '0')
			{
				current = current.leftChild;
			}
			else
			{
				current = current.rightChild;
			}

			if (current.key != emptyNodeChar)
			{
				decodedMessage.append((char) current.key);
				current = huffmanDecodingTree.getRoot();
			}
		}

		return decodedMessage.toString();
	}

	/**
	 * Построить дерево Хаффмана для кодирования сообщения
	 *
	 * @param messageForEncoding сообщение для постройки дерева
	 * @return дерево Хаффмана
	 */
	static Tree createEncodingTree(String messageForEncoding)
	{
		PriorityQueue<Tree> trees = new PriorityQueue<>(
			(Tree firstTree, Tree secondTree) ->
			{
				if (firstTree.getRoot().value > secondTree.getRoot().value)
				{
					return 1;
				}
				else if (firstTree.getRoot().value < secondTree.getRoot().value)
				{
					return -1;
				}
				else
				{
					return 0;
				}
			});

		makeFrequencyTable(messageForEncoding)
			.entrySet()
			.forEach((Map.Entry<Character, Integer> entry) ->
			{
				Tree tempTree = new Tree(new Node(entry.getKey(), entry.getValue()));
				trees.add(tempTree);
			});

		if (trees.size() == 1)
		{
			Node leftNode = trees.poll().getRoot();
			Node newNode = new Node(emptyNodeChar, leftNode.value);
			newNode.leftChild = leftNode;
			trees.add(new Tree(newNode));
		}
		else
		{
			while (trees.size() != 1)
			{
				Node leftNode = trees.poll().getRoot();
				Node rightNode = trees.poll().getRoot();

				Node newNode = new Node(emptyNodeChar, leftNode.value + rightNode.value);
				newNode.leftChild = leftNode;
				newNode.rightChild = rightNode;
				trees.add(new Tree(newNode));
			}
		}

		return trees.poll();
	}

	/**
	 * Построить дерево Хаффмана для декодирования сообщения
	 *
	 * @param codeTable таблица двоичных кодов
	 * @return дерево Хаффмана
	 */
	private static Tree createDecodingTree(HashMap<Character, String> codeTable)
	{
		Tree huffmanDecodingTree = new Tree(new Node(emptyNodeChar, emptyNodeChar));
		Node rootNode = huffmanDecodingTree.getRoot();
		codeTable.forEach((key, value) ->
		{
			Node currentNode = rootNode;
			for (char ch : value.toCharArray())
			{
				if (ch == '0')
				{
					if (currentNode.leftChild == null)
					{
						currentNode.leftChild = new Node(emptyNodeChar, emptyNodeChar);
					}
					currentNode = currentNode.leftChild;
				}
				else
				{
					if (currentNode.rightChild == null)
					{
						currentNode.rightChild = new Node(emptyNodeChar, emptyNodeChar);
					}
					currentNode = currentNode.rightChild;
				}
			}

			currentNode.key = key;
		});

		return huffmanDecodingTree;
	}

	/**
	 * Рекурсивное получение двоичного кода для символа
	 * при помощи дерева Хаффмана
	 *
	 * @param rootNode               корневой элемент дерева Хаффмана
	 * @param character              символ
	 * @param binaryCharacterBuilder постротель двоичного кода
	 * @return двоичный код символа
	 */
	private static String getBinaryCharacterCode(Node rootNode, char character, StringBuilder binaryCharacterBuilder)
	{
		String result = "";

		if (binaryCharacterBuilder == null)
		{
			binaryCharacterBuilder = new StringBuilder();
		}

		if (rootNode.key == (int) character)
		{
			return binaryCharacterBuilder.toString();
		}

		if (rootNode.leftChild != null)
		{
			result = getBinaryCharacterCode(rootNode.leftChild, character, binaryCharacterBuilder.append('0'));
			if (result != null && !result.equals(""))
			{
				return result;
			}
		}

		if (rootNode.rightChild != null)
		{
			result = getBinaryCharacterCode(rootNode.rightChild, character, binaryCharacterBuilder.append('1'));
			if (result != null && !result.equals(""))
			{
				return result;
			}
		}

		binaryCharacterBuilder.deleteCharAt(binaryCharacterBuilder.length() - 1);
		return result;
	}
}
