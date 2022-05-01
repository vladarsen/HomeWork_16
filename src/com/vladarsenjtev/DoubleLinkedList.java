package com.vladarsenjtev;


import java.util.Objects;

public class DoubleLinkedList<E> implements CustomList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E element) {
        if (first == null) {
            first = new Node<>(element);
            size++;
            return;
        }
        add(first, last, element);
    }

    private void add(Node<E> current, Node<E> prev, E value) {
        if (current.next == null) {
            current.next = new Node<>(value);
            last = current.prev;
            size++;
            return;
        }
        add(current.next, last, value);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);

        Node<E> current = first;
        for (int i = 1; i <= index; i++) {
            current = current.next;
        }
        return current.value;
    }

    @Override
    public boolean remove(int index) {
        Objects.checkIndex(index, size);

        Node<E> prev = null;
        Node<E> current = first;
        for (int i = 1; i <= index; i++) {
            prev = current;
            current = current.next;
        }

        if (prev == null) {
            if (current.next == null) {
                first = null;
                size--;
                return true;
            }
            first = current.next;
            size--;
            return true;
        }

        prev.next = current.next;
        size--;
        return true;
    }

    private class ListIterator<Е> implements CustomIterator<E> {
        private Node<E> currentNode;
        private Node<E> lastNode;

        public ListIterator(Node<E> currentNode, Node<E> lastNode) {
            this.currentNode = currentNode;
            this.lastNode = lastNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E value = currentNode.getValue();
            currentNode = currentNode.getNext();
            lastNode = currentNode;
            return value;
        }
    }

    public ListIterator<E> iterator() {
        return new ListIterator<E>(first,last);
    }

    private static class Node<E> {
        E value;
        Node<E> next;
        Node<E> prev;

        Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }
}
