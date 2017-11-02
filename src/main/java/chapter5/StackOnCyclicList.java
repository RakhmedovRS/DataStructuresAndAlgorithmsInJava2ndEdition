package chapter5;

import base.Queue;

/**
 * Программный проект 5.4 - Program project 5.4
 * Стэк основанный на цикличном списке
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class StackOnCyclicList implements Queue
{
    CyclicList cyclicList;
    int counter = 0;

    public StackOnCyclicList()
    {
        cyclicList = new CyclicList();
    }

    @Override
    public void insert(long value)
    {
        cyclicList.insertFirst(counter++, value);
    }

    @Override
    public long remove()
    {
        return (long)cyclicList.deleteFirst().dData;
    }

    @Override
    public long peekFront()
    {
        return (long)cyclicList.deleteFirst().dData;
    }

    @Override
    public boolean isEmpty()
    {
        return cyclicList.isEmpty();
    }

    @Override
    public boolean isFull()
    {
        return false;
    }
}
