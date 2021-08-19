package cn.zkz.structure.array;


public class TestArryay {

    public static void main(String[] args) {
        MyArray myArray = new MyArray();

        myArray.add(12);
        myArray.add(2);
        myArray.add(3);
        myArray.add(4);
        myArray.add(4);
        myArray.add(4);
        myArray.add(4);
        myArray.add(4);
        myArray.add(4);
        myArray.add(2,4);
        myArray.addFirst(22);
        myArray.addFirst(11);
        System.out.println(myArray);
        myArray.remove(12);
        myArray.remove(22);
        myArray.remove(4);
        myArray.remove(4);
        myArray.remove(4);
        myArray.remove(2);
        myArray.remove(4);
        myArray.remove(4);

        System.out.println(myArray);
    }
}
