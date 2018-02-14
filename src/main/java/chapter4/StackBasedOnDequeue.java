package chapter4;

import base.structures.Queue;

/**
 * Программный проект 4.3 - Program project 4.3
 *
 * Класс имплементирующий стэк на базе ДЕКа
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
class StackBasedOnDequeue implements Queue
{
	private DequeueImpl dequeue;

	StackBasedOnDequeue(int maxSize)
	{
		dequeue = new DequeueImpl(maxSize);
	}

	@Override
	public void insert(long value)
	{
		dequeue.insertRight(value);
	}

	@Override
	public long remove()
	{
		return dequeue.removeRight();
	}

	@Override
	public long peekFront()
	{
		return dequeue.peekRight();
	}

	@Override
	public boolean isEmpty()
	{
		return dequeue.isEmpty();
	}

	@Override
	public boolean isFull()
	{
		return dequeue.isFull();
	}
}
