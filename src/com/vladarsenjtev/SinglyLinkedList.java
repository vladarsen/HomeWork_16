package com.vladarsenjtev;

import java.util.Objects;

public class SinglyLinkedList<E> implements CustomList<E> {
    private int size;
    private Node<E> first;

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
        add(first, element);
    }

    private void add(Node<E> current, E value) {
        if (current.next == null) {
            current.next = new Node<>(value);
            size++;
            return;
        }
        add(current.next, value);
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

        public ListIterator(Node<E> head) {
            currentNode = head;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E value = currentNode.getValue();
            currentNode = currentNode.getNext();
            return value;
        }
    }

    public ListIterator<E> iterator() {
        return new ListIterator<E>(first);
    }

    private static class Node<E> {
        E value;
        Node<E> next;

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