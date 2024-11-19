package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        firstNode.prev = new Node(item, firstNode);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        lastNode.next = new Node(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size += 1;
    }

//    @Override
//    public boolean isEmpty(){
//        if(size == 0){
//            return true;
//        }else {
//            return false;
//        }
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.next;
        T removeItem = removeNode.item;
        sentinel.next = sentinel.next.next;
        removeNode.next.prev = sentinel;
        size = size - 1;
        return removeItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removeNode = sentinel.prev;
        T removeItem = removeNode.item;
        sentinel.prev = removeNode.prev;
        removeNode.prev.next = sentinel;
        size = size - 1;
        return removeItem;
    }

    @Override
    public T get(int index) {
        if (index < 0) {
            return null;
        }
        int nodeIndex = 0;
        for (Node getNode = sentinel.next; getNode.item != null; getNode = getNode.next) {
            if (nodeIndex != index) {
                nodeIndex = nodeIndex + 1;
            } else {
                return getNode.item;
            }
        }
        return null;
    }

    private T getRecursiveHelper(Node getNode, int nodeIndex, int index) {
        if (nodeIndex == index) {
            return getNode.item;
        }
        if (getNode.item == null) {
            return null;
        }
        return getRecursiveHelper(getNode.next, nodeIndex += 1, index);
    }

    public T getRecursive(int index) {
        Node p = sentinel.next;
        if (index < 0) {
            return null;
        }
        int nodeIndex = 0;
        return getRecursiveHelper(p, nodeIndex, index);
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {
        private int wizPos;

        LinkedListDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T item = (T) get(wizPos);
            wizPos += 1;
            return item;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> ol = (Deque<T>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
