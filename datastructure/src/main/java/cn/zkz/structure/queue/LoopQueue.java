package cn.zkz.structure.queue;

import cn.zkz.structure.array.MyArray;

import java.util.Arrays;
import java.util.Objects;

public class LoopQueue<T> implements Queue<T> {
    private T[] data;
    private int size;

    private int front;
    private int tail;

    public LoopQueue(int cap) {
        data = (T[]) new Object[cap];
        size=0;
    }
    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enqueue(T t) {
        // 进队列，如果数组已满，需要增容
        if ((tail+1)% data.length == front) {
            reset(data.length*2);
        }
        data[size] = t;
        tail = tail == data.length ? 0 :tail+1;
        size++;

    }

    private void reset(int cap) {
        T[] newData = (T[]) new Object[cap];
        for (int i=0; i<size; i++) {
            newData[i] = data[(i+front)%data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public T dequeue() {
        if (front == tail) {
            // 数据为空
            throw new RuntimeException("数据为空");
        }
        // 出队列，把front所在的数据出列

        T temp = data[front];
        data[front] = null;
        front = front == data.length ? 0 : front+1;
        size--;
        if (size == data.length/4) {
            reset(data.length/2);
        }
        return temp;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T getFront() {
        return data[front];
    }

    @Override
    public String toString() {
        return "LoopQueue{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", front=" + front +
                ", tail=" + tail +
                '}';
    }
}
