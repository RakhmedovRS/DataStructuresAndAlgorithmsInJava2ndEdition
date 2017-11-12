package chapter5;

/**
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class DoubleLink
{
	public long dData;
	DoubleLink next;
	DoubleLink previous;

	DoubleLink(long dData)
	{
		this.dData = dData;
	}

	void displayLink()
	{
		System.out.print(dData + " ");
	}
}
