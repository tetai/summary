package cn.zkz.structure.queue;

import cn.zkz.structure.stack.StackImpl;

public class TestQueue {
    public static void main(String[] args) {

        MyQueue<String> stringStack = new MyQueue<>();
        stringStack.enqueue("ss");
        stringStack.enqueue("dd");
        stringStack.enqueue("ff");
        stringStack.enqueue("2s");
        System.out.println(stringStack.toString());
        stringStack.dequeue();
        stringStack.dequeue();

        System.out.println(stringStack.toString());


    }
}
