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
public class StackBasedOnDequeueTest
{
	private StackBasedOnDequeue stackBasedOnDequeue;
	private int collectionSize = 10;

	@Before
	public void init()
	{
		stackBasedOnDequeue = new StackBasedOnDequeue(collectionSize);
	}

	@Test
	public void insertionTest()
	{
		IntStream.range(0, collectionSize).forEach(k -> stackBasedOnDequeue.insert(k));

		assertEquals(9, stackBasedOnDequeue.remove());
		assertEquals(8, stackBasedOnDequeue.remove());
	}

	@Test
	public void testPeek()
	{
		IntStream.range(0, collectionSize).forEach(k -> stackBasedOnDequeue.insert(k));

		assertEquals(9, stackBasedOnDequeue.peekFront());
		assertEquals(9, stackBasedOnDequeue.peekFront());
	}

	@Test
	public void testingVoidCheck()
	{
		assertTrue(stackBasedOnDequeue.isEmpty());

		stackBasedOnDequeue.insert(3);
		stackBasedOnDequeue.insert(1);
		stackBasedOnDequeue.remove();
		stackBasedOnDequeue.remove();

		assertTrue(stackBasedOnDequeue.isEmpty());
	}

	@Test
	public void testingFullnessCheck()
	{
		IntStream.range(0, collectionSize).forEach(k -> stackBasedOnDequeue.insert(k));

		assertTrue(stackBasedOnDequeue.isFull());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingVoidCheck2()
	{
		stackBasedOnDequeue.remove();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingFullnessCheck2()
	{
		IntStream.range(0, collectionSize + 1).forEach(k -> stackBasedOnDequeue.insert(k));
	}
}
