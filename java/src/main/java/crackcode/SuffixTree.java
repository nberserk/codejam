package crackcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class SuffixTree {
	SuffixTreeNode root = new SuffixTreeNode();
	public SuffixTree(String s){
		for (int i=0;i<s.length(); i++){
			String suffix = s.substring(i);
			root.insertString(suffix, i);
		}
	}

	public ArrayList<Integer> getIndexes(String s) {
		return root.getIndexes(s);
	}

	public static void main(String[] args) {
		String testString = "mississippi";
		String[] stringList = { "is", "sip", "hi", "sis" };
		SuffixTree tree = new SuffixTree(testString);
		for (SuffixTreeNode c : tree.root.mChildren.values()) {
			System.out.println(c.toString());
		}

		for (String s : stringList) {
			ArrayList<Integer> list = tree.getIndexes(s);
			if (list != null) {
				System.out.println(s + ": " + list.toString());
			}
		}

		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
		minHeap.add(1);
		minHeap.add(10);
		int min = minHeap.poll();
		System.out.println(min);
		
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(10,
				new Comparator<Integer>() {
					public int compare(Integer a, Integer a2) {
						return a2 - a;
			}
		});
		maxHeap.add(1);
		maxHeap.add(10);
		int max = maxHeap.poll();
		System.out.println(max);
	}

	
	static class SuffixTreeNode {
		public HashMap<Character, SuffixTreeNode> mChildren = new HashMap<Character, SuffixTreeNode>();
		private char value;
		private ArrayList<Integer> indexes = new ArrayList<Integer>();

		public void insertString(String s, int index) {
			indexes.add(index);
			
			if (s != null && s.length() > 0) {
				value = s.charAt(0);
				SuffixTreeNode child = null;
				if (mChildren.containsKey(value)) {
					child = mChildren.get(value);
				} else {
					child = new SuffixTreeNode();
					mChildren.put(value, child);
				}
				String remainder = s.substring(1);
				child.insertString(remainder, index);
			}
		}

		public ArrayList<Integer> getIndexes(String s) {
			if (s == null || s.length() == 0)
				return indexes;
			else {
				char first = s.charAt(0);
				if (mChildren.containsKey(first)) {
					String remainder = s.substring(1);
					return mChildren.get(first).getIndexes(remainder);
				}
			}
			return null;
		}
		
		@Override
		public String toString() {
			return value + ": " + indexes.toString();
		}

	}
}
