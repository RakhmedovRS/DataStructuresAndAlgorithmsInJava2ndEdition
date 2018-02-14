package chapter10;

import base.items.Item;
import javafx.util.Pair;

import java.util.List;

import static chapter10.BTreeOrder3Constants.*;
import static chapter10.Order.TREE_3_ORDER;

/**
 * Класс реализующий B-дерево
 *
 * @author rassoll
 * @created 26.01.2018
 * @$Author$
 * @$Revision$
 */
class BTree
{
	private final Order order;
	private BNode root;

	/**
	 * ctor
	 *
	 * @param order порядок B-дерева
	 */
	BTree(Order order)
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
			if (value < bNode.getItem(i).getKey())
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
	 * @param items коллекция для сортировки
	 * @param order порядок дерева используещегося для сортировки
	 * @return отсортированная коллекция
	 */
	static List<Item> sort(List<Item> items, Order order)
	{
		BTree tempTree = new BTree(order);
		items.forEach(tempTree::insert);
		items.clear();

		tempTree.reqSymmetricalBTreeWalk(tempTree.getRoot(), items);
		return items;
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
	 * Вставить элемент данных в B-дерево
	 * для B-дерева 3-го порядка алгоритм отличается от остальных
	 *
	 * @param newItem вставляемый элемент данных
	 */
	public void insert(Item newItem)
	{
		if (getTreeOrder().equals(TREE_3_ORDER))
		{
			insertIntoBTreeOrder3(newItem);
		}
		else
		{
			insertIntoBTree(newItem);
		}
	}

	/**
	 * @return порядок B-дерева
	 */
	Order getTreeOrder()
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
	 * @return минимальный {@link Item}
	 */
	Item getMinDataItem()
	{
		BNode currentNode = root;

		while (!currentNode.isLeaf())
		{
			currentNode = currentNode.getChild(LEFT_CHILD);
		}

		return currentNode.getItem(LEFT_ITEM);
	}

	/**
	 * Программный проект 10.2 - Program project 10.2
	 * Выполнить симметричный обход дерева
	 *
	 * @param rootBNode ссылка на корень дерева
	 * @param items     коллекция для помещения элементов данных
	 */
	void reqSymmetricalBTreeWalk(BNode rootBNode, List<Item> items)
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
				items.add(rootBNode.getItem(i));
			}
		}

		/*рекурсивные вызовы для каждого узла*/
		for (int i = 0; i < getTreeOrder().getOrder(); i++)
		{
			if (rootBNode.getChild(i) != null)
			{
				reqSymmetricalBTreeWalk(rootBNode.getChild(i), items);
				if (rootBNode.getItem(i) != null)
				{
					items.add(rootBNode.getItem(i));
				}
			}
			else
			{
				break;
			}
		}
	}

	/**
	 * Получить новый узел дерева
	 *
	 * @return новый узел соотвествующий порядку дерева
	 */
	private BNode getNewNode()
	{
		return new BNode(getTreeOrder());
	}

	/**
	 * Получить новый узел дерева
	 *
	 * @param newItem элемент данных
	 * @return новый узел соотвествующий порядку дерева
	 */
	private BNode getNewNode(Item newItem)
	{
		return new BNode(getTreeOrder(), newItem);
	}

	/**
	 * Вставить элемент данных в B-дерево 3-го порядка
	 *
	 * @param newItem вставляемый элемент данных
	 */
	private void insertIntoBTreeOrder3(Item newItem)
	{
		BNode currentBNode = root;

		/*двигаемся вниз пока не найдем позицию для вставки нового элемента*/
		while (!currentBNode.isLeaf())
		{
			currentBNode = getNextChild(currentBNode, (newItem.getKey()));
		}

		/*если найденный листовой узел для вставки полный, разбиваем его, иначе вставляем новый элемент*/
		if (currentBNode.isFull())
		{
			splitNodeOrder3(currentBNode, newItem);
		}
		else
		{
			currentBNode.insertItem(newItem);
		}
	}

	/**
	 * Метод выполняющий вставку элемента данных в B-дерево
	 *
	 * @param newItem вставляемый элемент
	 */
	private void insertIntoBTree(Item newItem)
	{
		BNode currentBNode = root;

		while (true)
		{
			/*если узел полон, он разбивается, переходим на уровень выше и ищем позицию для вставки*/
			if (currentBNode.isFull())
			{
				splitBNode(currentBNode);
				currentBNode = currentBNode.getParent();
				currentBNode = getNextChild(currentBNode, (newItem.getKey()));
			}
			/*узел листовой*/
			else if (currentBNode.isLeaf())
			{
				break;
			}
			else
			{
				currentBNode = getNextChild(currentBNode, (newItem.getKey()));
			}
		}
		currentBNode.insertItem(newItem);
	}

	/**
	 * Программный проект 10.4 - Program project 10.4
	 * Программный проект 10.5 - Program project 10.5
	 * Выполнить разбиение узла дерева 23
	 *
	 * Частный случай разбиения B-дерева, выполняется от узлового элемента вверх
	 *
	 * @param brokenBNode разбиваемый узел(предполагается, что разбиваемый узел полон)
	 * @param newItem     вставляемый элемент данных
	 */
	private void splitNodeOrder3(BNode brokenBNode, Item newItem)
	{
		/*создание и наполнение ноды более высокого порядка для сортировки элементов*/
		BNode highOrderNode = new BNode(getTreeOrder().nextOrder(), newItem);
		highOrderNode.insertItem(brokenBNode.getItem(LEFT_ITEM));
		highOrderNode.insertItem(brokenBNode.getItem(CENTRAL_ITEM));

		/*разбиваемый узел является корнем*/
		if (brokenBNode == root)
		{
			/*создаем новый корневой узел*/
			BNode newRoot = getNewNode(highOrderNode.getItem(1));

			/*узел не имеет потомков*/
			if (brokenBNode.isLeaf())
			{
				newRoot.connectChild(LEFT_CHILD, getNewNode(highOrderNode.getItem(LEFT_ITEM)));
				newRoot.connectChild(CENTRAL_CHILD, getNewNode(highOrderNode.getItem(RIGHT_ITEM)));
			}
			/*разбиваемый узел имеет потомков*/
			else
			{
				Pair<BNode, BNode> childNodes = getNewChildNodes(highOrderNode, brokenBNode, newItem);

				/*присоединяем новых потомков к новому корню*/
				newRoot.connectChild(LEFT_CHILD, childNodes.getKey());
				newRoot.connectChild(CENTRAL_CHILD, childNodes.getValue());
			}

			/*обновляем корень дерева*/
			root = newRoot;
		}
		/*разбиваемый узел не является корнем*/
		else
		{
			/*получаем ссылку на родительский узел*/
			BNode parentNode = brokenBNode.getParent();

			/*родительский узел полон, его необходимо разбить*/
			if (parentNode.isFull())
			{
				/*разбиваемый узел не имеет потомков*/
				if (brokenBNode.isLeaf())
				{
					/*очищаем разбиваемый узел*/
					brokenBNode.clean();

					/*наименьший элемент остается в разбиваемом узле*/
					brokenBNode.insertItem(highOrderNode.getItem(LEFT_ITEM));

					/*наибольший элемент остается в разбиваемом узле*/
					brokenBNode.insertItem(highOrderNode.getItem(RIGHT_ITEM));

					/*вызываем метод разбиения родительского узла, передавая в него средний элемент данных*/
					splitNodeOrder3(parentNode, highOrderNode.getItem(CENTRAL_ITEM));
				}
				/*разбиваемый узел имеет потомков*/
				else
				{
					Pair<BNode, BNode> childNodes = getNewChildNodes(highOrderNode, brokenBNode, newItem);

					/*присоединяем новых потомком к разбиваемому узлу*/
					brokenBNode.connectChild(LEFT_CHILD, childNodes.getKey());
					brokenBNode.connectChild(CENTRAL_CHILD, childNodes.getValue());

					/*вызываем разбиение родительского узла*/
					splitNodeOrder3(parentNode, highOrderNode.getItem(CENTRAL_ITEM));
				}
			}
			/*родительский узел не полон, можно вставлять элемент в него*/
			else
			{
				/*разбиваемый узел не имеет потомков*/
				if (brokenBNode.isLeaf())
				{
					/*очищаем разбиваемый узел*/
					brokenBNode.clean();

					/*разбиение происходит в левом элементе*/
					if (highOrderNode.getItem(CENTRAL_ITEM).getKey() < parentNode.getItem(LEFT_ITEM).getKey())
					{
						/*брат разбиваемого узла смещается на 1 позицию правее*/
						parentNode.connectChild(RIGHT_CHILD, parentNode.disconnectChild(CENTRAL_CHILD));
						/*к родительскому узлу прикрепляется новый брат разбиваемого узла содержащий наибольший элемент*/
						parentNode.connectChild(CENTRAL_CHILD, getNewNode(highOrderNode.getItem(RIGHT_ITEM)));
					}
					/*разбиение происходит в правом узле элементе*/
					else if (highOrderNode.getItem(CENTRAL_ITEM).getKey() > parentNode.getItem(LEFT_ITEM).getKey())
					{
						/*к родительскому узлу прикрепляется новый брат разбиваемого узла содержащий наибольший элемент*/
						parentNode.connectChild(RIGHT_CHILD, getNewNode(highOrderNode.getItem(RIGHT_ITEM)));
					}

					/*средний элемент вставляется в родительский узел*/
					parentNode.insertItem(highOrderNode.getItem(CENTRAL_ITEM));

					/*наименьший элемент остается в разбиваемом узле*/
					brokenBNode.insertItem(highOrderNode.getItem(LEFT_ITEM));
				}
				/*разбиваемый узел имеет потомков*/
				else
				{
					Pair<BNode, BNode> childNodes = getNewChildNodes(highOrderNode, brokenBNode, newItem);

					/*распределяем дочерние элементы родителького узла*/
					if (parentNode.getItem(LEFT_ITEM).getKey() > childNodes.getValue().getItem(LEFT_ITEM).getKey())
					{
						/*смешаем среднего потомка родительского элемента на одну позицию правее*/
						parentNode.connectChild(RIGHT_CHILD, parentNode.disconnectChild(CENTRAL_CHILD));

						/*присоединяем новых потомков в родительскому узлу*/
						parentNode.connectChild(LEFT_CHILD, childNodes.getKey());
						parentNode.connectChild(CENTRAL_CHILD, childNodes.getValue());
					}
					else
					{
						/*присоединяем новых потомков в родительскому узлу*/
						parentNode.connectChild(CENTRAL_CHILD, childNodes.getKey());
						parentNode.connectChild(RIGHT_CHILD, childNodes.getValue());
					}

					/*средний элемент вставляется в родительский узел*/
					parentNode.insertItem(highOrderNode.getItem(CENTRAL_ITEM));
				}
			}
		}
	}

	/**
	 * Метод создающий новые дочерние узлы для их дальнейшего прикрепления к родительскому узлу
	 *
	 * @param highOrderNode нода более высокого порядка
	 * @param brokenBNode   разбиваемый узел
	 * @param newItem       новый элемент данных
	 * @return пара содержащая новую левую и правую ноды
	 */
	private Pair<BNode, BNode> getNewChildNodes(BNode highOrderNode, BNode brokenBNode, Item newItem)
	{
		BNode newLeftChild = getNewNode();
		BNode newRightChild = getNewNode();

		/*очищаем разбиваемый узел*/
		brokenBNode.clean();

		/*наименьший элемент вставляется в нового левого потомка*/
		newLeftChild.insertItem(highOrderNode.getItem(LEFT_ITEM));

		/*наибольший элемент вставляется в нового правого потомка*/
		newRightChild.insertItem(highOrderNode.getItem(RIGHT_ITEM));

		/*источник разбиения левый потомок*/
		if (highOrderNode.getItem(LEFT_ITEM).getKey() == newItem.getKey())
		{
			/*если дочерний узел не содержит элементов данных, значит он подвергался разбиению, необходимые узлы лежат на уровень ниже*/
			if (brokenBNode.getChild(LEFT_CHILD).getItem(LEFT_ITEM) == null)
			{
				/*присоеднияем нового правого потомка к новому левому потомку*/
				newLeftChild.connectChild(CENTRAL_CHILD, brokenBNode.getChild(LEFT_CHILD).disconnectChild(CENTRAL_CHILD));

				/*присоединяем левого потомка разбиваемого узла к новому левому потомку*/
				newLeftChild.connectChild(LEFT_CHILD, brokenBNode.getChild(LEFT_CHILD).disconnectChild(LEFT_CHILD));
			}
			/*дочерний узел не подвергался разбиению*/
			else
			{
				/*присоеднияем нового правого потомка к новому левому потомку*/
				newLeftChild.connectChild(CENTRAL_CHILD, getNewNode(brokenBNode.getChild(LEFT_CHILD).removeItem()));

				/*присоединяем левого потомка разбиваемого узла к новому левому потомку*/
				newLeftChild.connectChild(LEFT_CHILD, brokenBNode.disconnectChild(LEFT_CHILD));
			}

			/*к новому правому потомку в качестве дочерних узлов присоединяем средний и правый узлы потомки разбиваемого узла*/
			newRightChild.connectChild(LEFT_CHILD, brokenBNode.disconnectChild(CENTRAL_CHILD));
			newRightChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(RIGHT_CHILD));
		}
		/*источник разбиения правый потомок*/
		else if (highOrderNode.getItem(RIGHT_ITEM).getKey() == newItem.getKey())
		{
			/*к новому левому потомку в качестве дочерних узлов присоединяем левый и средний узлы потомки разбиваемого узла*/
			newLeftChild.connectChild(LEFT_CHILD, brokenBNode.disconnectChild(LEFT_CHILD));
			newLeftChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(CENTRAL_CHILD));

			/*если дочерний узел не содержит элементов данных, значит он подвергался разбиению, необходимые узлы лежат на уровень ниже*/
			if (brokenBNode.getChild(RIGHT_CHILD).getItem(LEFT_ITEM) == null)
			{
				/*присоединяем левого потомка разбиваемого узла к новому правому потомку*/
				newRightChild.connectChild(LEFT_CHILD, brokenBNode.getChild(RIGHT_CHILD).disconnectChild(LEFT_CHILD));

				/*присоединяем правого потомка разбиваемого узла к новому правому потомку*/
				newRightChild.connectChild(CENTRAL_CHILD, brokenBNode.getChild(RIGHT_CHILD).disconnectChild(CENTRAL_CHILD));
			}
			/*дочерний узел не подвергался разбиению*/
			else
			{
				/*присоединяем правого потомка разбиваемого узла к новому правому потомку*/
				newRightChild.connectChild(LEFT_CHILD, getNewNode(brokenBNode.getChild(RIGHT_CHILD).getItem(LEFT_ITEM)));

				/*очищаем правого потомка разбиваемого узла и вновь заполняем наибольшим элементом данных*/
				Item tempItem = brokenBNode.getChild(RIGHT_CHILD).removeItem();
				brokenBNode.getChild(RIGHT_CHILD).removeItem();
				brokenBNode.getChild(RIGHT_CHILD).insertItem(tempItem);

				/*присоединяем правого потомка разбиваемого узла к новому правому потомку*/
				newRightChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(RIGHT_CHILD));
			}
		}
		/*источник разбиения средний потомок*/
		else if (highOrderNode.getItem(CENTRAL_ITEM).getKey() == newItem.getKey())
		{
			/*к новому левому потомку в качестве дочернего узла присоединяем левого потомка разбиваемого узла*/
			newLeftChild.connectChild(LEFT_CHILD, brokenBNode.disconnectChild(LEFT_CHILD));

			/*если дочерний узел не содержит элементов данных, значит он подвергался разбиению, необходимые узлы лежат на уровень ниже*/
			if (brokenBNode.getChild(CENTRAL_CHILD).getItem(LEFT_ITEM) == null)
			{
				/*к новому правому потомку в качестве дочернего узла присоединяем правого потомка, правого потомка разбиваемого узла*/
				newRightChild.connectChild(LEFT_CHILD, brokenBNode.getChild(CENTRAL_CHILD).disconnectChild(CENTRAL_CHILD));

				/*к новому левому потомку в качестве дочернего узла присоединяем левого потомка, правого потомка разбиваемого узла*/
				newLeftChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(CENTRAL_CHILD).disconnectChild(LEFT_CHILD));
			}
			/*дочерний узел не подвергался разбиению*/
			else
			{
				/*к новому левому потомку в качестве дочернего узла присоединяем правого потомка разбиваемого узла*/
				newLeftChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(CENTRAL_CHILD));

				/*присоединяем левого потомка к новому правому потомку, элемент данных забираем из правого потомка нового левого потомка*/
				newRightChild.connectChild(LEFT_CHILD, getNewNode(newLeftChild.getChild(CENTRAL_CHILD).removeItem()));
			}

			/*присоединяем правого потомка разбиваемого узла к новому правому потомку*/
			newRightChild.connectChild(CENTRAL_CHILD, brokenBNode.disconnectChild(RIGHT_CHILD));
		}

		return new Pair<>(newLeftChild, newRightChild);
	}

	/**
	 * Выполнить разбиение B узла B-дерева
	 *
	 * @param brokenBNode разбиваемый узел(предполагается, что разбиваемый узел полон)
	 */
	private void splitBNode(BNode brokenBNode)
	{
		BNode parent;
		int itemIndex;

		Item itemC = brokenBNode.removeItem();
		Item itemB = brokenBNode.removeItem();

		BNode child2 = brokenBNode.disconnectChild(getTreeOrder().getOrder() - 2);
		BNode child3 = brokenBNode.disconnectChild(getTreeOrder().getOrder() - 1);
		BNode newRight = new BNode(getTreeOrder());

		/*узел является корнем, создаем новый корень, делаем его родителем, связываем с родителем*/
		if (brokenBNode == root)
		{
			root = getNewNode();
			parent = root;
			root.connectChild(LEFT_CHILD, brokenBNode);
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
		newRight.connectChild(LEFT_CHILD, child2);
		newRight.connectChild(CENTRAL_CHILD, child3);
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
