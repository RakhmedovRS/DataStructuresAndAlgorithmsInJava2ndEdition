package chapter5;

import base.Dequeue;

/**
 * Программный проект 5.2 - Program project 5.2
 *
 * Класс имплементирующий ДЕК на базе двусвязного списка
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
class DeqOnDoublyLinkedList implements Dequeue
{
	private DLinkList dLinkList;

	DeqOnDoublyLinkedList()
	{
		dLinkList = new DLinkList();
	}

	@Override
	public void insertLeft(long value)
	{
		dLinkList.insertFirst(value);
	}

	@Override
	public void insertRight(long value)
	{
		dLinkList.insertLast(value);
	}

	@Override
	public long removeLeft()
	{
		return dLinkList.deleteFirst().dData;
	}

	@Override
	public long removeRight()
	{
		return dLinkList.deleteLast().dData;
	}

	@Override
	public long peekLeft()
	{
		return dLinkList.getFirst().dData;
	}

	@Override
	public long peekRight()
	{
		return dLinkList.getLast().dData;
	}

	@Override
	public boolean isEmpty()
	{
		return dLinkList.isEmpty();
	}

	@Override
	public boolean isFull()
	{
		return false;
	}
}
