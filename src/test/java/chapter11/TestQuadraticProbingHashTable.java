package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.*;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Тестирование сущности {@link QuadraticProbingHashTable}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestQuadraticProbingHashTable
{
	private static QuadraticProbingHashTable quadraticProbingHashTable;

	@Before
	public void init()
	{
		quadraticProbingHashTable = new QuadraticProbingHashTable(HASH_TABLE_SIZE);

		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach(key -> quadraticProbingHashTable.insert(new DataItem(key)));
	}

	@Test
	public void testHashFunction()
	{
		assertEquals(1, quadraticProbingHashTable.hashFunction(ITEM_1));
		assertEquals(0, quadraticProbingHashTable.hashFunction(ITEM_2));
		assertEquals(150, quadraticProbingHashTable.hashFunction(ITEM_3));
		assertEquals(199, quadraticProbingHashTable.hashFunction(ITEM_4));
	}

	@Test
	public void testInsertMethod()
	{
		TestHashTableBase.testInsertMethod(quadraticProbingHashTable);
	}

	@Test
	public void testDeleteMethod()
	{
		TestHashTableBase.testDeleteMethod(quadraticProbingHashTable);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(quadraticProbingHashTable);
	}

	/**
	 * Тестирование метода получения первого следующего за переданным значением, простого числа
	 */
	@Test
	public void testGetPrimeMethod()
	{
		assertEquals(3, QuadraticProbingHashTable.getPrime(2));
		assertEquals(5, QuadraticProbingHashTable.getPrime(3));
		assertEquals(7, QuadraticProbingHashTable.getPrime(6));
	}

	/**
	 * Тестирование метода проверки того, что переданное число является простым
	 */
	@Test
	public void checkIsPrimeMethod()
	{
		assertTrue(QuadraticProbingHashTable.isPrime(11));
		assertTrue(QuadraticProbingHashTable.isPrime(333));
		assertTrue(QuadraticProbingHashTable.isPrime(555));

		assertFalse(QuadraticProbingHashTable.isPrime(10));
		assertFalse(QuadraticProbingHashTable.isPrime(300));
		assertFalse(QuadraticProbingHashTable.isPrime(55556));
	}
}
