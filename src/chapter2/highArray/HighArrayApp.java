package chapter2.highArray;

/**
 * @author rassoll
 * @created 02.10.2017
 * @$Author$
 * @$Revision$
 */
public class HighArrayApp
{
    public static void main(String[] args)
    {
        int maxSize = 100;
        HighArray highArray = new HighArray(maxSize);

        highArray.insert(77);
        highArray.insert(99);
        highArray.insert(44);
        highArray.insert(55);
        highArray.insert(22);
        highArray.insert(88);
        highArray.insert(11);
        highArray.insert(00);
        highArray.insert(66);
        highArray.insert(33);

        highArray.insert(22);
        highArray.insert(33);
        highArray.insert(33);
        highArray.display();

        highArray.noDups();
        highArray.display();
    }

    /**
     * Программный проект 2.3 - Program project 2.3
     */
    private static long[] sortHighArray(HighArray highArray)
    {
        int size = highArray.getSize();
        long[] sortedHighArray = new long[size];
        for (int i = size; i > 0; i--)
        {
            sortedHighArray[i-1] = highArray.removeMax();
        }

        return sortedHighArray;
    }
}
