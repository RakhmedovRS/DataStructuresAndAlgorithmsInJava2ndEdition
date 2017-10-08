package chapter4;

/**
 *
 * Программный проект 4.2 - Program project 4.2
 *
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public class DequeueImpl implements Dequeue
{
    private int maxSize;
    private long[] deqArray;
    private int leftSide;
    private int rigthSide;
    private int nElements;

    public DequeueImpl(int maxSize)
    {
        this.maxSize = maxSize;
        deqArray = new long[this.maxSize];
        leftSide = 0;
        rigthSide = -1;
        nElements = 0;
    }


    /**
     * Вставка элемента в очередь, слева
     * @param value вставляемый элемент
     */
    @Override
    public void insertLeft(long value)
    {
        if (!isFull())
        {
            //Циклический перенос
            if (leftSide == 0)
            {
                leftSide = maxSize;
            }

            //Увеличение leftSide и вставка
            deqArray[--leftSide] = value;
            nElements++;
        }
        else
        {
            throw new UnsupportedOperationException(String.format("You can't insert %s dequeue is full" , value));
        }
    }

    /**
     * Вставка элемента в очередь, справа
     * @param value вставляемый элемент
     */
    @Override
    public void insertRight(long value)
    {
        if (!isFull())
        {
            //Циклический перенос
            if (rigthSide == maxSize - 1)
            {
                rigthSide = -1;
            }

            //Увеличение leftSide и вставка
            deqArray[++rigthSide] = value;
            nElements++;
        }
        else
        {
            throw new UnsupportedOperationException(String.format("You can't insert %s dequeue is full" , value));
        }
    }

    /**
     * Извлечение элемента из очереди, слева
     * @return элемент из очереди, слева
     */
    @Override
    public long removeLeft()
    {
        if (isEmpty())
        {
            throw new UnsupportedOperationException("You can't remove left element, dequeue is empty");
        }

        //Выборка и увеличение leftSide
        long temp = deqArray[leftSide++];

        //Циклический перенос
        if (leftSide == maxSize)
        {
            leftSide = 0;
        }

        nElements--;
        return temp;
    }

    /**
     * Извлечение элемента из очереди, справа
     * @return элемент из очереди, справа
     */
    @Override
    public long removeRight()
    {
        if (isEmpty())
        {
            throw new UnsupportedOperationException("You can't remove right element, dequeue is empty");
        }

        //Выборка и увеличение leftSide
        long temp = deqArray[rigthSide--];

        //Циклический перенос
        if (rigthSide == -1)
        {
            rigthSide = maxSize - 1;
        }

        nElements--;
        return temp;
    }

    /**
     * Просмотр первого элемента очереди, слева
     * @return первый элемент очереди, слева
     */
    @Override
    public long peekLeft()
    {
        return deqArray[leftSide];
    }

    /**
     * Просмотр первого элемента очереди, справа
     * @return первый элемент очереди, справа
     */
    @Override
    public long peekRight()
    {
        return deqArray[rigthSide];
    }

    @Override
    public boolean isEmpty()
    {
        return (nElements == 0);
    }

    @Override
    public boolean isFull()
    {
        return (nElements == maxSize);
    }
}
