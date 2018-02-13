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

		cyclicList.insert(1, 1);

		assertFalse(cyclicList.isEmpty());
	}

	@Test
	public void checkInsertFirstMethod()
	{
		cyclicList.insert(1, 1);
		cyclicList.insert(2, 2);
		cyclicList.insert(3, 3);

		Link link = new Link(1, 1);

		assertTrue(link.getData() == cyclicList.getFirst().getData());
	}

	@Test
	public void checkDeleteFirstMethod()
	{
		Link link = new Link(1, 1);
		Link link2 = new Link(3, 3);

		cyclicList.insert(1, 1);
		cyclicList.insert(2, 2);
		cyclicList.insert(3, 3);

		assertTrue(link2.getData() == cyclicList.deleteFirst().getData());
		assertTrue(link.getData() == cyclicList.getFirst().getData());
	}

	@Test
	public void checkFindMethod()
	{
		Link link = new Link(1, 1);
		Link link2 = new Link(3, 3);

		cyclicList.insert(1, 1);
		cyclicList.insert(2, 2);
		cyclicList.insert(3, 3);

		assertTrue(link.getData() == cyclicList.find(link.getKey()).getData());
		assertTrue(link2.getData() == cyclicList.find(link2.getKey()).getData());
	}

	@Test
	public void checkDeleteMethod()
	{
		Link link = new Link(1, 1);

		cyclicList.insert(1, 1);
		cyclicList.insert(2, 2);
		cyclicList.insert(3, 3);

		assertTrue(link.getData() == cyclicList.find(link.getKey()).getData());
		cyclicList.delete(link.getKey());
		assertNull(cyclicList.find(link.getKey()));
	}
}
