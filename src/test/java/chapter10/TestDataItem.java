package chapter10;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование сущности {@link DataItem}
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
public class TestDataItem
{

	/**
	 * Тестирование метода получения данных для печати
	 */
	@Test
	public void testGetDisplayData()
	{
		DataItem dataItem = new DataItem(1L);

		assertEquals(dataItem.getDisplayData(), String.format("/%s", dataItem.getDData()));

		dataItem = new DataItem(10L);

		assertEquals(dataItem.getDisplayData(), String.format("/%s", dataItem.getDData()));
	}
}
