package chapter7;

/**
 * Класс массива реализующий сортировку Шелла
 *
 * @author rassoll
 * @created 04.12.2017
 * @$Author$
 * @$Revision$
 */
class ArraySh
{
	private long[] theArray;
	private int nElements;

	ArraySh(int max)
	{
		theArray = new long[max];
		nElements = 0;
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
	 * Сортировка массива методом Шелла
	 */
	void shellSort()
	{
		int inner, outer;
		long temp;
		int h = 1;

		//вычисление интервальной последовательности
		while (h <= nElements / 3)
		{
			h = h * 3 + 1;
		}

		while (h > 0)
		{
			for (outer = h; outer < nElements; outer++)
			{
				temp = theArray[outer];
				inner = outer;

				while (inner > h - 1 && theArray[inner - h] >= temp)
				{
					theArray[inner] = theArray[inner - h];
					inner -= h;
				}

				theArray[inner] = temp;
			}

			//уменьшение интервальной последовательности
			h = (h - 1) / 3;

			//Упражнение 7.2 - Exercise 7.2
			//System.out.println(display());
		}
	}
}