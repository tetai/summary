package cn.zkz.structure.link;

public class TestLinkList {

    public static void main(String[] args) {
        MyLinkList<String> linkList = new MyLinkList<String>();
        linkList.add(0, "ss");
        linkList.add(0, "dd");
        linkList.add(1, "sfds");
        linkList.add(1, "s1fds");
        linkList.add(1, "s2fds");
        System.out.println("删除前"+linkList.toString());
        System.out.println("---------");

        linkList.remove("dd");
        linkList.remove("s1fds");
        System.out.println(linkList.toString());
    }
}
