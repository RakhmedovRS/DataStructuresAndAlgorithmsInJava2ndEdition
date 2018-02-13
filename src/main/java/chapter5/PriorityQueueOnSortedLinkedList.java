package chapter5;

import base.Queue;

/**
 * Программный проект 5.1 - Program project 5.1
 *
 * Класс имплементирующий приоритетную очередь на базе сортированного односвязного списка
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
class PriorityQueueOnSortedLinkedList implements Queue
{
	private SortedLinkedList list = new SortedLinkedList();
	private int counter = 0;

	@Override
	public void insert(long value)
	{
		list.insert(counter++, value);
	}

	@Override
	public long remove()
	{
		return (long) list.deleteFirst().getData();
	}

	@Override
	public long peekFront()
	{
		return (long) list.getFirst().getData();
	}

	@Override
	public boolean isEmpty()
	{
		return list.isEmpty();
	}

	@Override
	public boolean isFull()
	{
		return false;
	}

	public void print()
	{
		if (isEmpty())
		{
			System.out.println("PriorityQueueOnSortedLinkedList is empty");
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
