package chapter6;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.junit.Assert.assertTrue;

/**
 * Тестирование сущности {@link PowCalculator}
 *
 * @author rassoll
 * @created 27.11.2017
 * @$Author$
 * @$Revision$
 */
public class PowCalculatorTest
{
	/** Тестирование метода рекурсивного возведения в степень */
	@Test
	public void testPowMethod()
	{
		IntStream.range(1, 10).forEach(k -> assertTrue(Math.pow(2, k) == PowCalculator.pow(2, k)));
	}
}
