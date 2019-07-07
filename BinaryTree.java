package com.test;


class Node2 {
    int data;
    Node2 left, right;

    Node2(int d) {
        data = d;
        left = right = null;
    }
}

public class BinaryTree {


    boolean isBalanced(Node2 root) {
        if (root == null)
            return true;

        if (getHeight(root) == -1)
            return false;

        return true;
    }

    int getHeight(Node2 root) {
        if (root == null)
            return 0;

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        if (left == -1 || right == -1)
            return -1;

        if (Math.abs(left - right) > 1) {
            return -1;
        }

        return Math.max(left, right) + 1;

    }

}


       
    

