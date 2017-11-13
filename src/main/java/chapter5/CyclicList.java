package chapter5;

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
class CyclicList implements LinkedList
{
	Link current;

	CyclicList()
	{
		current = null;
	}

	Link step()
	{
		if (current != null)
		{
			current = current.next;
			return current;
		}
		else
		{
			return null;
		}
	}

	@Override
	public boolean isEmpty()
	{
		return (current == null);
	}

	@Override
	public void insertFirst(int iData, double dData)
	{
		Link newLink = new Link(iData, dData);

		if (current == null)
		{
			current = newLink;
			newLink.next = current;
		}
		else
		{
			newLink.next = current.next;
			current.next = newLink;
		}
	}

	@Override
	public Link deleteFirst()
	{
		Link temp = current;

		if (!isEmpty())
		{
			if (current.next == current)
			{
				temp = current;
				current = null;
			}
			else
			{
				temp = current.next;
				current.next = current.next.next;
			}
		}

		return temp;
	}

	@Override
	public Link find(int key)
	{
		if (!isEmpty())
		{
			Link temp = this.current;
			while (temp.iData != key)
			{
				if ((temp.next == null) || (temp.next == this.current))
				{
					return null;
				}
				else
				{
					temp = temp.next;
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
	public Link delete(int key)
	{
		Link tempCurrent;

		while (current.next.iData != key)
		{
			current = current.next;
		}

		if (current.next == current)
		{
			tempCurrent = current;
			current = null;
		}
		else
		{
			tempCurrent = current.next;
			current.next = current.next.next;
			step();
		}

		return tempCurrent;
	}

	@Override
	public Link getFirst()
	{
		return current;
	}

	@Override
	public void displayList()
	{
		Link temp = current;
		System.out.print("List (current->current-1): ");
		if (!isEmpty())
		{
			while (true)
			{
				System.out.print(temp.dData + " ");
				temp = temp.next;

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
}
