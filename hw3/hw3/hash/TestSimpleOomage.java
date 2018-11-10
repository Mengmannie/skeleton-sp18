package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;


import java.util.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        /* TODO: Write a test that ensures the hashCode is perfect,
          meaning no two SimpleOomages should EVER have the same
          hashCode UNLESS they have the same red, blue, and green values!
         */
        SimpleOomage o1 = new SimpleOomage(0,5,10);
        SimpleOomage o2 = new SimpleOomage(5,10,0);
        System.out.println("o1's hashcode is " + o1.hashCode());
        System.out.println("o2's hashcode is " + o2.hashCode());
        assertNotEquals(o1.hashCode(), o2.hashCode());
//        List<int[]> rgb = new ArrayList<>();
//        for (int i = 0; i <= 51; i++) {
//            for (int j = 0; j <= 51; j++) {
//                for (int k = 0; k <= 51; j++) {
//                    int[] combination = new int[3];
//                    combination[0] = i * 5;
//                    combination[1] = j * 5;
//                    combination[2] = k * 5;
//                    rgb.add(combination);
//                }
//            }
//        }
//        System.out.println(rgb.size());

//        for (int i = 0; i < rgb.size() - 1; i++) {
//            assertNotEquals(rgb.get(i).hashCode(), rgb.get(i + 1).hashCode());
//        }

    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCode Spread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
