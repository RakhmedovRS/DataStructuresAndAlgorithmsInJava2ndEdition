package chapter2;

import chapter2.highArray.HighArray;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author rassoll
 * @created 29.10.2017
 * @$Author$
 * @$Revision$
 */
public class HighArrayTest
{
    int arraySize = 10;
    HighArray highArray;

    @Before
    public void init()
    {
        highArray = new HighArray(arraySize);

        for (int i = 0; i < arraySize; i++)
        {
            highArray.insert(i);
        }
    }

    @Test
    public void checkSize()
    {
        assertEquals(arraySize, highArray.getSize());
    }

    @Test
    public void checkSearch()
    {
        for (int i = arraySize - 1; i >= 0; i--)
        {
            assertTrue(highArray.find(i));
        }
    }

    @Test
    public void checkDelete()
    {
        highArray.delete(1);

        assertFalse(highArray.find(1));
    }

    @Test
    public void insertionCheck()
    {
        highArray.delete(9);
        highArray.insert(11);

        assertTrue(highArray.find(11));
    }

    @Test
    public void checkMaxValueFinder()
    {
        assertEquals(9, highArray.getMax());
    }

    @Test
    public void checkMaxValueDelete()
    {
        long maxValue = highArray.getMax();

        highArray.delete(maxValue);

        assertNotEquals(maxValue, highArray.getMax());
    }
}
