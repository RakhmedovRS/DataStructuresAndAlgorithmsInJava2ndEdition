package chapter7;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование сущности {@link ArrayPartition}
 *
 * @author rassoll
 * @created 05.12.2017
 * @$Author$
 * @$Revision$
 */
public class ArrayPartitionTest
{
	private ArrayPartition arrayPartition;
	private int arrayPartitionSize = 10;

	@Before
	public void init()
	{
		arrayPartition = new ArrayPartition(arrayPartitionSize);
	}

	/**
	 * Тестирование работы метода вставки в массив
	 */
	@Test
	public void testInsertMethod()
	{
		IntStream.range(0, arrayPartitionSize).forEach(k -> arrayPartition.insert(k));
	}

	/**
	 * Тестирование ситуации с превышением возможного количества элементов
	 *
	 * @throws IndexOutOfBoundsException ожидаемая ошибка
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertMethodError() throws IndexOutOfBoundsException
	{
		IntStream.range(0, arrayPartitionSize + 1).forEach(k -> arrayPartition.insert(k));
	}

	/**
	 * Тестирование метода вывода содержимого массива
	 */
	@Test
	public void testDisplayMethod()
	{
		IntStream.range(0, arrayPartitionSize).forEach(k -> arrayPartition.insert(k));
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arrayPartition.display());
	}

	/**
	 * Тестирование метода перестановки двух элементов
	 */
	@Test
	public void checkSwapMethod()
	{
		IntStream.range(0, arrayPartitionSize).forEach(k -> arrayPartition.insert(k));

		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arrayPartition.display());

		arrayPartition.swap(1, 9);
		assertEquals("A = 0 9 2 3 4 5 6 7 8 1 ", arrayPartition.display());

		arrayPartition.swap(6, 2);
		assertEquals("A = 0 9 6 3 4 5 2 7 8 1 ", arrayPartition.display());

		arrayPartition.swap(4, 8);
		assertEquals("A = 0 9 6 3 8 5 2 7 4 1 ", arrayPartition.display());
	}

	/**
	 * Тестирование разбиения массива
	 */
	@Test
	public void checkPartitionMethod()
	{
		int multiplier = 3;
		int partition;

		arrayPartition = new ArrayPartition(arrayPartitionSize * multiplier);

		arrayPartition.insert(9);
		arrayPartition.insert(0);
		arrayPartition.insert(1);
		arrayPartition.insert(8);
		arrayPartition.insert(3);
		arrayPartition.insert(7);
		arrayPartition.insert(6);
		arrayPartition.insert(2);
		arrayPartition.insert(5);
		arrayPartition.insert(4);
		arrayPartition.insert(19);
		arrayPartition.insert(10);
		arrayPartition.insert(11);
		arrayPartition.insert(18);
		arrayPartition.insert(13);
		arrayPartition.insert(17);
		arrayPartition.insert(16);
		arrayPartition.insert(12);
		arrayPartition.insert(15);
		arrayPartition.insert(14);
		arrayPartition.insert(29);
		arrayPartition.insert(20);
		arrayPartition.insert(21);
		arrayPartition.insert(28);
		arrayPartition.insert(23);
		arrayPartition.insert(27);
		arrayPartition.insert(26);
		arrayPartition.insert(22);
		arrayPartition.insert(25);
		arrayPartition.insert(24);

		arrayPartition.partition(0, 10, 6);
		assertEquals("A = 4 0 1 5 3 2 6 7 8 9 19 10 11 18 13 17 16 12 15 14 29 20 21 28 23 27 26 22 25 24 ", arrayPartition.display());

		arrayPartition.partition(10, 20, 13);
		assertEquals("A = 4 0 1 5 3 2 6 7 8 9 12 10 11 13 18 17 16 19 15 14 29 20 21 28 23 27 26 22 25 24 ", arrayPartition.display());

		arrayPartition.partition(0, arrayPartitionSize * multiplier - 1, 15);
		assertEquals("A = 4 0 1 5 3 2 6 7 8 9 12 10 11 13 14 15 16 19 17 18 29 20 21 28 23 27 26 22 25 24 ", arrayPartition.display());
	}
}
