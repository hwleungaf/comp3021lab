package lab9;

import java.math.MathContext;

/**
 *
 * COMP 3021
 *
 This is a class that prints the maximum value of a given array of 90 elements

 This is a single threaded version.

 Create a multi-thread version with 3 threads:

 one thread finds the max among the cells [0,29]
 another thread the max among the cells [30,59]
 another thread the max among the cells [60,89]

 Compare the results of the three threads and print at console the max value.

 *
 * @author valerio
 *
 */
public class FindMax {
    // this is an array of 90 elements
    // the max value of this array is 9999
    static int[] array = { 1, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2, 3, 4543,
            234, 3, 454, 1, 2, 3, 1, 9999, 34, 5, 6, 343, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
            3, 4543, 234, 3, 454, 1, 2, 3, 1, 34, 5, 6, 5, 63, 5, 34, 2, 78, 2, 3, 4, 5, 234, 678, 543, 45, 67, 43, 2,
            3, 4543, 234, 3, 454, 1, 2, 3 };

    public static void main(String[] args) {
        new FindMax().printMax();
    }

    public void printMax() {
        // this is a single threaded version

        TaskClass oneThird =  new TaskClass(0, 29);
        TaskClass twoThird =  new TaskClass(30, 59);
        TaskClass threeThird =  new TaskClass(60, 89);
        Thread thread1 = new Thread(oneThird);
        Thread thread2 = new Thread(twoThird);
        Thread thread3 = new Thread (threeThird);
        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();;
        }catch (Exception e){
        }
        int max = Math.max(Math.max(oneThird.max, twoThird.max), threeThird.max);
        System.out.println("the max value is " + max);
    }

    /**
     * returns the max value in the array within a give range [begin,range]
     *
     * @param begin
     * @param end
     * @return
     */
    private int findMax(int begin, int end) {
        // you should NOT change this function
        int max = array[begin];
        for (int i = begin + 1; i <= end; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
    public class TaskClass implements Runnable {
        private int begin;
        private int end;
        private int max;
        TaskClass(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
        public void run(){
            max = findMax(begin, end);
        }
    }
}
