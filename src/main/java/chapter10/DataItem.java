package chapter10;

import base.Item;

/**
 * Элемент данных для B-дерева
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
class DataItem implements Item
{
	private long key;

	DataItem(long key)
	{
		this.key = key;
	}

	/**
	 * @return значение элемента данных
	 */
	public int getKey()
	{
		return (int)key;
	}

	/**
	 * @return данные для печати элемента данных
	 */
	public String getDisplayData()
	{
		return String.format("/%s", key);
	}
}
