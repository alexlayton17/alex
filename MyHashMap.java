import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/* Name: Alex Layton
 * Date: 3/29/2020
 * 
 * The purpose of this project is to implement our own hashMap. I am going to approach
 * it by having an ArrayList<LinkedList<HashNode<K,V>>>. The reason I chose this is because
 * I feel like it would be a lot more simpler to implement when having to deal with chaining and collisions 
 * for example. The LinkedList will be backed up by a HashNode class which will have traits of it's own that will
 * be very helpful throughout the project.
 * 
 * Example: 
 * MyHashMap<Integer, Integer> hash = new MyHashMap<Integer,Integer>();
 * hash.put(1,1);
 * hash.put(2,2);
 * hash.put(3,3);
 * hash.printTable();
 * 
 * 	Index 0: (0 conflicts), []
	Index 1: (0 conflicts), [1,]
	Index 2: (0 conflicts), [2,]
	Index 3: (0 conflicts), [3,]
	Index 4: (0 conflicts), []
	Index 5: (0 conflicts), []
	Index 6: (0 conflicts), []
	Index 7: (0 conflicts), []
	Total # of conflicts: 0
 * 
 */
public class MyHashMap<K,V> {
	
	private static final int numBuckets = 8;
	private ArrayList<LinkedList<HashNode<K,V>>> myHash;
	private int size;
	
	/* PARAM: N/A
	 * RETURN: A new instance of the MyHashMap class
	 */
	public MyHashMap() {	
		size = 0;
		myHash = new ArrayList<LinkedList<HashNode<K,V>>>();
		for(int i=0; i< numBuckets; i++) {
			myHash.add(new LinkedList<HashNode<K,V>>());
		}
	}
	
	/* PARAM: K key
	 * This will calculate using the built in
	 * hashcode function which bucket the key will go into
	 * RETURN: int index
	 */
	private int hash(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % numBuckets;
		return Math.abs(index);
	}
	
	/* PARAM: N/A
	 * This will reset every linked list in the ArrayList
	 * back to a new LinkedList
	 * RETURN: N/A
	 */
	private void clearMap(){
		myHash = new ArrayList<LinkedList<HashNode<K,V>>>();
		for(int i=0; i< numBuckets; i++) {
			myHash.add(new LinkedList<HashNode<K,V>>());
		}
	}
	
	/* PARAM: N/A
	 * This will clear the hashMap and set the size to 0
	 * RETURN: hashMap that is cleare
	 */
	public void clear() {
		size =0;
		clearMap();
	}
	
	/* PARAM: N/A
	 * This will search through the map and return the value that is mapped with the key
	 * RETURN: V value associated with the key
	 */
	public V get(K key) {
		int bucket = hash(key);
		LinkedList<HashNode<K,V>> llist = myHash.get(bucket);
		for(int i=0; i< llist.size(); i++) {
			if(llist.get(i).getKey().equals(key)) {
				return llist.get(i).getValue();
			}
			continue;
		}
		return null;
	}

	/* PARAM: K key
	 * This will search through the hashMap to
	 * check if the key that is passed in is contained
	 * RETURN: boolean. true or false
	 */
	public boolean containsKey(K key) {
		int bucket = hash(key);
		LinkedList<HashNode<K,V>> llist = myHash.get(bucket);
		for(HashNode<K,V> nodes: llist) {
			if(nodes.getKey().equals(key)) {
				return true;
			}
			continue;
		}
		return false;
	}
	
	/* PARAM: N/A
	 * RETURN: boolean whether hashMap is empty or not
	 * 
	 */
	public boolean isEmpty() {
		return size ==0;
	}
	
