package cn.zkz.structure.queue;

import cn.zkz.structure.array.MyArray;

public class MyQueue<E> implements Queue<E> {

    MyArray<E> array;

    public MyQueue(int cap) {
        array = new MyArray<>(cap);
    }
    public MyQueue() {
        array = new MyArray<>();
    }
    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void enqueue(E e) {
        array.add(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public boolean isEmpty() {
        return array.getSize() == 0;
    }

    @Override
    public E getFront() {
        return array.getFrist();
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "array=" + array +
                '}';
    }
}
