package chapter12;

/**
 * Сущность узла пирамиды
 *
 * @author rassoll
 * @created 18.02.2018
 * @$Author$
 * @$Revision$
 */
public class Node
{
	private int key;

	public Node(int key)
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

	/**
	 * Установить значение ключа
	 *
	 * @param key ключ
	 */
	public void setKey(int key)
	{
		this.key = key;
	}
}
