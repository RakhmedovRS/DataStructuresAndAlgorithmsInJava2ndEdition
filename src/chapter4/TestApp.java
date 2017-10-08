package chapter4;

/**
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public class TestApp
{
    public static void main(String[] args)
    {
        QueueImpl queue = new QueueImpl(5);
        Dequeue dequeue = new DequeueImpl(10);
        StackBasedOnDequeue stackBasedOnDequeue = new StackBasedOnDequeue(10);

        testQueue(queue);
        testDeque(dequeue);
        testStack(stackBasedOnDequeue);
    }

    private static void testQueue(QueueImpl queue)
    {
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

    private static void testDeque(Dequeue dequeue)
    {
        System.out.println("Testing Dequeue");

        dequeue.insertLeft(10);
        dequeue.insertRight(20);
        dequeue.insertLeft(30);
        dequeue.insertRight(40);
        dequeue.insertLeft(50);
        dequeue.insertRight(60);
        dequeue.insertLeft(70);
        dequeue.insertRight(80);
        dequeue.insertLeft(90);
        dequeue.insertRight(100);

        while (!dequeue.isEmpty())
        {
            long value = dequeue.removeLeft();
            System.out.print(value + " ");
        }
        System.out.println();
    }

    private static void testStack(StackBasedOnDequeue stackBasedOnDequeue)
    {
        System.out.println("Testing StackBasedOnDequeue");

        stackBasedOnDequeue.insert(10);
        stackBasedOnDequeue.insert(20);
        stackBasedOnDequeue.insert(30);
        stackBasedOnDequeue.insert(40);
        stackBasedOnDequeue.insert(50);

        while (!stackBasedOnDequeue.isEmpty())
        {
            long value = stackBasedOnDequeue.remove();
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
