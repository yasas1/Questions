package com.questions;

import java.util.*;

/**
 *  Questions with strings
 */
public class WithStrings {

    /**
     * Count string occurrences
     */
    public static void countStringOccurrences(String[] array) {

        int length = array.length;
        if (length == 0) {
            System.out.println("Null array");
        } else {
            HashMap<String, Integer> occurrences = new HashMap<>();
            int count;
            for (String word : array) {
                count = occurrences.get(word) != null ? occurrences.get(word) + 1 : 1;
                occurrences.put(word, count);
            }
            occurrences.forEach((k, v) -> System.out.println(k + " : " + v));

        }
    }

    /**
     * find maximum occurred string
     */
    public static String maxOccurrences(String[] array) {

        int length = array.length;
        if (length == 0) {
            return null;
        } else {
            HashMap<String, Integer> occurrences = new HashMap<>();
            int count;
            for (String word : array) {
                count = occurrences.containsKey(word) ? occurrences.get(word) + 1 : 1;
                occurrences.put(word, count);
            }

            String maxKey = "";
            int value = 0;

            for (Map.Entry<String, Integer> item : occurrences.entrySet()) {
                if (item.getValue() > value) {
                    value = item.getValue();
                    maxKey = item.getKey();
                }
            }
            return maxKey;
        }
    }

    /**
     * Reverse an array
     */
    public static void reverse(String[] array) {

        int length = array.length;
        if (length == 0) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < length / 2; i++) {
                System.out.println(i);

                String temp = array[i];
                array[i] = array[length - i - 1];
                array[length - i - 1] = temp;

            }
            System.out.println("reserve");
            for (String item : array) {
                System.out.print(item + ", ");
            }
            System.out.println("");
        }
    }

}
