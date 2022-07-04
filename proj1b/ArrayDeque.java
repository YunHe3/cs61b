public class ArrayDeque<T> implements Deque<T> {

	private T[] array;
	private int size; // size of the data
	/** Two pointers point to the head and tail of this array, Cycle structure. */
	private int head;
	private int tail;
	private int length; // length of the array

	/** APIs */
	public ArrayDeque() {
		length = 8;
		array = (T []) new Object[length];
		size = 0;
		head = tail = 0;
	}

//	public ArrayDeque (ArrayDeque<T> other) {
//		head = other.head;
//		tail = other.tail;
//		size = other.size;
//		length = other.length;
//		array = (T []) new Object[length];
//		System.arraycopy(other.array, 0, array, 0, length);
//	}

	@Override
	public void addFirst(T item) {
		size++;
		if (size > length) {
			incArray();
		}
		head = (head + length - 1) % length;
		array[head] = item;
		if (size == 1) {
			tail = head;
		}
	}

	@Override
	public void addLast(T item) {
		size++;
		if (size > length) {
			incArray();
		}
		tail = (tail + length + 1) % length;
		array[tail] = item;
		if(size == 1) {
			head = tail;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T removeFirst() {
		T temp = array[head];
		if (size > 0){
			head = (head + length + 1) % length;
			size--;
		}
		if (size == 0) {
			head = tail;
		}
		if (size > 16 && size < length / 4) {
			decArray();
		}
		return temp;
	}	

	@Override
	public T removeLast() {
		T temp = array[tail];
		if (size > 0) {
			tail = (tail + length - 1) % length;
			size--;
		}
		if (size == 0) {
			tail = head;
		}
		if (size > 16 && size < length / 4) {
			decArray();
		}
		return temp;
	}

	@Override
	public void printDeque() {
		int p = head;
		for (int i = 0; i < size; i++) {
			System.out.print(array[p] + " ");
			p = (p + 1) % length;
		}
		System.out.println();
	}

	@Override
	public T get(int index) {
		return array[(index + head) % length];
	}

	private void incArray() {
		// double the array
		T[] newArray = (T []) new Object[length * 2];
		myArrayCopy(newArray);
		array = newArray;
		length *= 2;
	}

	private void decArray() {
		// half the array
		T[] newArray = (T []) new Object[length / 2];
		myArrayCopy(newArray);
		array = newArray;
		length /= 2;
	}

	private void myArrayCopy(T[] newArray) {
		if (head < tail) {
			System.arraycopy(array, head, newArray, 0, size-1);
		}
		else {
			System.arraycopy(array, head, newArray, 0, length - head);
			System.arraycopy(array, 0, newArray, length - head, tail + 1);
			head = 0;
			tail = size - 2;
		}
	}
 }