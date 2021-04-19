/* Name: Alex Layton
 * Date: 3/16/2021
 * Class: CSC 210
 * 
 * This is a ListStack List class that will be backed up by Nodes. I will 
 * create a Node class and only have it wire to the front Node. This will be a
 * single linked list with only one Node controlling all the elements that are being 
 * passed in by the user. 
 * 
 * ListStack stack = new Stack();
 * stack.push(1);
 * stack.push(2);
 * stack.push(3);
 * 
 * System.out.print(stack.toString());
 * {1,2,3}
 *  
 */
public class ListStack implements StackInterface {
    private ListNode front;
    /*
     * This is a ListNode class.
     * This will keep track of the nodes
     * and make sure the order of the numbers
     * are going to where it is expected.
     */
    private class ListNode {
        private int data;
        private ListNode next;

        /*
         * PARAM: int data, ListNode next
         * 
         * This is a constructor for the ListNode class
         * 
         * RETURN: A new instance of the ListNode class
         */
        public ListNode(int data, ListNode next) {
            this.data = data;
            this.next = next;
        }

        /*
         * PARAM: int data
         * 
         * This is a constructor for the ListNode class
         * 
         * RETURN: A new instance of the ListNode class
         */
        public ListNode(int data) {
            this(data, null);
        }
    }

    /*
     * PARAM: N/A
     *
     * Constructor for the ListStack class
     *
     * RETURN: A new instance of the ListStack class
     */
    public ListStack() {
        front = null;
    }

    /*
     * PARAM: ListStack copy
     * 
     * This class will create a copy of the ListNode instance
     * 
     * RETURN: A new copy of the ListNode class.
     */
    public ListStack(ListStack copy) {
        ListNode temp = copy.front;

        while (temp != null) {
            push(temp.data);
            temp = temp.next;
        }
    }



    /*
     * PARAM: int value
     * 
     * This will push the value at the end of the stack.
     * 
     * RETURN: N/A
     */
    @Override
    public void push(int value) {
        // TODO Auto-generated method stub
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode current = front;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }


    /*
     * PARAM: N/A
     * 
     * This method will pop off the front of the stack
     * 
     * RETURN: int value
     */
    @Override
    public int pop() {
        if (front == null) {
            return -1;
        }
        int returnValue = front.data;
        ListNode temp = front;
        while (temp != null) {
            if (temp.next == null) {
                returnValue = temp.data;
                front = null;
                return returnValue;
            }
            else if (temp.next.next == null) {
                returnValue = temp.next.data;
                temp.next = null;
            }
            temp = temp.next;
        }
        return returnValue;
    }

    /*
     * PARAM: N/A
     * This method will peek the top of the stack
     * but not do anything with the stack
     * RETURN: int value
     */
    @Override
    public int peek() {
        if (front == null) {
            return -1;
        }
        ListNode temp = front;
        while (temp.next != null) {
            temp = temp.next;
        }
        return temp.data;
    }

    /*
     * PARAM: N/A
     * RETURN: True or False depedning on if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return front == null;
    }

    /*
     * PARAM: N/A
     * this method will return the size of the stack
     * RETURN: int size
     */
    @Override
    public int size() {
        int size = 0;
        ListNode current = front;
        while (current != null) {
            size += 1;
            current = current.next;
        }
        return size;
    }

    /*
     * PARAM: N/A
     * 
     * this will clear up the stack
     * 
     * RETURN: a stack that is cleared.
     */
    @Override
    public void clear() {
        front = null;
    }

    /*
     * PARAM: N/A
     * Return: stack in readable form
     * 
     */
    public String toString() {
        if (size() == 0) {
            return "{}";
        }
        String result = "{";
        ListNode current = front;
        while (current != null) {
            if (current.next == null) {
                result += current.data;
            }

            else {
                result += current.data + ",";
            }
            current = current.next;
        }
        result += "}";
        return result;
    }

    /*
     * PARAM: Object o
     * RETURN: boolean wether stack is equal or not
     * 
     */
    public boolean equals(Object o) {
        if (o instanceof ListStack) {
            ListStack other = (ListStack) o;
            if (other.size() == size()) {
                ListNode current = front;
                ListNode otherCurrent = other.front;
                while (current != null) {
                    if (current.data != otherCurrent.data) {
                        return false;
                    }
                    current = current.next;
                    otherCurrent = otherCurrent.next;
                }
                return true;
            } else {
                return false;
            }
        }
        else {
            return false;
        }
    }
}
            







