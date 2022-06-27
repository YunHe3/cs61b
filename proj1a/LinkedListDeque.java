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

		public Node(T v, Node p, Node a) {
			this.value = v;
			this.pre = p;
			this.next = a;
		}

	}

	private int size;
	private Node firstSentinelNode;
	private Node lastSentinelNode;


    /** Public methods. (APIs)*/

    public LinkedListDeque() {
    	// constructor
    	size = 0;
    	firstSentinelNode = new Node();
    	lastSentinelNode = new Node();

    	// cycle struct
    	firstSentinelNode.next = lastSentinelNode;
    	lastSentinelNode.pre = firstSentinelNode;
    	// firstSentinelNode.pre = lastSentinelNode;
    	// lastSentinelNode.next = firstSentinelNode;
    }

    public LinkedListDeque(LinkedListDeque other) {
    	// deep copy
    	this.size = other.size;
    	Node p = other.firstSentinelNode.next;
    	
    	while (p.next != null) {
    		addLast(p.value);
    	}

    }

    public void addFirst(T item) {
    	size++;
    	Node p = firstSentinelNode;
    	p.next = new Node(item, p, p.next);
    }

    public void addLast(T item) {
    	size++;
    	Node p = lastSentinelNode;
    	p.pre.next = new Node(item, p.pre, p);
    }

    public boolean isEmpty() {
    	return (size == 0);
    }

    public int size() {
    	return size;
    }

    public void printDeque() {
    	Node p = firstSentinelNode.next;
    	while (p.next != null) {
    		System.out.print(p.value);
    		p = p.next;
    	}
    }

    public T removeFirst() {
    	size--;
    	Node p = firstSentinelNode;
    	Node q = p.next;
    	p.next = q.next;
    	return q.value;
    }

    public T removeLast() {
    	size--;
    	Node p = lastSentinelNode;
    	Node q = p.pre;
    	p.pre = q.pre;
    	return q.value;
    }

    public T get(int index) {
    	Node p = firstSentinelNode;
    	for (int i = 0; i <= index; i++) {
    		p = p.next;
    	}
    	return p.value;
    }

    // public T getRecursize(int index) {
    // 	// Same as get, but use recursize.
    // 	if (index == 0) return firstSentinelNode.next;



    // }
}