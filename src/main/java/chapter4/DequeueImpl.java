package chapter4;

import base.structures.Dequeue;

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
	private int rightSide;
	private int nElements;

	DequeueImpl(int maxSize)
	{
		this.maxSize = maxSize;
		deqArray = new long[this.maxSize];
		leftSide = 0;
		rightSide = -1;
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
			if (rightSide == maxSize - 1)
			{
				rightSide = -1;
			}

			//Увеличение leftSide и вставка
			deqArray[++rightSide] = value;
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
		long temp = deqArray[rightSide--];

		//Циклический перенос
		if (rightSide == -1)
		{
			rightSide = maxSize - 1;
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
		return deqArray[rightSide];
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
