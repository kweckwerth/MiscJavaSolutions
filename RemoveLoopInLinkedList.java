package com.test;


public class RemoveLoopInLinkedList {
// Java program to detect and remove loop in linked list 

    static Node head;

    static class Node {
        int id;
        Node next;

        Node(int id) {
            this.id = id;
            next = null;
        }
    }

    // Function that detects loop in the list 
    int detectAndRemoveLoop(Node node) {
        Node slow = node, fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If slow and fast meet at same point then loop is present 
            if (slow == fast) {
                removeLoop(slow, node);
                return 1;
            }
        }
        return 0;
    }

    // Function to remove loop 
    void removeLoop(Node loop, Node curr) {
        Node ptr1 = null, ptr2 = null;
        ptr1 = curr;
        while (true) { 
  
            /* Now start a pointer from loop_node and check if it ever 
             reaches ptr2 */
            ptr2 = loop;
            while (ptr2.next != loop && ptr2.next != ptr1) {
                ptr2 = ptr2.next;
            } 
  
            /* If ptr2 reahced ptr1 then there is a loop. So break the 
             loop */
            if (ptr2.next == ptr1) {
                break;
            }

            /* If ptr2 did't reach ptr1 then try the next node after ptr1 */
            ptr1 = ptr1.next;
        } 
  
        /* After the end of loop ptr2 is the last node of the loop. So 
         make next of ptr2 as NULL */
        ptr2.next = null;
    }

    // Function to print the linked list 
    void printList(Node node) {
        while (node != null) {
            System.out.print(node.id + " ");
            node = node.next;
        }
    }


}
