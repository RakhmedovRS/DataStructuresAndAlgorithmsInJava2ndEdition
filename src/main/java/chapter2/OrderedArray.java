package chapter2;

/**
 * Класс упорядоченного массива с высокоуровневым интерфейсом
 *
 * @author rassoll
 * @created 02.10.2017
 * @$Author$
 * @$Revision$
 */
class OrderedArray
{
	private long[] a;
	private int nElements;

	OrderedArray(int nElements)
	{
		a = new long[nElements];
		this.nElements = 0;
	}

	/**
	 * Программный проект 2.4 - Program project 2.4
	 *
	 * Поиск позиции элемента searchKey в массиве
	 *
	 * @param searchKey значение для поиска
	 * @return позиция найденного элемента или размер массива в случае если элемент не найден
	 */
	public int find(long searchKey)
	{
		int lowerBound = 0;
		int upperBound = nElements - 1;
		int curIn;

		while (true)
		{
			curIn = (lowerBound + upperBound) / 2;
			if (a[curIn] == searchKey)
			{
				return curIn;
			}
			else if (lowerBound > upperBound)
			{
				return nElements;
			}
			else
			{
				if (a[curIn] < searchKey)
				{
					lowerBound = curIn + 1;
				}
				else
				{
					upperBound = curIn - 1;
				}
			}
		}
	}

	/**
	 * Программный проект 2.4 - Program project 2.4
	 *
	 * Вставка элемента в массив
	 *
	 * @param value вставляемое значение
	 */
	public void insert(long value)
	{
		int lowerBound = 0;
		int upperBound = nElements - 1;
		int curIn;

		while (true)
		{
			curIn = (lowerBound + upperBound) / 2;
			if (a[curIn] == value)
			{
				break;
			}
			else if (lowerBound > upperBound)
			{
				if (value >= a[curIn])
				{
					if (a[curIn] == 0 && nElements == 0)
					{
						curIn = 0;
					}
					else
					{
						curIn++;
					}
				}
				break;
			}
			else
			{
				if (a[curIn] < value)
				{
					lowerBound = curIn + 1;
				}
				else
				{
					upperBound = curIn - 1;
				}
			}
		}

		for (int j = nElements; j > curIn; j--)
		{
			a[j] = a[j - 1];
		}

		a[curIn] = value;
		nElements++;
	}

	/**
	 * Программный проект 2.4 - Program project 2.4
	 *
	 * Удаление элемента из массива
	 *
	 * @param value удаляемое значение
	 * @return признак произведенного удаления
	 */
	public boolean delete(long value)
	{
		int i = find(value);
		if (i == nElements)
		{
			return false;
		}
		else
		{
			for (int k = i; k < nElements - 1; k++)
			{
				a[k] = a[k + 1];
			}
			nElements--;
			return true;
		}
	}

	/**
	 * Полученение размера массиваъ
	 *
	 * @return количество элементов в массиве
	 */
	int getSize()
	{
		return nElements;
	}

	/**
	 * Вывод содержимого массива
	 */
	void display()
	{
		for (int i = 0; i < nElements; i++)
		{
			System.out.print(String.format("%s ", a[i]));
		}
		System.out.println();
	}

	/**
	 * Программный проект 2.5 - Program project 2.5
	 *
	 * Слияние с текущим массивом
	 *
	 * @param inputArray массив для выполнения слияния с текущим
	 */
	void merge(long[] inputArray)
	{
		for (long value : inputArray)
		{
			if (find(value) == nElements)
			{
				insert(value);
			}
		}
	}

	/**
	 * Дополнительный метод для тестирования
	 *
	 * Получить содержимое массива
	 *
	 * @return содерждимое массива
	 */
	String getValues()
	{
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < nElements; i++)
		{
			builder.append(a[i]);
			if (i != nElements - 1)
			{
				builder.append(", ");
			}
		}

		return builder.toString();
	}
}
