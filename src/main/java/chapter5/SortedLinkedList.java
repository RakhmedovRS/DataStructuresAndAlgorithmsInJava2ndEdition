package chapter5;

import base.LinkItem;
import base.LinkedList;

/**
 * Класс имплементирующий сортированный односвязный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
class SortedLinkedList implements LinkedList<LinkItem>
{
	private LinkItem first;

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
		LinkItem newLink = new Link(iData, dData);

		if (!isEmpty())
		{
			if (first.getData() > dData)
			{
				newLink.setNext(first);
				first = newLink;
			}
			else
			{
				LinkItem current = first;
				while (current.getNext() != null)
				{
					if (current.getData() <= dData && current.getNext().getData() > dData)
					{
						break;
					}
					current = current.getNext();
				}
				newLink.setNext(current.getNext());
				current.setNext(newLink);
			}
		}
		else
		{
			newLink.setNext(first);
			first = newLink;
		}
	}

	@Override
	public LinkItem deleteFirst()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("SortedLinkedList is empty");
		}

		LinkItem temp = first;
		first = first.getNext();
		return temp;
	}

	@Override
	public LinkItem find(int key)
	{
		LinkItem current = first;
		while (current.getKey() != key)
		{
			if (current.getNext() == null)
			{
				return null;
			}
			else
			{
				current = current.getNext();
			}
		}

		return current;
	}

	@Override
	public LinkItem delete(int key)
	{
		LinkItem current = first;
		LinkItem prev = first;
		while (current.getKey() != key)
		{
			if (current.getNext() == null)
			{
				return null;
			}
			else
			{
				current = current.getNext();
			}
		}
		prev.setNext(current.getNext());

		return current;
	}

	@Override
	public LinkItem getFirst()
	{
		return first;
	}

	@Override
	public void displayList()
	{
		System.out.print("List (first-->last): ");
		LinkItem current = first;
		while (current.getNext() != null)
		{
			current.displayLink();
			current = current.getNext();
		}
		current.displayLink();
		System.out.println("");
	}
}
