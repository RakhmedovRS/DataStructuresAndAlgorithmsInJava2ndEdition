package chapter5;

import base.items.DoubleLinkItem;

/**
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class DoubleLink implements DoubleLinkItem
{
	private long data;
	private DoubleLinkItem next;
	private DoubleLinkItem previous;

	DoubleLink(long dData)
	{
		this.data = dData;
	}

	@Override
	public int getKey()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public String getDisplayData()
	{
		return data + " ";
	}

	@Override
	public double getData()
	{
		return data;
	}

	@Override
	public DoubleLinkItem getPrevious()
	{
		return previous;
	}

	@Override
	public DoubleLinkItem getNext()
	{
		return next;
	}

	@Override
	public void setPrevious(DoubleLinkItem previous)
	{
		this.previous = previous;
	}

	@Override
	public void setNext(DoubleLinkItem next)
	{
		this.next = next;
	}

	@Override
	public void displayLink()
	{
		System.out.print(getDisplayData());
	}
}
