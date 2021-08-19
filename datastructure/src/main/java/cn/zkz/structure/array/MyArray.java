package cn.zkz.structure.array;

import java.util.Arrays;
/**
 * 自定义可扩展数组
 */
public class MyArray<T> {

    private T[] data;
    private int size;

    public MyArray() {
        this(10);
    }

    public MyArray(int cap) {
        this.data = (T[]) new Object[cap];
        this.size = 0;
    }

    public int getSize() {
        return this.size;
    }

    public void add(int index, T item) {

        if (size == data.length) {
            reset(data.length * 2);
        }


        if (index <0 || index > size) {
            throw new RuntimeException("长度不正确，index=" + index);
        }
        for (int i=size-1; i>=index; i--) {
            data[i+1] = data[i];
        }
        data[index] = item;
        size++;
    }

    public int contain(T item) {
        for (int i=0; i<size; i++) {
            if (data[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    public T removeLast() {
       return removeIndex(size-1);
    }
    public T removeFirst() {

        return removeIndex(0);
    }

    public T removeIndex(int index) {
        T temp = data[index];
        if (index == -1) {
            throw new RuntimeException("无此元素");
        }

        if (index <0 || index > size) {
            throw new RuntimeException("长度不正确，index=" + index);
        }

        for (int i=index; i<size-1; i++) {
            data[i] = data[i+1];
        }
        size--;

        if (size == data.length/4) {
            reset(data.length/2);
        }
        return temp;
    }

    public T remove(T item) {

        int index = contain(item);

        return removeIndex(index);
    }



    private void reset(int cap) {
        T[] newData = (T[]) new Object[cap];
        for (int i=0; i<size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    public void addFirst(T item) {
        add(0, item);
    }

    public void add(T item) {
        add(size, item);
    }



    @Override
    public String toString() {
        return "MyArray{" +
                "data=" + Arrays.toString(data) +
                ", size=" + size +
                ", cap=" + data.length +
                '}';
    }

    public T getLast() {
        return data[size-1];
    }

    public T getFrist() {
        return data[0];
    }
}
