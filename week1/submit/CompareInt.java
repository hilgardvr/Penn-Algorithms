public class Sort {
	public static void merge(CompareInt arr[], int start, int end) {
		int length = end - start + 1;
		CompareInt[] temp = new CompareInt[length];
		int mid = (end + start) / 2;
		int leftStart = start;		
		int midStart = mid + 1;
		int ctr = 0;
		
		while (leftStart <= mid && midStart <= end) {
			if (arr[leftStart].compareTo(arr[midStart]) < 0) {
				temp[ctr] = arr[leftStart];
				leftStart++;
			} else {
				temp[ctr] = arr[midStart];
				midStart++;
			}
			ctr++;
		}
		while (leftStart <= mid) {
			temp[ctr] = arr[leftStart];
			ctr++;
			leftStart++;
		}
		while (midStart <= end) {
			temp[ctr] = arr[midStart];
			ctr++;
			midStart++;
		}
		ctr = 0;
		while (ctr < length) {
			arr[start + ctr] = temp[ctr];
			ctr++;
		}
	}

	public static void mergesort(CompareInt arr[], int start, int end) {
		if (start < end) {
			int mid = (end + start) / 2;
			mergesort(arr, start, mid);
			mergesort(arr, mid + 1, end);
			merge(arr, start, end);
		}
		return;
	}

	public static void mergesort(CompareInt[] arr) {
		mergesort(arr, 0, arr.length - 1);
	}

	public static void main(String[] args) {
		CompareInt[] arr = new CompareInt[args.length];
		for (int i = 0; i < args.length; i++) {
			System.out.print(args[i] + " ");
			arr[i] = Integer.parseInt(args[i]);
		}
		System.out.println();
		mergesort(arr);
		for (int i = 0; i < args.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		return;
	}
}
