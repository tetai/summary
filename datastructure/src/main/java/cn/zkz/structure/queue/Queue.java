package cn.zkz.structure.queue;

public interface Queue<E> {

    int getSize();

    void enqueue(E e);

    E dequeue();

    boolean isEmpty();

    E getFront();
}
