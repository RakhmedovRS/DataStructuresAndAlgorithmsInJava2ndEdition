package chapter10;

import org.junit.Before;
import org.junit.Test;

import static chapter10.BOrder.TREE_23;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link BNode} для дерева 23
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestNode23
{
	private static final BOrder bTreeOrder = TREE_23;

	private static final DataItem leftDataItem = new DataItem(5);
	private static final DataItem rightDataItem = new DataItem(10);

	private static final BNode firstChildNode = new BNode(bTreeOrder);
	private static final BNode secondChildNode = new BNode(bTreeOrder);
	private static final BNode thirdChildNode = new BNode(bTreeOrder);

	private BNode bNode;

	@Before
	public void init()
	{
		bNode = new BNode(bTreeOrder);
		bNode.insertItem(leftDataItem);
		bNode.insertItem(rightDataItem);
	}

	/**
	 * Тестирование метода удаления элемента данных
	 */
	@Test
	public void testDeleteItem()
	{
		assertEquals(rightDataItem, bNode.removeItem());
		assertEquals(leftDataItem, bNode.removeItem());
		assertNull(bNode.removeItem());
	}

	/**
	 * Тестирование метода вставки элемента данных
	 */
	@Test
	public void testInsertItems()
	{
		bNode = new BNode(bTreeOrder);

		assertTrue(bNode.insertItem(new DataItem(1)) == 0);
		assertTrue(bNode.insertItem(new DataItem(2)) == 1);
	}

	/**
	 * Тестирование метода вставки элемента данных
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testFailInsertItems()
	{
		bNode = new BNode(bTreeOrder);

		assertTrue(bNode.insertItem(new DataItem(1)) == 0);
		assertTrue(bNode.insertItem(new DataItem(2)) == 1);
		assertTrue(bNode.insertItem(new DataItem(3)) == 1);
	}

	/**
	 * Тестирование метода поиска индекса элемента данных
	 */
	@Test
	public void testFindItemMethod()
	{
		assertTrue(bNode.findItem(leftDataItem.getDData()) == 0);
		assertTrue(bNode.findItem(rightDataItem.getDData()) == 1);
		assertTrue(bNode.findItem(100) == -1);
	}

	/**
	 * Тестирование метода, проверяющего заполненность узла
	 */
	@Test
	public void testIsFullMethod()
	{
		assertTrue(bNode.isFull());
		bNode.removeItem();
		assertFalse(bNode.isFull());
		bNode.insertItem(rightDataItem);
		assertTrue(bNode.isFull());
	}

	/**
	 * Тестирование метода возвращающего количество элементов данных в узле
	 */
	@Test
	public void testGettingNumElements()
	{
		assertTrue(bNode.getNumItems() == 2);
		bNode.removeItem();
		assertTrue(bNode.getNumItems() == 1);
		bNode.removeItem();
		assertTrue(bNode.getNumItems() == 0);
		bNode.insertItem(leftDataItem);
		assertTrue(bNode.getNumItems() == 1);
		bNode.insertItem(rightDataItem);
		assertTrue(bNode.getNumItems() == 2);
	}

	/**
	 * Тестирование метода получения данных для печати
	 */
	@Test
	public void testGetDisplayData()
	{
		assertEquals(bNode.getDisplayData(), String.format("/%s/%s/", leftDataItem.getDData(), rightDataItem.getDData()));
		bNode.removeItem();
		assertEquals(bNode.getDisplayData(), String.format("/%s/", leftDataItem.getDData()));
		bNode.removeItem();
		assertEquals("/", bNode.getDisplayData());
		bNode.insertItem(leftDataItem);
		assertEquals(bNode.getDisplayData(), String.format("/%s/", leftDataItem.getDData()));
		bNode.insertItem(rightDataItem);
		assertEquals(bNode.getDisplayData(), String.format("/%s/%s/", leftDataItem.getDData(), rightDataItem.getDData()));
	}

	/**
	 * Тестирование метода получения элемента данных по индексу
	 */
	@Test
	public void testGettingItem()
	{
		assertEquals(leftDataItem, bNode.getItem(0));
		assertEquals(rightDataItem, bNode.getItem(1));
		assertNull(bNode.getItem(-1));
		assertNull(bNode.getItem(-1));
	}

	/**
	 * Тестирование метода связки узла с потомком
	 */
	@Test
	public void testConnectingAndGettingChild()
	{
		bNode.connectChild(0, firstChildNode);
		bNode.connectChild(1, secondChildNode);
		bNode.connectChild(2, thirdChildNode);

		assertEquals(firstChildNode, bNode.getChild(0));
		assertEquals(secondChildNode, bNode.getChild(1));
		assertEquals(thirdChildNode, bNode.getChild(2));
	}

	/**
	 * Тестирование метода отсоединения потомка от узла
	 */
	@Test
	public void testDisconnectingChild()
	{
		bNode.connectChild(0, firstChildNode);
		bNode.connectChild(1, secondChildNode);
		bNode.connectChild(2, thirdChildNode);

		assertEquals(firstChildNode, bNode.disconnectChild(0));
		assertNull(bNode.getChild(0));
		assertEquals(secondChildNode, bNode.disconnectChild(1));
		assertNull(bNode.getChild(1));
		assertEquals(thirdChildNode, bNode.disconnectChild(2));
		assertNull(bNode.getChild(2));

		assertNull(bNode.disconnectChild(0));
		assertNull(bNode.disconnectChild(1));
		assertNull(bNode.disconnectChild(2));
	}

	/**
	 * Тестирование метода возвращающего признак листового узла
	 */
	@Test
	public void checkIsLeafMethod()
	{
		assertTrue(bNode.isLeaf());
		bNode.connectChild(0, firstChildNode);
		assertFalse(bNode.isLeaf());
		bNode.connectChild(0, secondChildNode);
		assertFalse(bNode.isLeaf());
		bNode.disconnectChild(1);
		assertFalse(bNode.isLeaf());
		bNode.disconnectChild(0);
		assertTrue(bNode.isLeaf());
	}

	/**
	 * Тестирование метода получения родительского узла
	 */
	@Test
	public void checkGettingParent()
	{
		bNode.connectChild(0, firstChildNode);
		bNode.connectChild(1, secondChildNode);
		bNode.connectChild(2, thirdChildNode);

		assertEquals(bNode, firstChildNode.getParent());
		assertEquals(bNode, secondChildNode.getParent());
		assertEquals(bNode, thirdChildNode.getParent());
	}
}
