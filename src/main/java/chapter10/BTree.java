package chapter10;

import java.util.List;

import static chapter10.BOrder.TREE_23;
import static chapter10.BOrder.TREE_234;

/**
 * Класс реализующий B дерево
 *
 * @author rassoll
 * @created 26.01.2018
 * @$Author$
 * @$Revision$
 */
class BTree
{
	private final BOrder order;
	private BNode root;

	/**
	 * ctor
	 *
	 * @param order порядок B дерева
	 */
	BTree(BOrder order)
	{
		this.order = order;
		root = new BNode(order);
	}

	/**
	 * Получить соотвествующего потомка
	 *
	 * @param bNode ссылка на узел(предполагается, что узел не пуст, не полон и не является листом)
	 * @param value значение для поиска
	 * @return потомок
	 */
	private static BNode getNextChild(BNode bNode, long value)
	{
		int i;

		int numItems = bNode.getNumItems();
		for (i = 0; i < numItems; i++)
		{
			if (value < bNode.getItem(i).getDData())
			{
				return bNode.getChild(i);
			}
		}
		return bNode.getChild(i);
	}

	/**
	 * Программный проект 10.3 - Program project 10.3
	 * Выполнить сортировку коллекции при помощи рекурсивного симметричного обхода дерева
	 *
	 * @param dataItems коллекция для сортировки
	 * @param order порядок дерева используещегося для сортировки
	 * @return отсортированная коллекция
	 */
	static List<DataItem> sort(List<DataItem> dataItems, BOrder order)
	{
		BTree tempTree = new BTree(order);
		dataItems.forEach(tempTree::insert);
		dataItems.clear();

		tempTree.reqSymmetricalBTreeWalk(tempTree.getRoot(), dataItems);
		return dataItems;
	}

