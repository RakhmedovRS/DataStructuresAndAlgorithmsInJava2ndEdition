package chapter9;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link RBNode}
 *
 * @author rassoll
 * @created 15.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestRBNode
{
	private RBNode rbNode;

	@Before
	public void init()
	{
		rbNode = new RBNode(1);
	}

	/**
	 * Тестирование переключения цветов конкретного узла
	 */
	@Test
	public void testWorkingWithColors()
	{
		assertFalse(rbNode.isBlack());
		rbNode.switchRBNodeColor();
		assertTrue(rbNode.isBlack());
		rbNode.switchRBNodeColor();
		assertFalse(rbNode.isBlack());

		rbNode = new RBNode(1, true);
		assertTrue(rbNode.isBlack());
	}

	/**
	 * Тестирование переключения цветов узла и его потомков
	 */
	@Test
	public void testWorkingWithColors2()
	{
		RBNode rbNode = new RBNode(10);
		rbNode.leftRBChild = new RBNode(5);
		rbNode.rightRBChild = new RBNode(15);

		assertFalse(rbNode.isBlack());
		assertFalse(rbNode.leftRBChild.isBlack());
		assertFalse(rbNode.rightRBChild.isBlack());

		rbNode.switchRBNodeAndDescendantsColor(rbNode);
		assertFalse(rbNode.isBlack());
		assertTrue(rbNode.leftRBChild.isBlack());
		assertTrue(rbNode.rightRBChild.isBlack());

		rbNode.switchRBNodeAndDescendantsColor(new RBNode(1));
		assertTrue(rbNode.isBlack());
		assertFalse(rbNode.leftRBChild.isBlack());
		assertFalse(rbNode.rightRBChild.isBlack());
	}

	@Test
	public void checkIsBlackNodeWithTwoRedDescendantsMethod()
	{
		RBNode rbNode = new RBNode(10);
		rbNode.leftRBChild = new RBNode(5);
		rbNode.rightRBChild = new RBNode(15);

		assertFalse(rbNode.isBlackNodeWithTwoRedDescendants());

		rbNode.switchRBNodeColor();
		assertTrue(rbNode.isBlackNodeWithTwoRedDescendants());
	}

	/**
	 * Тестирование метода проверяющего нарущения 3-го правила красно черного дерева
	 */
	@Test
	public void testViolationThirdRule()
	{
		rbNode = new RBNode(10, true);
		assertFalse(rbNode.isViolationThirdRule());

		rbNode.leftRBChild = new RBNode(5);
		assertFalse(rbNode.isViolationThirdRule());
		rbNode.rightRBChild = new RBNode(15);
		assertFalse(rbNode.isViolationThirdRule());

		rbNode.switchRBNodeColor();
		assertTrue(rbNode.isViolationThirdRule());

		rbNode.leftRBChild.switchRBNodeColor();
		assertTrue(rbNode.isViolationThirdRule());

		rbNode.leftRBChild = null;
		assertTrue(rbNode.isViolationThirdRule());

		rbNode.rightRBChild.switchRBNodeColor();
		assertFalse(rbNode.isViolationThirdRule());

		rbNode.rightRBChild = null;
		assertFalse(rbNode.isViolationThirdRule());
	}

	/**
	 * Проверка данных для печати
	 */
	@Test
	public void testGettingRBNodePrintData()
	{
		assertEquals("1", rbNode.getRBNodePrintData());
		rbNode.switchRBNodeColor();
		assertEquals("[1]", rbNode.getRBNodePrintData());
	}
}
