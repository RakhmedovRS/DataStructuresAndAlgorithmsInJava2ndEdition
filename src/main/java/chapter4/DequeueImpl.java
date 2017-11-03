package chapter4;

import base.Dequeue;

/**
 * Программный проект 4.2 - Program project 4.2
 *
 * Класс имплементирующий ДЕК
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
class DequeueImpl implements Dequeue
{
	private int maxSize;
	private long[] deqArray;
	private int leftSide;
	private int rigthSide;
	private int nElements;

	DequeueImpl(int maxSize)
	{
		this.maxSize = maxSize;
		deqArray = new long[this.maxSize];
		leftSide = 0;
		rigthSide = -1;
		nElements = 0;
	}

	@Override
	public void insertLeft(long value)
	{
		if (!isFull())
		{
			//Циклический перенос
			if (leftSide == 0)
			{
				leftSide = maxSize;
			}

			//Увеличение leftSide и вставка
			deqArray[--leftSide] = value;
			nElements++;
		}
		else
		{
			throw new UnsupportedOperationException(String.format("You can't insert %s dequeue is full", value));
		}
	}

	@Override
	public void insertRight(long value)
	{
		if (!isFull())
		{
			//Циклический перенос
			if (rigthSide == maxSize - 1)
			{
				rigthSide = -1;
			}

			//Увеличение leftSide и вставка
			deqArray[++rigthSide] = value;
			nElements++;
		}
		else
		{
			throw new UnsupportedOperationException(String.format("You can't insert %s dequeue is full", value));
		}
	}

	@Override
	public long removeLeft()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("You can't remove left element, dequeue is empty");
		}

		//Выборка и увеличение leftSide
		long temp = deqArray[leftSide++];

		//Циклический перенос
		if (leftSide == maxSize)
		{
			leftSide = 0;
		}

		nElements--;
		return temp;
	}

	@Override
	public long removeRight()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("You can't remove right element, dequeue is empty");
		}

		//Выборка и увеличение leftSide
		long temp = deqArray[rigthSide--];

		//Циклический перенос
		if (rigthSide == -1)
		{
			rigthSide = maxSize - 1;
		}

		nElements--;
		return temp;
	}

	@Override
	public long peekLeft()
	{
		return deqArray[leftSide];
	}

	@Override
	public long peekRight()
	{
		return deqArray[rigthSide];
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
