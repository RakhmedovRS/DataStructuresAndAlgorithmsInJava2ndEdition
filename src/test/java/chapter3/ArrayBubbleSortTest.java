package chapter3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author rassoll
 * @created 03.11.2017
 * @$Author$
 * @$Revision$
 */
public class ArrayBubbleSortTest
{
	private int arraySize = 10;
	private ArrayBubbleSort arrayBubbleSort;

	@Before
	public void init()
	{
		arrayBubbleSort = new ArrayBubbleSort(arraySize);
	}

	@Test
	public void insertionTesting()
	{
		arrayBubbleSort.insert(2);
		arrayBubbleSort.insert(1);
		arrayBubbleSort.insert(3);

		assertEquals("2, 1, 3", arrayBubbleSort.getValues());
	}

	@Test
	public void bubbleSortingTest()
	{
		arrayBubbleSort.insert(3);
		arrayBubbleSort.insert(1);
		arrayBubbleSort.insert(5);
		arrayBubbleSort.insert(6);
		arrayBubbleSort.insert(2);
		arrayBubbleSort.insert(8);
		arrayBubbleSort.insert(0);
		arrayBubbleSort.insert(9);
		arrayBubbleSort.insert(7);
		arrayBubbleSort.insert(4);

		arrayBubbleSort.sort();


		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", arrayBubbleSort.getValues());
	}

	@Test
	public void alternativeSortingTest()
	{
		arrayBubbleSort.insert(3);
		arrayBubbleSort.insert(1);
		arrayBubbleSort.insert(5);
		arrayBubbleSort.insert(6);
		arrayBubbleSort.insert(2);
		arrayBubbleSort.insert(8);
		arrayBubbleSort.insert(0);
		arrayBubbleSort.insert(9);
		arrayBubbleSort.insert(7);
		arrayBubbleSort.insert(4);

		arrayBubbleSort.alternativeSort();


		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", arrayBubbleSort.getValues());
	}

	@Test
	public void oddEvenSortingTest()
	{
		arrayBubbleSort.insert(3);
		arrayBubbleSort.insert(1);
		arrayBubbleSort.insert(5);
		arrayBubbleSort.insert(6);
		arrayBubbleSort.insert(2);
		arrayBubbleSort.insert(8);
		arrayBubbleSort.insert(0);
		arrayBubbleSort.insert(9);
		arrayBubbleSort.insert(7);
		arrayBubbleSort.insert(4);

		arrayBubbleSort.oddEvenSort();


		assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", arrayBubbleSort.getValues());
	}

}
