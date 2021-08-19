package cn.zkz.structure.queue;

public class TestLoopQueue {
    public static void main(String[] args) {

        LoopQueue<String> stringStack = new LoopQueue<>(2);
        stringStack.enqueue("ss");
        stringStack.enqueue("dd");
        stringStack.enqueue("ff");
        stringStack.enqueue("2s");
        System.out.println(stringStack.toString());
        stringStack.dequeue();
        stringStack.dequeue();
        stringStack.dequeue();

        System.out.println(stringStack.toString());


    }
}
