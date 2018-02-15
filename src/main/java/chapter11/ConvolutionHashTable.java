package chapter11;

import base.structures.HashTable;

/**
 * Программный проект 11.3 - Program project 11.3
 * Сущность хэш-таблицы реализующей метод свертки по алгоритму линейного пробирования
 *
 * @author rassoll
 * @created 15.02.2018
 * @$Author$
 * @$Revision$
 */
public class ConvolutionHashTable implements HashTable<Integer>
{
	private static final int TEN = 10;
	private Integer[] hashArray;
	private int arraySize;
	private Integer deletedItem;
	private int groupSize;
	private int elementsNumber;

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public ConvolutionHashTable(int size)
	{
		arraySize = size;
		hashArray = new Integer[arraySize];
		deletedItem = -1;
		groupSize = calcGroupSize(arraySize);
		elementsNumber = 0;
	}

	/**
	 * Вычислить длинну группы цифр в зависимости от размера массива
	 *
	 * @param arraySize размера массива
	 * @return размер группы цифр
	 */
	public static int calcGroupSize(int arraySize)
	{
		int tempValue = arraySize;
		for (int i = 1; true; i++)
		{
			tempValue /= TEN;
			if (tempValue < TEN)
			{
				return i;
			}
		}
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
	public int hashFunction(Integer item)
	{
		int hashValue = 0;

		char[] itemDigits = item.toString().toCharArray();
		if (itemDigits.length <= groupSize)
		{
			hashValue = item;
		}
		else
		{
			int tempValue = 0;
			for (int i = 0; i < itemDigits.length; i++)
			{
				tempValue += Integer.parseInt(String.valueOf(itemDigits[i]));

				if (i != 0 && (i + 1) % groupSize == 0
					|| (itemDigits.length % groupSize != 0 && i + 1 == itemDigits.length))
				{
					hashValue += tempValue;
					tempValue = 0;
				}
				else
				{
					tempValue *= TEN;
				}
			}
		}

		return hashValue % arraySize;
	}

	@Override
	public void insert(Integer item)
	{
		if (getLoadFactor() > MAX_LOAD_FACTOR)
		{
			rehash();
		}

		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null && !hashArray[hashValue].equals(deletedItem))
		{
			++hashValue;
			hashValue %= arraySize;
		}

		hashArray[hashValue] = item;
		elementsNumber++;
	}

	@Override
	public Integer delete(Integer item)
	{
		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].equals(item))
			{
				Integer value = hashArray[hashValue];
				hashArray[hashValue] = deletedItem;
				elementsNumber--;
				return value;
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public Integer find(Integer item)
	{

		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].equals(item))
			{
				return hashArray[hashValue];
			}
			++hashValue;
			hashValue %= arraySize;
		}
		return null;
	}

	@Override
	public void rehash()
	{
		arraySize = HashTable.getPrime(arraySize * 2);
		groupSize = calcGroupSize(arraySize);
		elementsNumber = 0;
		Integer[] tempArray = hashArray.clone();
		hashArray = new Integer[arraySize];

		for (int i = 0; i < tempArray.length; i++)
		{
			if (tempArray[i] != null && !tempArray[i].equals(deletedItem))
			{
				insert(tempArray[i]);
			}
		}
	}

	@Override
	public Integer[] getHashArray()
	{
		return hashArray.clone();
	}

	@Override
	public String getDisplayData()
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(this.getClass().getSimpleName());
		stringBuilder.append(String.format("%nHash table size: %s", getHashTableSize()));
		stringBuilder.append(String.format("%nElements number: %s", getElementsNumber()));
		stringBuilder.append(String.format("%nLoad factor: %s", getLoadFactor()));
		stringBuilder.append("\n");

		for (int i = 0; i < arraySize; i++)
		{
			if (hashArray[i] != null)
			{
				stringBuilder.append(String.format("%s ", hashArray[i]));
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
