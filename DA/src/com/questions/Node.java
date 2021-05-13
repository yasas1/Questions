package com.questions;

public class Node {

    int key;
    Node left, right;

    public Node(int item){
        this.key = item;
        this.left = null;
        this.right = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return key == node.key && left.equals(node.left) && right.equals(node.right);
    }

}
