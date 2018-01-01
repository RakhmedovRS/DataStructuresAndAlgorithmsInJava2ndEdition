package chapter8;

import java.util.Stack;

/**
 * Класс представляющий дерево
 *
 * @author rassoll
 * @created 01.01.2018
 * @$Author$
 * @$Revision$
 */
public class Tree
{
	private Node root;

	public Tree()
	{
		root = null;
	}

	/**
	 * Поиск элемента по {@param key}
	 *
	 * @param key ключ
	 * @return найденный узел
	 */
	public Node find(int key)
	{
		if (root == null)
		{
			return null;
		}

		Node current = root;
		while (current.key != key)
		{
			if (key < current.key)
			{
				current = current.leftChild;
			}
			else
			{
				current = current.rightChild;
			}

			if (current == null)
			{
				return null;
			}
		}
		return current;
	}

	/**
	 * Вставка пары {@param key} {@param value} в дерево
	 *
	 * @param key   ключ
	 * @param value значение
	 */
	public void insert(int key, double value)
	{
		Node newNode = new Node(key, value);

		//Корневого узла нет
		if (root == null)
		{
			root = newNode;
		}
		else
		{
			Node current = root;
			Node parent;
			while (true)
			{
				parent = current;
				if (key < current.key)
				{
					current = current.leftChild;
					if (current == null)
					{
						parent.leftChild = newNode;
						return;
					}
				}
				else
				{
					current = current.rightChild;
					if (current == null)
					{
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}

	/**
	 * Удаление элемента по {@param key}
	 *
	 * @param key ключ
	 * @return признак успешности удаления
	 */
	public boolean delete(int key)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;

		//поиск удаляемого узла
		while (current.key != key)
		{
			parent = current;

			if (key < current.key)
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else
			{
				isLeftChild = false;
				current = current.rightChild;
			}

			if (current == null)
			{
				return false;
			}
		}

		//удаление узла не имеющего потомков
		if (current.leftChild == null && current.rightChild == null)
		{
			if (current == root)
			{
				root = null;
			}
			else if (isLeftChild)
			{
				parent.leftChild = null;
			}
			else
			{
				parent.rightChild = null;
			}
		}
		//если нет правого потомка, узел заменяется левым поддеревом
		else if (current.rightChild == null)
		{
			if (current == root)
			{
				root = current.leftChild;
			}
			else if (isLeftChild)
			{
				parent.leftChild = current.leftChild;
			}
			else
			{
				parent.rightChild = current.leftChild;
			}
		}
		//если нет левого потомка, узел заменяется правым поддеревом
		else if (current.leftChild == null)
		{
			if (current == root)
			{
				root = current.rightChild;
			}
			else if (isLeftChild)
			{
				parent.leftChild = current.rightChild;
			}
			else
			{
				parent.rightChild = current.rightChild;
			}
		}
		//удаляемый узел имеет двух потомков
		else
		{
			Node successor = getSuccessor(current);

			if (current == root)
			{
				root = successor;
			}
			else if (isLeftChild)
			{
				parent.leftChild = successor;
			}
			else
			{
				parent.rightChild = successor;
			}
		}

		return true;
	}

	public void traverse(int traverseType)
	{
		switch (traverseType)
		{
			case 1:
				System.out.print("\nPreorder traversal: ");
				preOrder(root);
				break;
			case 2:
				System.out.print("\nInorder traversal: ");
				inOrder(root);
				break;
			case 3:
				System.out.print("\nPostorder traversal: ");
				postOrder(root);
				break;
		}
	}

	/**
	 * Поиск приемника для удаляемого узла
	 *
	 * @param delNode удаляемый узел
	 * @return узел приемник
	 */
	Node getSuccessor(Node delNode)
	{
		Node successorParent = delNode;
		Node successor = delNode;
		Node current = delNode.rightChild; //переход к правому потомку
		//пока есть левые потомки
		while (current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.leftChild; //переход к левому потомку
		}

		//если приемник не является правым потомком
		if (successor != delNode.rightChild)
		{
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = delNode.rightChild;
		}
		return successor;
	}

	/**
	 * Прямой обход дерева
	 *
	 * @param localRoot корневой элемент
	 */
	void preOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			System.out.print(localRoot.value + " ");
			preOrder(localRoot.leftChild);
			preOrder(localRoot.rightChild);
		}
	}

	/**
	 * Симметричный обход дерева
	 *
	 * @param localRoot корневой элемент
	 */
	void inOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			inOrder(localRoot.leftChild);
			System.out.print(localRoot.value + " ");
			inOrder(localRoot.rightChild);
		}
	}

	/**
	 * Обратный обход дерева
	 *
	 * @param localRoot корневой элемент
	 */
	void postOrder(Node localRoot)
	{
		if (localRoot != null)
		{
			postOrder(localRoot.leftChild);
			postOrder(localRoot.rightChild);
			System.out.print(localRoot.value + " ");
		}
	}

	/**
	 * Вывод содержимого дерева
	 */
	public void displayTree()
	{
		Stack<Node> globalStack = new Stack<>();
		globalStack.push(root);
		int nBlanks = 32;
		boolean isRowEmpty = false;

		System.out.println("................................................................");
		while (!isRowEmpty)
		{
			Stack<Node> localStack = new Stack<>();
			isRowEmpty = true;

			for (int i = 0; i < nBlanks; i++)
			{
				System.out.print(" ");
			}

			while (!globalStack.isEmpty())
			{
				Node temp = globalStack.pop();
				if (temp != null)
				{
					System.out.print(temp.key);
					localStack.push(temp.leftChild);
					localStack.push(temp.rightChild);

					if (temp.leftChild != null || temp.rightChild != null)
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
