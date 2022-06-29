public class ArrayDeque<T> {

	T[] array;
	int size; // size of the data
	/** Two pointers point to the head and tail of this array, Cycle structure. */
	int head;
	int tail;
	int length; // length of the array

	/** APIs */
	public ArrayDeque () {
		length = 8;
		array = (T []) new Object[length];
		size = 0;
		head = tail = 0;
	}

	public ArrayDeque (ArrayDeque other) {
		size = other.size;
		array = (T []) new Object[size];
		System.arraycopy(other.array, 0, array, 0, size);
	}

	public void addFirst(T item) {
		size++;
		if (size > length) {
			incArray();
		}
		head = (head - 1) % length;
		array[head] = item;
	}

	public void addLast(T item) {
		size++;
		if (size > length) {
			incArray();
		}
		tail = (tail + 1) % length;
		array[tail] = item;
	}

	public boolean isEmpty() {
		return (size == 0);
	}

	public int size() {
		return size;
	}

	public T removeFirst() { 
		T temp = array[head];
		head = (head + 1) % length;
		size--;
		if (size > 16 && size < length / 4) {
			decArray();
		}
		return temp;
	}	

	public T removeLast() {
		T temp = array[tail];
		tail = (tail - 1) % length;
		size--;
		if (size > 16 && size < length / 4) {
			decArray();
		}
		return temp;
	}

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
			System.arraycopy(array, head, newArray, 0, size);
		}
		else {
			System.arraycopy(array, head, newArray, 0, length - head);
			System.arraycopy(array, 0, newArray, length - head - 1, tail + 1);
			head = 0;
			tail = size - 1;
		}
	}
 }