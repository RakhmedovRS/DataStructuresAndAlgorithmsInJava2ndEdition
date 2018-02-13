package chapter5;

import base.DoubleLinkItem;
import base.DoublyLinkedList;

/**
 * Класс имплементирующий двухторонний связанный список
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
class DLinkList implements DoublyLinkedList<DoubleLinkItem>
{
	private DoubleLinkItem first;
	private DoubleLinkItem last;

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
		DoubleLinkItem newLink = new DoubleLink(value);
		if (isEmpty())
		{
			last = newLink;
		}
		else
		{
			first.setPrevious(newLink);
			newLink.setNext(first);
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
			last.setNext(newLink);
			newLink.setPrevious(last);
		}
		last = newLink;
	}

	@Override
	public DoubleLinkItem deleteFirst()
	{
		DoubleLinkItem temp = first;
		if (first.getNext() == null)
		{
			last = null;
		}
		else
		{
			first.getNext().setPrevious(null);
		}
		first = first.getNext();

		return temp;
	}

	@Override
	public DoubleLinkItem deleteLast()
	{
		DoubleLinkItem temp = last;
		if (first.getNext() == null)
		{
			first = null;
		}
		else
		{
			last.getPrevious().setNext(null);
		}
		last = last.getPrevious();

		return temp;
	}

	@Override
	public boolean insertAfter(long key, long dd)
	{
		DoubleLinkItem current = first;
		while (current.getData() != key)
		{
			current = current.getNext();
			if (current == null)
			{
				return false;
			}
		}

		DoubleLink newLink = new DoubleLink(dd);
		if (current == last)
		{
			newLink.setNext(null);
			last = newLink;
		}
		else
		{
			newLink.setNext(current.getNext());
			current.getNext().setPrevious(newLink);
		}

		newLink.setPrevious(current);
		current.setNext(newLink);

		return true;
	}

	@Override
	public DoubleLinkItem deleteKey(long key)
	{
		DoubleLinkItem current = first;

		if (first == null)
		{
			return current;
		}

		while (current.getData() != key)
		{
			current = current.getNext();
			if (current == null)
			{
				return null;
			}
		}

		if (current == first)
		{
			first = current.getNext();
		}
		else
		{
			current.getPrevious().setNext(current.getNext());
		}

		if (current == last)
		{
			last = current.getPrevious();
		}
		else
		{
			current.getNext().setPrevious(current.getPrevious());
		}

		return current;
	}

	@Override
	public void displayForward()
	{
		System.out.print("List (first-->last): ");
		DoubleLinkItem current = first;

		while (current != null)
		{
			current.displayLink();
			current = current.getNext();
		}
		System.out.println("");
	}

	@Override
	public void displayBackward()
	{
		System.out.print("List (last-->first): ");
		DoubleLinkItem current = last;

		while (current != null)
		{
			current.displayLink();
			current = current.getPrevious();
		}
		System.out.println("");
	}

	DoubleLinkItem getFirst()
	{
		return first;
	}

	DoubleLinkItem getLast()
	{
		return last;
	}
}
