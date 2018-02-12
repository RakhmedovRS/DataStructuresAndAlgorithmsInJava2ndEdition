package chapter11;

/**
 * Сущность хэш-таблицы с линейным пробированием
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class HashTable implements base.HashTable<DataItem>
{
	private DataItem[] hashArray;
	private int arraySize;
	private DataItem deletedItem;

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public HashTable(int size)
	{
		arraySize = size;
		hashArray = new DataItem[arraySize];
		deletedItem = new DataItem(-1);
	}

	@Override
	public int hashFunction(int key)
	{
		return key % arraySize;
	}

	@Override
	public void insert(DataItem item)
	{
		int hashValue = hashFunction(item.getKey());

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != -1)
		{
			++hashValue;
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
	}

	@Override
	public DataItem delete(int key)
	{
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == key)
			{
				DataItem dataItem = hashArray[hashValue];
				hashArray[hashValue] = deletedItem;
				return dataItem;
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public DataItem find(int key)
	{
		int hashValue = hashFunction(key);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == key)
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
