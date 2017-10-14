package base;

import chapter5.Link;

/**
 * @author rassoll
 * @created 14.10.2017
 * @$Author$
 * @$Revision$
 */
public interface LinkedList
{
    boolean isEmpty();

    void insertFirst(int iData, double dData);

    Link deleteFirst();

    Link find(int key);

    Link delete();

    Link getFirst();

    void displayList();
}
