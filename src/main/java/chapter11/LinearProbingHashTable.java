package chapter11;

import base.structures.HashTable;
import base.items.Item;

/**
 * Сущность хэш-таблицы с линейным пробированием
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class LinearProbingHashTable implements HashTable<Item>
{
	protected Item[] hashArray;
	protected int arraySize;
	protected Item deletedItem;

	/**
	 * Конструктор для классов наследников
	 */
	protected LinearProbingHashTable()
	{}

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public LinearProbingHashTable(int size)
	{
		arraySize = size;
		hashArray = new DataItem[arraySize];
		deletedItem = new DataItem(-1);
	}

	@Override
	public int hashFunction(Item item)
	{
		return item.getKey() % arraySize;
	}

	@Override
	public void insert(Item item)
	{
		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1)
		{
			++hashValue;
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
	}

	@Override
	public Item delete(Item item)
	{
		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == item.getKey())
			{
				Item dataItem = hashArray[hashValue];
				hashArray[hashValue] = deletedItem;
				return dataItem;
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public Item find(Item item)
	{
		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == item.getKey())
			{
				return hashArray[hashValue];
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public String getDisplayData()
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format("%s: ", this.getClass().getSimpleName()));

		for (int i = 0; i < arraySize; i++)
		{
			if (hashArray[i] != null)
			{
				stringBuilder.append(String.format("%s ", hashArray[i].getKey()));
			}
			else
			{
				stringBuilder.append("** ");
			}
		}
		stringBuilder.append("");

		return stringBuilder.toString();
	}
}
