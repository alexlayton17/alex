/* Name: Alex Layton
 * Date: 4/5/2020
 * 
 * Purpose: I will implement an implementation of a priority queue
 * backed up by a min heap. This minHeap will be an array.
 * 
 * The point of this queue is to take in patients and organize, and sort the
 * patients based on the name and priority that has been taken in.
 * 
 * We will the ultimatly take out the most urgen priority, which will be the lower the numbers
 * The most urgent
 * 
 * p.enqueue("Alex",3);
 * p.enqueue("Bob",4);
 * p.enqueue("Jack",1);
 * {Jack (1), Bob (4), Alex (3)}
 * 
 */
public class PatientQueue {

    private Patient[] patientArray;
    private static final int capacity = 10;
    private int size;
    /*
     * Constructor of the class. This
     * will take in no parameters and will
     * initilize all the variables
     */
    public PatientQueue() {
        patientArray = new Patient[capacity];
        patientArray[0] = new Patient("", -1000);
        size = 0;
    }

    /*
     * PARAM: index we are working with
     * This will take in the index we are working with and will
     * determine if the index will need to be bubbled down
     * RETURN: NOTHING
     */
    private void bubbleUp(int place) {
        while (place >= 1) {
            if (patientArray[place].priority < patientArray[parent(
                    place)].priority) {
                Patient temp = patientArray[parent(place)];
                patientArray[parent(place)] = patientArray[place];
                patientArray[place] = temp;
            } else {
                return;
            }
            place = parent(place);
        }
    }

    /*
     * PARAM: index we are working with
     * This will take in the index we are working with and will
     * determine if the index will need to be bubbled up
     * RETURN: NOTHING
     */
    private void bubbleDown(int cur) {
        while (cur < size) {
            if (patientArray[childTwo(cur)] != null
                    && patientArray[childOne(cur)] != null
                    && patientArray[cur].priority > patientArray[childOne(
                            cur)].priority
                    && patientArray[childTwo(
                            cur)].priority > patientArray[childOne(
                                    cur)].priority) {
                Patient temp = patientArray[cur];
                patientArray[cur] = patientArray[childOne(cur)];
                patientArray[childOne(cur)] = temp;
                cur = childOne(cur);
            } else if (patientArray[childTwo(cur)] != null
                    && patientArray[childOne(cur)] != null
                    && patientArray[cur].priority > patientArray[childTwo(
                            cur)].priority
                    && patientArray[childTwo(
                            cur)].priority < patientArray[childOne(
                                    cur)].priority) {
                Patient temp = patientArray[cur];
                patientArray[cur] = patientArray[childTwo(cur)];
                patientArray[childTwo(cur)] = temp;
                cur = childTwo(cur);
            } else if (patientArray[childTwo(cur)] == null
                    && patientArray[childOne(cur)] != null
                    && patientArray[childOne(
                            cur)].priority < patientArray[cur].priority) {
                Patient temp = patientArray[cur];
                patientArray[cur] = patientArray[childOne(cur)];
                patientArray[childOne(cur)] = temp;
                cur = childOne(cur);

            } else {
                break;

            }
        }
    }

    /*
     * PARAM: Index
     * This will return the childOne of the index that is being passed in
     * RETURN: childOne of this index
     */
    private int childOne(int i) {
        return i * 2;
    }

    /*
     * PARAM: Index
     * This will return the childTwo of the index that is being passed in
     * RETURN: childTwo of this index
     */
    private int childTwo(int i) {
        return i * 2 + 1;
    }

    /*
     * PARAM: Index
     * This will return the parent of the index that is being passed in
     * RETURN: Parent of thisindex
     * 
     */
    private int parent(int i) {
        return i / 2;
    }

    private void increaseArray() {
        Patient[] tempArray = new Patient[capacity * 4];
        for (int i = 0; i < this.size; i++) {
            tempArray[i] = patientArray[i];
        }
        patientArray = tempArray;
    }

    /*
     * PARAM: This is taking in a string name and a string parameter
     * This will insert the name and paramaeter and sort it's self based
     * on the priority that was passed in
     * RETURN: NOTHING
     */
    public void enqueue(String name, int priority) {
        if (size >= patientArray.length - 1) {
            increaseArray();
        }

        if (size == 0) {
            Patient patient = new Patient(name, priority);
            patientArray[size + 1] = patient;
            size++;
            return;
        }
        size++;
        int place = size;
        patientArray[size] = new Patient(name, priority);
        bubbleUp(place);
    }

    /*
     * PARAM: This is taking in a Patient patient
     * This will insert the name and paramaeter and sort it's self based
     * on the priority that was passed in
     * RETURN: NOTHING
     */
    public void enqueue(Patient patient) {
        this.enqueue(patient.name, patient.priority);
    }

    /*
     * PARAM: NOTHING
     * This will deqeue the element at the front of the lsit and sort
     * it's self based on the priorities
     * RETURN: name of the most urgent priority
     */
    public String dequeue() throws Exception {
        if (size == 0) {
            throw new Exception("Queue is empty");

        }
        String patientname = patientArray[1].name;
        patientArray[1] = patientArray[size];
        patientArray[size] = null;
        size--;
        bubbleDown(1);
        return patientname;
    }

    /*
     * PARAM: Nothing
     * This will peek the most urgent patient in the queue
     * RETURN: patients name
     */
    public String peek() throws Exception {
        if(size == 0) {
            throw new Exception(
                "Queue is empty");
        }
        return patientArray[1].name;
    }

    /*
     * PARAM: Nothing
     * This will peek the most urgent patient in the queue
     * RETURN: patients priority
     */
    public int peekPriority() throws Exception {
        if (size == 0) {
            throw new Exception("Queue is empty");
        }
        return patientArray[1].priority;
    }

    /*
     * PARAM: String name, int newPriority
     * This will take in a new priority and a string name based
     * on the name's priortiy that is being changed
     * RETURN:NOTHING
     */
    public void changePriority(String name, int newPriority) {
        int index = 0;
        for (int i = 0; i <= size; i++) {
            if (patientArray[i].name.equals(name)) {
                index = i;
            }
        }
        int oldp = patientArray[index].priority;
        patientArray[index].priority = newPriority;
        if (newPriority < oldp) {
            bubbleUp(index);
        } else {
            bubbleUp(index);
            bubbleDown(index);
        }
    }


    /*
     * PARAM: Nothing
     * This will peek the most urgent patient in the queue
     * RETURN: true or false, based on queue
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;
        for (int i = 1; i < patientArray.length; i++) {
            patientArray[i] = null;
        }
    }

    /*
     * RETURN: Size of patient queue
     */
    public int size() {
        return size;
    }

    /*
     * RETURN: A STRING OF THE QUEUE IN READABLE FORM
     */
    public String toString() {
        String string = "{";
        for (int i = 1; i < patientArray.length; i++) {
            if (patientArray[i] != null) {
                string += patientArray[i].toString();
            }
            if (i != patientArray.length - 1 && patientArray[i + 1] != null) {
                string += ", ";
            }
        }
        return string + "}";
    }
}
