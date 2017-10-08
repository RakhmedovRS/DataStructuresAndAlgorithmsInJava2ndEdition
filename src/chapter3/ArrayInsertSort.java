package chapter3;

import java.util.Calendar;

/**
 * @author rassoll
 * @created 03.10.2017
 * @$Author$
 * @$Revision$
 */
public class ArrayInsertSort implements BaseArray
{
    private long[] a;
    private int nElements;
    private boolean sorted;

    public ArrayInsertSort(int nElements)
    {
        a = new long[nElements];
        sorted = false;
    }

    @Override
    public void insert(long value)
    {
        a[nElements] = value;
        nElements++;
        sorted = false;
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

    /**
     * Программный проект 3.5 - Program project 3.5
     */
    @Override
    public void sort()
    {
        if (!sorted)
        {
            Long startTime = Calendar.getInstance().getTime().getTime();
            int outer, inner;
            long comparisons = 0;
            long permutations = 0;

            for (outer = 1; outer < nElements; outer++)
            {
                long temp = a[outer];
                inner = outer;
                while (inner > 0)
                {
                    comparisons++;
                    if (a[inner - 1] >= temp)
                    {
                        comparisons++;
                        a[inner] = a[inner - 1];
                    }
                    --inner;
                }
                a[inner] = temp;
                permutations++;
            }

            sorted = true;

            Long endTime = Calendar.getInstance().getTime().getTime();
            System.out.println(String.format(
                    "Insert sorting %s items took %s seconds. Performed %s comparisons and %s permutations",
                    nElements, (endTime - startTime) / 1000, comparisons, permutations));
        }
    }

    /**
     * Программный проект 3.2 - Program project 3.2
     */
    public long median()
    {
        sort();

        if (a.length % 2 != 0)
        {
            return a[a.length / 2];
        }
        else
        {
            return (a[((a.length - 1) / 2)] + a[(a.length / 2)]) / 2;
        }
    }

    /**
     * Программный проект 3.3, 3.6 - Program project 3.3 , 3.6
     */
    public void noDups()
    {
        sort();

        long[] temp = new long[nElements];
        int i, j;

        for (i = 0, j = 0; i < nElements; i++)
        {
            if (j != 0)
            {
                if (temp[j - 1] != a[i])
                {
                    temp[j] = a[i];
                    j++;
                }
            }
            else
            {
                temp[j] = a[i];
                j++;
            }
        }

        a = temp;
        nElements = j;
        sorted = false;

    }
}
