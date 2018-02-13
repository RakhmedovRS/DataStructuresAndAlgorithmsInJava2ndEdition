package base;

/**
 * Интерфейс элемента связаного списка
 *
 * @author rassoll
 * @created 13.02.2018
 * @$Author$
 * @$Revision$
 */
public interface LinkItem extends Item
{
	/**
	 * @return значение элемента
	 */
	double getData();

	/**
	 * @return ссылка на следующий элемент
	 */
	LinkItem getNext();

	/**
	 * Установить ссылку на следующий элемент
	 *
	 * @param next элемент
	 */
	void setNext(LinkItem next);

	/**
	 * Вывести данные о элементе списка на печать
	 */
	void displayLink();
}
