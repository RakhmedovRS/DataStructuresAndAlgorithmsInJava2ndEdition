package chapter11;

import base.structures.HashTable;
import chapter8.Node;
import chapter8.Tree;

/**
 * Программный проект 11.5 - Program project 11.5
 * Хэш-таблица использующая двоичные деревья для разрешения коллизий
 *
 * @author rassoll
 * @created 16.02.2018
 * @$Author$
 * @$Revision$
 */
public class HashTree implements HashTable<Integer>
{
	private Tree[] hashArray;
	private int arraySize;
	private int elementsNumber;

	/**
	 * Конструкторк
	 *
	 * @param size размер хэш-таблицы
	 */
	public HashTree(int size)
	{
		this.arraySize = size;
		hashArray = new Tree[arraySize];

		for (int i = 0; i < arraySize; i++)
		{
			hashArray[i] = new Tree();
		}
	}

	@Override
	public int getHashTableSize()
	{
		return arraySize;
	}

	@Override
	public int getElementsNumber()
	{
		return elementsNumber;
	}

	@Override
	public int hashFunction(Integer item)
	{
		return item % arraySize;
	}

	@Override
	public void insert(Integer item)
	{
		int hashValue = hashFunction(item);

		if (hashArray[hashValue] == null)
		{
			hashArray[hashValue] = new Tree();
		}

		hashArray[hashValue].insert(new Node(item, item));
		elementsNumber++;
	}

	@Override
	public Integer delete(Integer item)
	{
		int hashValue = hashFunction(item);
		if (hashArray[hashValue].delete(item))
		{
			elementsNumber--;
			return item;
		}
		else
		{
			return null;
		}
	}

	@Override
	public Integer find(Integer item)
	{
		int hashValue = hashFunction(item);
		return hashArray[hashValue].find(item).key;
	}

	@Override
	public void rehash()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Integer[] getHashArray()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDisplayData()
	{
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < arraySize; i++)
		{
			stringBuilder.append(String.format("%s. ", i));
			stringBuilder.append(hashArray[i].displayTree());
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
