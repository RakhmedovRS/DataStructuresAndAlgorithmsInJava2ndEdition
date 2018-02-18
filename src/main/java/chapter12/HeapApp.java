package chapter12;

import java.io.IOException;

import static base.util.Util.getChar;
import static base.util.Util.getInt;

/**
 * @author rassoll
 * @created 18.02.2018
 * @$Author$
 * @$Revision$
 */
public class HeapApp
{
	public static void main(String[] args) throws IOException
	{
		int value, value2;
		Heap heap = new Heap(31);
		heap.insert(70);
		heap.insert(40);
		heap.insert(50);
		heap.insert(20);
		heap.insert(60);
		heap.insert(100);
		heap.insert(80);
		heap.insert(30);
		heap.insert(10);
		heap.insert(90);

		boolean run = true;
		while (run)
		{
			System.out.println("Enter first letter of ");
			System.out.println("show, insert, remove, change or exit:");
			char choice = getChar(System.in);

			switch (choice)
			{
				case 's':
					heap.displayHeap();
					break;
				case 'i':
					System.out.println("Insert key value to insert: ");
					value = getInt(System.in);
					if (!heap.insert(value))
					{
						System.out.println("Can't insert; heap is full");
					}
					break;
				case 'r':
					System.out.println("Enter key value to remove: ");
					if (!heap.isEmpty())
					{
						heap.remove();
					}
					else
					{
						System.out.println("Can't remove; heap is full");
					}
					break;
				case 'c':
					System.out.println("Enter current index of item: ");
					value = getInt(System.in);
					System.out.println("Enter new key: ");
					value2 = getInt(System.in);
					if (!heap.change(value, value2))
					{
						System.out.println("Invalid index");
					}
					break;
				case 'e':
					run = false;
					break;
				default:
					System.out.println("Invalid entry\n");
			}
		}
	}
}
