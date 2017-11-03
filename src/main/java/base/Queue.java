package base;

/**
 * Интерфейс описывающий очередь
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public interface Queue
{

	/**
	 * Вставка элемента в очередь
	 *
	 * @param value вставляемый элемент
	 * @throws UnsupportedOperationException исключение в случе если дальнейшее добавление элементов невозможно по причине заполненности очереди
	 */
	void insert(long value) throws UnsupportedOperationException;

	/**
	 * Удаление элемента из очереди
	 *
	 * @return удаленный элемент
	 */
	long remove();

	/**
	 * Получение первого элемента в очереди
	 *
	 * @return первый элемент в очереди
	 */
	long peekFront();

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
