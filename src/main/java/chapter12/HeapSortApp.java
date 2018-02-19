package chapter12;

import java.io.IOException;

import static base.util.Util.getInt;

/**
 * @author rassoll
 * @created 19.02.2018
 * @$Author$
 * @$Revision$
 */
public class HeapSortApp
{
	public static void main(String[] args) throws IOException
	{
		int size;
		int i;
		System.out.println("Enter number of items: ");
		size = getInt(System.in);
		HeapSort heapSort = new HeapSort(size);
		for (i = 0; i < size; i++)
		{
			Node newNode = new Node((int) (java.lang.Math.random() * 100));
			heapSort.insertAt(i, newNode);
			heapSort.incrementSize();
		}

		System.out.println("Random: ");
		heapSort.displayArray();
		System.out.println();

		/*преобразуме массив в пирамиду*/
		for (i = size / 2 - 1; i >= 0; i--)
		{
			heapSort.trickleDown(i);
		}
		System.out.println("Heap: ");
		heapSort.displayArray();
		heapSort.displayHeap();
		System.out.println();

		/*извлечение узла из пирамиды с сохранением его в конце массива*/
		for (i = size - 1; i >= 0; i--)
		{
			Node biggestNode = heapSort.remove();
			heapSort.insertAt(i, biggestNode);
		}

		System.out.println("Sorted: ");
		heapSort.displayArray();
	}
}
