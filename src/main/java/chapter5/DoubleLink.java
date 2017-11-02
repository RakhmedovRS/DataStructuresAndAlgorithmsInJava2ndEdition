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
    public DoubleLink next;
    public DoubleLink previous;

    public DoubleLink(long dData)
    {
        this.dData = dData;
    }

    public void displayLink()
    {
        System.out.print(dData + " ");
    }
}
