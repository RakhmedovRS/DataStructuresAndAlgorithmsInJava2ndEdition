package chapter11;

import base.structures.HashTable;

/**
 * Программный проект 11.2 - Program project 11.2
 * Сущность хэш-таблицы с работающей со строками по алгоритму линейного пробирования
 * ограничение на состав букв в слове [a-z]
 *
 * @author rassoll
 * @created 14.02.2018
 * @$Author$
 * @$Revision$
 */
public class StringHashTable implements HashTable<String>
{
	private static final String ERROR_MESSAGE = "Переданное значение не соотвествует шаблону [a-z]";

	private String[] hashArray;
	private int arraySize;
	private String deletedItem;
	private int elementsNumber;

	/**
	 * Конструктор
	 *
	 * @param size размер хэш-таблицы
	 */
	public StringHashTable(int size)
	{
		arraySize = size;
		hashArray = new String[arraySize];
		deletedItem = "-1";
		elementsNumber = 0;
	}

	/**
	 * Метод проверяющий соотвествие символов в передаваемом значении шаблону [a-z]
	 *
	 * @param value проверяемое значение
	 * @return
	 */
	public static boolean isMatchLettersInString(String value)
	{
		return value.matches("^[a-z]+$");
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

	/**
	 * Функция хеширования строки по методу Горнера
	 *
	 * @param item элемент данных
	 * @return рассчитанное значение хэша
	 */
	@Override
	public int hashFunction(String item)
	{
		int hashValue = 0;
		for (int i = 0; i < item.length(); i++)
		{
			/*вычитается значение маленькой латинской буквы "а" в таблице ASCII*/
			int letter = item.charAt(i) - 'a';
			hashValue = (hashValue * 27 + letter) % arraySize;
		}

		return hashValue;
	}

	@Override
	public void insert(String item)
	{
		if (!isMatchLettersInString(item))
		{
			throw new IllegalArgumentException(ERROR_MESSAGE);
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
	public String delete(String item)
	{
		if (!isMatchLettersInString(item))
		{
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}

		int hashValue = hashFunction(item);

		while (hashArray[hashValue] != null)
		{
			if (hashArray[hashValue].equals(item))
			{
				String value = hashArray[hashValue];
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
	public String find(String item)
	{
		if (!isMatchLettersInString(item))
		{
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}

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
	public String getDisplayData()
	{
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(String.format("%s: ", this.getClass().getSimpleName()));

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
