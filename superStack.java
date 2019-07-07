package com.test;


import java.util.LinkedList;
import java.util.ListIterator;

/**
 * from revolve test
 * SELECT N , CASE WHEN P IS NULL THEN 'Root' ELSE CASE WHEN EXISTS (SELECT P FROM BST B WHERE A.N=B.P) THEN 'Inner' ELSE 'Leaf' END END FROM BST A ORDER BY N
 */
public class superStack {
    static void superStack(String[] operations) {
        if (operations == null || operations.length == 0) {
            System.out.println("EMPTY");
            return;
        }
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < operations.length; i++) {
            String current = operations[i];
            if (current.equals("pop")) {
                list.removeLast();
            } else {
                if (current.startsWith("push")) {
                    list.addLast(Integer.parseInt(current.split(" ")[1]));
                } else {
                    int j = 1;
                    int e = Integer.parseInt(current.split(" ")[1]);
                    int k = Integer.parseInt(current.split(" ")[2]);
                    ListIterator<Integer> listIterator = list.listIterator();

                    while (listIterator.hasNext()) {
                        if (j > e)
                            break;
                        listIterator.set(listIterator.next() + k);
                        j++;
                    }
                }
            }

            if (list.isEmpty())
                System.out.println("EMPTY");
            else
                System.out.println(list.getLast());

        }

    }

}