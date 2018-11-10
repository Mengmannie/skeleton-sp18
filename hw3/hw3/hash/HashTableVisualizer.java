package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class HashTableVisualizer {

    public static void main(String[] args) {
        /* scale: StdDraw scale
           N:     number of items
           M:     number of buckets */

        /* After getting your simpleOomages to spread out
           nicely, be sure to try
           scale = 0.5, N = 2000, M = 100. */

        double scale = 1;
        int N = 200;
        int M = 10;

        HashTableDrawingUtility.setScale(scale);
        List<Oomage> oomies = new ArrayList<>();
        for (int i = 0; i < N; i += 1) {
           oomies.add(ComplexOomage.randomComplexOomage());
        }

        List<Oomage> deadlyList = new ArrayList<>();
        List<Integer> param1 = new ArrayList<>();
        param1.add(1); param1.add(2); param1.add(3); param1.add(4);
        deadlyList.add(new ComplexOomage(param1));
        List<Integer> param2 = new ArrayList<>();
        param2.add(9); param2.add(2); param2.add(7); param2.add(4);
        deadlyList.add(new ComplexOomage(param2));
        List<Integer> param3 = new ArrayList<>();
        param3.add(4); param3.add(5); param3.add(8); param3.add(4);
        deadlyList.add(new ComplexOomage(param3));

        visualize(deadlyList, M, scale);
    }

    public static void visualize(List<Oomage> oomages, int M, double scale) {
        HashTableDrawingUtility.drawLabels(M);
        int[] numInBucket = new int[M];
        for (Oomage s : oomages) {
            int bucketNumber = (s.hashCode() & 0x7FFFFFFF) % M;
            double x = HashTableDrawingUtility.xCoord(numInBucket[bucketNumber]);
            numInBucket[bucketNumber] += 1;
            double y = HashTableDrawingUtility.yCoord(bucketNumber, M);
            s.draw(x, y, scale);
        }
    }
} 
