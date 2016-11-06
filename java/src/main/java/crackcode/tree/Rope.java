package main.java.crackcode.tree;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * from: http://www.careercup.com/question?id=24532662
 *
 * rope data structure?
 *

 You are given two array, first array contain integer which represent heights of persons and second array contain how many persons in front of him are standing who are greater than him in term of height and forming a queue. Ex
 A: 3 2 1
 B: 0 1 1
 It means in front of person of height 3 there is no person standing, person of height 2 there is one person in front of him who has greater height then he, similar to person of height 1. Your task to arrange them
 Ouput should be.
 3 1 2
 Here - 3 is at front, 1 has 3 in front ,2 has 1 and 3 in front.

 * idea :
 * - make tree has count property. count is # left nodes(taller)

 */

public class Rope {
	static class Node {
		int value;
		int count;
		Node left, right;

		Node(int v) {
			value=v;
			count=1;
		}
	}

	// assume height is sorted by decreasing order
	List<Integer> sortByCountTallerThanSelf(int[] height, int[] count){
		Node root = new Node(height[0]);
		for (int i = 1; i < height.length; i++) {
			Node cur = new Node(height[i]);
			insertNode(root, cur, count[i]);
		}
		List<Integer> ret = new ArrayList<>();
		traverse(root, ret);

		return ret;
	}

	private void traverse(Node node, List<Integer> ret) {
		if(node==null) return;

		traverse(node.left, ret);
		ret.add(node.value);
		traverse(node.right, ret);
	}

	private void insertNode(Node node, Node cur, int count) {
		if (node.count > count){
			node.count++;
			if(node.left==null) {
				node.left=cur;
			}else insertNode(node.left, cur, count);
		}else {
			count-=node.count;
			if(node.right==null){
				node.right= cur;
			}else insertNode(node.right, cur, count);
		}
	}

	@Test
	public void test(){
		int[] h = {6, 5, 4, 3, 2, 1};
		int[] c = {0, 0, 0, 2, 2, 4};

		int[] h2 = {3, 2, 1};
		int[] c2 = {0, 1, 1};

		assertEquals("[3, 1, 2]", sortByCountTallerThanSelf(h2, c2).toString());
		assertEquals("[4, 5, 2, 3, 1, 6]", sortByCountTallerThanSelf(h, c).toString());

	}

}
