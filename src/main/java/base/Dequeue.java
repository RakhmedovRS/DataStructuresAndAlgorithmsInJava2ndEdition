package base;

/**
 * Интерфейс описывающий структуру ДЕК или двухсторонняяя очередь
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public interface Dequeue
{
	/**
	 * Вставка элемента слева
	 *
	 * @param value вставляемый элемент
	 */
	void insertLeft(long value);

	/**
	 * Вставка элемента справа
	 *
	 * @param value вставляемый элемент
	 */
	void insertRight(long value);

	/**
	 * Удаление элемента слева
	 *
	 * @return удаленный элемент
	 */
	long removeLeft();

	/**
	 * Удаление элемента справа
	 *
	 * @return удаленный элемент
	 */
	long removeRight();

	/**
	 * Получить элемент слева
	 *
	 * @return элемент слева
	 */
	long peekLeft();

	/**
	 * Получить элемент справа
	 *
	 * @return элемент справа
	 */
	long peekRight();

	/**
	 * Проверить пустоту коллекции
	 *
	 * @return признак отсутствия элементов в коллекции
	 */
	boolean isEmpty();

	/**
	 * Проверить заполненность коллекции
	 *
	 * @return признак заполненности коллекции
	 */
	boolean isFull();
}
