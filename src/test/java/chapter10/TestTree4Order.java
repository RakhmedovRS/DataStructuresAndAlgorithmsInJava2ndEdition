package chapter10;

import org.junit.Before;
import org.junit.Test;

import static chapter10.BOrder.*;

/**
 * Тестирование сущности {@link BTree}
 *
 * @author rassoll
 * @created 29.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestTree4Order
{
	private static final BOrder bTreeOrder = TREE_4_ORDER;
	private static BTree bTree;
	private static DataItem minDataItem;

	@Before
	public void init()
	{
		bTree = new BTree(bTreeOrder);
		minDataItem = new DataItem(20);
		bTree.insert(50);
		bTree.insert(40);
		bTree.insert(60);
		bTree.insert(minDataItem);
		bTree.insert(70);
	}

	/**
	 * Проверка соотвествия порядка дерева
	 */
	@Test
	public void checkTreeOrder()
	{
		BTreeBaseTest.checkTreeOrder(bTree, bTreeOrder);
	}

	/**
	 * Тестирование метода поиска позиции элемента
	 */
	@Test
	public void testFindMethod()
	{
		BTreeBaseTest.testFindMethod(bTree, minDataItem);
	}

	/**
	 * Тестирование метода вставки элемента в дерево
	 */
	@Test
	public void testInsertMethod()
	{
		BTreeBaseTest.testInsertMethod(bTree);
	}

	/**
	 * Тестирование метода поиска минимального элемента данных в дереве
	 */
	@Test
	public void testGetMinDataItemMethod()
	{
		BTreeBaseTest.testGetMinDataItemMethod(bTree, minDataItem);
	}

	/**
	 * Тестирование метода выполняющего рекурсивный симметричный обход дерева
	 */
	@Test
	public void testReqSymmetricalBTreeWalkMethod()
	{
		BTreeBaseTest.testReqSymmetricalBTreeWalkMethod(bTreeOrder);
	}

	/**
	 * Метод тестирующий сортировку при помощи дерева 234
	 */
	@Test
	public void sortTest()
	{
		BTreeBaseTest.sortTest(bTreeOrder);
	}
}
