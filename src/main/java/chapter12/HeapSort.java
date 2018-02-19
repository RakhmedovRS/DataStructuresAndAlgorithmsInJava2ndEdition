package chapter12;

/**
 * Сущность пирамиды реализующей алгоритм пирамидальной сортировки
 *
 * @author rassoll
 * @created 19.02.2018
 * @$Author$
 * @$Revision$
 */
public class HeapSort
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;

	public HeapSort(int maxSize)
	{
		this.maxSize = maxSize;
		currentSize = 0;
		heapArray = new Node[maxSize];
	}

	/**
	 * Выполнить удаление узла из кучи
	 *
	 * @return удаленный узел
	 */
	public Node remove()
	{
		Node root = heapArray[0];
		heapArray[0] = heapArray[--currentSize];
		trickleDown(0);
		return root;
	}

	/**
	 * Выполнить перемещение узла вниз по куче
	 *
	 * @param index индекс перемещаемого узла
	 */
	public void trickleDown(int index)
	{
		int largerChild;
		Node top = heapArray[index];

		while (index < currentSize / 2)
		{
			int leftChild = 2 * index + 1;
			int rightChild = leftChild + 1;

			/*определим наибольшего потомка*/
			if (rightChild < currentSize
				&& heapArray[leftChild].getKey() < heapArray[rightChild].getKey())
			{
				largerChild = rightChild;
			}
			else
			{
				largerChild = leftChild;
			}

			if (top.getKey() >= heapArray[largerChild].getKey())
			{
				break;
			}

			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;
	}

	/**
	 * Вывести данные из кучи на печать
	 */
	public void displayHeap()
	{
		System.out.println("heapArray: ");
		for (int i = 0; i < currentSize; i++)
		{
			if (heapArray[i] != null)
			{
				System.out.println(heapArray[i].getKey() + " ");
			}
			else
			{
				System.out.println("--");
			}
		}

		int nBlanks = 32;
		int itemPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "................................";

		System.out.println(dots + dots);
		while (currentSize > 0)
		{
			if (column == 0)
			{
				for (int k = 0; k < nBlanks; k++)
				{
					System.out.print(' ');
				}
			}

			System.out.print(heapArray[j].getKey());
			if (++j == currentSize)
			{
				break;
			}

			if (++column == itemPerRow)
			{
				nBlanks /= 2;
				itemPerRow *= 2;
				column = 0;
				System.out.println();
			}
			else
			{
				for (int k = 0; k < nBlanks * 2 - 2; k++)
				{
					System.out.print(' ');
				}
			}
		}
		System.out.println("\n" + dots + dots);
	}

	/**
	 * Вывести на печать данный из массива кучи
	 */
	public void displayArray()
	{
		for (int i = 0; i < maxSize; i++)
		{
			System.out.print(heapArray[i].getKey() + " ");
			System.out.println();
		}
	}

	/**
	 * Выполнить вставку узла в указанную позицию
	 *
	 * @param index   позиция
	 * @param newNode узел
	 */
	public void insertAt(int index, Node newNode)
	{
		heapArray[index] = newNode;
	}

	/**
	 * Увеличить счетчик количества элементов в куче
	 */
	public void incrementSize()
	{
		currentSize++;
	}
}
