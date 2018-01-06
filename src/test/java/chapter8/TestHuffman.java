package chapter8;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link Huffman}
 *
 * @author rassoll
 * @created 03.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestHuffman
{
	private final String encodedMessage = "SUSIE_SAYS_IT_IS_EASY";
	private final String decodedMessage = "10011010110111100100101110100011001110011010001111010101110";

	/**
	 * Тестирование метода создающего частотную таблицу
	 */
	@Test
	public void testCreatingFrequencyTable()
	{
		HashMap<Character, Integer> frequencyTable = Huffman.makeFrequencyTable(encodedMessage);

		assertNotNull(frequencyTable);

		assertTrue(frequencyTable.containsKey('U'));
		assertEquals(1, frequencyTable.get('U').intValue());

		assertTrue(frequencyTable.containsKey('T'));
		assertEquals(1, frequencyTable.get('T').intValue());

		assertTrue(frequencyTable.containsKey('Y'));
		assertEquals(2, frequencyTable.get('Y').intValue());

		assertTrue(frequencyTable.containsKey('E'));
		assertEquals(2, frequencyTable.get('E').intValue());

		assertTrue(frequencyTable.containsKey('A'));
		assertEquals(2, frequencyTable.get('A').intValue());

		assertTrue(frequencyTable.containsKey('I'));
		assertEquals(3, frequencyTable.get('I').intValue());

		assertTrue(frequencyTable.containsKey('_'));
		assertEquals(4, frequencyTable.get('_').intValue());

		assertTrue(frequencyTable.containsKey('S'));
		assertEquals(6, frequencyTable.get('S').intValue());
	}

	/**
	 * Тестирование метода стоящего дерево Хаффмана
	 */
	@Test
	public void testMakeTreeMethod()
	{
		Tree huffmanTree = Huffman.createEncodingTree(encodedMessage);
		HashMap<Character, Integer> frequencyTable = Huffman.makeFrequencyTable(encodedMessage);

		assertNotNull(frequencyTable);
		assertNotNull(huffmanTree);

		final int[] counter = new int[1];
		counter[0] = 0;
		frequencyTable.entrySet().forEach((Map.Entry<Character, Integer> entry) -> counter[0] += entry.getValue());

		assertEquals(counter[0], huffmanTree.getRoot().value);

		huffmanTree.displayTree(true);
	}

	/**
	 * Тестирование метода создающего кодовую таблицу
	 */
	@Test
	public void testCreatingCodeTable()
	{
		HashMap<Character, String> codeTable = Huffman.makeCodeTable(encodedMessage);
		assertNotNull(codeTable);

		assertEquals(codeTable.get('S'), "10");
		assertEquals(codeTable.get('U'), "0110");
		assertEquals(codeTable.get('I'), "110");
		assertEquals(codeTable.get('E'), "1111");
		assertEquals(codeTable.get('_'), "00");
		assertEquals(codeTable.get('A'), "010");
		assertEquals(codeTable.get('Y'), "1110");
		assertEquals(codeTable.get('T'), "0111");
	}

	/**
	 * Тестирование метода кодирующего сообшения в двоичную форму
	 */
	@Test
	public void testEncodeMethod()
	{
		String encodedMessage = Huffman.encode(this.encodedMessage);
		assertNotNull(encodedMessage);
		assertEquals(decodedMessage, encodedMessage);
	}

	/**
	 * Тестирование метода декодирующего сообшения из двоичной формы
	 */
	@Test
	public void testDecodeMethod()
	{
		HashMap<Character, String> codeTable = Huffman.makeCodeTable(encodedMessage);
		assertNotNull(codeTable);

		assertEquals(encodedMessage, Huffman.decode(decodedMessage, codeTable));
	}
}
