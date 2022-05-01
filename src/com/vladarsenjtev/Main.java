package com.vladarsenjtev;


public class Main {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> digits = new SinglyLinkedList<>();
        digits.add(1);
        digits.add(5);
        digits.add(8);


        CustomIterator<Integer> inter = digits.iterator();

        while (inter.hasNext()) {
            System.out.println(inter.next());
        }
    }
}
