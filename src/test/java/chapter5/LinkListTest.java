package chapter5;

import base.items.LinkItem;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author rassoll
 * @created 08.11.2017
 * @$Author$
 * @$Revision$
 */
public class LinkListTest
{
	private LinkList linkList;
	private LinkItem item;

	@Before
	public void init()
	{
		linkList = new LinkList();
		item = new Link(5, 5);
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(linkList.isEmpty());

		linkList.insert(1, 1);

		assertFalse(linkList.isEmpty());
	}

	@Test
	public void checkGetFirstMethod()
	{
		linkList.insert(1, 1);
		assertNotNull(linkList.getFirst());
	}

	@Test
	public void checkLinkSearch()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insert(k, k));

		assertNotNull(linkList.find(item));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void checkDeleteFirstMethodOnEmptyLinkedList()
	{
		linkList.deleteFirst();
	}

	@Test
	public void checkDeleteFirstMethodOnFilledLinkedList()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insert(k, k));

		LinkItem link = linkList.getFirst();

		linkList.deleteFirst();

		assertNotEquals(link, linkList.getFirst());
	}

	@Test
	public void checkDeleteMethod()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insert(k, k));

		assertNotNull(linkList.find(item));

		linkList.delete(item);

		assertNull(linkList.find(item));
	}
}
