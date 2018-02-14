package chapter5;

import base.items.LinkItem;
import base.structures.LinkedList;

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
		insert(new Link(key, data));
	}

	@Override
	public void insert(LinkItem item)
	{
		if (current == null)
		{
			current = item;
			item.setNext(current);
		}
		else
		{
			item.setNext(current.getNext());
			current.setNext(item);
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
	public LinkItem find(LinkItem item)
	{
		if (!isEmpty())
		{
			LinkItem temp = this.current;
			while (temp.getKey() != item.getKey())
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
	public LinkItem delete(LinkItem item)
	{
		LinkItem tempCurrent;

		while (current.getNext().getKey() != item.getKey())
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
