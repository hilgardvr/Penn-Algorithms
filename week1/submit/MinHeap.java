

/**
 * A Heap implementation class
 * 
 * @param heap the array that holds the heap data
 * @param size the number of elements currently stored in the heap
 */
public class MinHeap {
	
	CompareInt[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new CompareInt[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(CompareInt val) {
        if (this.size == 0) {
            heap[1] = val;
        } else {
            int trav = size + 1;
            heap[trav] = val;
            while (trav > 1 && heap[trav/2].compareTo(heap[trav]) > 0) {
                CompareInt temp = heap[trav/2];
                heap[trav/2] = heap[trav];
                heap[trav] = temp;
                trav = trav / 2;
            }
        }
        this.size++;
        return;

	}
	
	/**
	 * Extracts the smallest element from the heap
	 */
	public CompareInt extractMin() {
        CompareInt min = heap[1];
		if (this.size > 0) {
            int trav = 1;
            heap[1] = heap[size];
            while (trav * 2 <= size) {
                int indexMin = trav * 2;
                if (indexMin + 1 <= size && heap[indexMin + 1].compareTo(heap[indexMin]) < 0) {
                    indexMin++;
                }
                if (heap[trav].compareTo(heap[indexMin]) > 0) {
                    CompareInt temp = heap[indexMin];
                    heap[indexMin] = heap[trav];
                    heap[trav] = temp;
                } else {
                    break;
                }
                trav = indexMin;
            }
            this.size--;
        }
        return min;
	}
	
	
}
