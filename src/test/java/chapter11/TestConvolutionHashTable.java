package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

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
	private static ConvolutionHashTable convolutionHashTable;

	@Before
	public void init()
	{
		convolutionHashTable = new ConvolutionHashTable(TestHashTableBase.HASH_TABLE_SIZE);

		int factor = ConvolutionHashTable.calcGroupSize(TestHashTableBase.HASH_TABLE_SIZE);
		Random random = new Random();

		IntStream.range(1, TestHashTableBase.HASH_TABLE_SIZE / 2).forEach(key ->
			{
				for (int i = 0; i < factor; i++)
				{
					key *= TEN;
					key += random.nextInt(key);
				}
				convolutionHashTable.insert(key + random.nextInt(key) * TEN);
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

		convolutionHashTable.insert(ITEM_5);

		assertEquals(ITEM_5, convolutionHashTable.find(ITEM_5));
	}

	@Test
	public void testDeleteMethod()
	{
		Integer itemForDeleting = 1000;
		convolutionHashTable.insert(itemForDeleting);

		Integer deletedItem = convolutionHashTable.delete(itemForDeleting);

		assertNotNull(deletedItem);
		assertEquals(itemForDeleting, deletedItem);

		deletedItem = convolutionHashTable.delete(itemForDeleting);
		assertNull(deletedItem);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(convolutionHashTable);
	}
}
