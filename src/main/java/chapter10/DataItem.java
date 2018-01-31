package chapter10;

/**
 * Элемент данных для B-дерева
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
class DataItem
{
	private long dData;

	DataItem(long dData)
	{
		this.dData = dData;
	}

	/**
	 * @return значение элемента данных
	 */
	long getDData()
	{
		return dData;
	}

	/**
	 * @return данные для печати элемента данных
	 */
	String getDisplayData()
	{
		return String.format("/%s", dData);
	}
}
