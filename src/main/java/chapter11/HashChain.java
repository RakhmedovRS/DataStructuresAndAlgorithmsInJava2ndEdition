package chapter11;

import base.HashTable;
import base.LinkItem;
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
	public int hashFunction(int key)
	{
		return key % arraySize;
	}

	@Override
	public void insert(LinkItem item)
	{
		int key = item.getKey();
		int hashValue = hashFunction(key);
		hashArray[hashValue].insert(item);
	}

	@Override
	public LinkItem delete(int key)
	{
		int hashValue = hashFunction(key);
		return hashArray[hashValue].delete(key);
	}

	@Override
	public LinkItem find(int key)
	{
		int hashValue = hashFunction(key);
		return hashArray[hashValue].find(key);
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
