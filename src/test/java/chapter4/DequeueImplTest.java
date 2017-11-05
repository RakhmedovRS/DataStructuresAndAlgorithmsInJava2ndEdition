package chapter4;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * @author rassoll
 * @created 05.11.2017
 * @$Author$
 * @$Revision$
 */
public class DequeueImplTest
{
	private DequeueImpl dequeue;
	private int collectionSize = 10;

	@Before
	public void init()
	{
		dequeue = new DequeueImpl(collectionSize);
	}

	@Test
	public void testingVoidCheck()
	{
		assertTrue(dequeue.isEmpty());

		dequeue.insertLeft(1);
		dequeue.insertRight(2);
		dequeue.removeLeft();
		dequeue.removeRight();

		assertTrue(dequeue.isEmpty());
	}

	@Test
	public void testingFullnessCheck()
	{
		IntStream.range(0, collectionSize).forEach(k -> dequeue.insertLeft(k));

		assertTrue(dequeue.isFull());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingVoidCheck2()
	{
		dequeue.removeLeft();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingVoidCheck3()
	{
		dequeue.removeRight();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingFullnessCheck2()
	{
		IntStream.range(0, collectionSize + 1).forEach(k -> dequeue.insertLeft(k));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testingFullnessCheck3()
	{
		IntStream.range(0, collectionSize + 1).forEach(k -> dequeue.insertRight(k));
	}

	@Test
	public void testPeekLeft()
	{
		dequeue.insertLeft(1);
		dequeue.insertLeft(2);

		assertEquals(2, dequeue.peekLeft());
		assertEquals(2, dequeue.peekLeft());
	}

	@Test
	public void testPeekRight()
	{
		dequeue.insertRight(1);
		dequeue.insertRight(2);

		assertEquals(2, dequeue.peekRight());
		assertEquals(2, dequeue.peekRight());
	}
}
