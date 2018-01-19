package chapter9;

import java.util.Stack;

/**
 * Класс описывающий сущность красно-черного дерева подчиняющегося следующим правилам:
 *
 * 1. Каждый узел окрашен в красный или черный цвет
 * 2. Корень всегда окрашен в черный цвет
 * 3. Если узел красный, то его потомки должны быть черными(хотя обратное не всегда истинно)
 * 4. Все пути от корня к узлу или пустому потомку должны содержать одинаковое количество черных узлов
 *
 * @author rassoll
 * @created 15.01.2018
 * @$Author$
 * @$Revision$
 */
class RBTree
{
	private RBNode rbRoot;

	/**
	 * Вставка ноды в дерево
	 *
	 * @param newNode нода
	 */
	public void insert(RBNode newNode)
	{
		//Корневого узла нет
		if (rbRoot == null)
		{
			//корневой узел должен быть черным
			if (!newNode.isBlack())
			{
				newNode.switchRBNodeColor();
			}
			rbRoot = newNode;
		}
		else
		{
			//стэк для хранения пути, при перемещении вниз по красно черному дереву
			Stack<RBNode> rbNodes = new Stack<>();

			RBNode parent = rbRoot;
			RBNode current = rbRoot;
			while (true)
			{
				rbNodes.push(parent);

				//проверка является ли узел черным с двумя красными потомками
				if (current.isBlackNodeWithTwoRedDescendants())
				{
					current.switchRBNodeAndDescendantsColor(rbRoot);
				}

				//проверка нарушения 3-го правила
				if (parent.isViolationThirdRule())
				{
					//получание предка узла current
					rbNodes.pop();
					RBNode grandParent = rbNodes.pop();

					//внешний внук узла grandParent, нарушение в ЛЕВОЙ части дерева
					if (grandParent.leftRBChild == parent && parent.leftRBChild == current)
					{
						turnRight(grandParent, parent);
					}
					//внешний внук узла grandParent, нарушение в ПРАВОЙ части дерева
					else if (grandParent.rightRBChild == parent && parent.rightRBChild == current)
					{
						turnLeft(grandParent, parent);
					}
					//внутренний внук узла grandParent, нарушение в ЛЕВОЙ части дерева
					else if (grandParent.leftRBChild == parent && parent.rightRBChild == current)
					{
						turnLeft(grandParent, parent, current);
						turnRight(grandParent, current);
					}
					//внутренний внук узла grandParent, нарушение в ПРАВОЙ части дерева
					else if (grandParent.rightRBChild == parent && parent.leftRBChild == current)
					{
						turnRight(grandParent, parent, current);
						turnLeft(grandParent, current);
					}
				}

				parent = current;

				//вставка
				if (newNode.key < current.key)
				{
					current = current.leftRBChild;
					if (current == null)
					{
						parent.leftRBChild = newNode;
						return;
					}
				}
				else
				{
					current = current.rightRBChild;
					if (current == null)
					{
						parent.rightRBChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * Установить новый корень красно черного дерева
	 *
	 * @param newRBRoot ссылка на новый корневой узел
	 */
	void setNewRBRoot(RBNode newRBRoot)
	{
		rbRoot = newRBRoot;

		//проверка соблюдения 1-го правила красно черного дерева
		if (!rbRoot.isBlack())
		{
			rbRoot.switchRBNodeColor();
		}
	}

	/**
	 * Выполнить ПРАВЫЙ поворот для ВНЕШНЕГО потомка
	 *
	 * @param grandParent ссылка на узел являющийся предком узла нарушителя
	 * @param parent      ссылка на родительсикй узел узла нарушителя
	 */
	void turnRight(RBNode grandParent, RBNode parent)
	{
		grandParent.switchRBNodeColor();
		parent.switchRBNodeColor();

		grandParent.leftRBChild = parent.rightRBChild;

		parent.rightRBChild = grandParent;

		//в случае если узел предок был корнем дерева, корень нужно изменить
		if (rbRoot == grandParent)
		{
			setNewRBRoot(parent);
		}

		//переключаем цвета подчиненных узлов
		rbRoot.leftRBChild.switchRBNodeColor();
		rbRoot.rightRBChild.switchRBNodeColor();
	}

	/**
	 * Выполнить ЛЕВЫЙ поворот для ВНЕШНЕГО потомка
	 *
	 * @param grandParent ссылка на узел являющийся предком узла нарушителя
	 * @param parent      ссылка на родительсикй узел узла нарушителя
	 */
	void turnLeft(RBNode grandParent, RBNode parent)
	{
		grandParent.switchRBNodeColor();
		parent.switchRBNodeColor();

		grandParent.rightRBChild = parent.leftRBChild;

		parent.leftRBChild = grandParent;

		//в случае если узел предок был корнем дерева, корень нужно изменить
		if (rbRoot == grandParent)
		{
			setNewRBRoot(parent);
		}

		//переключаем цвета подчиненных узлов
		rbRoot.leftRBChild.switchRBNodeColor();
		rbRoot.rightRBChild.switchRBNodeColor();
	}

	/**
	 * Выполнить ПРАВЫЙ поворот для ВНУТРЕННЕГО потомка
	 *
	 * @param grandParent ссылка на узел являющийся предком узла нарушителя
	 * @param parent      ссылка на родительсикй узел узла нарушителя
	 * @param current     сыылка на узел нарушитель
	 */
	private void turnRight(RBNode grandParent, RBNode parent, RBNode current)
	{
		grandParent.switchRBNodeColor();
		current.switchRBNodeColor();

		grandParent.rightRBChild = current;
		parent.leftRBChild = current.rightRBChild;
		current.rightRBChild = parent;
	}

	/**
	 * Выполнить ЛЕВЫЙ поворот для ВНУТРЕННЕГО потомка
	 *
	 * @param grandParent ссылка на узел являющийся предком узла нарушителя
	 * @param parent      ссылка на родительсикй узел узла нарушителя
	 * @param current     сыылка на узел нарушитель
	 */
	private void turnLeft(RBNode grandParent, RBNode parent, RBNode current)
	{
		current.switchRBNodeColor();

		grandParent.leftRBChild = current;
		parent.rightRBChild = current.leftRBChild;
		current.leftRBChild = parent;
	}

	/**
	 * @return ссылка на корень красно-черного дерева
	 */
	RBNode getRbRoot()
	{
		return rbRoot;
	}

	/**
	 * Вывод содержимого дерева
	 */
	void displayTree()
	{
		Stack<RBNode> globalStack = new Stack<>();
		globalStack.push(rbRoot);
		int nBlanks = 32;
		boolean isRowEmpty = false;

		System.out.println("................................................................");
		while (!isRowEmpty)
		{
			Stack<RBNode> localStack = new Stack<>();
			isRowEmpty = true;

			for (int i = 0; i < nBlanks; i++)
			{
				System.out.print(" ");
			}

			while (!globalStack.isEmpty())
			{
				RBNode temp = globalStack.pop();
				if (temp != null)
				{
					System.out.print(temp.getRBNodePrintData());
					localStack.push(temp.leftRBChild);
					localStack.push(temp.rightRBChild);

					if (temp.leftRBChild != null || temp.rightRBChild != null)
					{
						isRowEmpty = false;
					}
				}
				else
				{
					System.out.print("--");
					localStack.push(null);
					localStack.push(null);
				}

				for (int i = 0; i < nBlanks * 2 - 2; i++)
				{
					System.out.print(" ");
				}
			}
			System.out.println();
			nBlanks /= 2;

			while (!localStack.isEmpty())
			{
				globalStack.push(localStack.pop());
			}
		}
	}
}
