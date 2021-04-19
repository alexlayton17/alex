/* Name: Alex Layton
 * Date: 3/16/2021
 * Class: CSC 210
 * 
 * This is a LinkedList List class that will be backed up by Nodes. I will 
 * create a Node class and only have it wire to the front Node. This will be a
 * single linked list with only one Node controlling all the elements that are being 
 * passed in by the user. 
 * 
 * ListQueue queue = new ListQueue();
 * queue.enqueue(1);
 * queue.enqueue(2);
 * queue.enqueue(3);
 * 
 * System.out.print(queue.toString());
 * {1,2,3}
 *  
 */
public class ListQueue implements QueueInterface {
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

    private ListNode front;

    /*
     * PARAM: N/A
     * Constructor for the ListStack class
     * RETURN: A new instance of the ListStack class
     */
    public ListQueue() {
        front = null;
    }

    /*
     * PARAM: ListQueue copy
     * This class will create a copy of the ListQueue instance
     * RETURN: A new copy of the ListNode class.
     */
    public ListQueue(ListQueue copy) {
        ListNode temp = copy.front;
        while (temp != null) {
            enqueue(temp.data);
            temp = temp.next;
        }

    }

    /*
     * PARAM: int value
     * This will enqueue the value at the end of the queue.
     * RETURN: N/A
     */
    @Override
    public void enqueue(int value) {
        if (front == null) {
            front = new ListNode(value);
        } else {
            ListNode temp = front;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new ListNode(value);
        }
    }

    /*
     * PARAM: N/A
     * This method will pop off the front of the queue
     * RETURN: int value
     */
    @Override
    public int dequeue() {
        if (front == null) {
            return -1;
        }
        int returnVal = front.data;
        front = front.next;
        return returnVal;

    }

    /*
     * PARAM: N/A
     * This method will peek the top of the queue
     * but not do anything with the queue
     * RETURN: int value
     */
    @Override
    public int peek() {
        if (front == null) {
            return -1;
        }
        return front.data;
    }
    
    /*
     * PARAM: N/A
     * RETURN: True or False depedning on if queue is empty
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return front == null;
    }

    /*
     * PARAM: N/A
     * this method will return the size of the queue
     * RETURN: int size
     */
    @Override
    public int size() {
      int size = 0;
      ListNode temp = front;
      while(temp != null) {
          size += 1;
          temp = temp.next;
      }
      return size;
    }

    /*
     * PARAM: N/A
     * this will clear up the queue
     * RETURN: a squeue that is cleared.
     */
    @Override
    public void clear() {
        front = null;
    }

    /*
     * PARAM: N/A
     * Return: queue in readable form
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
     * RETURN: boolean whether queue is equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof ListQueue) {
            ListQueue other = (ListQueue) o;
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
