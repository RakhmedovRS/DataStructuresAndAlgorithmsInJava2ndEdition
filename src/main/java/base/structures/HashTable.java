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
	 * @return коэффициент наполненности хэш-таблицы
	 */
	default float getLoadFactor()
	{
		return (float) getElementsNumber() / getHashTableSize();
	}

	/**
	 * @return текущий максимальный размер хэш-таблицы
	 */
	int getHashTableSize();

	/**
	 * @return количество элементов находящихся в хэш-таблице
	 */
	int getElementsNumber();

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
