package chapter3;

import base.BaseArray;

/**
 * @author rassoll
 * @created 03.10.2017
 * @$Author$
 * @$Revision$
 */
public class SortingTest
{
    public static void main(String[] args)
    {
        int maxSize = 100000;

        System.out.println("\nRandom arrays results:");
        randomArrayFill(new ArrayBubbleSort(maxSize), maxSize);
        randomArrayFill(new ArrayInsertSort(maxSize), maxSize);
        randomArrayFill(new ArraySelectSort(maxSize), maxSize);

        System.out.println("\nRevers ordered arrays results:");
        randomArrayReverseFill(new ArrayBubbleSort(maxSize), maxSize);
        randomArrayReverseFill(new ArrayInsertSort(maxSize), maxSize);
        randomArrayReverseFill(new ArraySelectSort(maxSize), maxSize);

        System.out.println("\nOrdered arrays results:");
        randomArrayOrderedFill(new ArrayBubbleSort(maxSize), maxSize);
        randomArrayOrderedFill(new ArrayInsertSort(maxSize), maxSize);
        randomArrayOrderedFill(new ArraySelectSort(maxSize), maxSize);
    }

    private static void simpleArrayFill(BaseArray array)
    {
        array.insert(77);
        array.insert(99);
        array.insert(44);
        array.insert(22);
        array.insert(88);
        array.insert(11);
        array.insert(00);
        array.insert(66);
        array.insert(33);

        array.display();
        array.sort();
        array.display();
    }

    /**
     * Упражнение 3.1 - Exercise 3.1
     */
    private static void randomArrayFill(BaseArray array, int maxSize)
    {
        for (int i = 0; i < maxSize; i++)
        {
            long value = (long) (Math.random() * (maxSize - 1));
            array.insert(value);
        }

        array.sort();
    }

    /**
     * Упражнение 3.2 - Exercise 3.2
     */
    private static void randomArrayReverseFill(BaseArray array, int maxSize)
    {
        for (int i = maxSize; i > 0; i--)
        {
            array.insert(i);
        }

        array.sort();
    }

    /**
     * Упражнение 3.3 - Exercise 3.3
     */
    private static void randomArrayOrderedFill(BaseArray array, int maxSize)
    {
        for (int i = 0; i < maxSize; i++)
        {
            array.insert(i);
        }

        array.sort();
    }

}
