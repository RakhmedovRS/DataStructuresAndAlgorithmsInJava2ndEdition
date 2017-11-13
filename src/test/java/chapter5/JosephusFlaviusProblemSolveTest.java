package chapter5;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тестирование сущности {@link JosephusFlaviusProblem}
 *
 * @author rassoll
 * @created 13.11.2017
 * @$Author$
 * @$Revision$
 */
public class JosephusFlaviusProblemSolveTest
{
	@Test
	public void checkProblemSolving()
	{
		assertEquals("1 2 3 4 5 6 7 8 9 10 ", JosephusFlaviusProblem.solveProblem(10, 1, 1));
	}

	@Test
	public void checkProblemSolving2()
	{
		assertEquals("2 4 6 8 10 3 7 1 9 5 ", JosephusFlaviusProblem.solveProblem(10, 2, 1));
	}

	@Test
	public void checkProblemSolving3()
	{
		assertEquals("4 1 6 5 7 3 2 ", JosephusFlaviusProblem.solveProblem(7, 4, 1));
	}

}
