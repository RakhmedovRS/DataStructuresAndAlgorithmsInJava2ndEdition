package chapter11;

import base.items.Item;
import base.structures.HashTable;

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
	private int elementsNumber;

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public QuadraticProbingHashTable(int size)
	{
		arraySize = HashTable.getPrime(size);
		hashArray = new DataItem[arraySize];
		deletedItem = new DataItem(-1);
		elementsNumber = 0;
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
		elementsNumber++;
	}
}
