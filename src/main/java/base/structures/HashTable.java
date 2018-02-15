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
	float MAX_LOAD_FACTOR = 0.5F;
	/**
	 * Получить первое простое число большее чeм {@param min}
	 *
	 * @param min минимальное значение
	 * @return найденное простое число
	 */
	static int getPrime(int min)
	{
		for (int i = min + 1; true; i++)
		{
			if (isPrime(i))
			{
				return i;
			}
		}
	}

	/**
	 * Проверка числа того, что число {@param value} простое
	 *
	 * @param value проверяемое значение
	 * @return признак простого числа
	 */
	static boolean isPrime(int value)
	{
		for (int i = 2; (i * i <= value); i++)
		{
			if (value % 2 == 0)
			{
				return false;
			}
		}
		return true;
	}

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
	 * Программный проект 11.4 - Program project 11.4
	 * Увеличение размера хэш-таблицы вдвое
	 */
	void rehash();

	/**
	 * @return копия массива содержащего элементы хэш-таблицы
	 */
	T[] getHashArray();

	/**
	 * @return данные для вывода хэш-таблицы на печать
	 */
	String getDisplayData();
}
