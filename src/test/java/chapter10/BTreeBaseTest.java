package chapter10;

import base.items.Item;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Базовый класс для тестирования B-деревьев {@link BTree} различного порядка
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
	static void checkTreeOrder(BTree bTree, Order bTreeOrder)
	{
		assertEquals(bTreeOrder, bTree.getTreeOrder());
	}

	/**
	 * Тестирование метода поиска позиции элемента
	 */
	static void testFindMethod(BTree bTree, Item minDataItem)
	{
		assertTrue(bTree.find((long)minDataItem.getKey() - 1) == -1);
		assertTrue(bTree.find((long)minDataItem.getKey()) != -1);
	}

	/**
	 * Тестирование метода вставки элемента в дерево
	 */
	static void testInsertMethod(BTree bTree)
	{
		for (long i = 100; i < 200; i += 10)
		{
			bTree.insert(i);
			bTree.insert(i * 2);
		}

		for (long i = 100; i < 200; i += 10)
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
	static void testReqSymmetricalBTreeWalkMethod(Order bTreeOrder)
	{
		BTree bTree = new BTree(bTreeOrder);
		ArrayList<Item> localDataItems = new ArrayList<>();
		ArrayList<Item> dataItems = new ArrayList<>();

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
			if ((long)dataItem.getKey() > (long)t1.getKey())
			{
				return 1;
			}
			else if ((long)dataItem.getKey() > (long)t1.getKey())
			{
				return 0;
			}
			else
			{
				return -1;
			}
		});

		assertEquals(localDataItems, dataItems);
	}

	/**
	 * Метод тестирующий сортировку при помощи B-дерева
	 */
	static void sortTest(Order bTreeOrder)
	{
		List<Item> unsortedDataItems = new ArrayList<>();

		for (int i = 100; i >= 0; i--)
		{
			unsortedDataItems.add(new DataItem(i));
		}

		List<Item> dataItems = new ArrayList<>(unsortedDataItems);
		BTree.sort(dataItems, bTreeOrder);

		unsortedDataItems.sort((dt1, dt2) ->
		{
			if ((long)dt1.getKey() > (long)dt2.getKey())
			{
				return 1;
			}
			else if ((long)dt1.getKey() == (long)dt2.getKey())
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
