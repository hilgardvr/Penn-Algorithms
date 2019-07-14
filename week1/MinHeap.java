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
		System.out.println();
		return;
	}
		
}
