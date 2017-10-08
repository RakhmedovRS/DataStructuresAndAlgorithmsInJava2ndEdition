package chapter4;

/**
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public interface Queue
{
    void insert(long value);
    long remove();
    long peekFront();
    boolean isEmpty();
    boolean isFull();
}
