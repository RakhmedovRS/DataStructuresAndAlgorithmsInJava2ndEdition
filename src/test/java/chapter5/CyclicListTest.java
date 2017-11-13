package chapter5;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link CyclicList}
 *
 * @author rassoll
 * @created 13.11.2017
 * @$Author$
 * @$Revision$
 */
public class CyclicListTest
{
	private CyclicList cyclicList;

	@Before
	public void init()
	{
		cyclicList = new CyclicList();
	}

	@Test
	public void checkIsEmptyMethod()
	{
		assertTrue(cyclicList.isEmpty());

		cyclicList.insertFirst(1, 1);

		assertFalse(cyclicList.isEmpty());
	}

	@Test
	public void checkInsertFirstMethod()
	{
		cyclicList.insertFirst(1, 1);
		cyclicList.insertFirst(2, 2);
		cyclicList.insertFirst(3, 3);

		Link link = new Link(1, 1);

		assertTrue(link.dData == cyclicList.getFirst().dData);
	}

	@Test
	public void checkDeleteFirstMethod()
	{
		Link link = new Link(1, 1);
		Link link2 = new Link(3, 3);

		cyclicList.insertFirst(1, 1);
		cyclicList.insertFirst(2, 2);
		cyclicList.insertFirst(3, 3);

		assertTrue(link2.dData == cyclicList.deleteFirst().dData);
		assertTrue(link.dData == cyclicList.getFirst().dData);
	}

	@Test
	public void checkFindMethod()
	{
		Link link = new Link(1, 1);
		Link link2 = new Link(3, 3);

		cyclicList.insertFirst(1, 1);
		cyclicList.insertFirst(2, 2);
		cyclicList.insertFirst(3, 3);

		assertTrue(link.dData == cyclicList.find(link.iData).dData);
		assertTrue(link2.dData == cyclicList.find(link2.iData).dData);
	}

	@Test
	public void checkDeleteMethod()
	{
		Link link = new Link(1, 1);

		cyclicList.insertFirst(1, 1);
		cyclicList.insertFirst(2, 2);
		cyclicList.insertFirst(3, 3);

		assertTrue(link.dData == cyclicList.find(link.iData).dData);
		cyclicList.delete(link.iData);
		assertNull(cyclicList.find(link.iData));
	}
}
