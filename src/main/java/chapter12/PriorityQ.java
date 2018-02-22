package chapter12;

/**
 * Сущность приоритетной очереди на базе пирамиды
 *
 * @author rassoll
 * @created 22.02.2018
 * @$Author$
 * @$Revision$
 */
public class PriorityQ
{
	private Heap heap;

	public PriorityQ(int maxSize)
	{
		heap = new Heap(maxSize, false);
	}

	/**
	 * Вставить значение в очередь
	 *
	 * @param value значение
	 */
	public void insert(int value)
	{
		heap.insert(value);
	}

	/**
	 * @return первое значение очереди
	 */
	public int remove()
	{
		return heap.remove().getKey();
	}
}
