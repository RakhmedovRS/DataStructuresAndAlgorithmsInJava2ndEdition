package chapter11;

import base.HashTable;
import base.LinkItem;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static chapter11.TestHashTableBase.HASH_TABLE_SIZE;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

	@Before
	public void init()
	{
		hashChain = new HashChain(HASH_TABLE_SIZE);

		IntStream.range(0, HASH_TABLE_SIZE / 2).forEach(key -> hashChain.insert(new chapter11.Link(key)));
	}

	@Test
	public void testHashFunction()
	{
		TestHashTableBase.testHashFunction(hashChain);
	}


	@Test
	public void testInsertMethod()
	{
		LinkItem insertedItem = new Link(101);

		hashChain.insert(insertedItem);

		assertEquals(insertedItem, hashChain.find(insertedItem.getKey()));
	}

	@Test
	public void testDeleteMethod()
	{
		LinkItem deletedItem = hashChain.delete(10);

		assertNotNull(deletedItem);
		assertTrue(deletedItem.getKey() == 10);

		deletedItem = hashChain.delete(10);
		assertNull(deletedItem);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(hashChain);
	}
}
