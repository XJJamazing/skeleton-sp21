package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
    }

    //重新扩大数组
    private void reSize(int newSize) {
        T[] temp = (T[]) new Object[newSize];
        int ind = 0;
        for (int i = 0; i < size; i++) {
            ind = arrayInd(i);
            temp[newSize / 4 + i] = items[ind];
        }
        items = temp;
        nextFirst = newSize / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    //通过偏移量求所在位置
    private int arrayInd(int ind) {
        if (nextFirst + ind + 1 >= items.length) {
            return nextFirst + ind + 1 - items.length;
        } else {
            return nextFirst + ind + 1;
        }
    }

    @Override
    public void addFirst(T item) {
        if (size + 2 == items.length) {
            reSize((int) items.length * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length - 2) {
            reSize((int) (items.length * 2));
        }

        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size = size + 1;
    }

    @Override
    public int size() {
        return size;
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
    public void printDeque() {
        int ind = 0;
        for (int i = 0; i < size; i++) {
            ind = arrayInd(i);
            System.out.print(items[ind] + " ");
        }
    }

    private T getFirst() {
        int ind = arrayInd(0);
        return items[ind];
    }

    private T getLast() {
        int ind = arrayInd(size - 1);
        return items[ind];
    }

    @Override
    public T get(int i) {
        int ind =  arrayInd(i);
        return items[ind];
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            reSize(items.length / 2);
        }
        T item = getFirst();
        int ind = arrayInd(0);
        items[ind] = null;
        size = size - 1;
        nextFirst = ind;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            reSize(items.length / 2);
        }
        T item = getLast();
        int ind = arrayInd(size - 1);
        items[ind] = null;
        size = size - 1;
        nextLast = ind;
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {
        private int wizPos;

        ArrayDequeIterator() {
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
        Deque<T> oa = (Deque<T>) o;
        if (oa.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (!(oa.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
