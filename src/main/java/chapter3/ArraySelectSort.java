package chapter3;

import base.structures.Array;

import java.util.Calendar;

/**
 * Класс массива с высокоуровневым интерфейсом описывающий алгоритм сортировки выбором
 *
 * @author rassoll
 * @created 03.10.2017
 * @$Author$
 * @$Revision$
 */
class ArraySelectSort implements Array
{
	private long[] a;
	private int nElements;

	ArraySelectSort(int nElements)
	{
		a = new long[nElements];
	}

	@Override
	public void insert(long value)
	{
		a[nElements] = value;
		nElements++;
	}

	@Override
	public void display()
	{
		for (int i = 0; i < nElements; i++)
		{
			System.out.print(String.format("%s ", a[i]));
		}
		System.out.println();
	}

	@Override
	public void sort()
	{
		Long startTime = Calendar.getInstance().getTime().getTime();
		int outer, inner, minimal;
		for (outer = 0; outer < nElements - 1; outer++)
		{
			minimal = outer;
			for (inner = outer + 1; inner < nElements; inner++)
			{
				if (a[inner] < a[minimal])
				{
					minimal = inner;
				}
			}

			long temp = a[minimal];
			a[minimal] = a[outer];
			a[outer] = temp;
		}

		Long endTime = Calendar.getInstance().getTime().getTime();
		System.out.println(String.format("Selection sorting %s items took %s seconds", nElements, (endTime - startTime) / 1000));
	}

	@Override
	public String getValues()
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
