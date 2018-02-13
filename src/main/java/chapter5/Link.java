package chapter5;

import base.LinkItem;

/**
 * Элемент списка
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public class Link implements LinkItem
{
	private double data;
	private int key;
	private LinkItem next;

	Link(int key, double data)
	{
		this.key = key;
		this.data = data;
	}

	@Override
	public int getKey()
	{
		return key;
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
	public String getDisplayData()
	{
		return String.format("{%s, %s}", key, data);
	}

	@Override
	public void displayLink()
	{
		System.out.print(getDisplayData());
	}
}
