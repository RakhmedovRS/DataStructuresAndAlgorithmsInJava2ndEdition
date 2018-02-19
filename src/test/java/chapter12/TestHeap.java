package chapter12;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link Heap}
 *
 * @author rassoll
 * @created 19.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestHeap
{
	private Heap heap;
	private int heapSize = 50;

	@Before
	public void init()
	{
		heap = new Heap(heapSize);
		IntStream.range(0, heapSize / 2).forEach((key) -> heap.insert((int) (java.lang.Math.random() * key * 10)));
	}

	/**
	 * Тестирование метода проверки пустоты кучи
	 */
	@Test
	public void testIsEmptyMethod()
	{
		assertFalse(heap.isEmpty());
		heap = new Heap(heapSize);
		assertTrue(heap.isEmpty());
	}

	/**
	 * Тестирование метода вставку в кучу
	 */
	@Test
	public void testInsertMethod()
	{
		IntStream.range(heapSize / 2, heapSize).forEach((key) -> assertTrue(heap.insert((int) (java.lang.Math.random() * key))));
		assertFalse(heap.insert((int) (java.lang.Math.random() * heapSize)));
	}

	/**
	 * Тестирование метода удаления из кучи с обратным порядком сортировки
	 */
	@Test
	public void testRemoveReverseOrder()
	{
		IntStream.range(0, (heapSize / 2) / 2).forEach((key) ->
			{
				Node previousNode = heap.remove();
				assertNotNull(previousNode);
				Node tempNode = heap.remove();
				assertNotNull(previousNode);
				assertTrue(previousNode.getKey() >= tempNode.getKey());
			}
		);
	}

	/**
	 * Тестирование метода удаления из кучи с прямым порядком сортировки
	 */
	@Test
	public void testRemoveDirectOrder()
	{
		heap = new Heap(heapSize, true);
		IntStream.range(1, heapSize / 2).forEach((key) -> heap.insert((int) (java.lang.Math.random() + 1 * key * key)));

		IntStream.range(1, (heapSize / 2) / 2).forEach((key) ->
			{
				Node previousNode = heap.remove();
				assertNotNull(previousNode);
				Node tempNode = heap.remove();
				assertNotNull(previousNode);
				assertTrue(previousNode.getKey() <= tempNode.getKey());
			}
		);
	}
}
