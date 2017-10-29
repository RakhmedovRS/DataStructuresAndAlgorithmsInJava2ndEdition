package chapter6;

/**
 * Программный проект 6.3 - Program project 6.3
 *
 * @author rassoll
 * @created 29.10.2017
 * @$Author$
 * @$Revision$
 */
public class PowCalculator
{
    public static void main(String[] args)
    {
        System.out.println(pow(3,18));
    }

    /**
     * Возведение числа в степень
     * @param base основание
     * @param power степень
     * @return результат возведения числа {@param base} в степень {@param power}
     */
    private static int pow(int base, int power)
    {
        if (power == 1)
        {
            return base;
        }

        if (power%2 == 0)
        {
            return pow(base*base, power / 2);
        }
        else
        {
            return pow(base * base, power / 2) * base;
        }
    }
}
