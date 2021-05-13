package com.questions;

import java.util.Arrays;

/**
 * Find largest element in an array
 */
public class Largest {

    /**
     * Largest - Iterative way
     */
    public static int withIterative(int array[]) {

        int length = array.length;
        if (length == 0) return 0;
        else {
            int max = array[0];
            for (int i = 1; i < length; i++) {
                if (array[i] > max)
                    max = array[i];
            }
            return max;
        }

    }

    /**
     * Largest - Streaming way using Arrays.stream
     */
    public static int usingSteam(int array[]) {

        int length = array.length;
        if (length == 0) return 0;
        else {
            int max = Arrays.stream(array).max().getAsInt();
            return max;
        }

    }

    /**
     * Largest - Streaming way
     */
    public static int usingSort(int array[]) {

        int length = array.length;
        if (length == 0) return 0;
        else {
            Arrays.sort(array);
            return array[length - 1];
        }

    }

    /**
     * second Largest - iterative
     */
    public static int secondLargest(int array[]) {

        int length = array.length;

        if (length == 0) return 0;
        else {

            int largest = array[0];
            int secondLargest = array[0];

            for (int i = 1; i < length; i++) {

                if (array[i] > largest) {
                    secondLargest = largest;
                    largest = array[i];
                } else if (array[i] > secondLargest && array[i] != largest) {
                    secondLargest = array[i];
                }
            }
            return secondLargest;
        }

    }


}
