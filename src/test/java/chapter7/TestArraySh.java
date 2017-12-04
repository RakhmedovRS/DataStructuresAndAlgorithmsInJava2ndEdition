package chapter7;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование сущности {@link ArraySh}
 *
 * @author rassoll
 * @created 04.12.2017
 * @$Author$
 * @$Revision$
 */
public class TestArraySh
{
	private ArraySh arraySh;
	private int ArrayShSize = 10;

	@Before
	public void init()
	{
		arraySh = new ArraySh(ArrayShSize);
	}

	/**
	 * Тестирование работы метода вставки в массив
	 */
	@Test
	public void testInsertMethod()
	{
		IntStream.range(0, ArrayShSize).forEach(k -> arraySh.insert(k));
	}

	/**
	 * Тестирование ситуации с превышением возможного количества элементов
	 *
	 * @throws IndexOutOfBoundsException ожидаемая ошибка
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testInsertMethodError() throws IndexOutOfBoundsException
	{
		IntStream.range(0, ArrayShSize + 1).forEach(k -> arraySh.insert(k));
	}

	/**
	 * Тестирование метода вывода содержимого массива
	 */
	@Test
	public void testDisplayMethod()
	{
		IntStream.range(0, ArrayShSize).forEach(k -> arraySh.insert(k));
		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arraySh.display());
	}

	/**
	 * Тестирование корректности сортировки
	 */
	@Test
	public void checkShellSorting()
	{
		arraySh.insert(9);
		arraySh.insert(0);
		arraySh.insert(1);
		arraySh.insert(8);
		arraySh.insert(3);
		arraySh.insert(7);
		arraySh.insert(6);
		arraySh.insert(2);
		arraySh.insert(5);
		arraySh.insert(4);

		arraySh.shellSort(true);

		assertEquals("A = 0 1 2 3 4 5 6 7 8 9 ", arraySh.display());
	}
}
