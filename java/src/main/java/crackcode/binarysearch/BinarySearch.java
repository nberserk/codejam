package codejam.lib.binarysearch;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinarySearch {
	
	public static interface IValidator{
		boolean validate(int index);		
	}
	
	public static int binarySearch(int target, int[] a){
		int lo = 0;
		int hi = a.length - 1;
		while (lo<=hi) { // notice = is there.
			int mid = lo + (hi-lo)/2;
			if (a[mid] > target ) {
				hi = mid-1;
			}else if (a[mid] < target) {
				lo = mid + 1;
			}else{
				return mid;
			}
		}

		return -1;
	}


	public static int lowerBound(int lo, int hi, int[] a, int key){
		while (lo<hi) {
			int mid = lo + (hi-lo)/2;
			if (a[mid] < key) {
				lo = mid+1; 
			}else if(a[mid]>key) {
                hi = mid;
            }else{
				hi = mid;
			}
		}
        if(a[lo]==key) return lo;
        return -1;
	}

	public static int upperBound(int lo, int hi, int[] a, int key){
		while (lo<hi) {
			int mid = lo+(hi-lo+1)/2;
			
			if (a[mid] < key) {
				lo = mid;
			}else if (a[mid]>key){
                hi = mid-1;
            }else{
				lo = mid;
			}
		}
        if(a[lo]==key) return lo;
        return -1;
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
			int mid = lo + (hi-lo+1)/2; // notice +1 added, to work around infinite loop
			
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

	static int binarySearchRange(int[] a, int key) {
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
			int m = lo + (hi-lo) / 2;
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
	
	/**
	 * find popular repeated number n/4 times or more. log(N) required.
	 * 
	 * @param a
	 *            sorted array
	 * @param minCount
	 *            minCount
	 * @return popular number
	 **/
	static int findDominantNumber(int[] a) {
		int n = a.length;
		int minCount = n / 4;
		int target = a[n / 4];
		int r = binarySearchRange(a, target);
		if (r >= minCount)
			return target;

		target = a[n / 2];
		r = binarySearchRange(a, target);
		if (r >= minCount)
			return target;

		target = a[(n * 3) / 4];
		r = binarySearchRange(a, target);
		if (r >= minCount)
			return target;
		else
			return -1;
	}

    @Test
	public void testFindDominantNumber() {
		int r;
		int[] a = { 1, 2, 3, 4, 4, 4, 4, 4, 5, 6, 7, 8 };
		assertEquals(4, findDominantNumber(a));

		int[] b = { 1, 2, 3, 4, 5, 6, 7, 8 };
		assertEquals(-1, findDominantNumber(b));

		int[] c = { 1, 2, 2, 4, 5, 6, 7, 8 };
		assertEquals(2, findDominantNumber(c));
	}

	@Test
	public void testBinarySearchRange(){
		int[] a2 = { 1, 2, 3, 3, 3, 3, 5, 6, 7, 8, 9 };
		int[] a3 = { 1, 2, 4, 5, 7 };
		assertEquals(10, binarySearchStart(a2, 9));
		assertEquals(10, binarySearchEnd(a2, 9));
		assertEquals(-1, binarySearchStart(a2, 10));
		assertEquals(-1, binarySearchStart(a2, -1));
		assertEquals(4, binarySearchRange(a2, 3));
		assertEquals(0, binarySearchRange(a3, 3));
		assertEquals(0, binarySearchRange(a3, 100));
	}
    /*
	public static void main(String[] args) {
		testFindDominantNumber();

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

	}
*/
    @Test
    public void testBinarySearch(){
        int[] a= new int[]{1, 2,  3, 4, 7, 9, 10};

        assertEquals(2, binarySearch(3, a));
        assertEquals(-1, binarySearch(0, a));
        assertEquals(-1, binarySearch(100, a));
    }
    @Test
    public void testLowerBound_upperBound(){
        int[] a= new int[]{1, 1, 2, 2, 3, 3};

        int len = a.length-1;

        assertEquals(2, lowerBound(0, len, a, 2));
        assertEquals(-1, lowerBound(0, len, a, 4));
        assertEquals(3, upperBound(0, len, a, 2));
        assertEquals(5, upperBound(0, len, a, 3));
        assertEquals(-1, upperBound(0, len, a, 4));
    }

}
