package base;

/**
 * Интерфейс элемента двухсвязного списка
 *
 * @author rassoll
 * @created 13.02.2018
 * @$Author$
 * @$Revision$
 */
public interface DoubleLinkItem extends LinkItem
{
	@Override
	DoubleLinkItem getNext();

	@Override
	void setNext(LinkItem next);

	/**
	 * @return ссылка на предыдущий элемент
	 */
	DoubleLinkItem getPrevious();

	/**
	 * Установить ссылку на предыдущий элемент
	 *
	 * @param previous элемент
	 */
	void setPrevious(DoubleLinkItem previous);
}
