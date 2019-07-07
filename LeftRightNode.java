package com.test;


public class LeftRightNode {

    LeftRightNode left;
    LeftRightNode right;
    int data;
    String name;

    @Override
    public String toString() {
        return "name=" + name + ":data=" + data;
    }


    LeftRightNode(int d, String name) {
        data = d;
        left = null;
        right = null;
        this.name = name;
    }


    // Function that finds an int in a Node 
    public String findInt(LeftRightNode node, int value) {
        if (node == null) return null;

        if (node.data == value) {
            return node.name;
        } else {
            String found = findInt(node.left, value);
            if (found == null) {
                found = findInt(node.right, value);
            }

            return found;

        }
    }
}
