package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.stream.IntStream;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Тестирование сущности {@link StringHashTable}
 *
 * @author rassoll
 * @created 14.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestStringHashTable
{
	private static StringHashTable stringHashTable;

	@Before
	public void init()
	{
		stringHashTable = new StringHashTable(TestHashTableBase.HASH_TABLE_SIZE);

		Random random = new Random();

		IntStream.range(0, TestHashTableBase.HASH_TABLE_SIZE / 2).forEach(key ->
		{
			char[] chars = new char[3];
			chars[0] = (char) ('a' + random.nextInt(('z' - 'a') + 1));
			chars[1] = (char) ('a' + random.nextInt(('z' - 'a') + 1));
			chars[2] = (char) ('a' + random.nextInt(('z' - 'a') + 1));
			stringHashTable.insert(String.valueOf(chars));
		});
	}

	/**
	 * Тестирование метода проверяющего соотвествие символов в передаваемом значении шаблону [a-z]
	 */
	@Test
	public void testLetterMatcher()
	{
		assertTrue(StringHashTable.isMatchLettersInString("asdasd"));
		assertTrue(StringHashTable.isMatchLettersInString("asasdadasd"));
		assertTrue(StringHashTable.isMatchLettersInString("as"));
		assertTrue(StringHashTable.isMatchLettersInString("a"));

		assertFalse(StringHashTable.isMatchLettersInString("-"));
		assertFalse(StringHashTable.isMatchLettersInString("_dfgdfgdfg"));
		assertFalse(StringHashTable.isMatchLettersInString("asda1asd"));
	}

	@Test
	public void testHashFunction()
	{
		assertEquals(0, stringHashTable.hashFunction("a"));
		assertEquals(25, stringHashTable.hashFunction("z"));
		assertEquals(97, stringHashTable.hashFunction("cats"));
		assertEquals(112, stringHashTable.hashFunction("synchrophasotron"));
	}

	@Test
	public void testInsertMethod()
	{
		stringHashTable.insert("mother");

		assertEquals("mother", stringHashTable.find("mother"));

		stringHashTable.insert("father");

		assertEquals("father", stringHashTable.find("father"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertMethod2()
	{
		stringHashTable.insert("_mother");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertMethod3()
	{
		stringHashTable.insert("mot1her");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInsertMethod4()
	{
		stringHashTable.insert("mother ");
	}

	@Test
	public void testDeleteMethod()
	{
		stringHashTable.insert("mother");

		String deletedItem = stringHashTable.delete("mother");

		assertNotNull(deletedItem);
		assertEquals("mother", deletedItem);

		deletedItem = stringHashTable.delete("mother");
		assertNull(deletedItem);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteMethod2()
	{
		stringHashTable.delete("_mother");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteMethod3()
	{
		stringHashTable.delete("mot1her");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteMethod4()
	{
		stringHashTable.delete("mother ");
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(stringHashTable);
	}
}
