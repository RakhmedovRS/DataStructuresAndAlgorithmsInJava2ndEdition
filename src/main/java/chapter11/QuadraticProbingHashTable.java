package chapter11;

import base.items.Item;

/**
 * Программный проект 11.1 - Program project 11.1
 * Сущность хэш-таблицы с квадратичным пробированием пробированием
 *
 * @author rassoll
 * @created 14.02.2018
 * @$Author$
 * @$Revision$
 */
public class QuadraticProbingHashTable extends LinearProbingHashTable
{
	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public QuadraticProbingHashTable(int size)
	{
		arraySize = getPrime(size);
		hashArray = new DataItem[arraySize];
		deletedItem = new DataItem(-1);
	}

	/**
	 * Получить первое простое число большее чeм {@param min}
	 *
	 * @param min минимальное значение
	 * @return найденное простое число
	 */
	public static int getPrime(int min)
	{
		for (int i = min + 1; true; i++)
		{
			if (isPrime(i))
			{
				return i;
			}
		}
	}

	/**
	 * Проверка числа того, что число {@param value} простое
	 *
	 * @param value проверяемое значение
	 * @return признак простого числа
	 */
	public static boolean isPrime(int value)
	{
		for (int i = 2; (i * i <= value); i++)
		{
			if (value % 2 == 0)
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Вставить элемент данных в хэш-таблицу используюя квардратичное пробирование
	 * предполагается что хэш-таблица не заполненна
	 *
	 * @param item элемент данных
	 */
	@Override
	public void insert(Item item)
	{
		int hashValue = hashFunction(item);

		int i = 1;
		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1)
		{
			hashValue += i * i;
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
	}
}
