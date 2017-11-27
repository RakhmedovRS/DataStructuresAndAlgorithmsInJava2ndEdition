package chapter5;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link StackOnCyclicList}
 *
 * @author rassoll
 * @created 14.11.2017
 * @$Author$
 * @$Revision$
 */
public class StackOnCyclicListTest
{
	private StackOnCyclicList stackOnCyclicList;
	private int collectionSize = 10;

	@Before
	public void init()
	{
		stackOnCyclicList = new StackOnCyclicList();
	}

	@Test
	public void insertionTest()
	{
		IntStream.range(0, collectionSize).forEach(k -> stackOnCyclicList.insert(k));

		assertEquals(9, stackOnCyclicList.remove());
		assertEquals(8, stackOnCyclicList.remove());
	}

	@Test
	public void testPeek()
	{
		IntStream.range(0, collectionSize).forEach(k -> stackOnCyclicList.insert(k));

		assertEquals(0, stackOnCyclicList.peekFront());
		assertEquals(0, stackOnCyclicList.peekFront());
	}

	@Test
	public void testingVoidCheck()
	{
		assertTrue(stackOnCyclicList.isEmpty());

		stackOnCyclicList.insert(3);
		stackOnCyclicList.insert(1);
		stackOnCyclicList.remove();
		stackOnCyclicList.remove();

		assertTrue(stackOnCyclicList.isEmpty());
	}

	@Test
	public void testingFullnessCheck()
	{
		assertFalse(stackOnCyclicList.isFull());
	}
}
