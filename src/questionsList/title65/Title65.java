package questionsList.title65;

public class Title65 {
	public double findMedianSortedArrays(int[] A, int[] B) {
		// write your code here
		int length = A.length + B.length, i = 0, j = 0;
		if ((length & 1) == 1) {
			int mid = (length + 1) >> 1;
			int countNum = 0;
			while (countNum < mid - 1) {
				if (i == A.length) {
					j++;
				} else if (j == B.length) {
					i++;
				} else if (A[i] < B[j]) {
					i++;
				} else {
					j++;
				}
				countNum++;
			}
			if (i == A.length) {
				return B[j];
			}
			if (j == B.length) {
				return A[i];
			}
			if (A[i] < B[j]) {
				return A[i];
			} else {
				return B[j];
			}
		} else {
			int mid = length >> 1;
			int countNum = 0;
			while (countNum < mid - 1) {
				if (i == A.length) {
					j++;
				} else if (j == B.length) {
					i++;
				} else if (A[i] < B[j]) {
					i++;
				} else {
					j++;
				}
				countNum++;
			}
			if (i == A.length) {
				return (double) (B[j] + B[j + 1]) / 2;
			}
			if (j == B.length) {
				return (double) (A[i] + A[i + 1]) / 2;
			}
			if (A[i] < B[j]) {
				if (i == A.length - 1) {
					return (double) (A[i] + B[j]) / 2;
				}
				if (A[i + 1] < B[j]) {
					return (double) (A[i] + A[i + 1]) / 2;
				}
				return (double) (A[i] + B[j]) / 2;
			} else {
				if (j == B.length - 1) {
					return (double) (A[i] + B[j]) / 2;
				}
				if (B[j + 1] < A[i]) {
					return (double) (B[j] + B[j + 1]) / 2;
				}
				return (double) (A[i] + B[j]) / 2;
			}
		}
	}

	public static void main(String[] args) {
		Title65 a = new Title65();
		int[] s = { 1, 2, 3, 4, 5, 6 };
		int[] q = { 2, 3, 4, 5 };
		System.out.println(a.findMedianSortedArrays(s, q));
	}
}
