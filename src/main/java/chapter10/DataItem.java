package chapter10;

/**
 * Элемент данных для дерева 234
 *
 * @author rassoll
 * @created 23.01.2018
 * @$Author$
 * @$Revision$
 */
class DataItem
{
	public long dData;

	DataItem(long dData)
	{
		this.dData = dData;
	}

	/**
	 * @return данные для печати элемента данных
	 */
	String getDisplayData()
	{
		return String.format("/%s", dData);
	}
}
