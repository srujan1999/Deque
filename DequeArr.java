//reference https://algs4.cs.princeton.edu/13stacks/ResizingArrayQueue.java.html//
import java.util.*;
public class DequeArr<Item> implements Iterable<Item>  {
    private static final int INIT_CAPACITY = 8;

    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
    private int first;      // index of first element of queue
    private int last;       // index of next available slot

    public DequeArr()
    {
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty()
    {
        return n==0;
    }

    public int size()
    {
        return n;
    }

    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(first + i) % q.length];
        }
        q = copy;
        first = 0;
        last  = n;
    }

    public void addFirst(Item item)
    {
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        //Memory usage si higher and Time Complexity is in o(N)
        for(int i=n;i>0;i--)
        {
            q[i]=q[i-1];
        }
        q[0]=item;
        last++;
        n++;
    }

    public void addLast(Item item)
    {
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
        q[last++] = item;                        // add item
        if (last == q.length) last = 0;          // wrap-around
        n++;
    }

    public Item removeFirst()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = q[first];
        q[first] = null;                            // to avoid loitering
        n--;
        first++;
        last--;
        if (first == q.length) first = 0;           // wrap-around
        // shrink size of array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2); 
        return item;
    }

    public Item removeLast()
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item=q[last];
        while (item == null){
            item = q[last--];
        }
        n--;
        last--;
        // shrink size of array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2); 
        return item;
    }

    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item>{
        private int i=0;
        public boolean hasNext(){
            return i < n;
        }
        public void remove() { throw new UnsupportedOperationException();}
        public Item next(){
            if(!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }
    }
    public static void main(String[] args){
        // Scanner scan = new Scanner(System.in);
        DequeArr Deque1 = new DequeArr();
        Deque1.addFirst("msk");
        Deque1.addLast("msk1");
        Deque1.addFirst("msk2");
        Deque1.addLast("msk3");
        Deque1.addFirst("msk4");
        Deque1.addLast("msk5");
        
        System.out.println(Deque1.size());
        System.out.println(Deque1.removeFirst());
        System.out.println(Deque1.removeLast());
        System.out.println(Deque1.removeFirst());
        System.out.println(Deque1.removeLast());
        System.out.println(Deque1.removeFirst());
        System.out.println(Deque1.removeLast());
        System.out.print(Deque1.size());
    }

}