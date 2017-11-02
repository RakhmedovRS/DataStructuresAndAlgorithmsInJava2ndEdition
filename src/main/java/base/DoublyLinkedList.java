package base;

import chapter5.DoubleLink;

/**
 * @author rassoll
 * @created 15.10.2017
 * @$Author$
 * @$Revision$
 */
public interface DoublyLinkedList
{
    boolean isEmpty();

    void insertFirst(long value);

    void insertLast(long value);

    DoubleLink deleteFirst();

    DoubleLink deleteLast();

    boolean insertAfter(long key, long dd);

    DoubleLink deleteKey(long key);

    void displayForward();

    void displayBackward();
}
