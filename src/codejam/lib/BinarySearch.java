package codejam.lib;

import java.util.Arrays;

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
	
	public static void main(String[] args) {
		final int[] a = {1,10,20, 80, 99,101,200,1000};
		int result = BinarySearch.binarySearchSmallestSatisfyingCondition(0, a.length-1, new IValidator() {			
			@Override
			public boolean validate(int index) {
				if (a[index]>100) {
					return true;
				}
				return false;
			}
		});
		
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
	}
	

}
