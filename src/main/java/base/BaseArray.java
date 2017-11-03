package base;

/**
 * Базовый интерфейс массива
 *
 * @author rassoll
 * @created 03.10.2017
 * @$Author$
 * @$Revision$
 */
public interface BaseArray
{
	/**
	 * Вставка элемента в массив
	 *
	 * @param value вставляемый элемент
	 */
	void insert(long value);

	/**
	 * Вывод содержимого массива
	 */
	void display();

	/**
	 * Сортировка массива
	 */
	void sort();

	/**
	 * Дополнительный метод для тестирования
	 *
	 * Получить содержимое массива
	 *
	 * @return содерждимое массива
	 */
	String getValues();
}
