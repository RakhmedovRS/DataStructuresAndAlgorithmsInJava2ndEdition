package chapter5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author rassoll
 * @created 11.11.2017
 * @$Author$
 * @$Revision$
 */
public class DLinkListTest
{
	private DLinkList dLinkList;

	@Before
	public void init()
	{
		dLinkList = new DLinkList();
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(dLinkList.isEmpty());

		dLinkList.insertFirst(1L);

		assertFalse(dLinkList.isEmpty());
	}

	@Test
	public void checkGetFirstMethod()
	{
		dLinkList.insertFirst(1L);
		assertNotNull(dLinkList.getFirst());
	}

	@Test
	public void checkGetLastMethod()
	{
		dLinkList.insertLast(1L);
		assertNotNull(dLinkList.getLast());
	}

	@Test
	public void checkInsertFirstMethod()
	{
		dLinkList.insertFirst(1L);
		dLinkList.insertFirst(2L);
		dLinkList.insertFirst(3L);

		DoubleLink doubleLink = new DoubleLink(3L);

		assertTrue(doubleLink.getData() == dLinkList.getFirst().getData());
	}

	@Test
	public void checkInsertLastMethod()
	{
		dLinkList.insertLast(1);
		dLinkList.insertLast(2);
		dLinkList.insertLast(3);

		DoubleLink doubleLink = new DoubleLink(3L);

		assertTrue(doubleLink.getData() == dLinkList.getLast().getData());
	}

	@Test
	public void checkDeleteFirstMethod()
	{
		DoubleLink doubleLink = new DoubleLink(2L);
		DoubleLink doubleLink2 = new DoubleLink(3L);

		dLinkList.insertFirst(1L);
		dLinkList.insertFirst(2L);
		dLinkList.insertFirst(3L);

		assertTrue(doubleLink2.getData() == dLinkList.deleteFirst().getData());
		assertTrue(doubleLink.getData() ==dLinkList.getFirst().getData());
	}

	@Test
	public void checkDeleteLastMethod()
	{
		DoubleLink doubleLink = new DoubleLink(2L);
		DoubleLink doubleLink2 = new DoubleLink(3L);

		dLinkList.insertLast(1L);
		dLinkList.insertLast(2L);
		dLinkList.insertLast(3L);

		assertTrue(doubleLink2.getData() == dLinkList.deleteLast().getData());
		assertTrue(doubleLink.getData() == dLinkList.getLast().getData());
	}

	@Test
	public void checkInsertAfterMethod()
	{
		dLinkList.insertLast(1L);
		dLinkList.insertLast(2L);
		dLinkList.insertLast(3L);

		dLinkList.insertAfter(3, 10);

		assertTrue(dLinkList.getLast().getData() == 10);
	}

	@Test
	public void checkDeleteKeyMethod()
	{
		dLinkList.insertLast(1);
		dLinkList.insertLast(2);
		dLinkList.insertLast(3);

		dLinkList.deleteKey(2);
		assertNull(dLinkList.deleteKey(2));
		dLinkList.deleteKey(3);
		assertNull(dLinkList.deleteKey(3));
		dLinkList.deleteKey(1);
		assertNull(dLinkList.deleteKey(1));
	}
}
