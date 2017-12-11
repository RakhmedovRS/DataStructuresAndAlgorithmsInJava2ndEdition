package chapter7;

/**
 * Класс массива реализующий быструю сортировку и опредение медианы по трем точкам
 *
 * @author rassoll
 * @created 04.12.2017
 * @$Author$
 * @$Revision$
 */
class ArrayQk
{
	private long[] theArray;
	private int nElements;
	private int compares;
	private int swaps;

	ArrayQk(int arrayQkSize)
	{
		theArray = new long[arrayQkSize];
		nElements = 0;
		compares = 0;
		swaps = 0;
	}

	/**
	 * Вставка элемента в массив
	 *
	 * @param value вставлемое значение
	 * @throws IndexOutOfBoundsException в случае превышения максимально возможного количества элементов
	 */
	public void insert(long value) throws IndexOutOfBoundsException
	{
		theArray[nElements++] = value;
	}

	/**
	 * Вывод содержимого массива
	 *
	 * @return содержимое
	 */
	public String display()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("A = ");
		for (int i = 0; i < nElements; i++)
		{
			builder.append(theArray[i]);
			builder.append(" ");
		}

		return builder.toString();
	}

	/**
	 * @return количество произведенных сравнений
	 */
	public int getCompares()
	{
		return compares;
	}

	/**
	 * @return количество произведенных перестановок
	 */
	public int getSwaps()
	{
		return swaps;
	}

	/**
	 * Быстрая сортировка массива {@link ArrayQk}
	 */
	void quickSort()
	{
		reqQuickSort(0, nElements - 1);
	}

	/**
	 * Быстрая сортировка подмассива {@link ArrayQk} путем рекурсивных вызовов
	 *
	 * @param left  левая граница массива
	 * @param right правая граница массива
	 */
	void reqQuickSort(int left, int right)
	{
		int size = right - left + 1;

		compares++;
		if (size < 10)
		{
			insertionSort(left, right);
		}
		else
		{
			long median = medianOf3(left, right);
			int partition = partitionInt(left, right, median);
			reqQuickSort(left, partition - 1);
			reqQuickSort(partition + 1, right);
		}
	}

	/**
	 * Опредение медианы по трем точкам
	 *
	 * @param left  левая граница массива
	 * @param right правая граница массива
	 * @return медиана массива
	 */
	long medianOf3(int left, int right)
	{
		int center = (left + right) / 2;

		// упорядочение left и center
		compares++;
		if (theArray[left] > theArray[center])
		{
			swap(left, center);
		}

		// упорядочение left и right
		compares++;
		if (theArray[left] > theArray[right])
		{
			swap(left, right);
		}

		// упорядочение center и right
		compares++;
		if (theArray[center] > theArray[right])
		{
			swap(center, right);
		}

		//размещение медианы по правому краю
		swap(center, right - 1);

		return theArray[right - 1];
	}

	/**
	 * Разбиение диапазона
	 *
	 * @param left   левая граница массива
	 * @param right  правая граница массива
	 * @param median медиана массива
	 * @return опорный элемент
	 */
	int partitionInt(int left, int right, long median)
	{
		int leftPartition = left;
		int rightPartition = right - 1;

		while (true)
		{
			//поиск большего элемента
			compares++;
			while (theArray[++leftPartition] < median)
			{
			}

			//поиск меньшего элемента
			compares++;
			while (theArray[--rightPartition] > median)
			{
			}

			//Указатели сошлись, разбиение закончено
			compares++;
			if (leftPartition >= rightPartition)
			{
				break;
			}
			else
			{
				swap(leftPartition, rightPartition);
			}
		}

		//восстановелние опорного элемента
		swap(leftPartition, right - 1);

		return leftPartition;
	}

	/**
	 * Сотрировка методом вставки
	 *
	 * @param left  левая граница массива
	 * @param right правая граница массива
	 */
	void insertionSort(int left, int right)
	{
		int inner, outer;
		for (outer = left + 1; outer <= right; outer++)
		{
			long temp = theArray[outer];
			inner = outer;

			compares += 2;
			while (inner > left && theArray[inner - 1] >= temp)
			{
				theArray[inner] = theArray[inner - 1];
				--inner;
			}

			theArray[inner] = temp;
		}
	}

	/**
	 * Перестановка двух элементов
	 *
	 * @param firstValue  первый переставляемый элемент
	 * @param secondValue второй переставляемый элемент
	 */
	void swap(int firstValue, int secondValue)
	{
		swaps += 3;

		theArray[firstValue] = theArray[firstValue] + theArray[secondValue];
		theArray[secondValue] = theArray[firstValue] - theArray[secondValue];
		theArray[firstValue] = theArray[firstValue] - theArray[secondValue];
	}
}
