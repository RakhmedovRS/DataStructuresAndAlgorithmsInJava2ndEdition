package chapter9;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author rassoll
 * @created 15.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestRBTree
{
	private RBTree rbTree;

	@Before
	public void init()
	{
		rbTree = new RBTree();
	}

	/**
	 * Тестирование метода вставки
	 * с возникновением нарушения 3-го правила {@link RBTree},
	 * узел нарушитель является ВНЕШНИМ потомком
	 * нарушение происходит в ЛЕВОЙ части дерева
	 */
	@Test
	public void testInsertWithViolationInLeftPartOfRBTreeWithExternalGrandson()
	{
		/*структура исходного дерева
                                       [50]
                       25                              [75]
             [12]              [37]              --              --
          6      18         --      --       --      --      --      --
		 */
		RBNode oldRBRoot = new RBNode(50);
		RBNode newRBRoot = new RBNode(25);
		rbTree.insert(oldRBRoot);
		rbTree.insert(newRBRoot);
		rbTree.insert(new RBNode(75));
		rbTree.insert(new RBNode(12));
		rbTree.insert(new RBNode(37));
		rbTree.insert(new RBNode(6));
		rbTree.insert(new RBNode(18));

		assertEquals(oldRBRoot, rbTree.getRbRoot());

		//добавление узла вызывающего нарушение 3-го правила
		rbTree.insert(new RBNode(3));

		//проверка того, что корень дерева изменился на новый, т.е. поворот выполнился
		assertEquals(newRBRoot, rbTree.getRbRoot());

		/*проверка всех узлов на соотвествие цветов и позиций в красно черном дереве
		  дерево должно соотвествовать следующей структуре
		                                  [25]
		                [12]                               [50]
		       [6]              [18]              [37]              [75]
		    3      --        --      --        --      --        --      --*/
		assertEquals(newRBRoot, rbTree.getRbRoot());
		assertTrue(rbTree.getRbRoot().isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.key == 12);
		assertTrue(rbTree.getRbRoot().leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.key == 50);
		assertTrue(rbTree.getRbRoot().rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.key == 6);
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.key == 18);
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.key == 37);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.key == 75);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.leftRBChild.key == 3);
		assertFalse(rbTree.getRbRoot().leftRBChild.leftRBChild.leftRBChild.isBlack());
		rbTree.displayTree();
	}

	/**
	 * Тестирование метода вставки
	 * с возникновением нарушения 3-го правила {@link RBTree},
	 * узел нарушитель является ВНЕШНИМ потомком
	 * нарушение происходит в ПРАВОЙ части дерева
	 */
	@Test
	public void testInsertWithViolationInRightPartOfRBTreeWithExternalGrandson()
	{
		/*структура исходного дерева
                                [50]
                     [25]                              75
              --              --              [70]              [80]
          --      --      --      --       --      --        78      85
		 */

		RBNode oldRBRoot = new RBNode(50);
		RBNode newRBRoot = new RBNode(75);
		rbTree.insert(oldRBRoot);
		rbTree.insert(new RBNode(25));
		rbTree.insert(newRBRoot);
		rbTree.insert(new RBNode(80));
		rbTree.insert(new RBNode(70));
		rbTree.insert(new RBNode(85));
		rbTree.insert(new RBNode(78));

		//добавление узла вызывающего нарушение 3-го правила
		rbTree.insert(new RBNode(90));

		//проверка того, что корень дерева изменился на новый, т.е. поворот выполнился
		assertEquals(newRBRoot, rbTree.getRbRoot());

		/*проверка всех узлов на соотвествие цветов и позиций в красно черном дереве
		  дерево должно соотвествовать следующей структуре
		                                  [75]
		                   50                              80
		       [25]             [70]              [78]              [85]
		    --      --       --      --        --      --        --      90*/
		assertTrue(rbTree.getRbRoot().isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.key == 50);
		assertTrue(rbTree.getRbRoot().leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.key == 80);
		assertTrue(rbTree.getRbRoot().rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.key == 25);
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.key == 70);
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.key == 78);
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.key == 85);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.rightRBChild.key == 90);
		assertFalse(rbTree.getRbRoot().rightRBChild.rightRBChild.rightRBChild.isBlack());
		rbTree.displayTree();
	}

	/**
	 * Тестирование метода вставки
	 * с возникновением нарушения 3-го правила {@link RBTree},
	 * узел нарушитель является ВНУТРЕННИМ потомком
	 * нарушение происходит в ЛЕВОЙ части дерева
	 */
	@Test
	public void testInsertWithViolationInLeftPartOfRBTreeWithInternalGrandson()
	{
		/*структура исходного дерева
                                       [50]
                       25                              [75]
             [12]              [37]              --              --
          --      --        31      43       --      --      --      --
		 */
		RBNode oldRBRoot = new RBNode(50);
		RBNode newRBRoot = new RBNode(37);
		rbTree.insert(oldRBRoot);
		rbTree.insert(new RBNode(25));
		rbTree.insert(new RBNode(75));
		rbTree.insert(new RBNode(12));
		rbTree.insert(newRBRoot);
		rbTree.insert(new RBNode(31));
		rbTree.insert(new RBNode(43));

		assertEquals(oldRBRoot, rbTree.getRbRoot());

		//добавление узла вызывающего нарушение 3-го правила
		rbTree.insert(new RBNode(28));

		//проверка того, что корень дерева изменился на новый, т.е. поворот выполнился
		assertEquals(newRBRoot, rbTree.getRbRoot());

		/*проверка всех узлов на соотвествие цветов и позиций в красно черном дереве
		  дерево должно соотвествовать следующей структуре
		                                  [37]
		                [25]                               [50]
		       [12]              [31]              [43]              [75]
		    --     --         28      --        --      --        --      --*/
		assertEquals(newRBRoot, rbTree.getRbRoot());
		assertTrue(rbTree.getRbRoot().isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.key == 25);
		assertTrue(rbTree.getRbRoot().leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.key == 50);
		assertTrue(rbTree.getRbRoot().rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.key == 12);
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.key == 31);
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.key == 43);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.key == 75);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.leftRBChild.key == 28);
		assertFalse(rbTree.getRbRoot().leftRBChild.rightRBChild.leftRBChild.isBlack());
		rbTree.displayTree();
	}

	/**
	 * Тестирование метода вставки
	 * с возникновением нарушения 3-го правила {@link RBTree},
	 * узел нарушитель является ВНУТРЕННИМ потомком
	 * нарушение происходит в ЛЕВОЙ части дерева
	 */
	@Test
	public void testInsertWithViolationInRightPartOfRBTreeWithInternalGrandson()
	{
		/*структура исходного дерева
                                    [50]
                    [25]                              77
              --              --              [62]              [87]
          --      --      --      --       --      --        81      93
		 */
		RBNode oldRBRoot = new RBNode(50);
		RBNode newRBRoot = new RBNode(77);
		rbTree.insert(oldRBRoot);
		rbTree.insert(newRBRoot);
		rbTree.insert(new RBNode(25));
		rbTree.insert(new RBNode(62));
		rbTree.insert(new RBNode(87));
		rbTree.insert(new RBNode(81));
		rbTree.insert(new RBNode(93));

		//добавление узла вызывающего нарушение 3-го правила
		rbTree.insert(new RBNode(78));

		//проверка того, что корень дерева изменился на новый, т.е. поворот выполнился
		assertEquals(newRBRoot, rbTree.getRbRoot());

		/*проверка всех узлов на соотвествие цветов и позиций в красно черном дереве
		  дерево должно соотвествовать следующей структуре
		                                  [77]
		                [50]                               [87]
		       [25]              [62]              [81]              [93]
		    --     --         --      --        78      --        --      --*/

		assertEquals(newRBRoot, rbTree.getRbRoot());
		assertTrue(rbTree.getRbRoot().isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.key == 50);
		assertTrue(rbTree.getRbRoot().leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.key == 87);
		assertTrue(rbTree.getRbRoot().rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.key == 25);
		assertTrue(rbTree.getRbRoot().leftRBChild.leftRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.key == 62);
		assertTrue(rbTree.getRbRoot().leftRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.key == 81);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.key == 93);
		assertTrue(rbTree.getRbRoot().rightRBChild.rightRBChild.isBlack());
		assertTrue(rbTree.getRbRoot().rightRBChild.leftRBChild.leftRBChild.key == 78);
		assertFalse(rbTree.getRbRoot().rightRBChild.leftRBChild.leftRBChild.isBlack());
		rbTree.displayTree();
	}
}
