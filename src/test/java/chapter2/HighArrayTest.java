package chapter2;

import chapter2.highArray.HighArray;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author rassoll
 * @created 29.10.2017
 * @$Author$
 * @$Revision$
 */
public class HighArrayTest
{
	int arraySize = 10;
	HighArray highArray;

	@Before
	public void init()
	{
		highArray = new HighArray(arraySize);

		for (int i = 0; i < arraySize; i++)
		{
			highArray.insert(i);
		}
	}

	@Test
	public void checkSize()
	{
		assertEquals(arraySize, highArray.getSize());
	}

	@Test
	public void checkSearch()
	{
		for (int i = arraySize - 1; i >= 0; i--)
		{
			assertTrue(highArray.find(i));
		}
	}

	@Test
	public void checkDelete()
	{
		highArray.delete(1);

		assertFalse(highArray.find(1));
	}

	@Test
	public void insertionCheck()
	{
		highArray.delete(9);
		highArray.insert(11);

		assertTrue(highArray.find(11));
	}

	@Test
	public void checkMaxValueFinder()
	{
		assertEquals(9, highArray.getMax());
	}

	@Test
	public void checkMaxValueDelete()
	{
		long maxValue = highArray.getMax();

		highArray.delete(maxValue);

		assertNotEquals(maxValue, highArray.getMax());
	}

	@Test
	public void checkingAbsencesOfDuplicates()
	{
		HighArray tempArray = new HighArray(9);
		tempArray.insert(1);
		tempArray.insert(2);
		tempArray.insert(3);
		tempArray.insert(3);
		tempArray.insert(2);
		tempArray.insert(1);
		tempArray.insert(1);
		tempArray.insert(2);
		tempArray.insert(3);

		tempArray.noDups();

		assertEquals(3, tempArray.getSize());
	}

	@Test
	public void sortingCheck()
	{
		HighArray tempArray = new HighArray(10);
		tempArray.insert(3);
		tempArray.insert(1);
		tempArray.insert(5);
		tempArray.insert(6);
		tempArray.insert(2);
		tempArray.insert(8);
		tempArray.insert(0);
		tempArray.insert(9);
		tempArray.insert(7);
		tempArray.insert(4);

		tempArray.sort();

		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", tempArray.getValues());
	}
}