	/**
	 * Выполнить поиск позиции элемента в дереве
	 *
	 * @param key ключ для поиска
	 * @return позиция найденного элемента данных
	 */
	public int find(long key)
	{
		BNode currentBNode = root;
		int childNumber;
		while (true)
		{
			if ((childNumber = currentBNode.findItem(key)) != -1)
			{
				return childNumber;
			}
			else if (currentBNode.isLeaf())
			{
				/*узел не найден*/
				return -1;
			}
			else
			{
				/*продолжаем поиск "глубже"*/
				currentBNode = getNextChild(currentBNode, key);
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
		insert(new DataItem(value));
	}

	/**
	 * Вставить элемент данных
	 *
	 * @param newItem вставляемый элемент данных
	 */
	public void insert(DataItem newItem)
	{
		BNode currentBNode = root;

		while (true)
		{
			/*если узел полон, он разбивается, переходим на уровень выше и ищем позицию для вставки*/
			if (currentBNode.isFull())
			{
				split(currentBNode, newItem);
				currentBNode = currentBNode.getParent();
				currentBNode = getNextChild(currentBNode, newItem.getDData());
			}
			/*узел листовой*/
			else if (currentBNode.isLeaf())
			{
				break;
			}
			else
			{
				currentBNode = getNextChild(currentBNode, newItem.getDData());
			}
		}
		currentBNode.insertItem(newItem);
	}

	/**
	 * @return порядок B дерева
	 */
	BOrder getTreeOrder()
	{
		return order;
	}

	/**
	 * @return ссылка на корневой элемент дерева
	 */
	BNode getRoot()
	{
		return root;
	}

	/**
	 * Отобразить содержимое дерева
	 */
	void displayTree()
	{
		recDisplayTree(root, 0, 0);
	}

	/**
	 * Программный проект 10.1 - Program project 10.1
	 * Получение минимального элемента данных в дереве
	 *
	 * @return минимальный {@link DataItem}
	 */
	DataItem getMinDataItem()
	{
		BNode currentNode = root;

		while (!currentNode.isLeaf())
		{
			currentNode = currentNode.getChild(0);
		}

		return currentNode.getItem(0);
	}

	/**
	 * Программный проект 10.2 - Program project 10.2
	 * Выполнить симметричный обход дерева
	 *
	 * @param rootBNode ссылка на корень дерева
	 * @param dataItems коллекция для помещения элементов данных
	 */
	void reqSymmetricalBTreeWalk(BNode rootBNode, List<DataItem> dataItems)
	{
		/*базовое ограничение рекурсии*/
		if (rootBNode.isLeaf())
		{
			for (int i = 0; i < rootBNode.getNumItems(); i++)
			{
				if (rootBNode.getItem(i) == null)
				{
					break;
				}
				dataItems.add(rootBNode.getItem(i));
			}
		}

		/*рекурсивные вызовы для каждого узла*/
		for (int i = 0; i < 4; i++)
		{
			if (rootBNode.getChild(i) != null)
			{
				reqSymmetricalBTreeWalk(rootBNode.getChild(i), dataItems);
				if (rootBNode.getItem(i) != null)
				{
					dataItems.add(rootBNode.getItem(i));
				}
			}
			else
			{
				break;
			}
		}
	}

	/**
	 * Выполнить разбиение узла
	 *
	 * @param brokenBNode разбиваемый узел(предполагается, что разбиваемый узел полон)
	 * @param newItem вставляемый элемент данных
	 */
	private void split(BNode brokenBNode, DataItem newItem)
	{
		if (getTreeOrder().equals(TREE_23))
		{
			split23(brokenBNode, newItem);
		}
		else if (getTreeOrder().equals(TREE_234))
		{
			split234(brokenBNode);
		}
	}

	/**
	 * Выполнить разбиение узла дерева 23
	 *
	 * @param brokenBNode разбиваемый узел(предполагается, что разбиваемый узел полон)
	 * @param newItem вставляемый элемент данных
	 */
	private void split23(BNode brokenBNode, DataItem newItem)
	{
		BNode parent;
		int itemIndex;
		DataItem itemC;
		DataItem itemB;

		DataItem tempItem = brokenBNode.removeItem();
		if (tempItem.getDData() > newItem.getDData())
		{
			itemC = tempItem;
			itemB = brokenBNode.removeItem();
		}
		else
		{
			itemC = brokenBNode.removeItem();
			itemB = tempItem;
		}

		BNode child2 = brokenBNode.disconnectChild(1);
		BNode child3 = brokenBNode.disconnectChild(2);
		BNode newRight = new BNode(getTreeOrder());

		/*узел является корнем, создаем новый корень, делаем его родителем, связываем с родителем*/
		if (brokenBNode == root)
		{
			root = new BNode(getTreeOrder());
			parent = root;
			root.connectChild(0, brokenBNode);
		}
		/*получаем родителя*/
		else
		{
			parent = brokenBNode.getParent();
		}

		/*разбираемся с родителем*/
		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();
		/*перемещение связей родителя на одного потомка вправо*/
		for (int i = n - 1; i > itemIndex; i--)
		{
			BNode temp = parent.disconnectChild(i);
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
	 * Выполнить разбиение узла дерева
	 *
	 * @param brokenBNode разбиваемый узел(предполагается, что разбиваемый узел полон)
	 */
	private void split234(BNode brokenBNode)
	{
		BNode parent;
		int itemIndex;

		DataItem itemC = brokenBNode.removeItem();
		DataItem itemB = brokenBNode.removeItem();

		BNode child2 = brokenBNode.disconnectChild(2);
		BNode child3 = brokenBNode.disconnectChild(3);
		BNode newRight = new BNode(getTreeOrder());

		/*узел является корнем, создаем новый корень, делаем его родителем, связываем с родителем*/
		if (brokenBNode == root)
		{
			root = new BNode(getTreeOrder());
			parent = root;
			root.connectChild(0, brokenBNode);
		}
		/*получаем родителя*/
		else
		{
			parent = brokenBNode.getParent();
		}

		/*разбираемся с родителем*/
		itemIndex = parent.insertItem(itemB);
		int n = parent.getNumItems();
		/*перемещение связей родителя на одного потомка вправо*/
		for (int i = n - 1; i > itemIndex; i--)
		{
			BNode temp = parent.disconnectChild(i);
			parent.connectChild(i + 1, temp);
		}
		/*связываем newRight с родителем*/
		parent.connectChild(itemIndex + 1, newRight);

		/*разбираемся с узлом newRight*/
		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}

	private void recDisplayTree(BNode thisNode, int level, int childNumber)
	{
		System.out.println(String.format("level=%s child=%s ", level, childNumber));
		System.out.println(thisNode.getDisplayData());

		int numItems = thisNode.getNumItems();
		for (int i = 0; i < numItems + 1; i++)
		{
			BNode nextNode = thisNode.getChild(i);
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
