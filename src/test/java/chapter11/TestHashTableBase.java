package chapter11;

import base.HashTable;
import base.Item;

import static junit.framework.TestCase.*;

/**
 * Базовый класс для тестирования хэш-таблиц реализующих интерфейс {@link base.HashTable}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
abstract public class TestHashTableBase
{
	static final int HASH_TABLE_SIZE = 200;

	/**
	 * Тестирование функции вычисляющей хэш-значение
	 */
	public static void testHashFunction(HashTable hashTable)
	{
		assertEquals(1, hashTable.hashFunction(1));
		assertEquals(1, hashTable.hashFunction(201));
		assertEquals(150, hashTable.hashFunction(150));
		assertEquals(0, hashTable.hashFunction(400));
	}

	/**
	 * Тестирование метода вставки в хэш-таблицу элементов данных
	 */
	public static void testInsertMethod(HashTable hashTable)
	{
		DataItem insertedItem = new DataItem(101);

		hashTable.insert(insertedItem);

		assertEquals(insertedItem, hashTable.find(insertedItem.getKey()));
	}

	/**
	 * Тестирование метода удаления элементов данных из хэш-таблицы
	 */
	public static void testDeleteMethod(HashTable hashTable)
	{
		Item deletedItem = hashTable.delete(10);

		assertNotNull(deletedItem);
		assertTrue((int)deletedItem.getKey() == 10);

		deletedItem = hashTable.delete(10);
		assertNull(deletedItem);
	}

	/**
	 * Тестирование метода получения данных для печати хэш-таблицы
	 */
	public static void testGetDisplayDataMethod(HashTable hashTable)
	{
		assertNotNull(hashTable.getDisplayData());
	}
}
