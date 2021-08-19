package cn.zkz.structure.stack;

import cn.zkz.structure.array.MyArray;

public class StackImpl<E> implements Stack<E> {

    MyArray<E> array;

    public StackImpl(int cap) {
        array = new MyArray<>(cap);
    }
    public StackImpl() {
        array = new MyArray<>();
    }
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void push(E e) {
        array.add(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public E peep() {
        return array.getLast();
    }

    @Override
    public String toString() {
        return "StackImpl{" +
                "array=" + array.toString() +
                '}';
    }
}
