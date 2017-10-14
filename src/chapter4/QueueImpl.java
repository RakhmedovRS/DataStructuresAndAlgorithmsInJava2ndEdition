package chapter4;

import base.Queue;

/**
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public class QueueImpl implements Queue
{
    private int maxSize;
    private long[] queArray;
    private int front;
    private int rear;
    private int nElements;

    public QueueImpl(int maxSize)
    {
        this.maxSize = maxSize;
        queArray = new long[this.maxSize];
        front = 0;
        rear = -1;
        nElements = 0;
    }

    /**
     * Вставка элемента в конец очереди
     * @param value вставляемые элемент
     */
    @Override
    public void insert(long value)
    {
        if (isFull())
        {
            throw new UnsupportedOperationException("QueueImpl is full");
        }

        //Циклический перенос
        if (rear == maxSize - 1)
        {
            rear = -1;
        }

        //Увеличение rear и вставка
        queArray[++rear] = value;
        nElements++;
    }

    /**
     * Извлечение элемента из начала очереди
     * @return элемент из начала очереди
     */
    @Override
    public long remove()
    {
        //Выборка и увеличение front
        long temp = queArray[front++];

        //Циклический перенос
        if (front == maxSize)
        {
            front = 0;
        }

        nElements--;
        return temp;
    }

    /**
     * Чтение элемента в начале очереди
     * @return элемент в начале очереди
     */
    @Override
    public long peekFront()
    {
        return queArray[front];
    }

    /**
     * @return признак "пустоты" очереди
     */
    @Override
    public boolean isEmpty()
    {
        return (nElements == 0);
    }

    /**
     * @return признак полностью заполненной очереди
     */
    @Override
    public boolean isFull()
    {
        return (nElements == maxSize);
    }

    /**
     * @return количество элементов в очереди
     */
    int size()
    {
        return nElements;
    }

    /**
     * Программный проект 4.1 - Program project 4.1
     */
    void print()
    {
        if (isEmpty())
        {
            System.out.println("QueueImpl is empty");
        }
        else
        {
            while (!isEmpty())
            {
                long n  = remove();
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }

}
