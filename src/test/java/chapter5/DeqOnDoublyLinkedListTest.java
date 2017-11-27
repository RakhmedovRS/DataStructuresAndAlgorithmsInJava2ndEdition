package chapter5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link DeqOnDoublyLinkedList}
 *
 * @author rassoll
 * @created 05.11.2017
 * @$Author$
 * @$Revision$
 */
public class DeqOnDoublyLinkedListTest
{
	private DeqOnDoublyLinkedList dequeue;

	@Before
	public void init()
	{
		dequeue = new DeqOnDoublyLinkedList();
	}

	@Test
	public void testingVoidCheck()
	{
		assertTrue(dequeue.isEmpty());

		dequeue.insertLeft(1);
		dequeue.insertRight(3);
		dequeue.removeLeft();
		dequeue.removeRight();

		assertTrue(dequeue.isEmpty());
	}

	@Test
	public void testingFullnessCheck()
	{
		assertFalse(dequeue.isFull());
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
