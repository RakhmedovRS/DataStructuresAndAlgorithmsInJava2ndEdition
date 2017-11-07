package chapter4;

import base.Queue;

/**
 * Класс имплементирующий очередь
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
class QueueImpl implements Queue
{
	private int maxSize;
	private long[] queArray;
	private int front;
	private int rear;
	private int nElements;

	QueueImpl(int maxSize)
	{
		this.maxSize = maxSize;
		queArray = new long[this.maxSize];
		front = 0;
		rear = -1;
		nElements = 0;
	}

	@Override
	public void insert(long value)
	{
		if (isFull())
		{
			throw new UnsupportedOperationException("QueueImpl is full");
		}

		//Циклический перенос
		if (rear == maxSize - 1)
		{
			rear = -1;
		}

		//Увеличение rear и вставка
		queArray[++rear] = value;
		nElements++;
	}

	@Override
	public long remove()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("QueueImpl is empty");
		}

		//Выборка и увеличение front
		long temp = queArray[front++];

		//Циклический перенос
		if (front == maxSize)
		{
			front = 0;
		}

		nElements--;
		return temp;
	}

	@Override
	public long peekFront()
	{
		return queArray[front];
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

	int size()
	{
		return nElements;
	}

	/**
	 * Программный проект 4.1 - Program project 4.1
	 *
	 * Вывести содержимое очереди
	 */
	void print()
	{
		if (isEmpty())
		{
			System.out.println("QueueImpl is empty");
		}
		else
		{
			while (!isEmpty())
			{
				long n = remove();
				System.out.print(n + " ");
			}
			System.out.println();
		}
	}
}
