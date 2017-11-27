package chapter6;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Тестирование сущности {@link CheapCalculatorTest}
 *
 * @author rassoll
 * @created 27.11.2017
 * @$Author$
 * @$Revision$
 */
public class CheapCalculatorTest
{
	/** Тестирование метода рекурсивного умножения двух множителей */
	@Test
	public void checkMultiplicationMethod()
	{
		IntStream.range(1, 10).forEach(k -> assertEquals(k * k, CheapCalculator.multiplication(k, k)));
	}
}
