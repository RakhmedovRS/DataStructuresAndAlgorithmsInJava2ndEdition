package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Утилитный класс для работы с вводом пользователя
 *
 * @author rassoll
 * @created 12.02.2018
 * @$Author$
 * @$Revision$
 */
public abstract class Util
{
	/**
	 * Получить введенную строку из потока ввода
	 *
	 * @param inputStream поток ввода
	 * @return строка ввода
	 * @throws IOException в случае ошибок при получении строки
	 */
	public static String getString(InputStream inputStream) throws IOException
	{
		InputStreamReader isr = new InputStreamReader(inputStream);
		BufferedReader br = new BufferedReader(isr);
		return br.readLine();
	}

	/**
	 * Получить символ из потока ввода
	 *
	 * @param inputStream поток ввода
	 * @return введенный символ
	 * @throws IOException в случае ошибок при получении символа
	 */
	public static char getChar(InputStream inputStream) throws IOException
	{
		return getString(inputStream).charAt(0);
	}

	/**
	 * Получить число из потока ввода
	 *
	 * @param inputStream поток ввода
	 * @return введенное число
	 * @throws IOException в случае ошибок при получении числа
	 */
	public static int getInt(InputStream inputStream) throws IOException
	{
		return Integer.parseInt(getString(inputStream));
	}
}
