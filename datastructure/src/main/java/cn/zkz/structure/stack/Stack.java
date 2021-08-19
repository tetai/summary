package cn.zkz.structure.stack;

public interface Stack<E> {

    int getSize();

    void push(E e);

    E pop();

    boolean isEmpty();

    E peep();
}
