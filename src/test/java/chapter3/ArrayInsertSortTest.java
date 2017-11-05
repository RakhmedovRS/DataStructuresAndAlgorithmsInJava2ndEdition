package chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author rassoll
 * @created 05.11.2017
 * @$Author$
 * @$Revision$
 */
public class ArrayInsertSortTest
{
	private int arraySize = 10;
	private ArrayInsertSort arrayInsertSort;

	@Before
	public void init()
	{
		arrayInsertSort = new ArrayInsertSort(arraySize);
	}

	@Test
	public void insertionTesting()
	{
		arrayInsertSort.insert(2);
		arrayInsertSort.insert(1);
		arrayInsertSort.insert(3);

		assertEquals("2, 1, 3", arrayInsertSort.getValues());
	}

	@Test
	public void testSortByInsertMethod()
	{
		arrayInsertSort.insert(3);
		arrayInsertSort.insert(1);
		arrayInsertSort.insert(5);
		arrayInsertSort.insert(6);
		arrayInsertSort.insert(2);
		arrayInsertSort.insert(0);
		arrayInsertSort.insert(8);
		arrayInsertSort.insert(9);
		arrayInsertSort.insert(7);
		arrayInsertSort.insert(4);

		arrayInsertSort.sort();

		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", arrayInsertSort.getValues());
	}

	@Test
	public void medianFinderTest()
	{
		arrayInsertSort.insert(3);
		arrayInsertSort.insert(1);
		arrayInsertSort.insert(5);
		arrayInsertSort.insert(6);
		arrayInsertSort.insert(2);
		arrayInsertSort.insert(8);
		arrayInsertSort.insert(0);
		arrayInsertSort.insert(9);
		arrayInsertSort.insert(7);
		arrayInsertSort.insert(4);

		assertEquals(4L, arrayInsertSort.median());
	}

	@Test
	public void checkForAbsencesOfDuplicates()
	{
		arrayInsertSort.insert(3);
		arrayInsertSort.insert(1);
		arrayInsertSort.insert(5);
		arrayInsertSort.insert(6);
		arrayInsertSort.insert(2);
		arrayInsertSort.insert(3);
		arrayInsertSort.insert(0);
		arrayInsertSort.insert(9);
		arrayInsertSort.insert(7);
		arrayInsertSort.insert(3);

		arrayInsertSort.noDups();

		assertEquals("0, 1, 2, 3, 5, 6, 7, 9", arrayInsertSort.getValues());
	}
}
