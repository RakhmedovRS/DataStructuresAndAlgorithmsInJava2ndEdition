package base;

/**
 * Интерфейс описывающий структуру хэш-таблица
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 *
 * @param <T> сущность реализающая {@link Item}
 */
public interface HashTable<T extends Item>
{
	/**
	 * Хэш-функция
	 *
	 * @param key ключ
	 * @return рассчитанное значение хэша
	 */
	int hashFunction(int key);

	/**
	 * Вставить элемент данных в хэш-таблицу
	 * предполагается что хэш-таблица не заполненна
	 *
	 * @param item элемент данных
	 */
	void insert(Item item);

	/**
	 * Удалить элемент данных из хэщш-таблицы
	 *
	 * @param key ключ удаляемого элемента данных
	 * @return удаленный элемент данных
	 */
	T delete(int key);

	/**
	 * Найти элемент данных
	 * предполагается что хэш-таблица не заполненна
	 *
	 * @param key ключ элемента данных
	 * @return найденный элемент данных
	 */
	T find(int key);

	/**
	 * @return данные для вывода хэш-таблицы на печать
	 */
	String getDisplayData();
}
