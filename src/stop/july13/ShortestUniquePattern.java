package stop.july13;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

//import org.junit.Assert;

public class ShortestUniquePattern {
	BufferedWriter mWriter = null;
	/**
	 * @param args
	 */
	
	public static class Trie {		 
        private TrieNode root = new TrieNode();
        
        public void addWord(String str) {
        	char[] c = str.toCharArray();
        	root.addWord(c, 0);
		}
        
        public int minLengthUniqueSubSequence(String needle, int currentMin){
        	char[] c = needle.toCharArray();
        	
//        	System.out.println("Finding " + needle);
        	TrieNode node = root.getChildNode(c[0]);
        	if (node.getCount()==1) {
//        		System.out.println("found: "+1);
				return 1;
			}
        	return node.uniqueLength(c, 1, currentMin);        	
        }
        
        @Override
        public String toString() {
        	return root.toString();        	
        }

		public void print() {
			for (char c: root.mChildren.keySet()) {
				TrieNode node = root.mChildren.get(c);
				System.out.println( node.toString());
			}
			
		}
	}
	
	public static class TrieNode {
		private int depth; 
		public int mCount;
		private Character mChar;
		public HashMap<Character, TrieNode> mChildren = new HashMap<Character, TrieNode>();
		
		public TrieNode(){
			// just for Trie
		}
		
		public TrieNode(char d, int depth) {
			mChar=d;
			this.depth = depth;
		}
		
		public int getCount() {
			return mCount;
		}
		
		public int uniqueLength(char[] c, int idx, int currentMin) {
			if (idx>= c.length) {
				return -1;
			}
//			if (idx+1 >= currentMin) {
//				return -1;
//			}

//			for (int i = 0; i < depth; i++) {
//				System.out.print(" ");
//			}
//			System.out.println(mChar);
			
			TrieNode node = mChildren.get(c[idx]);
			if (node.getCount()==1) {
				return idx+1;
			}
			return node.uniqueLength(c, idx + 1, currentMin);			
		}
		
		@Override
		public String toString() {	
			StringBuilder sb = new StringBuilder();
			
			String indent = "";
			for (int i = 0; i < depth; i++) {
				indent += "  ";
			}
			sb.append(indent + mChar+"-"+mCount + "\n");
			
			//indent += " ";
			for (char key : mChildren.keySet()) {
				
				TrieNode node = mChildren.get(key);
				sb.append(node.toString());
			}
			
			return sb.toString();			
		}

		public void addWord(char[] c, int index) {
			if (index >= c.length) {
				return;
			}
			
			char key = c[index];
			TrieNode node = mChildren.get(key);
			if (node==null) {
				node = new TrieNode(key, depth+1);
				mChildren.put(key, node);
			}
			node.mCount++;
			node.addWord(c, index +1);			
		}

		public TrieNode getChildNode(char ch) {
			return mChildren.get(ch);			
		}
	
		
		
		
	}
	
	
	public static void main(String[] args) {
		tests();
		
		ShortestUniquePattern a = new ShortestUniquePattern();
		try {
			// a.solution("d:/flag_1.in", "d:/flag_1.out");
			a.solution("./src/july2013/string.in", "./src/july2013/string.out");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void tests(){
//		Trie trie = new Trie();
//		
//		String[] strings = new String[] {"zebra", "brazil"};
//		for (int i = 0; i < strings.length; i++) {
//			for (int k = 0; k < strings[i].length(); k++) {
//				String s = strings[i].substring(k);
//				//System.out.println(s);
//				trie.addWord(s);					
//			}			
//		}		
//		
//		//Assert.assertEquals(expected, actual);
//		Assert.assertEquals(4 , trie.minLengthUniqueSubSequence("braz", 100));
		
		
	}

	public void solution(String inputFilePath, String usrOutputFilePath)
			throws IOException {

		BufferedReader br = null;


		br = new BufferedReader(new FileReader(inputFilePath));
		mWriter = new BufferedWriter(new FileWriter(usrOutputFilePath));

		String str = br.readLine();
		int problemCount = Integer.parseInt(str);

		for (int i = 0; i < problemCount; i++) {
			// parse a problem
			
			int wordCount = Integer.parseInt(br.readLine());
			String[] strings = new String[wordCount];			
			
			for (int j = 0; j < wordCount; j++) {
				strings[j] = br.readLine().trim();
			}
			
			int[]	r = solveProblemUsingTrie(strings); //solveProblem(strings);
			
			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Case# %d\n", i+1));
			for (int j : r) {
				sb.append(j + "\n");
			}
			String out = sb.toString();
			print(out);
			write(out);
		}

		try {
			br.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		try {
			mWriter.flush();
			mWriter.close();
		} catch (Exception e2) {
			System.out.println(e2);
		}

	}
	
	private int[] solveProblemUsingTrie(String[] strings) {
		int[] r = new int[strings.length];
		Arrays.fill(r, Integer.MAX_VALUE);
		
		Trie t = new Trie();
		for (int i = 0; i < strings.length; i++) {
			for (int k = 0; k < strings[i].length(); k++) {
				String s = strings[i].substring(k);
				//System.out.println(s);
				t.addWord(s);					
			}			
		}
		
		System.out.println(t);
		
		//t.print();		
		for (int i = 0; i < strings.length; i++) {			
			for (int k = 0; k < strings[i].length(); k++) {
				String s = strings[i].substring(k);
				int min = t.minLengthUniqueSubSequence(s, r[i]);
				System.out.println("searching "+s+ ": "+ min);
				if (min!=-1 ) {
					r[i] = Math.min(min, r[i]);
					if (r[i]==1) {
						// already min value, so skip additional searching
						break;
					}
				}
			}
			
			
//outer:		for (int j = 1; j < str.length(); j++) {
//				for (int k = 0; k < str.length(); k++) {
//					String s = str.substring(k, k+j);
//					if(t.contains(s.toCharArray(),false)==false){
//						r[i] = s.length();
//						break outer;
//					}
//				}
//			}
		}
		
		for (int i : r) {
			if (i== Integer.MAX_VALUE) {
				throw new RuntimeException("oops");
			}
		}
		
		return r;
	}
	

	private int[] solveProblem(String[] strings) {
		int[] r = new int[strings.length];
		
			
		
		
		//Arrays.fill(r, -1);
		
		for (int i = 0; i < r.length; i++) {
			String source = strings[i];
			int len = source.length();
			r[i] = len;
			
			// init cache
			int[][][] c = new int[strings.length][len][len];
			
outer:		for (int j = 1; j <= len; j++) {
				int[][] pp = new int[strings.length][len]; 
				for (int k = 0; k <= len-j; k++) {
					
					Point p = new Point(k,j);
					String s = source.substring(k, k+j);
					boolean found = false;
					for (int l = 0; l < strings.length; l++) {						
						if (i==l) {
							continue;
						}
						
						int prev=0;
						if (j>1) {
							prev = c[l][k][j-1];
							if (prev==-1) {
								c[l][k][j] = -1;
								continue;
							}
						}
						
						int idx = strings[l].indexOf(s, prev);
						c[l][k][j] = idx;
						if (idx!=-1) {
							found = true;
							break;
						}else{
							
						}
					}
					if (found==false) {
						r[i] = j;
						break outer;
					}
				}				
			}
		}
		
		return r;
	}	

	private void write(String str) {
		try {
			mWriter.write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void print(String str) {
		System.out.println(str);
	}

}
