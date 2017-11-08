package chapter5;

import base.LinkedList;

/**
 * Класс имплементирующий односвязный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
class LinkList implements LinkedList
{
	private Link first;

	LinkList()
	{
		first = null;
	}

	@Override
	public boolean isEmpty()
	{
		return (first == null);
	}

	@Override
	public void insertFirst(int iData, double dData)
	{
		Link newLink = new Link(iData, dData);
		newLink.next = first;
		first = newLink;
	}

	@Override
	public Link deleteFirst()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("Link list is empty");
		}

		Link temp = first;
		first = first.next;
		return temp;
	}

	@Override
	public Link find(int key)
	{
		Link current = first;
		while (current.iData != key)
		{
			if (current.next == null)
			{
				return null;
			}
			else
			{
				current = current.next;
			}
		}

		return current;
	}

	@Override
	public Link delete(int key)
	{
		Link current = first;
		Link prev = first;
		while (current.iData != key)
		{
			if (current.next == null)
			{
				return null;
			}
			else
			{
				prev = current;
				current = current.next;
			}
		}
		prev.next = current.next;

		return current;
	}

	@Override
	public Link getFirst()
	{
		return first;
	}

	@Override
	public void displayList()
	{
		System.out.print("List (first-->last): ");
		Link current = first;
		while (current.next != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}
}
