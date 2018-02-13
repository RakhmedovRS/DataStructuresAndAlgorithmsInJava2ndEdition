package chapter11;

import base.LinkItem;

/**
 * Сущность элемент списка
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class Link implements LinkItem
{
	private int data;
	private LinkItem next;

	/**
	 * Конструктор
	 *
	 * @param data значение элемента данных
	 */
	public Link(int data)
	{
		this.data = data;
	}

	@Override
	public double getData()
	{
		return data;
	}

	@Override
	public LinkItem getNext()
	{
		return next;
	}

	@Override
	public void setNext(LinkItem next)
	{
		this.next = next;
	}

	@Override
	public void displayLink()
	{
		System.out.println(getDisplayData());
	}

	@Override
	public int getKey()
	{
		return data;
	}

	@Override
	public String getDisplayData()
	{
		return String.valueOf(data);
	}
}
