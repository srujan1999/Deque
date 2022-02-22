import java.util.*;
public class Dequearray<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 5;

    private Item[] q;      
    private int n;          
    private int first;      
    private int last; 
    
    // construct an empty deque
    public Dequearray(){
        q = (Item[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;  
    }

    // is the deque empty?
    public boolean isEmpty(){
        return n==0;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if((first==0 && last==INIT_CAPACITY-1)||(first==last+1)) throw new IllegalArgumentException();  
        if(first==-1 && last==-1) first=0;last=0;
        if(first==0) first=INIT_CAPACITY-1;
        else first--;
        q[first]=item;
        n++;
    }

    // add the item to the back
    public void addLast(Item item){
        if(first==0 && last==INIT_CAPACITY-1 ||(first==last+1)) throw new IllegalArgumentException();  
        if(first==-1 && last==-1) first=0;last=0;
        if(last==INIT_CAPACITY-1) last=0;
        else last++;
        q[last]=item;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(first==-1 && last==-1) throw new NoSuchElementException(); 
        Item item=q[first];
        if(first==last) first=last=-1;
        if(first==INIT_CAPACITY-1) first=0;
        else first++;
        n--;
        return item;    
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(first==-1 && last==-1) throw new NoSuchElementException(); 
        Item item=q[last];
        if(first==last) first=last=-1;
        if(last==0) last=INIT_CAPACITY-1;
        if(item==null) {
            last=last--;
            return q[last];}
        else last--;
        n--;
        return item;
    }
    

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new ArrayIterator();
    }
    public class ArrayIterator implements Iterator<Item>{
        private int i = 0;
        public boolean hasNext()  { return i < n;                               }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + first) % q.length];
            i++;
            return item;
        }

    }

    

    // unit testing (required)
    public static void main(String[] args){
        Dequearray<Integer> Deque1 = new Dequearray<Integer>();
        System.out.println(Deque1.isEmpty());
        Deque1.addFirst(2);
        Deque1.addFirst(5);
        Deque1.addLast(8);
        Deque1.addLast(1);
        Deque1.addFirst(9);
        Deque1.addLast(100);

        System.out.println(Deque1.removeFirst());
        System.out.println(Deque1.removeLast());
        System.out.println(Deque1.removeFirst());
        System.out.println(Deque1.removeLast());
        // System.out.println(Deque1.removeFirst());
        // System.out.println(Deque1.removeLast());
        System.out.println(Deque1.size());
        System.out.println(Deque1.isEmpty());

        //2 8 1 5
        //9 5 2 8 1 100

    }

}