

public class LinkedListDeque<T>{

    public  class IntNode{
        public T item;
        public IntNode prev;
        public IntNode next;

        public IntNode(T x, IntNode n, IntNode p){
            item = x;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel;
//    private IntNode last;
    private int size;

    /**create an empty LinkedListDeque. */

    public LinkedListDeque(){
        size = 0;
        sentinel = new IntNode(null,null,null);
//        last = sentinel;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T x){
        sentinel = new IntNode(null,null,null);
        sentinel.next = new IntNode(x,sentinel,sentinel);
//        last = sentinel.next;
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Adds x to the front of the list. */

    public void addFirst(T x){

        size += 1;
        sentinel.next = new IntNode(x,sentinel.next,sentinel);
//        System.out.println(sentinel.next.item);
//        if(last == sentinel){
//            last = sentinel.next;
//        }
        if(sentinel.prev == sentinel)
            sentinel.prev = sentinel.next.next;

    }

    public void addLast(T x){
        size += 1;
//        last.next = new IntNode(x,null,last);
//        last = last.next;
        sentinel.prev.next = new IntNode(x,sentinel,sentinel.prev);
        sentinel.prev = sentinel.prev.next;
//        if(sentinel.next == sentinel)
//            sentinel.next = sentinel.prev;

    }



    public T get(int x){
        IntNode p;
        p = sentinel.next;
        for(int i = 0; i < x; i++){
            p = p.next;
        }
//        System.out.println("list of "+x +" is "+ p.item);
        return p.item;
    }

    public IntNode getRecursive(int x){
        if(x == 0)
            return sentinel.next;
        else{
            return getRecursive(x - 1).next;
        }
    }

    public T getRecursize(int x){
        return getRecursive(x).item;

    }

    public T removeLast(){
        size -= 1;
        IntNode p = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return p.item;
//        if(last != sentinel) {
//            last = last.prev;
//            last.next = null;
//            return p.item;
//        }
//        else{
//            return null;
//        }
    }

    public T removeFirst(){
        size -= 1;
//
        IntNode p = sentinel.next;
        sentinel.next = p.next;
        p.next.prev = sentinel;
        return p.item;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        else
            return false;
    }

    public void printDeque(){
        for(int i = 0; i < size; i++){
            System.out.print(this.get(i)+ " ");
        }
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
//        list.addFirst(1);
//        list.addFirst(2);
        list.addLast(3);
//        list.add(6);
//        list.add(7);
//        list.add(8);
//        list.removeFirst();
//        list.removeLast();
//        list.addFirst(1);
        System.out.println("List size is " + list.size);
//        for(int i = 0; i < list.size; i++){
//            System.out.println(list.get(i));
//        }
        list.printDeque();
    }





}
