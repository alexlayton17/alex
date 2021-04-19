/* Name: Alex Layton
 * Date: 3/16/2021
 * 
 * This is an ArrayQueue class backed up by an Array. We are going
 * to set the initial capactiy of the Array to 10.
 * 
 * ArrayQueue queue = new ArrayQueue();
 * queue.enqueue(1);
 * queue.enqueue(2);
 * queue.enqueue(3);
 * 
 * System.out.print(queue.toString());
 * {1,2,3} 
 */
public class ArrayQueue implements QueueInterface {

    public static final int MAX_VALUE = 10; // inital capacity of the array
    private int size; // keeps track of the size
    private int[] array; // array that backs up the queue

    /*
     * PARAM: N/A
     * constructor for the ArrayQueue
     * REUTRN: new instance of the ArrayQueue
     */
    public ArrayQueue() {
        array = new int[MAX_VALUE];
        size = 0;
    }

    /*
     * PARAM: ArrayQueue copy
     * This class will create a copy of the ArrayQueue instance
     * RETURN: A new copy of the ArrayQueue class.
     */
    public ArrayQueue(ArrayQueue copy) {
        this.size = copy.size;
        this.array = new int[this.size];
        for (int i = 0; i < this.size; i++) {
            this.array[i] = copy.array[i];
        }
    }

    /*
     * PARAM: N/A
     * RETURN: increased size of the array's initial capacity
     */
    private void increaseSize() {
        int[] tempArray = new int[MAX_VALUE * 3];
        for (int i = 0; i < this.size; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    /*
     * PARAM: int value
     * this will push the value at the end of the queue
     * RETURN: N/A
     */
    @Override
    public void enqueue(int value) {
        if (size >= array.length) {
            increaseSize();
        }
        array[size] = value;
        size++;
    }

    /*
     * PARAM: N/A
     * this will dequeue the element at the front of the queue
     * RETURN: int value
     */
    @Override
    public int dequeue() {
        if (size == 0) {
            return -1;
        }
        int dequeue = array[0];
        int tempNum = array[0];
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = tempNum;
        size--;
        return dequeue;
    }

    /*
     * PARAM: N/A
     * this will peek the element at the front of the queue
     * RETURN: int value
     */
    @Override
    public int peek() {
        // TODO Auto-generated method stub
        if (size == 0) {
            return -1;
        }
        return array[0];
    }

    /*
     * PARAM: N/A
     * RETURN: True or False depedning on if queue is empty
     */
    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return size == 0;
    }

    /*
     * PARAM: N/A
     * this method will return the size of the queue
     * RETURN: int size
     */
    @Override
    public int size() {
        // TODO Auto-generated method stub
        return size;
    }

    /*
     * PARAM: N/A
     * this will clear up the queue
     * RETURN: a queue that is cleared.
     */
    @Override
    public void clear() {
        size = 0;
    }

    /*
     * PARAM: N/A
     * Return: queue in readable form
     */
    public String toString() {
        String s = "{";
        if (size == 0) {
            return "{}";
        }
        if (!s.isEmpty()) {
            for (int i = 0; i < size - 1; i++) {
                s = s + "" + array[i] + ",";
            }
            s += array[size - 1];
            s += "}";
        }
        return s;
    }

    /*
     * PARAM: Object o
     * RETURN: boolean whether queue is equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof ArrayQueue) {
            ArrayQueue other = (ArrayQueue) o;
            if (other.size == this.size) {
                for (int i = 0; i < size; i++) {
                    if (other.array[i] != array[i]) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
}


