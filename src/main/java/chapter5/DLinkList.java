package chapter5;

import base.DoublyLinkedList;

/**
 * Класс имплементирующий двухторонний связанный список
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
class DLinkList implements DoublyLinkedList
{
	private DoubleLink first;
	private DoubleLink last;

	DLinkList()
	{
		first = null;
		last = null;
	}

	@Override
	public boolean isEmpty()
	{
		return (first == null);
	}

	@Override
	public void insertFirst(long value)
	{
		DoubleLink newLink = new DoubleLink(value);
		if (isEmpty())
		{
			last = newLink;
		}
		else
		{
			first.previous = newLink;
			newLink.next = first;
		}
		first = newLink;
	}

	@Override
	public void insertLast(long value)
	{
		DoubleLink newLink = new DoubleLink(value);
		if (isEmpty())
		{
			first = newLink;
		}
		else
		{
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}

	@Override
	public DoubleLink deleteFirst()
	{
		DoubleLink temp = first;
		if (first.next == null)
		{
			last = null;
		}
		else
		{
			first.next.previous = null;
		}
		first = first.next;

		return temp;
	}

	@Override
	public DoubleLink deleteLast()
	{
		DoubleLink temp = last;
		if (first.next == null)
		{
			first = null;
		}
		else
		{
			last.previous.next = null;
		}
		last = last.previous;

		return temp;
	}

	@Override
	public boolean insertAfter(long key, long dd)
	{
		DoubleLink current = first;
		while (current.dData != key)
		{
			current = current.next;
			if (current == null)
			{
				return false;
			}
		}

		DoubleLink newLink = new DoubleLink(dd);
		if (current == last)
		{
			newLink.next = null;
			last = newLink;
		}
		else
		{
			newLink.next = current.next;
			current.next.previous = newLink;
		}

		newLink.previous = current;
		current.next = newLink;

		return true;
	}

	@Override
	public DoubleLink deleteKey(long key)
	{
		DoubleLink current = first;

		if (first == null)
		{
			return current;
		}

		while (current.dData != key)
		{
			current = current.next;
			if (current == null)
			{
				return null;
			}
		}

		if (current == first)
		{
			first = current.next;
		}
		else
		{
			current.previous.next = current.next;
		}

		if (current == last)
		{
			last = current.previous;
		}
		else
		{
			current.next.previous = current.previous;
		}

		return current;
	}

	@Override
	public void displayForward()
	{
		System.out.print("List (first-->last): ");
		DoubleLink current = first;

		while (current != null)
		{
			current.displayLink();
			current = current.next;
		}
		System.out.println("");
	}

	@Override
	public void displayBackward()
	{
		System.out.print("List (last-->first): ");
		DoubleLink current = last;

		while (current != null)
		{
			current.displayLink();
			current = current.previous;
		}
		System.out.println("");
	}

	DoubleLink getFirst()
	{
		return first;
	}

	DoubleLink getLast()
	{
		return last;
	}
}
