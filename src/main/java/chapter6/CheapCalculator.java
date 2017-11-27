package chapter6;

/**
 * Программный проект 6.1 - Program project 6.1
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class CheapCalculator
{
	public static void main(String[] args)
	{
		System.out.println(multiplication(5, 5));
	}

	/**
	 * Рекурсивное умножение двух множителей
	 *
	 * @param val1 первый множитель
	 * @param val2 второй множитель
	 * @return произведение множителей
	 */
	static int multiplication(int val1, int val2)
	{
		if (val2 == 1)
		{
			return val1;
		}

		return val1 + multiplication(val1, val2 - 1);
	}
}
