package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static junit.framework.TestCase.*;

/**
 * Тестирование сущности {@link HashTable}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestHashTable
{
	private static HashTable hashTable;

	@Before
	public void init()
	{
		int HASH_TABLE_SIZE = 200;
		hashTable = new HashTable(HASH_TABLE_SIZE);

		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach(key -> hashTable.insert(new DataItem(key)));
	}

	/**
	 * Тестирование функции вычисляющей хэш-значение
	 */
	@Test
	public void testHashFunction()
	{
		assertTrue(1 == hashTable.hashFunction(1));
		assertTrue(1 == hashTable.hashFunction(201));
		assertTrue(150 == hashTable.hashFunction(150));
		assertTrue(0 == hashTable.hashFunction(400));
	}

	/**
	 * Тестирование метода вставки в хэш-таблицу элементов данных
	 */
	@Test
	public void testInsertMethod()
	{
		DataItem insertedItem = new DataItem(101);

		hashTable.insert(insertedItem);

		assertEquals(insertedItem, hashTable.find(insertedItem.getKey()));
	}

	/**
	 * Тестирование метода удаления элементов данных из хэш-таблицы
	 */
	@Test
	public void testDeleteMethod()
	{
		DataItem deletedItem = hashTable.delete(10);

		assertNotNull(deletedItem);
		assertTrue(deletedItem.getKey() == 10);

		deletedItem = hashTable.delete(10);
		assertNull(deletedItem);
	}

	/**
	 * Тестирование метода получения данных для печати хэш-таблицы
	 */
	@Test
	public void testGetDisplayDataMethod()
	{
		assertNotNull(hashTable.getDisplayData());
	}
}
