package chapter12;

/**
 * Сущность пирамиды на базе массива
 *
 * @author rassoll
 * @created 18.02.2018
 * @$Author$
 * @$Revision$
 */
public class Heap
{
	private Node[] heapArray;
	private int maxSize;
	private int currentSize;
	private boolean directOrder;
	private boolean correctHeap;

	/**
	 * Конструктор
	 *
	 * @param maxSize размер пирамиды
	 */
	public Heap(int maxSize)
	{
		this(maxSize, false);
	}

	/**
	 * Программный проект 12.1 - Program project 12.1
	 * Конструктор
	 *
	 * @param maxSize     размер пирамиды
	 * @param directOrder признак прямого порядка узлов
	 */
	public Heap(int maxSize, boolean directOrder)
	{
		this.maxSize = maxSize;
		currentSize = 0;
		heapArray = new Node[maxSize];
		this.directOrder = directOrder;
		correctHeap = true;
	}

	/**
	 * @return признак заполненности кучи
	 */
	public boolean isEmpty()
	{
		return currentSize == 0;
	}

	/**
	 * Вставить ключ в кучу
	 *
	 * @param key ключ
	 * @return признак успешности вставки
	 */
	public boolean insert(int key)
	{
		if (currentSize == maxSize)
		{
			return false;
		}

		Node newNode = new Node(key);
		heapArray[currentSize] = newNode;
		tickleUp(currentSize++);
		return true;
	}

	/**
	 * Выполнить перемещение узла вверх по куче
	 *
	 * @param index индекс перемещаемого узла
	 */
	public void tickleUp(int index)
	{
		int parent = (index - 1) / 2;
		Node bottom = heapArray[index];

		/*перемещение вверх при обратном порядке*/
		while (index > 0 && heapArray[parent].getKey() < bottom.getKey() && !directOrder)
		{
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}

		/*перемещение вверх при прямом порядке*/
		while (index > 0 && heapArray[parent].getKey() > bottom.getKey() && directOrder)
		{
			heapArray[index] = heapArray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}

		heapArray[index] = bottom;
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
				largerChild = !directOrder ? rightChild : leftChild;
			}
			else
			{
				largerChild = !directOrder ? leftChild : rightChild;
			}

			if ((top.getKey() >= heapArray[largerChild].getKey() && !directOrder)
				|| (top.getKey() <= heapArray[largerChild].getKey() && directOrder))
			{
				break;
			}

			heapArray[index] = heapArray[largerChild];
			index = largerChild;
		}
		heapArray[index] = top;
	}

	/**
	 * Изменить приоритет узла в куче
	 *
	 * @param index    индекс узла
	 * @param newValue новый приоритет
	 * @return успешность применения изменений
	 */
	public boolean change(int index, int newValue)
	{
		if (index < 0 || index >= currentSize)
		{
			return false;
		}

		int oldValue = heapArray[index].getKey();
		heapArray[index].setKey(newValue);

		if (oldValue < newValue && !directOrder)
		{
			tickleUp(index);
		}
		else
		{
			trickleDown(index);
		}
		return true;
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
			System.out.println("");
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
	 * Программный проект 12.1 - Program project 12.1
	 * Вставить новый узел в пирамиду без проверки условий
	 *
	 * @param key данные нового узла
	 */
	public void toss(int key)
	{
		heapArray[currentSize++] = new Node(key);
		correctHeap = false;
	}

	/**
	 * Восстановить корректность пирамиды
	 */
	public void restoreHeap()
	{
		for (int i = currentSize - 1; i >= 0; i--)
		{
			tickleUp(i);
		}

		correctHeap = true;
	}
}

