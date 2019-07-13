import java.util.Arrays;

public class QuickSelect {

	public static void swop(int[] arr, int l, int r) {
		int temp = arr[l];
		arr[l] = arr[r];
		arr[r] = temp;
		return;
	} 

	public static int partition(int[] arr, int l, int r) {
		int pivot = arr[r];
		int index = l;
		for (int ctr = l; ctr < r; ctr++) {
			if (arr[ctr] <= pivot) {
				swop(arr, index, ctr);
				index++;
			}
		}
		swop(arr, index, r);
		return index;
	}

	public static int quickSelect(int[] arr, int l, int r, int k) {
		int index = partition(arr, l, r); 
		if (index - l == k - 1) {
			return arr[index];
		} else if (index - l > k - 1) {
			return quickSelect(arr, l, index - 1, k);
		} else {
			return quickSelect(arr, index + 1, r, k - index + l - 1);
		}
	}

	public static int quickSelect(int k, int[] arr) {
		return quickSelect(arr, 0, arr.length - 1, k);
	}

	public static void main(String[] args) {
		int[] arr = new int[args.length];
		for (int i = 0; i < args.length; i++) {
			arr[i] = Integer.parseInt(args[i]);
		}
		System.out.println();
		System.out.println("k: " + arr[0]);
		int[] temp = Arrays.copyOfRange(arr, 1, arr.length);
		for (int j = 0; j < temp.length; j++) {
			System.out.print(temp[j] + " ");
		}
		System.out.println();
		System.out.println("kth: " + quickSelect(arr[0], Arrays.copyOfRange(arr, 1, arr.length)));
		System.out.println();
		return;
	}
}
