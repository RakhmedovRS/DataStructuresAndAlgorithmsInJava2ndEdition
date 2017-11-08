package chapter5;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * @author sbt-rakhmedov-rs
 * @created 08.11.2017
 * @$Author$
 * @$Revision$
 */
public class LinkListTest
{
	private LinkList linkList;

	@Before
	public void init()
	{
		linkList = new LinkList();
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(linkList.isEmpty());

		linkList.insertFirst(1, 1);

		assertFalse(linkList.isEmpty());
	}

	@Test
	public void checkGetFirstMethod()
	{
		linkList.insertFirst(1, 1);
		assertNotNull(linkList.getFirst());
	}

	@Test
	public void checkLinkSearch()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insertFirst(k, k));
		assertNotNull(linkList.find(5));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void checkDeleteFirstMethodOnEmptyLinkedList()
	{
		linkList.deleteFirst();
	}

	@Test
	public void checkDeleteFirstMethodOnFilledLinkedList()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insertFirst(k, k));

		Link link = linkList.getFirst();

		linkList.deleteFirst();

		assertNotEquals(link, linkList.getFirst());
	}

	@Test
	public void checkDeleteMethod()
	{
		IntStream.range(0, 10).forEach(k -> linkList.insertFirst(k, k));

		assertNotNull(linkList.find(5));

		linkList.delete(5);

		assertNull(linkList.find(5));
	}
}
