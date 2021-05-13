package com.questions;

public class BinaryTree {

    Node root;

    public BinaryTree(int key){
        this.root = new Node(key);
    }

    public BinaryTree(){
        this.root = null;
    }

    public void insert(int key){
        this.root = insertRec(this.root, key);
    }

    private Node insertRec(Node rootNode, int key){
          /* If the tree is empty,return a new node */
        if (rootNode == null){
            rootNode = new Node(key);
            return rootNode;
        }
           /* Otherwise, recur down the tree */
        if (key < rootNode.key)
            rootNode.left = insertRec(rootNode.left, key);
        else if (key > rootNode.key)
            rootNode.right = insertRec(rootNode.right, key);
           /* return the (unchanged) node pointer */
        return rootNode;
    }

    /** Pre Order traversal (Root -Left - Right  ) */
    public void preOrderTravers(Node node){
        if (node == null)
            return;

        System.out.print(node.key + " ");
        preOrderTravers(node.left);
        preOrderTravers(node.right);
    }

    /** n order traversal (Left - Root - Right)  */
    public void inOrderTravers(Node node){
        if (node == null)
            return;

        inOrderTravers(node.left);
        System.out.print(node.key + " ");
        inOrderTravers(node.right);
    }

    /** Post order traversal (Left - Root - Right) */
    public void postOrderTravers(Node node){
        if (node == null)
            return;

        postOrderTravers(node.left);
        postOrderTravers(node.right);
        System.out.print(node.key + " ");
    }


    public void leftSide(Node node){
        if (node == null)
            return;

//        if (node.equals(this.root.right))
//            return;

        // pre order ( root - left -right )
        System.out.print(node.key + " ");
        leftSide(node.left);
        if (!node.equals(this.root))
            leftSide(node.right);
    }

    public void leftCorner(Node node){
        if (node == null)
            return;

        System.out.print(node.key + " ");
        leftCorner(node.left);

    }




}
