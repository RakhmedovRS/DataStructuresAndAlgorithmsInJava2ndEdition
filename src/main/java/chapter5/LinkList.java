package chapter5;

import base.LinkItem;
import base.LinkedList;

/**
 * Класс имплементирующий односвязный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
class LinkList implements LinkedList<LinkItem>
{
	private LinkItem first;

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
	public void insert(int key, double data)
	{
		insert(new Link(key, data));
	}

	@Override
	public void insert(LinkItem item)
	{
		item.setNext(first);
		first = item;
	}

	@Override
	public LinkItem deleteFirst()
	{
		if (isEmpty())
		{
			throw new UnsupportedOperationException("Link list is empty");
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
				prev = current;
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
		System.out.println("");
	}
}
