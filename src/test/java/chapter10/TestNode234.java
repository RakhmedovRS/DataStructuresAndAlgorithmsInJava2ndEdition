package chapter10;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Тестирование сущности {@link Node234}
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestNode234
{
	private static final DataItem leftDataItem = new DataItem(5);
	private static final DataItem centralDataItem = new DataItem(10);
	private static final DataItem rightDataItem = new DataItem(20);

	private static final Node234 firstChildNode = new Node234();
	private static final Node234 secondChildNode = new Node234();
	private static final Node234 thirdChildNode = new Node234();

	private Node234 node234;

	@Before
	public void init()
	{
		node234 = new Node234();
		node234.insertItem(leftDataItem);
		node234.insertItem(centralDataItem);
		node234.insertItem(rightDataItem);
	}

	/**
	 * Тестирование метода удаления элемента данных
	 */
	@Test
	public void testDeleteItem()
	{
		assertEquals(rightDataItem, node234.removeItem());
		assertEquals(centralDataItem, node234.removeItem());
		assertEquals(leftDataItem, node234.removeItem());
		assertNull(node234.removeItem());
	}

	/**
	 * Тестирование метода вставки элемента данных
	 */
	@Test
	public void testInsertItems()
	{
		node234 = new Node234();

		assertTrue(node234.insertItem(new DataItem(1)) == 0);
		assertTrue(node234.insertItem(new DataItem(2)) == 1);
		assertTrue(node234.insertItem(new DataItem(3)) == 2);
	}

	/**
	 * Тестирование метода поиска индекса элемента данных
	 */
	@Test
	public void testFindItemMethod()
	{
		assertTrue(node234.findItem(centralDataItem.dData) == 1);
		assertTrue(node234.findItem(leftDataItem.dData) == 0);
		assertTrue(node234.findItem(rightDataItem.dData) == 2);
		assertTrue(node234.findItem(100) == -1);
	}

	/**
	 * Тестирование метода, проверяющего заполненность узла
	 */
	@Test
	public void testIsFullMethod()
	{
		assertTrue(node234.isFull());
		node234.removeItem();
		assertFalse(node234.isFull());
		node234.insertItem(rightDataItem);
		assertTrue(node234.isFull());
	}

	/**
	 * Тестирование метода возвращающего количество элементов данных в узле
	 */
	@Test
	public void testGettingNumElements()
	{
		assertTrue(node234.getNumItems() == 3);
		node234.removeItem();
		assertTrue(node234.getNumItems() == 2);
		node234.removeItem();
		assertTrue(node234.getNumItems() == 1);
		node234.removeItem();
		assertTrue(node234.getNumItems() == 0);
		node234.insertItem(leftDataItem);
		assertTrue(node234.getNumItems() == 1);
		node234.insertItem(centralDataItem);
		assertTrue(node234.getNumItems() == 2);
		node234.insertItem(rightDataItem);
		assertTrue(node234.getNumItems() == 3);
	}

	/**
	 * Тестирование метода получения данных для печати
	 */
	@Test
	public void testGetDisplayData()
	{
		assertEquals(node234.getDisplayData(), String.format("/%s/%s/%s/", leftDataItem.dData, centralDataItem.dData, rightDataItem.dData));
		node234.removeItem();
		assertEquals(node234.getDisplayData(), String.format("/%s/%s/", leftDataItem.dData, centralDataItem.dData));
		node234.removeItem();
		assertEquals(node234.getDisplayData(), String.format("/%s/", leftDataItem.dData));
		node234.removeItem();
		assertEquals("/", node234.getDisplayData());
		node234.insertItem(leftDataItem);
		assertEquals(node234.getDisplayData(), String.format("/%s/", leftDataItem.dData));
		node234.insertItem(centralDataItem);
		assertEquals(node234.getDisplayData(), String.format("/%s/%s/", leftDataItem.dData, centralDataItem.dData));
		node234.insertItem(rightDataItem);
		assertEquals(node234.getDisplayData(), String.format("/%s/%s/%s/", leftDataItem.dData, centralDataItem.dData, rightDataItem.dData));
	}

	/**
	 * Тестирование метода получения элемента данных по индексу
	 */
	@Test
	public void testGettingItem()
	{
		assertEquals(leftDataItem, node234.getItem(0));
		assertEquals(centralDataItem, node234.getItem(1));
		assertEquals(rightDataItem, node234.getItem(2));
		assertNull(node234.getItem(-1));
		assertNull(node234.getItem(-1));
	}

	/**
	 * Тестирование метода связки узла с потомком
	 */
	@Test
	public void testConnectingAndGettingChild()
	{
		node234.connectChild(0, firstChildNode);
		node234.connectChild(1, secondChildNode);
		node234.connectChild(2, thirdChildNode);

		assertEquals(firstChildNode, node234.getChild(0));
		assertEquals(secondChildNode, node234.getChild(1));
		assertEquals(thirdChildNode, node234.getChild(2));
	}

	/**
	 * Тестирование метода отсоединения потомка от узла
	 */
	@Test
	public void testDisconnectingChild()
	{
		node234.connectChild(0, firstChildNode);
		node234.connectChild(1, secondChildNode);
		node234.connectChild(2, thirdChildNode);

		assertEquals(firstChildNode, node234.disconnectChild(0));
		assertNull(node234.getChild(0));
		assertEquals(secondChildNode, node234.disconnectChild(1));
		assertNull(node234.getChild(1));
		assertEquals(thirdChildNode, node234.disconnectChild(2));
		assertNull(node234.getChild(2));

		assertNull(node234.disconnectChild(0));
		assertNull(node234.disconnectChild(1));
		assertNull(node234.disconnectChild(2));
	}

	/**
	 * Тестирование метода возвращающего признак листового узла
	 */
	@Test
	public void checkIsLeafMethod()
	{
		assertTrue(node234.isLeaf());
		node234.connectChild(0, firstChildNode);
		assertFalse(node234.isLeaf());
		node234.connectChild(0, secondChildNode);
		assertFalse(node234.isLeaf());
		node234.disconnectChild(1);
		assertFalse(node234.isLeaf());
		node234.disconnectChild(0);
		assertTrue(node234.isLeaf());
	}

	/**
	 * Тестирование метода получения родительского узла
	 */
	@Test
	public void checkGettingParent()
	{
		node234.connectChild(0, firstChildNode);
		node234.connectChild(1, secondChildNode);
		node234.connectChild(2, thirdChildNode);

		assertEquals(node234, firstChildNode.getParent());
		assertEquals(node234, secondChildNode.getParent());
		assertEquals(node234, secondChildNode.getParent());
	}
}
