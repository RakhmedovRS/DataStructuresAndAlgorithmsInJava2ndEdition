package chapter3;

import base.BaseArray;

import java.util.Calendar;

/**
 *
 * @author rassoll
 * @created 02.10.2017
 * @$Author$
 * @$Revision$
 */
public class ArrayBubbleSort implements BaseArray
{
    private long[] a;
    private int nElements;

    public ArrayBubbleSort(int nElements)
    {
        a = new long[nElements];
        this.nElements = 0;
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
        int inner, outer;
        for (outer = nElements - 1; outer > 1; outer--)
        {
            for (inner = 0; inner < outer; inner++)
            {
                if (a[inner] > a[inner + 1])
                {
                    long temp = a[inner];
                    a[inner] = a[inner + 1];
                    a[inner + 1] = temp;
                }
            }
        }

        Long endTime = Calendar.getInstance().getTime().getTime();
        System.out.println(String.format("Bubble sorting %s items took %s seconds", nElements, (endTime - startTime)/1000));
    }

    /**
     * Программный проект 3.1 - Program project 3.1
     */
    public void alternativeSort()
    {
        Long startTime = Calendar.getInstance().getTime().getTime();

        int inner, rightOuter, leftOuter;
        for (rightOuter = nElements - 1, leftOuter = 0; rightOuter > 1; rightOuter--, leftOuter ++)
        {
            for (inner = 0; inner < rightOuter; inner++)
            {
                if (a[inner] > a[inner + 1])
                {
                    long temp = a[inner];
                    a[inner] = a[inner + 1];
                    a[inner + 1] = temp;
                }
            }
            for (inner = rightOuter; inner > 0; inner--)
            {
                if (a[inner] < a[inner - 1])
                {
                    long temp = a[inner];
                    a[inner] = a[inner - 1];
                    a[inner - 1] = temp;
                }
            }
        }

        Long endTime = Calendar.getInstance().getTime().getTime();
        System.out.println(String.format("Alternative bubble sorting %s items took %s seconds", nElements, (endTime - startTime)/1000));
    }
}
