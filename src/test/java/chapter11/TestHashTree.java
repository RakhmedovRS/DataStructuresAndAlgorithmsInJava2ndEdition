package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.HASH_TABLE_SIZE;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Тестирование сущности {@link HashTree}
 *
 * @author rassoll
 * @created 16.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestHashTree
{
	private static HashTree hashTree;
	private static ArrayList<Integer> additionalItems;

	{
		additionalItems = new ArrayList<>();
		IntStream.range(100_000, 100_010).forEach((value) -> additionalItems.add(value));
	}

	@Before
	public void init()
	{
		hashTree = new HashTree(HASH_TABLE_SIZE);

		Random random = new Random();
		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach(key ->
		{
			key += random.nextInt(key + 1) * 10;
			hashTree.insert(key);
		}
		);

		System.out.println(hashTree.getDisplayData());
	}

	@Test
	public void testHashFunction()
	{
		assertEquals(1, hashTree.hashFunction(1));
		assertEquals(1, hashTree.hashFunction(201));
		assertEquals(150, hashTree.hashFunction(150));
		assertEquals(0, hashTree.hashFunction(400));
	}

	@Test
	public void testInsertMethod()
	{
		Integer insertedItem = 101;

		hashTree.insert(insertedItem);

		assertEquals(insertedItem, hashTree.find(insertedItem));
	}

	@Test
	public void testDeleteMethod()
	{
		Integer itemForDeleting = 1000;
		hashTree.insert(itemForDeleting);
		Integer deletedItem = hashTree.delete(itemForDeleting);

		assertNotNull(deletedItem);
		assertEquals(itemForDeleting, deletedItem);

		deletedItem = hashTree.delete(itemForDeleting);
		assertNull(deletedItem);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(hashTree);
	}

	@Test
	public void checkGettingElementsNumber()
	{
		TestHashTableBase.checkGettingElementsNumber(hashTree, HASH_TABLE_SIZE / 2, additionalItems);
	}

	@Test
	public void checkHashTableSize()
	{
		assertEquals(HASH_TABLE_SIZE, hashTree.getHashTableSize());
	}

	@Test
	public void testGettingLoadFactor()
	{
		assertEquals((HASH_TABLE_SIZE / 2) / (float) HASH_TABLE_SIZE, hashTree.getLoadFactor());
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testGettingHashArray()
	{
		hashTree.getHashArray();
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testRehash()
	{
		hashTree.rehash();
	}
}
