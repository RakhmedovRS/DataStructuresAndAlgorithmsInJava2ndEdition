package chapter4;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author rassoll
 * @created 05.11.2017
 * @$Author$
 * @$Revision$
 */
public class PriorityQueueTest
{
	private PriorityQueue priorityQueue;
	private int collectionSize = 10;

	@Before
	public void init()
	{
		priorityQueue = new PriorityQueue(collectionSize);
	}

	@Test
	public void testingVoidCheck()
	{
		assertTrue(priorityQueue.isEmpty());

		priorityQueue.insert(1);
		priorityQueue.insert(2);
		priorityQueue.remove();
		priorityQueue.remove();

		assertTrue(priorityQueue.isEmpty());
	}

	@Test
	public void testingFullnessCheck()
	{
		IntStream.range(0, collectionSize).forEach(k -> priorityQueue.insert(k));

		assertTrue(priorityQueue.isFull());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingVoidCheck2()
	{
		priorityQueue.remove();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingFullnessCheck2()
	{
		IntStream.range(0, collectionSize + 1).forEach(k -> priorityQueue.insert(k));
	}

	@Test
	public void checkPriority()
	{
		priorityQueue.insert(100);
		priorityQueue.insert(20);
		priorityQueue.insert(30);
		priorityQueue.insert(11);
		priorityQueue.insert(2);

		assertEquals(2, priorityQueue.remove());
		assertEquals(11, priorityQueue.remove());
		assertEquals(20, priorityQueue.remove());
		assertEquals(30, priorityQueue.remove());
		assertEquals(100, priorityQueue.remove());
	}

	@Test
	public void checkPriorityPeek()
	{
		priorityQueue.insert(50);
		priorityQueue.insert(20);
		priorityQueue.insert(1);
		priorityQueue.insert(30);
		priorityQueue.insert(11);

		assertEquals(1, priorityQueue.peekFront());
	}
}
