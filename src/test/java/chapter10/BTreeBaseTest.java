package chapter10;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Базовый класс для тестирования B деревьев {@link BTree} различного порядка
 *
 * @author rassoll
 * @created 31.01.2018
 * @$Author$
 * @$Revision$
 */
abstract class BTreeBaseTest
{
	/**
	 * Проверка соотвествия порядка дерева
	 */
	 static void checkTreeOrder(BTree bTree, BOrder bTreeOrder)
	{
		assertEquals(bTreeOrder, bTree.getTreeOrder());
	}

	/**
	 * Тестирование метода поиска позиции элемента
	 */
	static void testFindMethod(BTree bTree, DataItem minDataItem)
	{
		assertTrue(bTree.find(minDataItem.getDData() - 1) == -1);
		assertTrue(bTree.find(minDataItem.getDData()) != -1);
	}

	/**
	 * Тестирование метода вставки элемента в дерево
	 */
	static void testInsertMethod(BTree bTree)
	{
		for (int i = 100; i < 200; i += 10)
		{
			bTree.insert(i);
			bTree.insert(new DataItem(i * 2));
		}

		for (int i = 100; i < 200; i += 10)
		{
			assertTrue(bTree.find(i) != -1);
			assertTrue(bTree.find(i * 2) != -1);
		}
	}

	/**
	 * Тестирование метода поиска минимального элемента данных в дереве
	 */
	static void testGetMinDataItemMethod(BTree bTree, DataItem minDataItem)
	{
		assertEquals(minDataItem, bTree.getMinDataItem());
		for (int i = 10; i > 0; i--)
		{
			minDataItem = new DataItem(i);
			bTree.insert(minDataItem);
			assertEquals(minDataItem, bTree.getMinDataItem());
		}
	}

	/**
	 * Тестирование метода выполняющего рекурсивный симметричный обход дерева
	 */
	static void testReqSymmetricalBTreeWalkMethod(BOrder bTreeOrder)
	{
		BTree bTree = new BTree(bTreeOrder);
		ArrayList<DataItem> localDataItems = new ArrayList<>();
		ArrayList<DataItem> dataItems = new ArrayList<>();

		for (int i = 10; i < 80; i += 10)
		{
			DataItem localDataItem = new DataItem(i);
			localDataItems.add(localDataItem);
			bTree.insert(localDataItem);
		}

		bTree.reqSymmetricalBTreeWalk(bTree.getRoot(), dataItems);
		assertEquals(localDataItems, dataItems);

		for (int i = 0; i < 10; i++)
		{
			DataItem localDataItem = new DataItem(i);
			localDataItems.add(localDataItem);
			bTree.insert(localDataItem);
		}

		dataItems = new ArrayList<>();
		bTree.reqSymmetricalBTreeWalk(bTree.getRoot(), dataItems);

		localDataItems.sort((dataItem, t1) ->
		{
			if (dataItem.getDData() > t1.getDData())
			{
				return 1;
			}
			else if (dataItem.getDData() > t1.getDData())
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
	 * Метод тестирующий сортировку при помощи B дерева
	 */
	static void sortTest(BOrder bTreeOrder)
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
		BTree.sort(dataItems, bTreeOrder);

		unsortedDataItems.sort((dt1, dt2) ->
		{
			if (dt1.getDData() > dt2.getDData())
			{
				return 1;
			}
			else if (dt1.getDData() == dt2.getDData())
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
