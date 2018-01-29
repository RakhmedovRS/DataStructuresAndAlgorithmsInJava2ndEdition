package chapter10;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование сущности {@link Tree234}
 *
 * @author rassoll
 * @created 29.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestTree234
{
	private static Tree234 tree234;
	private static DataItem minDataItem;

	@Before
	public void init()
	{
		tree234 = new Tree234();
		minDataItem = new DataItem(30);
		tree234.insert(50);
		tree234.insert(40);
		tree234.insert(60);
		tree234.insert(minDataItem);
		tree234.insert(70);
	}

	/**
	 * Тестирование метода поиска позиции элемента
	 */
	@Test
	public void testFindMethod()
	{
		assertTrue(tree234.find(minDataItem.dData - 1) == -1);
		assertTrue(tree234.find(minDataItem.dData) != -1);
	}

	/**
	 * Тестирование метода вставки элемента в дерево
	 */
	@Test
	public void testInsertMethod()
	{
		for (int i = 100; i < 200; i += 10)
		{
			tree234.insert(i);
			tree234.insert(new DataItem(i * 2));
		}

		for (int i = 100; i < 200; i += 10)
		{
			assertTrue(tree234.find(i) != -1);
			assertTrue(tree234.find(i * 2) != -1);
		}
	}

	/**
	 * Тестирование метода поиска минимального элемента данных в дереве 234
	 */
	@Test
	public void testGetMinDataItemMethod()
	{
		assertEquals(minDataItem, tree234.getMinDataItem());
		for (int i = 10; i > 0; i--)
		{
			minDataItem = new DataItem(i);
			tree234.insert(minDataItem);
			assertEquals(minDataItem, tree234.getMinDataItem());
		}
	}

	/**
	 * Тестирование метода выполняющего рекурсивный симметричный обход дерева
	 */
	@Test
	public void testReqSymmetricalTree234WalkMethod()
	{
		tree234 = new Tree234();
		ArrayList<DataItem> localDataItems = new ArrayList<>();
		ArrayList<DataItem> dataItems = new ArrayList<>();

		for (int i = 10; i < 80; i += 10)
		{
			DataItem localDataItem = new DataItem(i);
			localDataItems.add(localDataItem);
			tree234.insert(localDataItem);
		}

		tree234.reqSymmetricalTree234Walk(tree234.getRoot(), dataItems);
		assertEquals(localDataItems, dataItems);

		for (int i = 0; i < 10; i++)
		{
			DataItem localDataItem = new DataItem(i);
			localDataItems.add(localDataItem);
			tree234.insert(localDataItem);
		}

		dataItems = new ArrayList<>();
		tree234.reqSymmetricalTree234Walk(tree234.getRoot(), dataItems);

		localDataItems.sort((dataItem, t1) ->
		{
			if (dataItem.dData > t1.dData)
			{
				return 1;
			}
			else if (dataItem.dData > t1.dData)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		});

		assertEquals(localDataItems, dataItems);

		dataItems.forEach(dataItem -> System.out.println(dataItem.getDisplayData()));
	}

	/**
	 * Метод тестирующий сортировку при помощи дерева 234
	 */
	@Test
	public void sortTest()
	{
		List<DataItem> unsortedDataItems = new ArrayList<>();
		unsortedDataItems.add(new DataItem(1));
		unsortedDataItems.add(new DataItem(10));
		unsortedDataItems.add(new DataItem(9));
		unsortedDataItems.add(new DataItem(2));
		unsortedDataItems.add(new DataItem(8));
		unsortedDataItems.add(new DataItem(4));
		unsortedDataItems.add(new DataItem(3));
		unsortedDataItems.add(new DataItem(6));
		unsortedDataItems.add(new DataItem(5));
		unsortedDataItems.add(new DataItem(7));

		List<DataItem> dataItems = new ArrayList<>(unsortedDataItems);
		Tree234.sort(dataItems);

		unsortedDataItems.sort((dt1, dt2) ->
		{
			if (dt1.dData > dt2.dData)
			{
				return 1;
			}
			else if (dt1.dData == dt2.dData)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		});

		assertEquals(unsortedDataItems, dataItems);
	}
}
