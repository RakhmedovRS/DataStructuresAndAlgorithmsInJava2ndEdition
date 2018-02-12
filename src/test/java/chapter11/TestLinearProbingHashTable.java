package chapter11;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

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
	private static LinearProbingHashTable linearProbingHashTable;

	@Before
	public void init()
	{
		linearProbingHashTable = new LinearProbingHashTable(TestHashTableBase.HASH_TABLE_SIZE);

		IntStream.range(0, TestHashTableBase.HASH_TABLE_SIZE / 2).forEach(key -> linearProbingHashTable.insert(new DataItem(key)));
	}

	@Test
	public void testHashFunction()
	{
		TestHashTableBase.testHashFunction(linearProbingHashTable);
	}

	@Test
	public void testInsertMethod()
	{
		TestHashTableBase.testInsertMethod(linearProbingHashTable);
	}

	@Test
	public void testDeleteMethod()
	{
		TestHashTableBase.testDeleteMethod(linearProbingHashTable);
	}

	@Test
	public void testGetDisplayDataMethod()
	{
		TestHashTableBase.testGetDisplayDataMethod(linearProbingHashTable);
	}
}
