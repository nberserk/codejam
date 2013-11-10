package topcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/*
 * SRM 218
 * http://community.topcoder.com/stat?c=problem_statement&pm=3093&rd=5864
 */
public class PermissionTree {

	public static void main(String[] args) {
		int[] r = null;
		
		PermissionTree p = new PermissionTree();
		r = p.findHome(new String[] {"0 Admin", "0 Joe,Phil", "0 Joe"}, new String[]{"Admin", "Joe", "Phil"});
		System.out.println(Arrays.toString(r));

		r = p.findHome(new String[] { "0 Admin" }, new String[] { "Peter",
				"Paul", "Mary" });
		System.out.println(Arrays.toString(r));

		r = p.findHome(new String[] { "0 Admin", "2 John", "0 Peter,John",
				"0 Tim", "1 John" }, new String[] { "John" });
		System.out.println(Arrays.toString(r));

		r = p.findHome(new String[] { "0 Admin", "0 Jeff", "1 Mark,Tim",
				"1 Tim,Jeff", "0 Dan", "4 Ed", "4 Tom", "4 Kyle,Ed", "0 Ben",
				"8 Rich", "8 Sam", "8 Tim" }, new String[] { "Jeff", "Ed",
				"Tim", "Steve" });
		System.out.println(Arrays.toString(r));

	}
	
	static class Node{
		public Node(Node parent) {
			this.parent = parent;
		}
		int index;
		Node parent;
		ArrayList<Node> children = new ArrayList<Node>();
		HashSet<String> users= new HashSet<String>();
		public int getDepth(){
			int d = 0;
			Node cur = this;
			while (cur.parent!=null) {
				d++;
				cur = cur.parent;				
			}
			return d;
		}

		@Override
		public String toString() {
			return String.format("%d %s", index, users);
		}
		public Node findIndex(int parentIndex) {
			if (parentIndex==index) {
				return this;
			}
			
			for (Node node : children) {
				Node c = node.findIndex(parentIndex);
				if (c!=null) {
					return c;
				}
			}
			return null;
		}
		public Node findHome(String user) {
			Node home = null;
			
			
			if (users.contains(user)) {
				return this;
			}
			
			for (Node c : children) {
				Node candidate = c.findHome(user);
				if (candidate==null) {
					continue;
				}
				if (home==null) {
					home = candidate;
				}else{
					int curDepth = home.getDepth();
					int candidateDepth = candidate.getDepth();
					if (curDepth == candidateDepth) {
						home = home.parent;
					} else {
						home = curDepth > candidateDepth ? candidate : home;
					}
				}				
			}
			
			return home;
		}
	}
	
	
	int[] findHome(String[] folders, String[] users){
		Node root = new Node(null);
		root.index = 0;
		
		ArrayList<Integer> post = new ArrayList<Integer>();

		for (int i = 0; i < folders.length; i++) {
			String[] v = folders[i].split(" ");
			String[] u = v[1].split(",");
			int parentIndex = Integer.parseInt(v[0]);
			Node parent = root.findIndex(parentIndex);
			if (parent==null) {
				post.add(i);
				continue;

			}
			
			Node node = new Node(parent);
			node.index = i;
			for (String string : u) {
				node.users.add(string);	
			}
			
			parent.children.add(node);		
		}
		
		for (Integer integer : post) {
			String[] v = folders[integer].split(" ");
			String[] u = v[1].split(",");
			int parentIndex = Integer.parseInt(v[0]);
			Node parent = root.findIndex(parentIndex);
			if (parent == null) {
				System.out.println("oops");
			}

			Node node = new Node(parent);
			node.index = integer;
			for (String string : u) {
				node.users.add(string);
			}

			parent.children.add(node);
		}

		int[] r = new int[users.length];
		for (int i = 0; i < r.length; i++) {
			Node home =root.findHome(users[i]);
			if (home!=null) {
				r[i] =  home.index;	
			}else{
				r[i] = -1;
			}			
		}
		
		return r;
	}

}
