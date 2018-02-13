package chapter5;

import base.LinkItem;
import base.LinkedList;

/**
 * Программный проект 5.5 - Program project 5.5
 *
 * Класс имплементирующий циклический список
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
class CyclicList implements LinkedList<LinkItem>
{
	LinkItem current;

	CyclicList()
	{
		current = null;
	}

	@Override
	public boolean isEmpty()
	{
		return (current == null);
	}

	@Override
	public void insert(int key, double data)
	{
		Link newLink = new Link(key, data);

		if (current == null)
		{
			current = newLink;
			newLink.setNext(current);
		}
		else
		{
			newLink.setNext(current.getNext());
			current.setNext(newLink);
		}
	}

	@Override
	public LinkItem deleteFirst()
	{
		LinkItem temp = current;

		if (!isEmpty())
		{
			if (current.getNext() == current)
			{
				temp = current;
				current = null;
			}
			else
			{
				temp = current.getNext();
				current.setNext(current.getNext().getNext());
			}
		}

		return temp;
	}

	@Override
	public LinkItem find(int key)
	{
		if (!isEmpty())
		{
			LinkItem temp = this.current;
			while (temp.getKey() != key)
			{
				if ((temp.getNext() == null) || (temp.getNext() == this.current))
				{
					return null;
				}
				else
				{
					temp = temp.getNext();
				}
			}

			return temp;
		}
		else
		{
			return null;
		}
	}

	@Override
	public LinkItem delete(int key)
	{
		LinkItem tempCurrent;

		while (current.getNext().getKey() != key)
		{
			current = current.getNext();
		}

		if (current.getNext() == current)
		{
			tempCurrent = current;
			current = null;
		}
		else
		{
			tempCurrent = current.getNext();
			current.setNext(current.getNext().getNext());
			step();
		}

		return tempCurrent;
	}

	@Override
	public LinkItem getFirst()
	{
		return current;
	}

	@Override
	public void displayList()
	{
		LinkItem temp = current;
		System.out.print("List (current->current-1): ");
		if (!isEmpty())
		{
			while (true)
			{
				System.out.print(temp.getData() + " ");
				temp = temp.getNext();

				if (temp == current)
				{
					break;
				}
			}
		}
		else
		{
			System.out.print("null ");
		}
		System.out.println("");
	}

	LinkItem step()
	{
		if (current != null)
		{
			current = current.getNext();
			return current;
		}
		else
		{
			return null;
		}
	}
}
