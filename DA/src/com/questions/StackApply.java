package com.questions;

import java.util.Stack;

public class StackApply {

    public static boolean isThisBalancedParenthesis(String expr){

        Stack<Character> stack = new Stack<>();

        for ( int i = 0 ; i < expr.length(); i++){

            char x = expr.charAt(i);
            if(x == '(' || x == '[' || x== '{'){
                stack.push(x);
                continue;
            }
            if(stack.isEmpty())
                return false;
            else {
                char check = stack.pop();
                switch (x){
                    case ')':
                        if(check != '(')
                            return false;
                        break;
                    case ']':
                        if(check != '[')
                            return false;
                        break;
                    case '}':
                        if(check != '{')
                            return false;
                        break;
                }
            }

        }
        return (stack.isEmpty());
    }

    public static boolean isThisPalindrome(String word){

        Stack stack = new Stack();
        int length = word.length();
        if(length==1 || length==0)
            return true;

        for(int i=0; i < length; i++){
            char x = word.charAt(i);
            stack.push(x);
        }

        String checkPalindrome = "";
        while(!stack.isEmpty()) {
            checkPalindrome = checkPalindrome + stack.pop();
        }
        return word.equals(checkPalindrome);
    }

}
