package chapter3;

import base.BaseArray;

import java.util.Calendar;

/**
 * @author rassoll
 * @created 03.10.2017
 * @$Author$
 * @$Revision$
 */
public class ArraySelectSort implements BaseArray
{
    private long[] a;
    private int nElements;

    public ArraySelectSort(int nElements)
    {
        a = new long[nElements];
    }

    @Override
    public void insert(long value)
    {
        a[nElements] = value;
        nElements++;
    }

    @Override
    public void display()
    {
        for (int i = 0; i < nElements; i++)
        {
            System.out.print(String.format("%s ", a[i]));
        }
        System.out.println();
    }

    @Override
    public void sort()
    {
        Long startTime = Calendar.getInstance().getTime().getTime();
        int outer, inner, minimal;
        for (outer = 0; outer < nElements - 1; outer++)
        {
            minimal = outer;
            for (inner = outer + 1; inner < nElements; inner++)
            {
                if (a[inner] < a[minimal])
                {
                    minimal = inner;
                }
            }

            long temp = a[minimal];
            a[minimal] = a[outer];
            a[outer] = temp;
        }

        Long endTime = Calendar.getInstance().getTime().getTime();
        System.out.println(String.format("Selection sorting %s items took %s seconds", nElements, (endTime - startTime)/1000));
    }
}