	/* PARAM: K key
	 * This will remove the element corresponding with the key
	 * RETURN: V value paired to that key
	 * 
	 */
	public V remove(K key) {
		int bucket = hash(key);
		V returnVal = null;
		LinkedList<HashNode<K,V>> llist = myHash.get(bucket);
		for(int i=0; i < llist.size(); i++) {
			if(llist.get(i).key.equals(key)) {
				returnVal = llist.get(i).value;
				llist.remove(i);
				size--;
				return returnVal;
			}
			continue;
		} return null;
	}
	
	/* PARAM: V val
	 * This will search through the hashMap to
	 * check if the value that is passed in is contained
	 * RETURN: boolean. true or false
	 */
	public boolean containsValue(V val) {
		for(int i=0; i< numBuckets; i++) {
			LinkedList<HashNode<K,V>> list = myHash.get(i);
			for(int x=0; x< list.size(); x++) {
				if(list.get(x).value.equals(val)) {
					return true;
				}	
				continue;
			}
			continue;
		}	
		return false;
	}
	/* PARAM: N/A
	 * RETURN: A set of all keys
	 */
	public java.util.Set<K> keySet(){
		Set<K> keys = new HashSet<K>();
		for(int i=0; i < numBuckets; i++) {
			LinkedList<HashNode<K,V>> list = myHash.get(i);
			for(int x=0; x < list.size(); x++) {
				keys.add(list.get(x).key);
			}
			continue;
		}return keys;
	}
	/* PARAM: K key, V val
	 * This will put the new HashNode in the hashmap
	 * RETURN: V val corresponding to the key
	 */
	public V put(K key, V val) {
		int bucket = hash(key);
		HashNode<K,V> newNode = new HashNode<K,V>(key,val);
		V returnVal = null;
		if(myHash.get(bucket).isEmpty()) {
			myHash.get(bucket).add(0,newNode);
		} else {
			LinkedList<HashNode<K,V>>list = myHash.get(bucket);
			for(int i=0; i < list.size(); i++) {
				if(list.get(i).key.equals(key)) {
					returnVal = list.get(i).value;
					list.get(i).value = val;
					return returnVal;
				}
			}myHash.get(bucket).add(0, newNode);
		}
		size++;
		return null;
	}

	/*PARAM: N/A
	 * RERTURN: SIZE of map
	 * 
	 */
	public int size() {
		return size;
	}
	
	/*PARAM: N/A
	 * RETURN: a printed table with the keys and conflicts happening in the map
	 * 
	 */
	public void printTable() {
		int allConflicts =0;
		for(int i=0; i< numBuckets; i++) {
			 System.out.println("Index " + i + ": (" + calculateConflicts(myHash.get(i)) + " conflicts), [" + printValues(myHash.get(i)) + "]") ;
			 allConflicts += calculateConflicts(myHash.get(i));	
		} System.out.print("Total # of conflicts: " + allConflicts);	
	}
	/* PARAM: LinkedList<HashNode<K,V>>
	 * RETURN: int of how many valus are paired to the same key
	 * 
	 */
	private int calculateConflicts(LinkedList<HashNode<K,V>> llist) {
		int conflicts = 0;
		for(int i=0; i< llist.size(); i++) {
			conflicts +=1;
		}if(conflicts == 0 || conflicts ==1) {
			return 0;
		}return conflicts-1;
	}

	/* PARAM: LinkedList<HashNode<K,V>>
	 * RETURN: all the values in the map printed
	 */
	private String printValues(LinkedList<HashNode<K,V>> llist) {
		String s ="";
		for(int i=0; i <llist.size(); i++) {
			s+= llist.get(i).key + ",";
		}		return s;
	}
	
	private class HashNode<K,V> {
		private K key;
	    private V value;
	    private HashNode<K, V> next;
	    public HashNode(K key, V value)
	    {
	        this.setKey(key);
	        this.setValue(value);
	    }

		public K getKey() {
			return key;
		}
		public void setKey(K key) {
			this.key = key;
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		public HashNode<K, V> getNext() {
			return next;
		}
		public void setNext(HashNode<K, V> next) {
			this.next = next;
		}
	}	
}
