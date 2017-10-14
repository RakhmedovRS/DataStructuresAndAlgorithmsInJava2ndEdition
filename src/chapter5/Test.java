package chapter5;

import base.LinkedList;
import base.Queue;

/**
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public class Test
{
    public static void main(String[] args)
    {
//        testMemoryCapacity();
//        testSortedLinkedList();
        testPriorityQueueOnSortedLinkedList();
    }

    /**
     * Упражнение 5.2 - Program project 5.2
     */
    public static void testMemoryCapacity()
    {
        LinkedList list = new LinkList();

        for (int i = 0, j = 0, freq = 1000; i < freq; i++)
        {
            list.insertFirst(i,i * 0.1D);

            if (i == 999)
            {
                i = 0;
                System.out.println(String.format("Inserted - %s", ++j * freq));
            }
        }
    }

    public static void testSortedLinkedList()
    {
        LinkedList list = new SortedLinkedList();
        list.insertFirst(10, 100);
        list.insertFirst(11, 111);
        list.insertFirst(12, 16);
        list.insertFirst(13, 15);
        list.insertFirst(17, 11);
        list.insertFirst(14, 14);
        list.insertFirst(15, 13);
        list.insertFirst(16, 12);

        list.displayList();
    }

    public static void testPriorityQueueOnSortedLinkedList()
    {
        PriorityQueueOnSortedLinkedList queue = new PriorityQueueOnSortedLinkedList();

        System.out.println("Testing Queue");

        queue.insert(10);
        queue.insert(20);
        queue.insert(30 );
        queue.insert(40 );

        queue.remove();
        queue.remove();
        queue.remove();

        queue.insert(50 );
        queue.insert(60 );
        queue.insert(70 );
        queue.insert(80 );

        queue.print();
    }
}
