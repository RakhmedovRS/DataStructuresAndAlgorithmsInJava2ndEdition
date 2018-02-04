package chapter10;

import org.junit.Test;

/**
 * Тестирование сущности {@link BTree}
 *
 * @author rassoll
 * @created 29.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestBTree
{
	private static BTree bTree;
	private static DataItem minDataItem;

	/**
	 * Проверка соотвествия порядка дерева
	 */
	@Test
	public void checkTreeOrder()
	{
		for (Order bTreeOrder : Order.values())
		{
			bTree = new BTree(bTreeOrder);
			BTreeBaseTest.checkTreeOrder(bTree, bTreeOrder);
		}
	}

	/**
	 * Тестирование метода поиска позиции элемента
	 */
	@Test
	public void testFindMethod()
	{
		for (Order bTreeOrder : Order.values())
		{
			bTree = new BTree(bTreeOrder);
			minDataItem = new DataItem(30);
			bTree.insert(50);
			bTree.insert(40);
			bTree.insert(60);
			bTree.insert(minDataItem);
			bTree.insert(70);
			BTreeBaseTest.testFindMethod(bTree, minDataItem);
		}
	}

	/**
	 * Тестирование метода вставки элемента в дерево
	 */
	@Test
	public void testInsertMethod()
	{
		for (Order bTreeOrder : Order.values())
		{
			BTreeBaseTest.testInsertMethod(new BTree(bTreeOrder));
		}
	}

	/**
	 * Тестирование метода поиска минимального элемента данных в B-дереве
	 */
	@Test
	public void testGetMinDataItemMethod()
	{
		for (Order bTreeOrder : Order.values())
		{
			bTree = new BTree(bTreeOrder);
			minDataItem = new DataItem(30);
			bTree.insert(50);
			bTree.insert(40);
			bTree.insert(60);
			bTree.insert(minDataItem);
			bTree.insert(70);
			BTreeBaseTest.testGetMinDataItemMethod(bTree, minDataItem);
		}
	}

	/**
	 * Тестирование метода выполняющего рекурсивный симметричный обход дерева
	 */
	@Test
	public void testReqSymmetricalBTreeWalkMethod()
	{
		for (Order bTreeOrder : Order.values())
		{
			BTreeBaseTest.testReqSymmetricalBTreeWalkMethod(bTreeOrder);
		}
	}

	/**
	 * Метод тестирующий сортировку при помощи B-дерева
	 */
	@Test
	public void sortingTest()
	{
		for (Order bTreeOrder : Order.values())
		{
			BTreeBaseTest.sortTest(bTreeOrder);
		}
	}
}
