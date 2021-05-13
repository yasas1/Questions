package com.questions;

public class Main {

    public static void main(String[] args) {

        BinaryTree tree = new BinaryTree();
        Node root = new Node(1);
        tree.root = root;
        Node second = new Node(2);
        tree.root.left = second;
        tree.root.right = new Node(3);
        tree.root.right.right = new Node(7);
        tree.root.right.left = new Node(6);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        tree.root.left.left.left = new Node(8);
        tree.root.left.right.right = new Node(9);

        /**
         *                 1
         *             /        \
         *         2              3
         *        / \           /   \
         *     4      5       6       7
         *    /        \
         *   8          9
         * */

        System.out.println("\nLeft side");
        tree.leftSide(root);

        System.out.println("\nLeft corner");
        tree.leftCorner(root);

        System.out.println("\nPre Order traversal (Root -Left - Right  ) ");
        tree.preOrderTravers(root);

        System.out.println("\nIn order traversal (Left - Root - Right) ");
        tree.inOrderTravers(root);
        System.out.println("");
        tree.inOrderTravers(second);

        System.out.println("\nPost order traversal (Left - Root - Right) ");
        tree.postOrderTravers(root);

        System.out.println("\n nEw Tree");
        BinaryTree newTree = new BinaryTree();
        Node newRoot = new Node(10);
        newTree.root = newRoot;
        newTree.insert(2);
        newTree.insert(4);
        newTree.insert(20);
        newTree.insert(5);
        newTree.insert(25);
        newTree.inOrderTravers(newRoot);


        System.out.println("---------Balance Parenthesis-----------");
        String expr1 = "{}{}";
        System.out.println("\n is expr1 balanced ? " + StackApply.isThisBalancedParenthesis(expr1));
        String expr2= "{()[()]}";
        System.out.println("\n is expr2 balanced ? "+ StackApply.isThisBalancedParenthesis(expr2));
        String expr3 = "{()[()}";
        System.out.println("\n is expr3 balanced ? "+ StackApply.isThisBalancedParenthesis(expr3));

        System.out.println("---------Check Palindrome-----------");
        String word1 = "madam";
        System.out.println("\n is word1 Palindrome ? " + StackApply.isThisPalindrome(word1));
        String word2= "madamt";
        System.out.println("\n is word2 Palindrome ? " + StackApply.isThisPalindrome(word2));


    }
}
