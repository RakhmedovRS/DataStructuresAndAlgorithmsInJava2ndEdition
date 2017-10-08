package chapter4;

/**
 * @author rassoll
 * @created 08.10.2017
 * @$Author$
 * @$Revision$
 */
public interface Dequeue
{
    void insertLeft(long value);
    void insertRight(long value);
    long removeLeft();
    long removeRight();
    long peekLeft();
    long peekRight();
    boolean isEmpty();
    boolean isFull();
}
