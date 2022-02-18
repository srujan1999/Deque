import java.util.*;
public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    int n;
    private class Node{
        Item item;
        Node next;
    }
    
    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        if (first == null && last == null){
            return true;
        }
        return false;
    }

    // return the number of items on the deque
    public int size(){
        return n;
    }

    // add the item to the front
    public void addFirst(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        n+=1;

    }

    // add the item to the back
    public void addLast(Item item){
        if(item == null) throw new IllegalArgumentException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        oldlast.next = oldlast;
        n = n+1;

    }

    // remove and return the item from the front
    public Item removeFirst(){
        if(isEmpty()) throw new NoSuchElementException(); 
        Item item = first.item;
        first = first.next;
        n = n-1;
        return item;    
    }

    // remove and return the item from the back
    public Item removeLast(){
        if(isEmpty()) throw new NoSuchElementException(); 
        
        if(first.next==null)
        { 
            Item item1 = first.item;
            last=null; 
            first=null;
            n--;
            return item1; 
        } 
        else 
        { 
            Node temp= first; 
            while(temp.next!=last) 
            { 
                temp=temp.next; 
            } 
            last=temp; 
            last.next=null; 
            n--;
            return last.item; 
        }     
    }
    

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new hasIterator();
    }
    public class hasIterator implements Iterator<Item>{
        Node temp = first;
        public boolean hasNext(){ 
            return temp != null; 
        }
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item1 = temp.item;
            temp = temp.next;
            return item1;
        }
        public void remove() {
            throw new UnsupportedOperationException(); } 

    }

    

    // unit testing (required)
    public static void main(String[] args){
        Deque Deque1 = new Deque();
        Deque1.addFirst("MSK");
        System.out.println(Deque1.isEmpty());
        System.out.println(Deque1.size());
        System.out.println(Deque1.removeLast());
        System.out.print(Deque1.size());
    }

}