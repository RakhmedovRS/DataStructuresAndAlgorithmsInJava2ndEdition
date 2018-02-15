package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.HASH_TABLE_SIZE;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Тестирование сущности {@link ConvolutionHashTable}
 *
 * @author rassoll
 * @created 15.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestConvolutionHashTable
{
	private static final int TEN = 10;
	private static ConvolutionHashTable hashTable;
	private static ArrayList<Integer> additionalItems;

	{
		additionalItems = new ArrayList<>();
		IntStream.range(100_000, 100_010).forEach((value) -> additionalItems.add(value));
	}

	@Before
	public void init()
	{
		hashTable = new ConvolutionHashTable(HASH_TABLE_SIZE);

		int factor = ConvolutionHashTable.calcGroupSize(HASH_TABLE_SIZE);
		Random random = new Random();

		IntStream.range(1, HASH_TABLE_SIZE / 2).forEach(key ->
			{
				for (int i = 0; i < factor; i++)
				{
					key *= TEN;
					key += random.nextInt(key);
				}
				hashTable.insert(key + random.nextInt(key) * TEN);
			}
		);
	}

	/**
	 * Тестирование метода рассчета длинны группы цифр
	 */
	@Test
	public void textCalcGroupSize()
	{
		int size = TEN;
		for (int i = 1; i < TEN; i++)
		{
			assertEquals(i, ConvolutionHashTable.calcGroupSize(size));
			size *= TEN;
		}
	}

	@Test
	public void testHashFunction()
	{
		assertEquals(46, new ConvolutionHashTable(100).hashFunction(1234));
		assertEquals(51, new ConvolutionHashTable(100).hashFunction(12345));
		assertEquals(9, new ConvolutionHashTable(100).hashFunction(1234567));
		assertEquals(80, new ConvolutionHashTable(100).hashFunction(12345678));

		assertEquals(127, new ConvolutionHashTable(1000).hashFunction(1234));
		assertEquals(168, new ConvolutionHashTable(1000).hashFunction(12345));
		assertEquals(586, new ConvolutionHashTable(1000).hashFunction(1234567));
		assertEquals(657, new ConvolutionHashTable(1000).hashFunction(12345678));
		assertEquals(368, new ConvolutionHashTable(1000).hashFunction(123456789));
	}

	@Test
	public void testInsertMethod()
	{
		final Integer ITEM_5 = 101;

		hashTable.insert(ITEM_5);

		assertEquals(ITEM_5, hashTable.find(ITEM_5));
	}

	@Test
	public void testDeleteMethod()
	{
		Integer itemForDeleting = 1000;
		hashTable.insert(itemForDeleting);

		Integer deletedItem = hashTable.delete(itemForDeleting);

		assertNotNull(deletedItem);
		assertEquals(itemForDeleting, deletedItem);

		deletedItem = hashTable.delete(itemForDeleting);
		assertNull(deletedItem);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(hashTable);
	}

	@Test
	public void checkGettingElementsNumber()
	{
		TestHashTableBase.checkGettingElementsNumber(hashTable, HASH_TABLE_SIZE / 2 - 1, additionalItems);
	}

	@Test
	public void checkHashTableSize()
	{
		TestHashTableBase.checkHashTableSize(hashTable, HASH_TABLE_SIZE, additionalItems);
	}

	@Test
	public void testGettingLoadFactor()
	{
		TestHashTableBase.testGettingLoadFactor(hashTable, (HASH_TABLE_SIZE / 2 - 1) / (float) HASH_TABLE_SIZE, additionalItems);
	}
}
