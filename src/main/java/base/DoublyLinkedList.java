package base;

/**
 * Интерфейс описывающий двухторонний связанный список
 *
 * @param <T> тип реализующий интерфейс {@link DoubleLinkItem}
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public interface DoublyLinkedList<T extends DoubleLinkItem>
{
	/**
	 * Проверить пустоту коллекции
	 *
	 * @return признак отсутствия элементов в коллекции
	 */
	boolean isEmpty();

	/**
	 * Вставка элемента в начало списка
	 *
	 * @param value вставляемый элемент
	 */
	void insertFirst(long value);

	/**
	 * Вставка элемента в конец списка
	 *
	 * @param value вставляемый элемент
	 */
	void insertLast(long value);

	/**
	 * Удаление элемента из начала списка
	 *
	 * @return удаленный элемент
	 */
	T deleteFirst();

	/**
	 * Удаление элемента из конца списка
	 *
	 * @return удаленный элемент
	 */
	T deleteLast();

	/**
	 * Вставка элемента dd после элемента key
	 *
	 * @param key элемент после которого нужно выполнить вставку
	 * @param dd  вставляемый элемент
	 * @return признак успешности вставки
	 */
	boolean insertAfter(long key, long dd);

	/**
	 * Удаление конкретного элемента
	 *
	 * @param key удаляемый элемент
	 * @return признак успешности удаления
	 */
	T deleteKey(long key);

	/**
	 * Отобразить содержимое в прямом порядке от first до last
	 */
	void displayForward();

	/**
	 * Отобразить содержимое в обратном порядке от last до first
	 */
	void displayBackward();
}
