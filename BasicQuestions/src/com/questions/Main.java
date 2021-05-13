package com.questions;

public class Main {

    public static void main(String[] args) {

        int arrayLargest[] = {40, 34, 56, 10, 75, 75, 25, 22};
        System.out.println("-------------------Largest---------------------- ");

        System.out.println("Largest iterative : " + Largest.withIterative(arrayLargest));
        System.out.println("Largest stream : " + Largest.usingSteam(arrayLargest));
        System.out.println("Largest sort : " + Largest.usingSort(arrayLargest));
        System.out.println("Second Largest iterative : " + Largest.secondLargest(arrayLargest));

        System.out.println("----------------------------------------- ");

        System.out.println("---------------String occurrences-------------------------- ");
        String[] arrayOccurrences = {"facebook", "test", "test", "facebook", "find", "find", "test", "Test"};

        WithStrings.countStringOccurrences(arrayOccurrences);
        System.out.println("Max occurrences word : " + WithStrings.maxOccurrences(arrayOccurrences));
        String[] arrayReserve = {"1", "2", "3", "4", "5", "6", "7"};
        WithStrings.reverse(arrayReserve);

        System.out.println("----------------------------------------- ");

        System.out.println("------------------Bubble Sort----------------------- ");
        int arrayBubbleSortt[] = {40, 34, 56, 10, 75, 75, 25, 22};
        Sorting.printArray(arrayBubbleSortt);

        Sorting.bubbleSort(arrayBubbleSortt);

        System.out.println(" After Bubble sort");
        Sorting.printArray(arrayBubbleSortt);

        System.out.println("----------------------------------------- ");

        System.out.println("------------------Selection Sort----------------------- ");
        int arraySelectionSortt[] = {40, 34, 56, 10, 75, 75, 25, 22};
        Sorting.printArray(arraySelectionSortt);

        Sorting.selectionSort(arraySelectionSortt);

        System.out.println(" After Bubble sort");
        Sorting.printArray(arraySelectionSortt);

        System.out.println("------------------Fibonacci----------------------- ");
        long start = System.currentTimeMillis();
        System.out.println("fibonacci " + Fibonacci.fib(30));
        long end = System.currentTimeMillis();
        float sec = (end - start) / 1000F; System.out.println(sec + " seconds");

        System.out.println("----------------------------------------- ");
        long start1 = System.currentTimeMillis();
        System.out.println("fibonacci Bottom up " + Fibonacci.fibBottomUpDynamic(30));
        long end1 = System.currentTimeMillis();
        float sec1 = (end1 - start1) / 1000F; System.out.println(sec1 + " seconds");

        System.out.println("----------------------------------------- ");
        long start2 = System.currentTimeMillis();
        System.out.println("fibonacci top down " + Fibonacci.fibTopDownDynamic(30));
        long end2 = System.currentTimeMillis();
        float sec2 = (end2 - start2) / 1000F; System.out.println(sec2 + " seconds");

    }
}
