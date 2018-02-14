package chapter5;

import base.items.LinkItem;
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
	private LinkItem item;

	@Before
	public void init()
	{
		sortedLinkList = new SortedLinkedList();
		item = new Link(5, 5);
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(sortedLinkList.isEmpty());

		sortedLinkList.insert(1, 1);

		assertFalse(sortedLinkList.isEmpty());
	}

	@Test
	public void checkGetFirstMethod()
	{
		sortedLinkList.insert(1, 1);
		assertNotNull(sortedLinkList.getFirst());
	}

	@Test
	public void checkInsertFirstMethod()
	{
		IntStream.range(1, 10).forEach(k -> sortedLinkList.insert(k, k));

		assertTrue(1 == sortedLinkList.getFirst().getData());

		sortedLinkList.insert(0, 0);

		assertTrue(0 == sortedLinkList.getFirst().getData());
	}

	@Test
	public void checkLinkSearch()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insert(k, k));
		assertNotNull(sortedLinkList.find(new Link(5, 5)));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void checkDeleteFirstMethodOnEmptyLinkedList()
	{
		sortedLinkList.deleteFirst();
	}

	@Test
	public void checkDeleteFirstMethodOnFilledLinkedList()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insert(k, k));

		LinkItem link = sortedLinkList.getFirst();

		sortedLinkList.deleteFirst();

		assertNotEquals(link, sortedLinkList.getFirst());
	}

	@Test
	public void checkDeleteMethod()
	{
		IntStream.range(0, 10).forEach(k -> sortedLinkList.insert(k, k));

		assertNotNull(sortedLinkList.find(item));

		sortedLinkList.delete(item);

		assertNull(sortedLinkList.find(item));
	}

	@Test
	public void checkSorting()
	{
		sortedLinkList = new SortedLinkedList();

		IntStream.range(0, 100).forEach((key) -> sortedLinkList.insert(key, java.lang.Math.random() * 100));

		LinkItem current = sortedLinkList.getFirst();
		LinkItem next = current.getNext();

		while (next != null)
		{
			assertTrue(current.getData() < next.getData());

			current = next;
			next = current.getNext();
		}
	}
}
