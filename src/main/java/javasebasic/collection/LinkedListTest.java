package javasebasic.collection;

import java.util.LinkedList;

public class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println(list);

        list.addFirst("first");
        list.offer("last");

        System.out.println(list);

        list.add(2, "a"); //can put in a duplicated element
        System.out.println(list);

        System.out.println(list.get(2));
    }
}
