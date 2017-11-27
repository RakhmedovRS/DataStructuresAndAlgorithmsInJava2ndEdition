package chapter5;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link SortedLinkedList}
 *
 * @author rassoll
 * @created 08.11.2017
 * @$Author$
 * @$Revision$
 */
public class SortedLinkListTest
{
	private SortedLinkedList sortedLinkList;

	@Before
	public void init()
	{
		sortedLinkList = new SortedLinkedList();
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(sortedLinkList.isEmpty());

		sortedLinkList.insertFirst(1, 1);

		assertFalse(sortedLinkList.isEmpty());
	}

	@Test
	public void checkGetFirstMethod()
	{
		sortedLinkList.insertFirst(1, 1);
		assertNotNull(sortedLinkList.getFirst());
	}

	@Test
	public void checkInsertFirstMethod()
	{
		IntStream.range(1, 10).forEach(k -> sortedLinkList.insertFirst(k, k));

		assertTrue(1 == sortedLinkList.getFirst().dData);

		sortedLinkList.insertFirst(0, 0);

		assertTrue(0 == sortedLinkList.getFirst().dData);
	}

	@Test
	public void checkLinkSearch()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insertFirst(k, k));
		assertNotNull(sortedLinkList.find(5));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void checkDeleteFirstMethodOnEmptyLinkedList()
	{
		sortedLinkList.deleteFirst();
	}

	@Test
	public void checkDeleteFirstMethodOnFilledLinkedList()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insertFirst(k, k));

		Link link = sortedLinkList.getFirst();

		sortedLinkList.deleteFirst();

		assertNotEquals(link, sortedLinkList.getFirst());
	}

	@Test
	public void checkDeleteMethod()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insertFirst(k, k));

		assertNotNull(sortedLinkList.find(5));

		sortedLinkList.delete(5);

		assertNull(sortedLinkList.find(5));
	}
}
