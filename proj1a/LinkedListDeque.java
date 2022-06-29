public class LinkedListDeque <T>{

	/** Basic data struct.*/
	private class Node {

		public T value;
		public Node pre;
		public Node next;

		public Node() {
			pre = next = null;
		}

		public Node(T v) {
			// constructor
			this.value = v;
			pre = next = null;
		}

	}

	private int size;
	private final Node SentinelNode;


    /** Public methods. (APIs)*/

    public LinkedListDeque() {
    	// constructor
    	size = 0;
    	SentinelNode = new Node();
    	// cycle struct
    	SentinelNode.next = SentinelNode;
    	SentinelNode.pre = SentinelNode;
    }

    public LinkedListDeque(LinkedListDeque<T> other) {
    	// deep copy
    	this.size = other.size;
		SentinelNode = new Node();
    	Node p = other.SentinelNode.next;
    	
    	int count = size;
    	while (count > 0) {
    		addLast(p.value);
    		count--;
    	}
    }

    public void addFirst(T item) {
    	size++;
    	Node p = SentinelNode;
    	Node q = new Node(item);
    	q.next = p.next;
    	p.next.pre = q;
    	p.next = q;
    	q.pre = p;
    }

    public void addLast(T item) {
    	size++;
    	Node p = SentinelNode;
    	Node q = new Node(item);
    	q.pre = p.pre;
    	p.pre.next = q;
    	p.pre = q;
    	q.next = p;
    }

    public boolean isEmpty() {
    	return (size == 0);
    }

    public int size() {
    	return size;
    }

    public void printDeque() {
		int count = size;
    	Node p = SentinelNode.next;
    	while (count > 0) {
    		System.out.print(p.value);
    		p = p.next;
			count--;
    	}
    }

    public T removeFirst() {
    	size--;
    	Node p = SentinelNode;
    	Node q = p.next;
    	p.next = q.next;
    	return q.value;
    }

    public T removeLast() {
    	size--;
    	Node p = SentinelNode;
    	Node q = p.pre;
    	p.pre = q.pre;
    	return q.value;
    }

    public T get(int index) {
    	Node p = SentinelNode;
    	for (int i = 0; i <= index; i++) {
    		p = p.next;
    	}
    	return p.value;
    }

     public T getRecursize(int index) {
     	// Same as get, but use recursize.
     	return helper(SentinelNode.next, index);
     }

	 private T helper(Node ptr, int index) {
		if (index == 0) return ptr.value;
		else {
			return helper(ptr.next, index - 1);
		}
	 }
}