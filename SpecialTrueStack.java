package com.test;


import java.util.Stack; 

/* A class that supports all the stack operations and one additional 
operation getMin() that returns the minimum element from stack at 
any time. This class inherits from the stack class and uses an 
auxiliarry stack that holds minimum elements */

public class SpecialTrueStack extends Stack<Integer> {
    Stack<Integer> min = new Stack<>();

    /* SpecialStack's member method to insert an element to it. This method 
    makes sure that the min stack is also updated with appropriate minimum 
    values */
    void push(int x) {
        if (isEmpty()) {
            super.push(x);
            min.push(x);
        } else {
            super.push(x);
            int y = min.pop();
            min.push(y);
            if (x < y)
                min.push(x);
            else
                min.push(y);
        }
    }

    /* SpecialStack's member method to insert an element to it. This method 
    makes sure that the min stack is also updated with appropriate minimum 
    values */
    @Override
    public Integer pop() {
        int x = super.pop();
        min.pop();
        return x;
    }

    /* SpecialStack's member method to get minimum element from it. */
    int getMin() {
        int x = min.pop();
        min.push(x);
        return x;
    }


} 