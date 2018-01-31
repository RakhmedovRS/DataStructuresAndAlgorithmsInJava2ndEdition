package chapter10;

/**
 * Узел B дерева
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
class BNode
{
	private final int order;
	private int numItems;
	private BNode parent;
	private DataItem[] itemArray;
	private BNode[] childArray;


	/**
	 * ctor
	 *
	 * @param order порядок B дерева
	 */
	BNode(BOrder order)
	{
		this.order = order.getBOrder();
		itemArray = new DataItem[this.order - 1];
		childArray = new BNode[this.order];

	}

	/**
	 * @return родительский узел
	 */
	BNode getParent()
	{
		return parent;
	}

	/**
	 * Получить элемент данных
	 *
	 * @param index индекс элемента данных
	 * @return элемент данных
	 */
	DataItem getItem(int index)
	{
		if ((index < 0) || (index >= itemArray.length))
		{
			return null;
		}
		else
		{
			return itemArray[index];
		}
	}

	/**
	 * Связать узел с потомком
	 *
	 * @param childNum позиция потомка
	 * @param child    узел потомок
	 */
	void connectChild(int childNum, BNode child)
	{
		childArray[childNum] = child;
		if (child != null)
		{
			child.parent = this;
		}
	}

	/**
	 * Отсоединить потомка от узла
	 *
	 * @param childNum позиция потомка
	 * @return отсоединенный потомок
	 */
	BNode disconnectChild(int childNum)
	{
		BNode tempNode = childArray[childNum];
		childArray[childNum] = null;
		return tempNode;
	}

	/**
	 * Получить узел потомок
	 *
	 * @param childNum номер узла
	 * @return узел потомок
	 */
	BNode getChild(int childNum)
	{
		return childArray[childNum];
	}

	/**
	 * @return признак листового узла
	 */
	boolean isLeaf()
	{
		return childArray[0] == null;
	}

	/**
	 * @return количество элементов данных
	 */
	int getNumItems()
	{
		return numItems;
	}

	/**
	 * @return признак заполненности узла
	 */
	boolean isFull()
	{
		return numItems == order - 1;
	}

	/**
	 * Определить индекс элемента в пределах узла
	 *
	 * @param key ключ элемента
	 * @return индекс элемента
	 */
	int findItem(long key)
	{
		for (int i = 0; i < order - 1; i++)
		{
			if (itemArray[i] == null)
			{
				break;
			}
			else if (itemArray[i].getDData() == key)
			{
				return i;
			}
		}
		return -1;
	}

	/**
	 * Вставить элемент данных
	 * предполагается, что узел не пуст
	 *
	 * @param newItem элемент данных
	 * @return индекс вставленного элемента данных
	 */
	int insertItem(DataItem newItem)
	{
		numItems++;
		long newKey = newItem.getDData();
		for (int i = order - 2; i >= 0; i--)
		{
			if (itemArray[i] == null)
			{
				continue;
			}
			else
			{
				long itsKey = itemArray[i].getDData();
				if (newKey < itsKey)
				{
					itemArray[i + 1] = itemArray[i];
				}
				else
				{
					itemArray[i + 1] = newItem;
					return i + 1;
				}
			}
		}
		itemArray[0] = newItem;
		return 0;
	}

	/**
	 * Удалить наибольший элемент данных
	 * предполагается, что узел не пуст
	 *
	 * @return удаленный элемент данных
	 */
	DataItem removeItem()
	{
		if (numItems == 0)
		{
			return null;
		}

		DataItem temp = itemArray[numItems - 1];
		itemArray[numItems-1] = null;
		numItems--;
		return temp;
	}

	/**
	 * @return данные для печати узла
	 */
	String getDisplayData()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numItems; i++)
		{
			builder.append(itemArray[i].getDisplayData());
		}
		builder.append("/");

		return builder.toString();
	}
}
