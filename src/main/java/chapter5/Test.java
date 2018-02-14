package chapter5;

import base.items.LinkItem;
import base.structures.LinkedList;

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
		testSortedLinkedList();
		testPriorityQueueOnSortedLinkedList();
		testDeqOnDoublyLinkedList();
		textCyclicLink();
		testStackOnCyclicList();
	}

	/**
	 * Упражнение 5.2 - Program project 5.2
	 */
	public static void testMemoryCapacity()
	{
		LinkedList list = new LinkList();

		for (int i = 0, j = 0, freq = 1000; i < freq; i++)
		{
			list.insert(i, i * 0.1D);

			if (i == 999)
			{
				i = 0;
				System.out.println(String.format("Inserted - %s", ++j * freq));
			}
		}
	}

	public static void testSortedLinkedList()
	{
		System.out.println("Testing SortedLinkedList");

		LinkedList list = new SortedLinkedList();

		list.insert(10, 100);
		list.insert(11, 111);
		list.insert(12, 16);
		list.insert(13, 15);
		list.insert(17, 11);
		list.insert(14, 14);
		list.insert(15, 13);
		list.insert(16, 12);

		list.displayList();

		System.out.println(" ");
	}

	public static void testPriorityQueueOnSortedLinkedList()
	{
		System.out.println("Testing Queue");

		PriorityQueueOnSortedLinkedList queue = new PriorityQueueOnSortedLinkedList();

		queue.insert(10);
		queue.insert(20);
		queue.insert(30);
		queue.insert(40);

		queue.remove();
		queue.remove();
		queue.remove();

		queue.insert(50);
		queue.insert(60);
		queue.insert(70);
		queue.insert(80);

		queue.print();

		System.out.println(" ");
	}

	public static void testDeqOnDoublyLinkedList()
	{
		System.out.println("Testing Dequeue");

		DeqOnDoublyLinkedList dequeue = new DeqOnDoublyLinkedList();

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

		System.out.println(" ");
		System.out.println(" ");
	}

	public static void textCyclicLink()
	{
		System.out.println("Testing CyclicLink");

		CyclicList list = new CyclicList();

		LinkItem item = new Link(5,5);

		list.insert(1, 1);
		list.insert(2, 2);
		list.insert(3, 3);
		list.insert(4, 4);
		list.insert(item);
		list.insert(6, 6);
		list.insert(7, 7);
		list.insert(8, 8);

		list.displayList();
		list.delete(item);
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();
		list.deleteFirst();
		list.displayList();

		System.out.println(" ");
	}

	public static void testStackOnCyclicList()
	{
		System.out.println("Testing StackOnCyclicList");

		StackOnCyclicList stackOnCyclicList = new StackOnCyclicList();

		stackOnCyclicList.insert(10);
		stackOnCyclicList.insert(20);
		stackOnCyclicList.insert(30);
		stackOnCyclicList.insert(40);
		stackOnCyclicList.insert(50);

		while (!stackOnCyclicList.isEmpty())
		{
			long value = stackOnCyclicList.remove();
			System.out.print(value + " ");
		}
		System.out.println(" ");
	}
}
