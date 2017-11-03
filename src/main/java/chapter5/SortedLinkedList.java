package chapter5;

import base.LinkedList;

/**
 * Класс имплементирующий сортированный односвязный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
class SortedLinkedList implements LinkedList
{
	private Link first;

	SortedLinkedList()
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

		if (!isEmpty())
		{
			if (first.dData > dData)
			{
				newLink.next = first;
				first = newLink;
			}
			else
			{
				Link current = first;
				while (current.next != null)
				{
					if (current.dData <= dData && current.next.dData > dData)
					{
						break;
					}
					current = current.next;
				}
				newLink.next = current.next;
				current.next = newLink;
			}
		}
		else
		{
			newLink.next = first;
			first = newLink;
		}
	}

	@Override
	public Link deleteFirst()
	{
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
		current.displayLink();
		System.out.println("");
	}
}
