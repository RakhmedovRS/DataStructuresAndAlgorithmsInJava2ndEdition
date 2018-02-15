package chapter11;

import base.items.LinkItem;
import base.structures.HashTable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.HASH_TABLE_SIZE;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Тестирование сущности {@link HashChain}
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestHashChain
{
	private static HashTable<LinkItem> hashChain;
	private static ArrayList<Link> additionalItems;

	{
		additionalItems = new ArrayList<>();
		IntStream.range(100_000, 100_010).forEach((value) -> additionalItems.add(new Link(value)));
	}

	@Before
	public void init()
	{
		hashChain = new HashChain(HASH_TABLE_SIZE);

		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach(key -> hashChain.insert(new Link(key)));
	}

	@Test
	public void testHashFunction()
	{
		assertEquals(1, hashChain.hashFunction(new Link(1)));
		assertEquals(1, hashChain.hashFunction(new Link(201)));
		assertEquals(150, hashChain.hashFunction(new Link(150)));
		assertEquals(0, hashChain.hashFunction(new Link(400)));
	}

	@Test
	public void testInsertMethod()
	{
		LinkItem insertedItem = new Link(101);

		hashChain.insert(insertedItem);

		assertEquals(insertedItem, hashChain.find(insertedItem));
	}

	@Test
	public void testDeleteMethod()
	{
		LinkItem itemForDeleting = new Link(1000);
		hashChain.insert(itemForDeleting);
		LinkItem deletedItem = hashChain.delete(itemForDeleting);

		assertNotNull(deletedItem);
		assertEquals(itemForDeleting, deletedItem);

		deletedItem = hashChain.delete(itemForDeleting);
		assertNull(deletedItem);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(hashChain);
	}

	@Test
	public void checkGettingElementsNumber()
	{
		TestHashTableBase.checkGettingElementsNumber(hashChain, HASH_TABLE_SIZE / 2, additionalItems);
	}

	@Test
	public void checkHashTableSize()
	{
		assertEquals(HASH_TABLE_SIZE, hashChain.getHashTableSize());
	}

	@Test
	public void testGettingLoadFactor()
	{
		assertEquals((HASH_TABLE_SIZE / 2) / (float) HASH_TABLE_SIZE, hashChain.getLoadFactor());
	}
}
