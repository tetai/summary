package cn.zkz.structure.stack;

public class TestStack {
    public static void main(String[] args) {

        StackImpl<String> stringStack = new StackImpl<>();
        stringStack.push("ss");
        stringStack.push("dd");
        stringStack.push("ff");
        stringStack.push("2s");
        System.out.println(stringStack.toString());
        stringStack.pop();
        stringStack.pop();

        System.out.println(stringStack.toString());


    }
}
