/**
 * @author Sherry Nie
 * @version 1.2
 */
public class ArrayDeque<T> {
    /**
     * A generic array to store items.
     */
    private T[] item;
    /**
     * The size of the array.
     */
    private int size;
    /**
     * Pointers for front.
     */
    private int nextFirst;
    /**
     * Pointers for end.
     */
    private int nextLast;
    /**
     * Refactor to resize the array so that it is
     * proportional to the number of items.
     */
    private final int refactor = 2;

    /**
     * Empty constructor.
     */
    public ArrayDeque() {
        item = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    /**
     * Constructor of initial item.
     *
     * @param x initial item of the array
     */
    public ArrayDeque(T x) {
        item = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        item[nextFirst] = x;
        nextFirst--;
        size = 1;
    }

    /**
     * Enlarge the array when size exceeds the item length by adding firstly.
     *
     * @param csize current size
     */
    private void lresize(int csize) {
        T[] n = (T[]) new Object[csize];
        if (Math.abs(nextLast - nextFirst) == 1) {
            int min = Math.min(nextFirst, nextLast);
            int max = Math.max(nextFirst, nextLast);

            System.arraycopy(item, 0, n, item.length, min + 1);
            System.arraycopy(item, max, n, max, item.length - max);
            nextLast = item.length + min + 1;

        }

        item = n;
    }

    /**
     * Calculate the usage factor of array.
     *
     * @return the usage factor of current array
     */
    private double usageFactor() {
        return (double) size / item.length;
    }

    /**
     * Shorten the array when the usage factor is less than 0.25.
     *
     * @param nsize new size of array to be set
     */
    private void resize(int nsize) {
        T[] n = (T[]) new Object[nsize];
        if (nextFirst < nextLast) {
            System.arraycopy(item, nextFirst + 1, n, 4, this.size);
            nextLast = nextLast - (nextFirst - 3);
            nextFirst = 3;
        } else {
            System.arraycopy(item, 0, n, 0, nextLast);
            System.arraycopy(item, nextFirst + 1, n,
                    nsize - (this.size - nextLast), this.size - nextLast);
            nextFirst = nsize - (this.size - nextLast) - 1;
        }

        item = n;
    }

    /**
     * Enlarge the array when size exceeds the item length by adding lastly.
     * @param nsize new array size
     */
    private void fresize(int nsize) {
        T[] n = (T[]) new Object[nsize];
        if (nextLast - nextFirst == 1) {
            System.arraycopy(item, 0, n, 0, nextFirst + 1);
            System.arraycopy(item, nextLast, n,
                    nsize - (item.length - nextLast), item.length - nextLast);
            nextFirst = nsize - (item.length - nextLast) - 1;
        } else {
            System.arraycopy(item, 0, n, 0, item.length);
        }
        item = n;
    }

    /**
     * Add item from the end.
     * @param x item to be added
     */
    public void addLast(T x) {
        if (item.length == size) {
            lresize(size * refactor);
        }

        if (nextLast == item.length) {
            nextLast = 0;
        }

        item[nextLast] = x;
        nextLast++;
        size += 1;
    }

    /**
     * Add item from the front.
     * @param x item to be added
     */
    public void addFirst(T x) {
        if (item.length == size) {
            fresize(size * refactor);
        }

        if (nextFirst == -1) {
            nextFirst = item.length - 1;
        }

        item[nextFirst] = x;
        nextFirst--;
        size += 1;
    }

    /**
     * Remove the item from the end.
     * @return item removed
     */
    public T removeLast() {
        if (nextLast == 0) {
            nextLast = item.length;
        }
        T remove = item[nextLast - 1];
        size -= 1;
        item[nextLast - 1] = null;
        nextLast--;

        if (usageFactor() <= 0.25 && item.length > 16) {
            resize(item.length / 2);
        }

        return remove;
    }

    /**
     * Remove the item from the front.
     * @return item removed
     */
    public T removeFirst() {
        if (nextFirst == item.length - 1) {
            nextFirst = -1;
        }
        T remove = item[nextFirst + 1];
        size -= 1;
        item[nextFirst + 1] = null;
        nextFirst++;

        if (usageFactor() <= 0.25 && item.length > 16) {
            resize(item.length / 2);
        }
        return remove;
    }

    /**
     * A Help method to create a new array with correct adding sequence.
     * @return array with right sequence
     */
    public T[] rearrange() {
        T[] output = (T[]) new Object[item.length];
        int f = 0;
        if (nextFirst >= nextLast || nextLast - nextFirst == 1) {
            for (int i = nextFirst + 1; i <= item.length - 1; i++) {
                output[f] = item[i];
                f++;
            }

            for (int i = 0; i <= nextLast - 1; i++) {
                output[f] = item[i];
                f++;
            }
        } else {
            for (int i = nextFirst + 1; i <= nextLast - 1; i++) {
                output[f] = item[i];
                f++;
            }
        }

        return output;
    }

    /**
     * Print out the array.
     */
    public void printDeque() {
        for (int i = 0; i < rearrange().length; i++) {
            if (rearrange()[i] != null) {
                System.out.print(rearrange()[i] + " ");
            }
        }
        System.out.println();
    }

    /**
     * Get the item of corresponding index.
     * @param x item index
     * @return item
     */
    public T get(int x) {
        return rearrange()[x];
    }


    /**
     * Size of the array.
     * @return number of occupied item
     */
    public int size() {
        return size;
    }

    /**
     * Decide whether the array is empty or not.
     * @return true if array is empty
     */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

}
