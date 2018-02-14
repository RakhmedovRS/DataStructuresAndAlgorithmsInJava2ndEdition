package chapter11;

import base.structures.HashTable;
import base.items.LinkItem;
import chapter5.SortedLinkedList;

/**
 * Хэш-таблица использующая метод цепочек
 *
 * @author rassoll
 * @created 13.02.2018
 * @$Author$
 * @$Revision$
 */
public class HashChain implements HashTable<LinkItem>
{
	private SortedLinkedList[] hashArray;
	private int arraySize;

	/**
	 * Конструкторк
	 *
	 * @param size размер хэш-таблицы
	 */
	public HashChain(int size)
	{
		this.arraySize = size;
		hashArray = new SortedLinkedList[arraySize];

		for (int i = 0; i < arraySize; i++)
		{
			hashArray[i] = new SortedLinkedList();
		}
	}

	@Override
	public int hashFunction(LinkItem item)
	{
		return item.getKey() % arraySize;
	}

	@Override
	public void insert(LinkItem item)
	{
		int hashValue = hashFunction(item);
		hashArray[hashValue].insert(item);
	}

	@Override
	public LinkItem delete(LinkItem item)
	{
		int hashValue = hashFunction(item);
		return hashArray[hashValue].delete(item);
	}

	@Override
	public LinkItem find(LinkItem item)
	{
		int hashValue = hashFunction(item);
		return hashArray[hashValue].find(item);
	}

	@Override
	public String getDisplayData()
	{
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < arraySize; i++)
		{
			stringBuilder.append(String.format("%s. ", i));
			stringBuilder.append(hashArray[i].getDisplayData());
			stringBuilder.append("\n");
		}

		return stringBuilder.toString();
	}
}
