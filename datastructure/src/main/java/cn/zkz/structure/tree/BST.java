package cn.zkz.structure.tree;

/**
 * 而分搜索树
 */
public class BST<E extends Comparable> {

    class Node<E> {
        private Node left;
        private Node right;
        private E val;

        public Node(E e) {
            val = e;
        }

        @Override
        public String toString() {
            return val+"";
        }
    }

    private Node<E> root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        // 如果根节点为空，则赋值根节点，否则遍历位置
        Node<E> ele = new Node<>(e);
        if (root == null) {
            root = ele;
            size ++;
        }else {
            add(root, e);
        }


    }

    private void add(Node<E> node, E e) {

        if (e.equals(node.val)) {
            return;
        }else if (e.compareTo(node.val) < 0 && node.left == null) {
            node.left = new Node<>(e);
            size++;
            return;
        }else if (e.compareTo(node.val) > 0 && node.right == null){
            node.right = new Node<>(e);
            size++;
            return;
        }
        if(e.compareTo(node.val) < 0) {
            // 放到左子树
            add(node.left, e);
        }else {
            add(node.right, e);
        }
    }

    /**
     * 先序遍历
     * @return
     */
    public void preOrder() {
        preOrder(root);
    }

    public void preOrder(Node node) {
        if (node == null) {
            return ;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 获取数据
     * @param e
     * @return
     */
    public Node<E> get(E e) {
        if (e.equals(root.val)) {
            return root;
        }
        return get(root, e);
    }

    private Node<E> get(Node node, E e) {
        if (node == null || node.val.equals(e)) {
            return node;
        }
        if (e.compareTo(node.val) < 0) {
            return get(node.left, e);
        }else {
            return get(node.right, e);
        }

    }

}
