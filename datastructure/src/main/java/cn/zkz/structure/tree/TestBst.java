package cn.zkz.structure.tree;

public class TestBst {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        bst.add(11);
        bst.add(2);
        bst.add(3);
        bst.add(55);
        bst.add(52);
        System.out.println(bst);
        bst.preOrder();
        BST<Integer>.Node<Integer> integerNode = bst.get(2);
        System.out.println(bst.get(11));
    }
}
