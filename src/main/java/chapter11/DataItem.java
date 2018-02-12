package chapter11;

/**
 * Сущность элемента данных
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class DataItem
{
	private int key;

	/**
	 * Конструктор
	 *
	 * @param key ключ
	 */
	public DataItem(int key)
	{
		this.key = key;
	}

	/**
	 * @return ключ
	 */
	public int getKey()
	{
		return key;
	}
}
