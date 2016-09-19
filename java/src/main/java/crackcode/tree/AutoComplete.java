package main.java.crackcode.tree;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by darren on 9/19/16.
 *
 * Let's say you have List<String> history. implement auto complete function when given history, and input string.
 *
 */
public class AutoComplete extends TestCase{
    static class Node{
        String val;
        boolean isLeaf;
        HashMap<Character, Node> child = new HashMap<>();

        public Node(){
        }

        public Node(char c){
            val = String.valueOf(c);
        }

        void add(char c){
            child.put(c, new Node(c));
        }

        void insert(String word){

            char[] in = word.toCharArray();
            Node node = this;
            for (int i = 0; i < in.length; i++) {
                if(!node.child.containsKey(in[i]))
                    node.add(in[i]);
                node = node.child.get(in[i]);
            }
            node.isLeaf=true;
        }

        public List<String> getCandidate(String input){
            List<String> ret = new ArrayList<>();
            char[] c = input.toCharArray();
            Node node = this;
            for (int i = 0; i < c.length; i++) {
                if(!node.child.containsKey(c[i]))
                    return ret;
                node = node.child.get(c[i]);
            }

            node.collectCandidate(ret, input);
            return ret;
        }

        private void collectCandidate(List<String> out, String cur) {
            if(isLeaf)
                out.add(cur);
            for(Node n: child.values()){
                n.collectCandidate(out, cur+n.val);
            }
        }

    }

    public void testAutoComplete(){
        String[] history = {"amazon", "ama", "apple", "google", "facebook"};
        Node root = new Node();
        for (int i = 0; i < history.length; i++) {
            root.insert(history[i]);
        }

        List<String> r = root.getCandidate("a");
        assertEquals(3, r.size());
        assertEquals(history[2], r.get(0));
        assertEquals(history[1], r.get(1));
        assertEquals(history[0], r.get(2));

        r = root.getCandidate("ama");
        assertEquals(2, r.size());
        assertTrue(r.contains(history[0]));
        assertTrue(r.contains(history[1]));


        r = root.getCandidate("b");
        assertEquals(0, r.size());



    }
}
