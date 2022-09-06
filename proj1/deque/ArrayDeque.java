package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    /** Resizes the underlying array to the target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int First_loc = Math.abs(capacity-size)/2;
        System.arraycopy(items, nextFirst+1, a, First_loc, size);
        items = a;
        nextFirst = First_loc-1;
        nextLast = First_loc + size;
    }
    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        if(i < 0 || i > size -1){
            return null;
        }
        return(items[i+nextFirst+1]);

    }
    /** Inserts X into the front of the list. */
    public void addFirst(T x) {
        if(isEmpty()){
            items[nextFirst] = x;
            nextFirst -= 1;
            return;
        }
        T[] a = (T[]) new Object[items.length];
        System.arraycopy(items, 0, a, 0, items.length);
        a[nextFirst] = x;
        items = a;
        nextFirst -= 1;
        size = size + 1;
        if(nextFirst == -1){
            resize(items.length * 2);
        }
    }
    /** Inserts X into the back of the list. */

    public void addLast(T x) {
        if(isEmpty()){
            items[nextLast] = x;
            size += 1;
            nextLast += 1;
            return;
        }
        items[nextLast] = x;
        nextLast += 1;
        size = size + 1;
        if(nextLast == items.length){
            resize(size*2);
        }
    }

    /**
     * Determine if list is empty or not
     */

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }
    /** Returns the number of items in the list. */

    public int size() {
        return size;
    }

    /**
     * Print out each item in the Array list
     */

    public void printDeque(){
        for(int i = 0; i < size; i += 1){
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    /**
     * Remove the first item of the list and return the removed item
     */

    public T removeFirst(){
        if (size < items.length / 4) {
            resize(items.length / 4); //this part: implement usage ratio: R = size/items.length, if R < 0.25, resize to 1/4 item.length, not 1/4 size.
        }
        if(isEmpty()){
            return null;
        }
        T First_item = items[nextFirst+1];
        T[] a = (T[]) new Object[items.length];
        System.arraycopy(items, 0, a, 0, items.length);
        a[nextFirst+1] = null;
        items = a;
        size -= 1;
        nextFirst += 1;
        return First_item;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */

    public T removeLast() {
        if (size < items.length / 4) {
            resize(items.length / 4); //this part: implement usage ratio: R = size/items.length, if R < 0.25, resize to 1/4 item.length, not 1/4 size.
        }
        if(isEmpty()){
            return null;
        }
        T Last_item = items[nextLast-1];
        items[nextLast-1] = null;
        nextLast -= 1;
        size = size - 1;
        return Last_item;
    }

}