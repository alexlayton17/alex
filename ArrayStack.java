/* Name: Alex Layton
 * Date: 3/16/2021
 * 
 * This is an ArrayStack class backed up by an Array. We are going
 * to set the initial capactiy of the Array to 10.
 * 
 * ArrayStack stack = new ArrayStack();
 * queue.enqueue(1);
 * queue.enqueue(2);
 * queue.enqueue(3);
 * 
 * System.out.print(queue.toString());
 * {1,2,3} 
 */
public class ArrayStack implements StackInterface
{

    private final int MAX_CAPACITY = 10; // inital capacity of the array
    private int size; // keeps track of the size
    private int[] array; // array that backs up the queue
    /*
     * PARAM: N/A
     * constructor for the ArrayStack
     * REUTRN: new instance of the ArrayStack
     */
    public ArrayStack() {
        size = 0;
        array = new int[MAX_CAPACITY];
    }

    /*
     * PARAM: N/A
     * constructor for the ArrayStack
     * REUTRN: new instance of the ArrayStack
     */
    public ArrayStack(ArrayStack copy) {
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
        int[] tempArray = new int[MAX_CAPACITY * 3];
        for (int i = 0; i < this.size; i++) {
            tempArray[i] = array[i];
        }
        array = tempArray;
    }

    /*
     * PARAM: int value
     * this will push the value at the front of the stack
     * RETURN: N/A
     */
    @Override
    public void push(int value) {
        if (size >= array.length) {
            increaseSize();
        }
        array[size] = value;
        size++;
    }

    /*
     * PARAM: N/A
     * this will pop the element at the front of the stack
     * RETURN: int value
     */
    @Override
    public int pop() {
        if (size == 0) {
            return -1;
        }
        int numPop = array[size - 1];
        size = size - 1;
        return numPop;
    }

    /*
     * PARAM: N/A
     * this will peek the element at the front of the stack
     * RETURN: int value
     */
    @Override
    public int peek() {
        if (size == 0) {
            return -1;
        }
        int numPeek = array[size - 1];
        return numPeek;
    }

    /*
     * PARAM: N/A
     * RETURN: True or False depedning on if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * PARAM: N/A
     * this method will return the size of the stack
     * RETURN: int size
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * PARAM: N/A
     * this will clear up the stack
     * RETURN: a stack that is cleared.
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        size = 0;

    }

    /*
     * PARAM: N/A
     * Return: stack in readable form
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
     * RETURN: boolean whether stack is equal or not
     */
    public boolean equals(Object o) {
        if (o instanceof ArrayStack) {
            ArrayStack other = (ArrayStack) o;
            if (other.size == this.size) {
                for (int i = 0; i < size; i++) {
                    if (other.array[i] != array[i]) {
                        return false;
                    }

                }
                return true;
            }
        }

        else {
            return false;
        }
        return false;

    }

}
