package base;

/**
 * * Интерфейс описывающий односторонний связанный список
 *
 * @param <T> тип расширающий интерфейс {@link LinkItem}
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public interface LinkedList<T extends LinkItem>
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
	 * @param key ключ
	 * @param data значение
	 */
	void insert(int key, double data);

	/**
	 * Удаление первого элемента в списке
	 *
	 * @return удаленный элемент
	 */
	T deleteFirst();

	/**
	 * Поиск элемента в списке
	 *
	 * @param key ключ для поиска
	 * @return найденный элемент
	 */
	T find(int key);

	/**
	 * Удаление элемента с ключем key из списка
	 *
	 * @param key ключ для поиска удаляемого элемента
	 * @return найденный элемент
	 */
	T delete(int key);

	/**
	 * Получение первого элемента в списке
	 *
	 * @return первый элемент
	 */
	T getFirst();

	/**
	 * Вывод содержимого массива
	 */
	void displayList();
}
