package chapter5;

import java.util.stream.IntStream;

/**
 * Программный проект 5.5 - Program project 5.5
 *
 * @author rassoll
 * @created 29.10.2017
 * @$Author$
 * @$Revision$
 */
public class JosephusFlaviusProblem
{
    public static void main(String[] args)
    {
        solveProblem(10, 3, 1);
    }

    private static void solveProblem(int peopleCount, int count, int peopleNumber)
    {
        CyclicList cyclicList = new CyclicList();

        IntStream.range(0, peopleCount).forEach(k -> cyclicList.insertFirst(k , k));

        while (cyclicList.current.iData != peopleNumber)
        {
            cyclicList.step();
        }

        while(!cyclicList.isEmpty())
        {
            for (int i = 0; i < count; i++)
            {
                cyclicList.step();
            }

            System.out.print(cyclicList.current.iData + " ");
            cyclicList.delete(cyclicList.current.iData);
        }
    }
}
