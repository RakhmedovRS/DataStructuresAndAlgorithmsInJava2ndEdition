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
		return (long)dLinkList.deleteFirst().getData();
	}

	@Override
	public long removeRight()
	{
		return (long)dLinkList.deleteLast().getData();
	}

	@Override
	public long peekLeft()
	{
		return (long)dLinkList.getFirst().getData();
	}

	@Override
	public long peekRight()
	{
		return (long)dLinkList.getLast().getData();
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
