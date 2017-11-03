package chapter4;

import base.Queue;

/**
 * Программный проект 4.4 - Program project 4.4
 *
 * Класс имплементирующий приоритетную очередь
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
class PriorityQueue implements Queue
{
	private int maxSize;
	private long[] queArray;
	private int nElements;

	PriorityQueue(int maxSize)
	{
		this.maxSize = maxSize;
		queArray = new long[this.maxSize];
		nElements = 0;
	}

	@Override
	public void insert(long value)
	{
		if (isFull())
		{
			throw new UnsupportedOperationException("Priority Queue is full");
		}

		queArray[nElements++] = value;
	}

	@Override
	public long remove()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("You can't remove element Priority Queue is empty");
		}

		long retValue = peekFront();
		int i;

		for (i = 0; i < nElements; i++)
		{
			if (retValue == queArray[i])
			{
				break;
			}
		}

		for (int k = i; k < nElements; k++)
		{
			queArray[k] = queArray[k + 1];
		}

		nElements--;
		return retValue;
	}

	@Override
	public long peekFront()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("You can't get priority element Priority Queue is empty");
		}

		long retValue = 0;
		int i;

		for (i = 0; i < nElements; i++)
		{
			if (i != 0)
			{
				if (retValue > queArray[i])
				{
					retValue = queArray[i];
				}
			}
			else
			{
				retValue = queArray[i];
			}
		}

		return retValue;
	}

	@Override
	public boolean isEmpty()
	{
		return (nElements == 0);
	}

	@Override
	public boolean isFull()
	{
		return (nElements == maxSize);
	}
}
