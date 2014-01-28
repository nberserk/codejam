package codejam.lib;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * fast string match algorithm, O(N)
 * 
 */
public class KMPSearch {
	
	/*
	 * t means previously match count
	 */
	public static int[] generateFailureTable(String word){
		int[] failure = new int[word.length()];
		//failure[0] = 0;
		
		// i : index of word
		// j : match count
		
		int j = 0;
		for (int i = 1; i < word.length(); i++) {
			while (j > 0 && word.charAt(j) != word.charAt(i)) {
				j = failure[j - 1];
			}
			if (word.charAt(j) == word.charAt(i)) {
				j++;
			}
			failure[i] = j;
		}
		
		return failure;
	}
	
	public static int find(String haystack, String needle){
		int[] f = generateFailureTable(needle);
		int m = needle.length();				
		int n = haystack.length();
				
		int j = 0; //index of needle[j]		
		
		for (int i = 0; i < n; i++) {
			while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
				j = f[j - 1];
			}
			if (needle.charAt(j) == haystack.charAt(i)) {
				j++;
			}
			if (j == m) {
				//j = f[j-1];
				return i - m + 1;				
			}
		}
		
		return -1;		
	}
	
	public static int[] findAll(String haystack, String needle){
		int[] f = generateFailureTable(needle);
		int m = needle.length();				
		int n = haystack.length();
				
		int j = 0; //index of needle
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		for (int i = 0; i < n; i++) { // i is index of haystack
			while (j > 0 && needle.charAt(j) != haystack.charAt(i)) {
				j = f[j - 1];
			}
			if (needle.charAt(j) == haystack.charAt(i)) {
				j++;
			}
			if (j == m) {
				result.add(i - m + 1);
				j = f[j-1];				
			}
		}
		
		int[] r = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			r[i] = result.get(i);
		}
		
		return r;		
	}
	
	public static void main(String[] args) {
		// testFailureFunction();
		// testFind();
		
		
		
	}
	
	private static void testFind() {
		int pos = find("AABAACAABAA", "AAB");
		if (pos != 0) {
			System.out.println("wrong");
		}
		
		int[] r = findAll("AABAACAABAA", "AAB");		
		System.out.println(Arrays.toString(r));
		if (r.length  != 2) {
			System.out.println("wrong");
		}
		
		
		
		
		
	}

	public static void testFailureFunction(){
		int[] f = generateFailureTable("AABAACAABAA");
		int[] expected = {0,1,0,1,2,0,1,2,3,4,5};		
		if (!Arrays.equals(f, expected)) {
			System.out.println("wrong");
		}
		
		f = generateFailureTable("AAAAA");
		expected = new int[] {0,1,2,3,4};		
		if (!Arrays.equals(f, expected)) {
			System.out.println("wrong");
		}
		
		f = generateFailureTable("AAABAAA");
		expected = new int[] {0,1,2,0,1,2,3};		
		if (!Arrays.equals(f, expected)) {
			System.out.println("wrong");
		}
		
		f = generateFailureTable("AAABAAA");
		expected = new int[] {0,1,2,0,1,2,3};		
		if (!Arrays.equals(f, expected)) {
			System.out.println("wrong");
		}
		
		f = generateFailureTable("ABABAC");
		expected = new int[] {0,0,1,2,3,0};		
		if (!Arrays.equals(f, expected)) {
			System.out.println("wrong");
		}
		
		
	}

}
