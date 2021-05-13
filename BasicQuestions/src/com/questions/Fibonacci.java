package com.questions;

public class Fibonacci {

    public static int fib(int n){
        if (n<2)
            return 1;
        else
            return fib(n-1)+fib(n-2);
    }

    /** Bottom up memory dynamic programming */
    public static int fibBottomUpDynamic(int n){

        int mem[] = new int[n+1];
        mem[1] = mem[2] = 1;

        for(int i = 3; i <=n; i++){
            mem[i] = mem[i-1]+mem[i-2];
        }
        return mem[n];
    }

    /** Top Down up memory dynamic programming */
    public static int fibTopDownDynamic(int n){

        int mem[] = new int[n+1];

        if(n==1 || n==2)
            return 1;
        if(mem[n]!=0)
            return mem[n];
        mem[n] = fibTopDownDynamic(n-1)+fibTopDownDynamic(n-2);

        return mem[n];

    }

}
