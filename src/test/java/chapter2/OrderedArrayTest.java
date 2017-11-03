package chapter2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author rassoll
 * @created 29.10.2017
 * @$Author$
 * @$Revision$
 */
public class OrderedArrayTest
{
	int arraySize = 10;
	OrderedArray orderedArray;

	@Before
	public void init()
	{
		orderedArray = new OrderedArray(arraySize);

		for (int i = 0; i < arraySize; i++)
		{
			orderedArray.insert(i);
		}
	}

	@Test
	public void checkSize()
	{
		assertEquals(arraySize, orderedArray.getSize());
	}

	@Test
	public void checkSearch()
	{
		assertEquals(1, orderedArray.find(1));
		assertEquals(orderedArray.getSize(), orderedArray.find(100));
	}

	@Test
	public void checkDelete()
	{
		assertTrue(orderedArray.delete(1));
		assertEquals(orderedArray.getSize(), orderedArray.find(1));
	}

	@Test
	public void insertionCheck()
	{
		orderedArray.delete(9);
		orderedArray.insert(11);

		assertEquals(9, orderedArray.find(11));
	}

	@Test
	public void checkArraysMerge()
	{
		OrderedArray orderedArray = new OrderedArray(10);
		orderedArray.insert(1);
		orderedArray.insert(3);
		orderedArray.insert(9);
		orderedArray.insert(7);
		orderedArray.insert(5);

		long[] tempArray = new long[5];
		tempArray[0] = 4;
		tempArray[1] = 6;
		tempArray[2] = 0;
		tempArray[3] = 2;
		tempArray[4] = 8;

		orderedArray.merge(tempArray);

		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", orderedArray.getValues());

	}
}
