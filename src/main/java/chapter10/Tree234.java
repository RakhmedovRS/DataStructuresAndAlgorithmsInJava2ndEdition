package chapter10;

/**
 * Класс реализующий дерево 234
 *
 * @author rassoll
 * @created 26.01.2018
 * @$Author$
 * @$Revision$
 */
public class Tree234
{
	private Node234 root = new Node234();

	/**
	 * Выполнить поиск элемента в дереве 234
	 *
	 * @param key ключ для поиска
	 * @return позиция найденного элемента данных
	 */
	public int find(long key)
	{
		Node234 currentNode234 = root;
		int childNumber;
		while (true)
		{
			if ((childNumber = currentNode234.findItem(key)) != -1)
			{
				return childNumber;
			}
			else if (currentNode234.isLeaf())
			{
				/*узел не найден*/
				return -1;
			}
			else
			{
				/*продолжаем поиск "глубже"*/
				currentNode234 = getNextChild(currentNode234, key);
			}
		}
	}

	/**
	 * Вставить элемент данных
	 *
	 * @param value вставляемое значение
	 */
	public void insert(long value)
	{
		Node234 currentNode234 = root;
		DataItem tempItem = new DataItem(value);

		while (true)
		{
			/*если узел полон, он разбивается, переходим на уровень выше и ищем позицию для вставки*/
			if (currentNode234.isFull())
			{
				split(currentNode234);
				currentNode234 = currentNode234.getParent();
				currentNode234 = getNextChild(currentNode234, value);
			}
			/*узел листовой*/
			else if (currentNode234.isLeaf())
			{
				break;
			}
			else
			{
				currentNode234 = getNextChild(currentNode234, value);
			}
		}
		currentNode234.insertItem(tempItem);
	}

	/**
	 * Получить соотвествующего потомка
	 *
	 * @param node234 ссылка на узел(предполагается, что узел не пуст, не полон и не является листом)
	 * @param value   значение для поиска
	 * @return потомок
	 */
	public Node234 getNextChild(Node234 node234, long value)
	{
		int i;

		int numItems = node234.getNumItems();
		for (i = 0; i < numItems; i++)
		{
			if (value < node234.getItem(i).dData)
			{
				return node234.getChild(i);
			}
		}
		return node234.getChild(i);
	}

	/**
	 * Выполинить разбиение узла
	 *
	 * @param brokenNode234 разбиваемый узел(предполагается, что разбиваемый узел полон)
	 */
	public void split(Node234 brokenNode234)
	{
		DataItem itemB = brokenNode234.removeItem();
		DataItem itemC = brokenNode234.removeItem();
		Node234 parent;
		Node234 child2 = brokenNode234.disconnectChild(2);
		Node234 child3 = brokenNode234.disconnectChild(3);
		Node234 newRight = new Node234();
		int itemIndex;

		/*узел является корнем, создаем новый корень, делаем его родителем, связываем с родителем*/
		if (brokenNode234 == root)
		{
			root = new Node234();
			parent = root;
			root.connectChild(0, brokenNode234);
		}
		/*получаем родителя*/
		else
		{
			parent = brokenNode234.getParent();
		}

		/*разбираемся с родителем*/
		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();
		/*перемещение связей родителя на одного потомка вправо*/
		for (int i = n - 1; i > itemIndex; i--)
		{
			Node234 temp = parent.disconnectChild(i);
			parent.connectChild(i + 1, temp);
		}
		/*связываем newRight с родителем*/
		parent.connectChild(itemIndex + 1, newRight);

		/*разбираемся с узлом newRight*/
		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}

	/**
	 * Отобразить содержимое дерева
	 */
	public void displayTree()
	{
		recDisplayTree(root, 0, 0);
	}

	private void recDisplayTree(Node234 thisNode, int level, int childNumber)
	{
		System.out.println(String.format("level=%s child=%s ", level, childNumber));
		System.out.println(thisNode.getDisplayData());

		int numItems = thisNode.getNumItems();
		for (int i = 0; i < numItems + 1; i++)
		{
			Node234 nextNode = thisNode.getChild(i);
			if (nextNode != null)
			{
				recDisplayTree(nextNode, level + 1, i);
			}
			else
			{
				return;
			}
		}
	}
}
