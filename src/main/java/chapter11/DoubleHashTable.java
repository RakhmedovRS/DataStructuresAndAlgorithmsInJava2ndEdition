package chapter11;

import base.HashTable;
import base.Item;

/**
 * Сущность хэш-таблицы с двойным хешированием
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class DoubleHashTable implements HashTable<Item>
{
	private Item[] hashArray;
	private Integer arraySize;
	private Item deletedItem;

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public DoubleHashTable(int size)
	{
		this.arraySize = size;
		hashArray = new Item[arraySize];
		deletedItem = new DataItem(-1);
	}

	@Override
	public int hashFunction(int key)
	{
		return key % arraySize;
	}

	/**
	 * Вторая хэш-функция
	 * должна обладать следующими характеристиками:
	 * 1. не должна совпадать с первичной хэш-функцией {@link #hashFunction(int)}
	 * 2. резуальтат никогда не должен быть равен 0
	 *
	 * @param key ключ
	 * @return рассчитанное значение хэша
	 */
	public int hashFunction2(int key)
	{
		return 5 - key % 5;
	}

	@Override
	public void insert(Item item)
	{
		int hashValue = hashFunction(item.getKey());
		int stepSize = hashFunction2(item.getKey());

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != deletedItem.getKey())
		{
			/*смещение*/
			hashValue += stepSize;
			/*возврат к началу*/
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
	}

	@Override
	public Item delete(int key)
	{
		int hashValue = hashFunction(key);
		int stepSize = hashFunction2(key);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == key)
			{
				Item item = hashArray[hashValue];
				hashArray[hashValue] = deletedItem;
				return item;
			}
			/*смещение*/
			hashValue += stepSize;
			/*возврат к началу*/
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public Item find(int key)
	{
		int hashValue = hashFunction(key);
		int stepSize = hashFunction2(key);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == key)
			{
				return hashArray[hashValue];
			}
			/*смещение*/
			hashValue += stepSize;
			/*возврат к началу*/
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

