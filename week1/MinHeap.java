public class MinHeap {
	
	int[] heap;
	int size;

	/**
	 * Constructs a new heap with maximum capacity n
	 * Remember to index your heap at 1 instead of 0!
	 * @param n the maximum number of elements allowed in the heap
	 */
	public MinHeap(int n) {
		heap = new int[n+1];
		size = 0;
	}
	
	/**
	 * Adds an element to the heap
	 * 
	 * @param val the value to be added to the heap
	 */
	public void add(int val) {
        if (this.size == 0) {
            heap[1] = val;
        } else {
            int trav = size + 1;
            heap[trav] = val;
            while (trav > 1 && heap[trav/2] > heap[trav]) {
		int temp = heap[trav/2];
		heap[trav/2] = heap[trav];
		heap[trav] = temp;
		trav = trav / 2;
	    }
        }
        this.size++;
		System.out.println("Heap after insert of: " + val);
		for (int i = 1; i <= this.size; i++) {
			System.out.print(heap[i] + " ");
		}
		System.out.println();
		return;
	}
	
	/**
	 * Extracts the smallest element from the heap
	 */
	public int extractMin() {
		if (this.size > 0) {
			int trav = 1;
			int min = heap[1];
			heap[1] = heap[size];
			while (trav * 2 <= size) {
				int indexMin = trav * 2;
				if (indexMin + 1 <= size && heap[indexMin + 1] < heap[indexMin]) {
					indexMin++;
				}
				if (heap[trav] > heap[indexMin]) {
					int temp = heap[indexMin];
					heap[indexMin] = heap[trav];
					heap[trav] = temp;
				} else {
					break;
				}
				trav = indexMin;
			}
			this.size--;
			return min;
		}		
		return 0;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
			arr[i] = Integer.parseInt(args[i]);
		}
		System.out.println();
		MinHeap h = new MinHeap(arr.length);
		for (int i = 0; i < arr.length; i++) {
			h.add(arr[i]);
		}
		for (int i = 0; i < 3; i++) {
			System.out.println("min: " + h.extractMin());
		}
		System.out.println("After");
		for (int i = 1; i <= h.size; i++) {
			System.out.print(h.heap[i] + " ");
		}
		System.out.println();
		return;
	}
		
}
