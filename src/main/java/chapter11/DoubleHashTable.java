package chapter11;

import base.structures.HashTable;
import base.items.Item;

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
	private int elementsNumber;

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
		elementsNumber = 0;
	}

	@Override
	public int getHashTableSize()
	{
		return arraySize;
	}

	@Override
	public int getElementsNumber()
	{
		return elementsNumber;
	}

	@Override
	public int hashFunction(Item item)
	{
		return item.getKey() % arraySize;
	}

	/**
	 * Вторая хэш-функция
	 * должна обладать следующими характеристиками:
	 * 1. не должна совпадать с первичной хэш-функцией {@link #hashFunction(Item)}
	 * 2. резуальтат никогда не должен быть равен 0
	 *
	 * @param item элемент данных
	 * @return рассчитанное значение хэша
	 */
	public int hashFunction2(Item item)
	{
		return 5 - item.getKey() % 5;
	}

	@Override
	public void insert(Item item)
	{
		int hashValue = hashFunction(item);
		int stepSize = hashFunction2(item);

		while (hashArray[hashValue] != null && hashArray[hashValue].getKey() != deletedItem.getKey())
		{
			/*смещение*/
			hashValue += stepSize;
			/*возврат к началу*/
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
		elementsNumber++;
	}

	@Override
	public Item delete(Item item)
	{
		int hashValue = hashFunction(item);
		int stepSize = hashFunction2(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == item.getKey())
			{
				Item newItem = hashArray[hashValue];
				hashArray[hashValue] = deletedItem;
				elementsNumber--;
				return newItem;
			}
			/*смещение*/
			hashValue += stepSize;
			/*возврат к началу*/
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public Item find(Item item)
	{
		int hashValue = hashFunction(item);
		int stepSize = hashFunction2(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].getKey() == item.getKey())
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

