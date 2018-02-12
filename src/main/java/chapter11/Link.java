package chapter11;

import base.Item;

/**
 * Сущность элемент списка
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public class Link implements Item
{
	private int data;
	public Link next;

	/**
	 * Конструктор
	 *
	 * @param data значение элемента данных
	 */
	public Link(int data)
	{
		this.data = data;
	}

	@Override
	public int getKey()
	{
		return data;
	}
}
