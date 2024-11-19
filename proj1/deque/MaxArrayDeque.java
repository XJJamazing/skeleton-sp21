package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }
    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (cmp.compare(this.get(i), maxItem) > 0) {
                maxItem = this.get(i);
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (c.compare(this.get(i), maxItem) > 0) { //compare(T o1, T o2) 方法返回一个整数值来比较两个对象的顺序,返回负数：表示 o1 小于 o2,返回零：表示 o1 等于 o2,返回正数：表示 o1 大于 o2。
                maxItem = this.get(i);
            }
        }
        return maxItem;
    }
}
