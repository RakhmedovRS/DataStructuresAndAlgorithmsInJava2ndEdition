package base.structures;

/**
 * Интерфейс описывающий структуру хэш-таблица
 *
 * @param <T> параметризующий тип данных
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public interface HashTable<T>
{
	/**
	 * Хэш-функция
	 *
	 * @param item элемент данных
	 * @return рассчитанное значение хэша
	 */
	int hashFunction(T item);

	/**
	 * Вставить элемент данных в хэш-таблицу
	 * предполагается что хэш-таблица не заполненна
	 *
	 * @param item элемент данных
	 */
	void insert(T item);

	/**
	 * Удалить элемент данных из хэщш-таблицы
	 *
	 * @param item элемент данных
	 * @return удаленный элемент данных
	 */
	T delete(T item);

	/**
	 * Найти элемент данных
	 * предполагается что хэш-таблица не заполненна
	 *
	 * @param item элемент данных
	 * @return найденный элемент данных
	 */
	T find(T item);

	/**
	 * @return данные для вывода хэш-таблицы на печать
	 */
	String getDisplayData();
}
