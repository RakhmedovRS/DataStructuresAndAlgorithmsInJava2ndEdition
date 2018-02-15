package chapter11;

import base.items.Item;
import base.structures.HashTable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.HASH_TABLE_SIZE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование сущности {@link LinearProbingHashTable}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestLinearProbingHashTable
{
	private static LinearProbingHashTable hashTable;
	private static LinearProbingHashTable secondHashTable;
	private static ArrayList<DataItem> additionalItems;

	{
		additionalItems = new ArrayList<>();
		IntStream.range(100_000, 100_010).forEach((value) -> additionalItems.add(new DataItem(value)));
	}

	@Before
	public void init()
	{
		hashTable = new LinearProbingHashTable(HASH_TABLE_SIZE);
		secondHashTable = new LinearProbingHashTable(HASH_TABLE_SIZE);

		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach((key) ->
		{
			Item item = new DataItem((int) (java.lang.Math.random() * HASH_TABLE_SIZE) + HASH_TABLE_SIZE);

			hashTable.insert(item);
			secondHashTable.insert(item);
		});
	}

	@Test
	public void testHashFunction()
	{
		TestHashTableBase.testHashFunction(hashTable);
	}

	@Test
	public void testInsertMethod()
	{
		TestHashTableBase.testInsertMethod(hashTable);
	}

	@Test
	public void testDeleteMethod()
	{
		TestHashTableBase.testDeleteMethod(hashTable);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(hashTable);
	}

	@Test
	public void checkGettingElementsNumber()
	{
		TestHashTableBase.checkGettingElementsNumber(hashTable, HASH_TABLE_SIZE / 2, additionalItems);
	}

	@Test
	public void checkHashTableSize()
	{
		TestHashTableBase.checkHashTableSize(hashTable, HASH_TABLE_SIZE, additionalItems);
	}

	@Test
	public void testGettingLoadFactor()
	{
		TestHashTableBase.testGettingLoadFactor(hashTable, (HASH_TABLE_SIZE / 2) / (float) HASH_TABLE_SIZE, additionalItems);
	}

	@Test
	public void testRehash()
	{
		hashTable.insert(new DataItem((int) (java.lang.Math.random() * HASH_TABLE_SIZE) + HASH_TABLE_SIZE));
		hashTable.insert(new DataItem((int) (java.lang.Math.random() * HASH_TABLE_SIZE) + HASH_TABLE_SIZE));
		hashTable.insert(new DataItem((int) (java.lang.Math.random() * HASH_TABLE_SIZE) + HASH_TABLE_SIZE));
		hashTable.insert(new DataItem((int) (java.lang.Math.random() * HASH_TABLE_SIZE) + HASH_TABLE_SIZE));

		Assert.assertEquals(HashTable.getPrime(HASH_TABLE_SIZE * 2), hashTable.getHashArray().length);

		Item[] hashArray = secondHashTable.getHashArray();
		for (int i = 0; i < hashArray.length; i++)
		{
			if (hashArray[i] != null)
			{
				Assert.assertEquals(hashArray[i].getKey(), hashTable.find(hashArray[i]).getKey());
				assertTrue(hashArray[i].getKey() != -1);
			}
		}
	}
}
