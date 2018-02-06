package chapter10;

import org.junit.Before;
import org.junit.Test;

import static chapter10.BTreeOrder3Constants.*;
import static chapter10.Order.TREE_3_ORDER;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link BNode} для B-дерева 3 порядка
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestNode3Order
{
	private static final Order bTreeOrder = TREE_3_ORDER;

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

		assertTrue(bNode.insertItem(new DataItem(2)) == 0);
		assertTrue(bNode.insertItem(new DataItem(1)) == 0);
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
		assertTrue(bNode.insertItem(new DataItem(3)) == 2);
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
		assertEquals(leftDataItem, bNode.getItem(LEFT_ITEM));
		assertEquals(rightDataItem, bNode.getItem(CENTRAL_ITEM));
		assertNull(bNode.getItem(-1));
		assertNull(bNode.getItem(-1));
	}

	/**
	 * Тестирование метода связки узла с потомком
	 */
	@Test
	public void testConnectingAndGettingChild()
	{
		bNode.connectChild(LEFT_CHILD, firstChildNode);
		bNode.connectChild(CENTRAL_CHILD, secondChildNode);
		bNode.connectChild(RIGHT_CHILD, thirdChildNode);

		assertEquals(firstChildNode, bNode.getChild(LEFT_CHILD));
		assertEquals(secondChildNode, bNode.getChild(CENTRAL_CHILD));
		assertEquals(thirdChildNode, bNode.getChild(RIGHT_CHILD));
	}

	/**
	 * Тестирование метода отсоединения потомка от узла
	 */
	@Test
	public void testDisconnectingChild()
	{
		bNode.connectChild(LEFT_CHILD, firstChildNode);
		bNode.connectChild(CENTRAL_CHILD, secondChildNode);
		bNode.connectChild(RIGHT_CHILD, thirdChildNode);

		assertEquals(firstChildNode, bNode.disconnectChild(LEFT_CHILD));
		assertNull(bNode.getChild(LEFT_CHILD));
		assertEquals(secondChildNode, bNode.disconnectChild(CENTRAL_CHILD));
		assertNull(bNode.getChild(CENTRAL_CHILD));
		assertEquals(thirdChildNode, bNode.disconnectChild(RIGHT_CHILD));
		assertNull(bNode.getChild(RIGHT_CHILD));

		assertNull(bNode.disconnectChild(LEFT_CHILD));
		assertNull(bNode.disconnectChild(CENTRAL_CHILD));
		assertNull(bNode.disconnectChild(RIGHT_CHILD));
	}

	/**
	 * Тестирование метода возвращающего признак листового узла
	 */
	@Test
	public void checkIsLeafMethod()
	{
		assertTrue(bNode.isLeaf());
		bNode.connectChild(LEFT_CHILD, firstChildNode);
		assertFalse(bNode.isLeaf());
		bNode.connectChild(LEFT_CHILD, secondChildNode);
		assertFalse(bNode.isLeaf());
		bNode.disconnectChild(CENTRAL_CHILD);
		assertFalse(bNode.isLeaf());
		bNode.disconnectChild(LEFT_CHILD);
		assertTrue(bNode.isLeaf());
	}

	/**
	 * Тестирование метода получения родительского узла
	 */
	@Test
	public void checkGettingParent()
	{
		bNode.connectChild(LEFT_CHILD, firstChildNode);
		bNode.connectChild(CENTRAL_CHILD, secondChildNode);
		bNode.connectChild(RIGHT_CHILD, thirdChildNode);

		assertEquals(bNode, firstChildNode.getParent());
		assertEquals(bNode, secondChildNode.getParent());
		assertEquals(bNode, thirdChildNode.getParent());
	}
}
