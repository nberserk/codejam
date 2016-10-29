package main.java.crackcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by darren on 10/29/16.
 */
public class WordSquares {
    static class Node{
        char ch;
        String word;
        HashMap<Character, Node> child = new HashMap<>();

        public Node(){
        }

        public Node(char c){
            ch = c;
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
            node.word = word;
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

            node.collectCandidate(ret);
            return ret;
        }

        private void collectCandidate(List<String> out) {
            if(word!=null)
                out.add(word);
            for(Node n: child.values()){
                n.collectCandidate(out);
            }
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        Node root = new Node();
        for(String s: words){
            root.insert(s);
        }
        List<List<String>> ret = new ArrayList<>();
        return ret;
    }
}
