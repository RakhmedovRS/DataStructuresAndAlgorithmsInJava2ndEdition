package base;

import chapter5.Link;

/**
 * Интерфейс описывающий односторонний связанный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public interface LinkedList
{
	/**
	 * Проверить пустоту коллекции
	 *
	 * @return признак отсутствия элементов в коллекции
	 */
	boolean isEmpty();

	/**
	 * Вставка элемента в список
	 *
	 * @param iData ключ
	 * @param dData значение
	 */
	void insertFirst(int iData, double dData);

	/**
	 * Удаление первого элемента в списке
	 *
	 * @return удаленный элемент
	 */
	Link deleteFirst();

	/**
	 * Поиск элемента в списке
	 *
	 * @param key ключ для поиска
	 * @return найденный элемент
	 */
	Link find(int key);

	/**
	 * Удаление элемента с ключем key из списка
	 *
	 * @param key ключ для поиска удаляемого элемента
	 * @return найденный элемент
	 */
	Link delete(int key);

	/**
	 * Получение первого элемента в списке
	 *
	 * @return первый элемент
	 */
	Link getFirst();

	/**
	 * Вывод содержимого массива
	 */
	void displayList();
}
