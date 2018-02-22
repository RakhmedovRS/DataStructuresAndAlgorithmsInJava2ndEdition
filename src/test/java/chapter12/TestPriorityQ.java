package chapter12;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование сущности {@link PriorityQ}
 *
 * @author rassoll
 * @created 22.02.2018
 * @$Author$
 * @$Revision$
 */
public class TestPriorityQ
{
	private PriorityQ priorityQ;
	private int priorityQSize = 50;

	@Before
	public void init()
	{
		priorityQ = new PriorityQ(100);
		IntStream.range(priorityQSize / 2, priorityQSize).forEach((key) -> priorityQ.insert((int) (java.lang.Math.random() * key)));
	}

	/**
	 * Тестирование метода вставки в приоритетную очередь
	 */
	@Test
	public void testInsertMethod()
	{
		IntStream.range(priorityQSize / 2, priorityQSize).forEach((key) -> priorityQ.insert((int) (java.lang.Math.random() * key)));
	}

	/**
	 * Тестирование метода удаления из приоритетной очереди
	 */
	@Test
	public void testRemoveReverseOrder()
	{
		IntStream.range(0, (priorityQSize / 2) / 2).forEach((key) ->
			{
				int previousValue = priorityQ.remove();
				int tempValue = priorityQ.remove();
				assertTrue(previousValue >= tempValue);
			}
		);
	}
}
