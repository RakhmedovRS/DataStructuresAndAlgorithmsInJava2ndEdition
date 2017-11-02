package chapter5;

import base.LinkedList;

/**
 * Сортированный односвязный список
 *
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public class SortedLinkedList implements LinkedList
{
    private Link first;

    public SortedLinkedList()
    {
        first = null;
    }

    @Override
    public boolean isEmpty()
    {
        return (first == null);
    }

    /**
     * Вставка элемента в список в соотвествии сортировки по значению
     *
     * @param iData ключ
     * @param dData значение
     */
    @Override
    public void insertFirst(int iData, double dData)
    {
        Link newLink = new Link(iData, dData);

        if (!isEmpty())
        {
            if (first.dData > dData)
            {
                newLink.next = first;
                first = newLink;
            }
            else
            {
                Link current = first;
                while (current.next != null)
                {
                    if (current.dData <= dData && current.next.dData > dData)
                    {
                        break;
                    }
                    current = current.next;
                }
                newLink.next = current.next;
                current.next = newLink;
            }
        }
        else
        {
            newLink.next = first;
            first = newLink;
        }
    }

    /**
     * Удалить первый элемент списка
     *
     * @return удаленный элемент
     */
    @Override
    public Link deleteFirst()
    {
        Link temp = first;
        first = first.next;
        return temp;
    }

    /**
     * Поиск элемента с заданным ключом
     *
     * @param key ключ
     * @return найденный элемент
     */
    @Override
    public Link find(int key)
    {
        Link current = first;
        while (current.iData != key)
        {
            if (current.next == null)
            {
                return null;
            }
            else
            {
                current = current.next;
            }
        }

        return current;
    }

    /**
     * Удалить элемент с заданным ключом
     *
     * @param key ключ
     * @return удаленный элемент
     */
    @Override
    public Link delete(int key)
    {
        Link current = first;
        Link prev = first;
        while (current.iData != key)
        {
            if (current.next == null)
            {
                return null;
            }
            else
            {
                current = current.next;
            }
        }
        prev.next = current.next;

        return current;
    }

    /**
     * Получить ссылку на первый элемент списка
     *
     * @return первый элемент списка
     */
    @Override
    public Link getFirst()
    {
        return first;
    }

    /**
     * Вывести содержимое списка, путем перебора начиная с первого элемента
     */
    @Override
    public void displayList()
    {
        System.out.print(String.format("List (first-->last): "));
        Link current = first;
        while (current.next != null)
        {
            current.displayLink();
            current = current.next;
        }
        current.displayLink();
        System.out.println("");
    }
}
