

public class ArrayDeque<T>{
    T[] item;
    int size;
    int nextFirst;
    int nextLast;
    public final int REFACTOR = 2;

    public ArrayDeque(){
        item = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(T x){
        item = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        item[nextFirst] = x;
        nextFirst--;
        size = 1;
    }

    public void Lresize(int size){
        T[] n = (T[]) new Object[size];
        if(Math.abs(nextLast - nextFirst) == 1 ) {
            int min = Math.min(nextFirst,nextLast);
            int max = Math.max(nextFirst,nextLast);

            System.arraycopy(item,0,n,item.length,min + 1);
                System.arraycopy(item, max, n, max, item.length - max);
                nextLast = item.length + min + 1;

        }
        item = n;
    }

    public double usageFactor(){
        double usagefactor = (double)size / item.length;
//        System.out.println("size "+size +" length "+item.length);
        return usagefactor;
    }

    public void resize(int size){
//        System.out.println("resize");
        T[] n = (T[]) new Object[size];
        if(nextFirst < nextLast ) {
            System.out.println("nextFirst "+ nextFirst + " n.length "+ n.length);
            System.arraycopy(item, nextFirst + 1, n, 4, this.size);
            nextLast = nextLast - (nextFirst - 3);
            nextFirst = 3;
        }

        else{
            System.arraycopy(item,0,n,0,nextLast);
            System.arraycopy(item, nextFirst + 1, n, size - (this.size - nextLast),this.size - nextLast);
            System.out.println("nextFirst "+ nextFirst+" nextLast "+ nextLast+ " length "+item.length+" size "+size);
            nextFirst = size - (this.size - nextLast) - 1;
            System.out.println("nextFirst "+ nextFirst+" nextLast "+ nextLast+ " length "+item.length+" size "+size);
        }
//        System.out.println("nextFirst "+ nextFirst+" nextLast "+ nextLast+ " length "+item.length+" size "+size);

//        System.out.println("nextFirst "+ nextFirst);
        item = n;
    }

    public void Fresize(int size){
        T[] n = (T[]) new Object[size];
        if(nextLast - nextFirst == 1 ) {
                System.arraycopy(item,0,n,0,nextFirst + 1);
                System.arraycopy(item, nextLast, n, size - (item.length - nextLast), item.length - nextLast);
                nextFirst = size - (item.length - nextLast) - 1;
        }
        else{
            System.arraycopy(item,0,n,0,item.length);
        }
        item = n;
    }

    public void addLast(T x){
        if(item.length == size) {
            Lresize(size * REFACTOR);
        }

        if(nextLast == item.length)
            nextLast = 0;

        item[nextLast] = x;
        nextLast++;
        size += 1;
    }

    public void addFirst(T x){
        if(item.length == size) {
            Fresize(size * REFACTOR);
        }

        if(nextFirst == -1)
            nextFirst = item.length - 1;

        item[nextFirst] = x;
        nextFirst--;
        size += 1;
    }

    public T removeLast(){
        T remove = item[nextLast - 1];
        size -= 1;
        item[nextLast - 1] = null;
        nextLast--;
        if(nextLast == -1)
            nextLast = item.length - 1;
        System.out.println("usageFactor  "+usageFactor());
        if(usageFactor() <= 0.25 && item.length > 16) {
//            size = size /2;
            resize(item.length / 2);
        }

        return remove;
    }

    public T removeFirst(){
        System.out.println("nextFirst "+ nextFirst);
        T remove = item[nextFirst + 1];
        size -= 1;
        item[nextFirst + 1] = null;
        nextFirst++;
        if(nextFirst == item.length - 1 )
            nextFirst = -1;
        if(usageFactor() <= 0.25 && item.length > 16) {
//            size = size /2;
            System.out.println(usageFactor());
            resize(item.length / 2);
        }
        return remove;
    }

    public T[] rearrange(){
        T[] output = (T[]) new Object[item.length];
        int f = 0;
        if(nextFirst >= nextLast || nextLast - nextFirst == 1){
            for(int i = nextFirst + 1; i <= item.length - 1; i++) {
                output[f] = item[i];
                f++;
            }

            for(int i = 0; i <= nextLast - 1; i++){
                output[f] = item[i];
                f++;
            }
        }
        else{
            for(int i = nextFirst + 1; i <= nextLast - 1; i++){
//                System.out.println("nextFirst         "+ nextFirst+ " item.length "+ item.length);
                output[f] = item[i];
                f++;
            }
        }

        return output;
    }

    public void printDeque(){
        for(int i = 0; i <rearrange().length; i++) {
            if (rearrange()[i] != null)
                System.out.print(rearrange()[i] + " ");
        }
        System.out.println();
    }

    public T get(int x){
        return rearrange()[x];
    }

    public T getreal (int x){
        return item[x];
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

    public static void main(String[] args) {
        ArrayDeque<Integer> aD = new ArrayDeque<>();

        for(int i = 1; i <= 17; i++){
            aD.addLast(i);
        }

        for(int i = 0; i < 10; i++){
            aD.removeLast();
        }
        aD.printDeque();

        System.out.println();

    }



}
