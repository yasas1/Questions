package com.questions;

import java.util.ArrayList;
import java.util.List;

public class Sorting {

    public static void printArray(int array[]) {

        for (int item : array) {
            System.out.print(item + " , ");
        }
        System.out.println("");
    }

    /**
     * Bubble sort
     * repeatedly swapping the adjacent element if they are in wrong order
     */
    public static void bubbleSort(int array[]) {

        int length = array.length;

        for (int i = 0; i < length - 1; i++) {

            for (int j = 0; j < length - i - 1; j++) {

                if (array[j] > array[j + 1]) {
                    //swap
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Selection sort
     * repeatedly finding the minimum or maximum from unsorted part and putting it at the beginning
     * */
    public static void selectionSort(int array[]){

        int length = array.length;

        for (int i = 0; i < length-1; i++) {

            //find the index of minimum
            int min_idx=i;
            for(int j=i+1; j < length ; j++){
                if(array[j]<array[min_idx]){
                    min_idx=j;
                }
            }
            //swap the minimum with
            int temp = array[min_idx];
            array[min_idx]=array[i];
            array[i]=temp;
        }

    }

    /*public static int[] quickSort(List<Integer> array){

        int length = array.length;
        if(length <=1){
            return array;
        }
        else{
            int pivot = array[length-1];

            List<Integer> greater = new ArrayList<Integer>;
            List<Integer> lower = new ArrayList<Integer>;

            for(int item : array) {
                if (item > pivot)
                    greater.add(item);
                else
                    lower.add(item);
            }


        }


    }*/

}
