package chapter5;

import base.LinkedList;

/**
 * Программный проект 5.3 - Program project 5.3
 * Циклический список
 *
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public class CyclicList implements LinkedList
{
    Link current;

    public CyclicList()
    {
        current = null;
    }

    public Link step()
    {
        if (current != null)
        {
            return current.next;
        }
        else
        {
            return null;
        }
    }

    @Override
    public boolean isEmpty()
    {
        return (current == null);
    }

    /**
     * Вставка элемента в список
     *
     * @param iData ключ
     * @param dData значение
     */
    @Override
    public void insertFirst(int iData, double dData)
    {
        Link newLink = new Link(iData, dData);

        if (current == null)
        {
            current = newLink;
            newLink.next = current;
        }
        else
        {
            newLink.next = current.next;
            current.next = newLink;
        }
    }

    /**
     * Удалить элемент списка следующий за current
     *
     * @return удаленный элемент
     */
    @Override
    public Link deleteFirst()
    {
        Link temp = current;

        if (!isEmpty())
        {
            if (current.next == current)
            {
                temp = current;
                current = null;
            }
            else
            {
                temp = current.next;
                current.next = current.next.next;
            }
        }

        return temp;
    }

    @Override
    public Link find(int key)
    {
        if (!isEmpty())
        {
            Link temp = this.current;
            while (temp.iData != key)
            {
                if ((temp.next == null) || (temp.next == this.current))
                {
                    return null;
                }
                else
                {
                    temp = temp.next;
                }
            }

            return temp;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Link delete(int key)
    {
        if (!isEmpty())
        {
            Link temp = current;
            Link prev = null;
            while (temp.iData != key)
            {
                if ((temp.next == null) || (temp.next == this.current))
                {
                    return null;
                }
                else
                {
                    prev = temp;
                    temp = temp.next;
                }
            }

            if (prev != null)
            {
                prev.next = temp.next;
            }
            else
            {
                current = current.next;
            }

            return temp;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Link getFirst()
    {
        return current;
    }

    @Override
    public void displayList()
    {
        Link temp = current;
        System.out.print("List (current->current-1): ");
        if (!isEmpty())
        {
            while (true)
            {
                System.out.print(temp.dData + " ");
                temp = temp.next;

                if (temp == current)
                {
                    break;
                }
            }
        }
        else
        {
            System.out.print("null ");
        }
        System.out.println("");
    }
}
