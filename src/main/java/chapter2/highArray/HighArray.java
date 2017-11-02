package chapter2.highArray;

/**
 * Класс массива с высокоуровневым интерфейсом
 *
 * @author rassoll
 * @created 02.10.2017
 * @$Author$
 * @$Revision$
 */
public class HighArray
{
    private long[] a;
    private int nElements;

    public HighArray(int nElements)
    {
        a = new long[nElements];
        this.nElements = 0;
    }

    public boolean find(long searchKey)
    {
        int i;
        for (i = 0; i < nElements; i++)
        {
            if (a[i] == searchKey)
            {
                break;
            }
        }

        if (i == nElements)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public void insert(long value)
    {
        a[nElements] = value;
        nElements++;
    }

    public boolean delete(long value)
    {
        int i;
        for (i = 0; i < nElements; i++)
        {
            if (value == a[i])
            {
                break;
            }
        }

        if (i == nElements)
        {
            return false;
        }
        else
        {
            for (int k = i; k < nElements - 1; k++)
            {
                a[k] = a[k + 1];
            }
            nElements--;
            return true;
        }
    }

    /**
     * Программный проект 2.1 - Program project 2.1
     */
    public long getMax()
    {
        long retValue = -1;

        if (a.length == 0)
        {
            return retValue;
        }

        for (int i = 0; i < nElements; i++)
        {
            if (a[i] > retValue)
            {
                retValue = a[i];
            }
        }

        return retValue;
    }

    /**
     * Программный проект 2.2 - Program project 2.2
     */
    public long removeMax()
    {
        long maxValue = getMax();
        delete(maxValue);

        return maxValue;
    }

    public int getSize()
    {
        return nElements;
    }

    public void display()
    {
        for (int i = 0; i < nElements; i++)
        {
            System.out.print(String.format("%s ", a[i]));
        }
        System.out.println();
    }

    /**
     * Программный проект 2.6 - Program project 2.6
     */
    public void noDups()
    {
        int arrSize = nElements;
        int counter = 0;
        for (int j = 0; j < arrSize; j++)
        {
            for (int i = 0; i < arrSize; i++)
            {
                if (a[j] == a[i])
                {
                    counter++;
                    if (counter > 1)
                    {
                        delete(a[j]);
                        counter = 1;
                    }
                }
            }
        }
    }

    /**
     * Программный проект 2.3 - Program project 2.3
     */
    private void sort()
    {
        int size = getSize();
        long[] sortedHighArray = new long[size];
        for (int i = size; i > 0; i--)
        {
            sortedHighArray[i-1] = removeMax();
        }
    }
}
