package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        size = 0;
        nextFirst = 4;
        nextLast = 5;
        items =(T[]) new Object[8];
    }

    //重新扩大数组
    private void reSize(int newSize){
        T[] temp =(T[]) new Object[newSize];
        int ind = 0;
        for (int i = 0; i < size; i++){
            ind = arrayInd(i);
            temp[newSize / 4 + i] = items[ind];
        }
        items = temp;
        nextFirst = newSize / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    //通过偏移量求所在位置
    private int arrayInd(int ind){
        if (nextFirst + ind + 1 > items.length){
            return nextFirst + ind + 1 - items.length;
        }else {
            return nextFirst + ind + 1;
        }
    }

    public void addFirst(T item){
        if (size + 2 == items.length){
            reSize((int) items.length * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0){
            nextFirst = items.length - 1;
        }else {
            nextFirst -= 1;
        }
        size += 1;
    }

    public void addLast(T item){
        if (size + 2 == items.length){
            reSize((int) items.length * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1){
            nextLast = 0;
        }else {
            nextLast += 1;
        }
        size += 1;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else {
            return false;
        }
    }

    public void printDeque(){
        int ind = 0;
        for (int i = 0; i < size; i++){
            ind = arrayInd(i);
            System.out.print(items[ind] + " ");
        }
    }

    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        if (size - 1 < items.length / 4 && size > 8){
            reSize(items.length / 2);
        }
        T item = items[arrayInd(0)];
        items[arrayInd(0)] = null;
        size = size - 1;
        nextFirst = arrayInd(0);
        return item;
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        if (size - 1 < items.length / 4 && size > 8){
            reSize(items.length / 2);
        }
        int ind = arrayInd(size - 1);
        T item = items[ind];
        size = size - 1;
        nextLast = arrayInd(ind);
        return item;
    }

    public T get(int index){
        int ind = arrayInd(index);
        return items[ind];
    }

    @Override
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator<T> implements Iterator<T>{
        private int wizPos;

        public ArrayDequeIterator(){
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

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null){
            return false;
        }
        if (!(o instanceof ArrayDeque)){
            return false;
        }
        ArrayDeque<T> o1 = (ArrayDeque<T>) o;
        if (o1.size() != this.size()){
            return false;
        }
        for (int i = 0; i < size; i++){
            if (o1.get(i) !=  this.get(i)){
                return false;
            }
        }
        return true;
    }
}
