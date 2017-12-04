package chapter7;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Класс тестирования сущности {@link ArrayQk}
 *
 * @author rassoll
 * @created 04.12.2017
 * @$Author$
 * @$Revision$
 */
public class TestArrayQk
{
	private ArrayQk arrayQk;
	private int arrayQkSize = 10;

	@Before
	public void init()
	{
		arrayQk = new ArrayQk(arrayQkSize);
	}

	/**
	 * Тестирование работы метода вставки в массив
	 */
	@Test
	public void testInsertMethod()
	{
		IntStream.range(0, arrayQkSize).forEach(k -> arrayQk.insert(k));
	}

	/**
	 * Тестирование ситуации с превышением возможного количества элементов
	 *
	 * @throws IndexOutOfBoundsException ожидаемая ошибка
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertMethodError() throws IndexOutOfBoundsException
	{
		IntStream.range(0, arrayQkSize + 1).forEach(k -> arrayQk.insert(k));
	}

	/**
	 * Тестирование метода вывода содержимого массива
	 */
	@Test
	public void testDisplayMethod()
	{
		IntStream.range(0, arrayQkSize).forEach(k -> arrayQk.insert(k));
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arrayQk.display());
	}

	/**
	 * Тестирование метода перестановки двух элементов
	 */
	@Test
	public void checkSwapMethod()
	{
		IntStream.range(0, arrayQkSize).forEach(k -> arrayQk.insert(k));

		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arrayQk.display());

		arrayQk.swap(0, 9);
		assertEquals("A = 9 1 2 3 4 5 6 7 8 0 ", arrayQk.display());

		arrayQk.swap(6, 2);
		assertEquals("A = 9 1 6 3 4 5 2 7 8 0 ", arrayQk.display());

		arrayQk.swap(4, 8);
		assertEquals("A = 9 1 6 3 8 5 2 7 4 0 ", arrayQk.display());
	}

	/**
	 * Тестирование сортировки методом вставки подмассива {@link ArrayQk}
	 */
	@Test
	public void checkInsertionSort()
	{
		int multiplier = 2;

		arrayQk = new ArrayQk(arrayQkSize * multiplier);

		arrayQk.insert(9);
		arrayQk.insert(0);
		arrayQk.insert(1);
		arrayQk.insert(8);
		arrayQk.insert(3);
		arrayQk.insert(7);
		arrayQk.insert(6);
		arrayQk.insert(2);
		arrayQk.insert(5);
		arrayQk.insert(4);

		IntStream.range(arrayQkSize + 1, arrayQkSize * multiplier).forEach(k -> arrayQk.insert(k));

		assertEquals("A = 9 0 1 8 3 7 6 2 5 4 11 12 13 14 15 16 17 18 19 ", arrayQk.display());

		arrayQk.insertionSort(0, arrayQkSize);
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 11 12 13 14 15 16 17 18 19 ", arrayQk.display());
	}

	/**
	 * Тестирование метода получения медианы по трем точкам
	 */
	@Test
	public void checkMedianOf3Method()
	{
		arrayQk.insert(9);
		arrayQk.insert(0);
		arrayQk.insert(1);
		arrayQk.insert(8);
		arrayQk.insert(3);
		arrayQk.insert(7);
		arrayQk.insert(6);
		arrayQk.insert(2);
		arrayQk.insert(5);
		arrayQk.insert(4);

		assertEquals("A = 9 0 1 8 3 7 6 2 5 4 ", arrayQk.display());

		assertEquals(4, arrayQk.medianOf3(0, arrayQkSize - 1));
		assertEquals("A = 3 0 1 8 5 7 6 2 4 9 ", arrayQk.display());
	}

	/**
	 * Тестирование метода быстрой сортировки  подмассива {@link ArrayQk} путем рекурсивного вызова
	 */
	@Test
	public void reqQuickSort()
	{
		int multiplier = 3;

		arrayQk = new ArrayQk(arrayQkSize * multiplier);

		arrayQk.insert(9);
		arrayQk.insert(0);
		arrayQk.insert(1);
		arrayQk.insert(8);
		arrayQk.insert(3);
		arrayQk.insert(7);
		arrayQk.insert(6);
		arrayQk.insert(2);
		arrayQk.insert(5);
		arrayQk.insert(4);
		arrayQk.insert(19);
		arrayQk.insert(10);
		arrayQk.insert(11);
		arrayQk.insert(18);
		arrayQk.insert(13);
		arrayQk.insert(17);
		arrayQk.insert(16);
		arrayQk.insert(12);
		arrayQk.insert(15);
		arrayQk.insert(14);
		arrayQk.insert(29);
		arrayQk.insert(20);
		arrayQk.insert(21);
		arrayQk.insert(28);
		arrayQk.insert(23);
		arrayQk.insert(27);
		arrayQk.insert(26);
		arrayQk.insert(22);
		arrayQk.insert(25);
		arrayQk.insert(24);

		assertEquals("A = 9 0 1 8 3 7 6 2 5 4 19 10 11 18 13 17 16 12 15 14 29 20 21 28 23 27 26 22 25 24 ", arrayQk.display());

		arrayQk.reqQuickSort(0, arrayQkSize - 1);
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 19 10 11 18 13 17 16 12 15 14 29 20 21 28 23 27 26 22 25 24 ", arrayQk.display());
	}

	/**
	 * Тестирование быстрой сортировки
	 */
	@Test
	public void checkQuickSortMethod()
	{
		int multiplier = 3;

		arrayQk = new ArrayQk(arrayQkSize * multiplier);

		arrayQk.insert(9);
		arrayQk.insert(0);
		arrayQk.insert(1);
		arrayQk.insert(8);
		arrayQk.insert(3);
		arrayQk.insert(7);
		arrayQk.insert(6);
		arrayQk.insert(2);
		arrayQk.insert(5);
		arrayQk.insert(4);
		arrayQk.insert(19);
		arrayQk.insert(10);
		arrayQk.insert(11);
		arrayQk.insert(18);
		arrayQk.insert(13);
		arrayQk.insert(17);
		arrayQk.insert(16);
		arrayQk.insert(12);
		arrayQk.insert(15);
		arrayQk.insert(14);
		arrayQk.insert(29);
		arrayQk.insert(20);
		arrayQk.insert(21);
		arrayQk.insert(28);
		arrayQk.insert(23);
		arrayQk.insert(27);
		arrayQk.insert(26);
		arrayQk.insert(22);
		arrayQk.insert(25);
		arrayQk.insert(24);

		assertEquals("A = 9 0 1 8 3 7 6 2 5 4 19 10 11 18 13 17 16 12 15 14 29 20 21 28 23 27 26 22 25 24 ", arrayQk.display());

		arrayQk.quickSort();
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 ", arrayQk.display());
	}
}
