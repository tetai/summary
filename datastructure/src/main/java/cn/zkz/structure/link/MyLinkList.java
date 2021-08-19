package cn.zkz.structure.link;

/**
 * 链表
 */
public class MyLinkList<E> {

    private class Node{
        E e;
        Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
         public Node(E e) {
            this(e,null);
        }
        public Node() {
            this(null,null);
        }
    }

    private Node head;
    int size;

    public MyLinkList() {
        size = 0;
        head = null;
    }

    public int getSize() {
        return size;
    }

    /**
     * 往链表特定位置添加元素
     * @param index
     * @param item
     */
    public void add(int index, E item) {
        if ((index <0 || index >= size) && size !=0) {
            throw new RuntimeException("index <0 || index >= size");
        }
        Node addItem = new Node(item);
        if (index == 0) {
            if (head == null ) {
                head = addItem;
            }else {
                addItem.next = head;
                head = addItem;
            }
        }else {
            Node pre = head;
            for (int i=0;i<index-1;i++) {
                pre = pre.next;
            }
            addItem.next = pre.next;
            pre.next = addItem;
        }
        size++;
    }

    /**
     * 删除特定元素
     * @param item
     */
    public void remove(E item) {

        // 判断开头元素是否等于需要删除元素
        while (head.e.equals(item)) {
            head = head.next;
            size--;
        }
        if (size == 0) {
            return;
        }

        Node node = head;
        while (node != null && node.next != null) {
            if (node.next.e.equals(item)) {
                node.next = node.next.next;

                size--;
            }
            node = node.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(head.e.toString() + "->");
        Node node = head;
        for (int i=0;i<size-1; i++) {
            node = node.next;
            s.append(node.e.toString() + "->");

        }
        return s.toString();
    }
}
