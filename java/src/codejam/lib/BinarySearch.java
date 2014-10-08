package codejam.lib;



public class BinarySearch {
	
	public static interface IValidator{
		boolean validate(int index);		
	}
	
		
	
	public static int binarySearch(int target, int[] a){
		int lo = 0;
		int hi = a.length - 1;
		while (lo<=hi) {
			int mid = lo + (hi-lo)/2;
			if (a[mid] > target ) {
				hi = mid-1;
			}else if (a[mid] < target) {
				lo = mid + 1;
			}else{
				return mid;
			}
		}		
		
		//Arrays.binarySearch(a, key);
		return -1;
	}
	
	/*
	 * ideas comes from python bisect module
	 * gets insertion index of array a, to make a ascending list
	 */
	public static int bisect_left(int lo, int hi, int[] a, int key){
		while (lo<hi) {
			int mid = (lo+hi)/2;
			if (a[mid] < key) {
				lo = mid+1; 
			}else{
				hi = mid;
			}
		}
		return lo;
	}
	
	/*
	 * only difference between bisect_left is, when equal lo = mid +1
	 */
	public static int bisect_right(int lo, int hi, int[] a, int key){
		while (lo<hi) {
			int mid = (lo+hi)/2;
			
			if (a[mid] <= key) {
				lo = mid+1; 
			}else{
				hi = mid;
			}
		}
		return lo;
	}
	
	
	/*
	 * find biggest index of array satisfying the predicate(smaller than targetValue)
	 * yyyyyynnnnnn
	 * find last index of y
	 * 
	 */
	public static int binarySearchBiggestSatisfyingCondition(int lo, int hi, IValidator validator){		
		while (lo<hi) {
			// special
			int mid;
			if (hi-lo ==1) {
				mid = lo + (hi-lo+1)/2; // notice +1 added, round up. this is because
			}else{
				mid = lo + (hi-lo)/2; 
			}
			
			if (validator.validate(mid)){
				lo = mid;
			}else {
				hi = mid-1;
			}
		}
		
		if (validator.validate(lo)) {
			return lo ;
		}		
		return -1;		
	}
	
	/*
	 * find smallest index of array satisfying the predicate(smaller than 100)
	 * nnnnnyyyyyy
	 * find first y
	 */
	public static int binarySearchSmallestSatisfyingCondition(int lo, int hi, IValidator validator){
		while (lo<hi) {
			int mid = lo + (hi-lo)/2; // notice +1 added, round up. this is because 
			if ( validator.validate(mid) ) { // predicate function is true
				hi = mid;
			}else {
				lo = mid+1;
			}
		}
		
		if (validator.validate(lo)) {
			return lo ;
		}		
		return -1;
	}

	static int countKeys(int[] a, int key) {
		int s = binarySearchStart(a, key);
		if (s == -1)
			return 0;
		int e = binarySearchEnd(a, key);
		return e - s + 1;
	}

	static int binarySearchEnd(int[] a, int key) {
		int lo = 0;
		int hi = a.length - 1;

		while (lo < hi) {
			int m = (lo + hi + 1) / 2;
			if (a[m] > key) {
				hi = m - 1;
			} else if (a[m] < key) {
				lo = m + 1;
			} else {
				lo = m;
			}
		}

		if (a[lo] == key)
			return lo;

		return -1;

	}

	static int binarySearchStart(int[] a, int key) {
		int lo = 0;
		int hi = a.length - 1;

		while (lo < hi) {
			int m = (lo + hi) / 2;
			if (a[m] > key) {
				hi = m - 1;
			} else if (a[m] < key) {
				lo = m + 1;
			} else {
				hi = m;
			}
		}

		if (a[lo] == key)
			return lo;

		return -1;
	}
	
	public static void main(String[] args) {
		final int[] a = { 1, 10, 20, 80, 99, 101, 200, 1000 };
		int result = BinarySearch.binarySearchSmallestSatisfyingCondition(0, a.length-1, new IValidator() {			
			@Override
			public boolean validate(int index) {
				if (a[index]>100) {
					return true;
				}
				return false;
			}
		});
		
		result = bisect_left(0, a.length-1, a, 100);
		assert result == 5;
		
		result = bisect_left(0, a.length-1, a, 101);
		assert result == 5;
		
		result = bisect_right(0, a.length-1, a, 101);
		assert result == 6;
			
		result = BinarySearch.binarySearchBiggestSatisfyingCondition(0, a.length-1, new IValidator() {			
			@Override
			public boolean validate(int index) {
				if (a[index]<100) {
					return true;
				}
				return false;
			}
		});
		
		// getting largest one
		System.out.println(result);
		System.out.println("\n");

		//
		int[] a2 = { 1, 2, 3, 3, 3, 3, 5, 6, 7, 8, 9 };
		int[] a3 = { 1, 2, 4, 5, 7 };
		CheckUtil.check(4, countKeys(a2, 3));
		CheckUtil.check(0, countKeys(a3, 3));
		CheckUtil.check(0, countKeys(a3, 100));
	}	

}
