package chapter11;

import base.items.Item;
import base.structures.HashTable;

import static junit.framework.TestCase.*;

/**
 * Базовый класс для тестирования хэш-таблиц реализующих интерфейс {@link HashTable}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
@SuppressWarnings("unchecked")
abstract class TestHashTableBase
{
	static final int HASH_TABLE_SIZE = 200;
	static final Item ITEM_1 = new DataItem(1);
	static final Item ITEM_2 = new DataItem(201);
	static final Item ITEM_3 = new DataItem(150);
	static final Item ITEM_4 = new DataItem(400);

	/**
	 * Тестирование функции вычисляющей хэш-значение
	 */
	static void testHashFunction(HashTable hashTable)
	{
		assertEquals(1, hashTable.hashFunction(ITEM_1));
		assertEquals(1, hashTable.hashFunction(ITEM_2));
		assertEquals(150, hashTable.hashFunction(ITEM_3));
		assertEquals(0, hashTable.hashFunction(ITEM_4));
	}

	/**
	 * Тестирование метода вставки в хэш-таблицу элементов данных
	 */
	public static void testInsertMethod(HashTable hashTable)
	{
		final Item ITEM_5 = new DataItem(101);

		hashTable.insert(ITEM_5);

		assertEquals(ITEM_5, hashTable.find(ITEM_5));
	}

	/**
	 * Тестирование метода удаления элементов данных из хэш-таблицы
	 */
	static void testDeleteMethod(HashTable<Item> hashTable)
	{
		Item itemForDeleting = new DataItem(1000);
		hashTable.insert(itemForDeleting);

		Item deletedItem = hashTable.delete(itemForDeleting);

		assertNotNull(deletedItem);
		assertEquals(itemForDeleting, deletedItem);

		deletedItem = hashTable.delete(itemForDeleting);
		assertNull(deletedItem);
	}

	/**
	 * Тестирование метода получения данных для печати хэш-таблицы
	 */
	static void testGetDisplayDataMethod(HashTable hashTable)
	{
		assertNotNull(hashTable.getDisplayData());
	}
}
