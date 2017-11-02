package chapter5;

import base.DoublyLinkedList;

/**
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class DLinkList implements DoublyLinkedList
{
    public DoubleLink first;
    public DoubleLink last;

    public DLinkList()
    {
        first = null;
        last = null;
    }

    public DoubleLink getFirst()
    {
        return first;
    }

    public DoubleLink getLast()
    {
        return last;
    }

    @Override
    public boolean isEmpty()
    {
        return (first == null);
    }

    /**
     * Вставка элемента в начало списка
     *
     * @param value вставляемый элемент
     */
    @Override
    public void insertFirst(long value)
    {
        DoubleLink newLink = new DoubleLink(value);
        if (isEmpty())
        {
            last = newLink;
        }
        else
        {
            first.previous = newLink;
            newLink.next = first;
        }
        first = newLink;
    }

    /**
     * Вставка элемента в конец списка
     *
     * @param value вставляемый элемент
     */
    @Override
    public void insertLast(long value)
    {
        DoubleLink newLink = new DoubleLink(value);
        if (isEmpty())
        {
            first = newLink;
        }
        else
        {
            last.next = newLink;
            newLink.previous = last;
        }
        last = newLink;
    }

    /**
     * Удаление элемента из начала списка
     *
     * @return удаленный элемент
     */
    @Override
    public DoubleLink deleteFirst()
    {
        DoubleLink temp = first;
        if (first.next == null)
        {
            last = null;
        }
        else
        {
            first.next.previous = null;
        }
        first = first.next;

        return temp;
    }

    /**
     * Удаление элемента из конца списка
     *
     * @return удаленный элемент
     */
    @Override
    public DoubleLink deleteLast()
    {
        DoubleLink temp = first;
        if (first.next == null)
        {
            first = null;
        }
        else
        {
            last.previous.next = null;
        }
        last = last.previous;

        return temp;
    }

    /**
     * Вставка элемента dd после элемента key
     *
     * @param key элемент после которого нужно выполнить вставку
     * @param dd  вставляемый элемент
     * @return признак успешности вставки
     */
    @Override
    public boolean insertAfter(long key, long dd)
    {
        DoubleLink current = first;
        while (current.dData != key)
        {
            current = current.next;
            if (current == null)
            {
                return false;
            }
        }

        DoubleLink newLink = new DoubleLink(dd);
        if (current == last)
        {
            newLink.next = null;
            last = newLink;
        }
        else
        {
            newLink.next = current.next;
            current.next.previous = newLink;
        }

        newLink.previous = current;
        current.next = newLink;

        return true;
    }

    /**
     * Удаление конкретного элемента
     *
     * @param key удаляемый элемент
     * @return признак успешности удаления
     */
    @Override
    public DoubleLink deleteKey(long key)
    {
        DoubleLink current = first;

        while (current.dData != key)
        {
            current = current.next;
            if (current == null)
            {
                return null;
            }
        }

        if (current == first)
        {
            first = current.next;
        }
        else
        {
            current.previous.next = current.next;
        }

        if (current == last)
        {
            last = current.previous;
        }
        else
        {
            current.next.previous = current.previous;
        }

        return current;
    }

    /**
     * Отобразить содержимое в прямом порядке от first до last
     */
    @Override
    public void displayForward()
    {
        System.out.print("List (first-->last): ");
        DoubleLink current = first;

        while (current != null)
        {
            current.displayLink();
            current = current.next;
        }
        System.out.println("");
    }

    /**
     * Отобразить содержимое в обратном порядке от last до first
     */
    @Override
    public void displayBackward()
    {
        System.out.print("List (last-->first): ");
        DoubleLink current = last;

        while (current != null)
        {
            current.displayLink();
            current = current.previous;
        }
        System.out.println("");
    }
}
