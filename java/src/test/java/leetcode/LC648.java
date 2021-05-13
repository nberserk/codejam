package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;


public class LC648 {

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

        public String getCandidate(String input){
            LinkedList<String> ret = new LinkedList<>();
            char[] c = input.toCharArray();
            Node node = this;
            for (int i = 0; i < c.length; i++) {
                if(!node.child.containsKey(c[i]))
                    return null;
                node = node.child.get(c[i]);
                if(node.word!=null) return node.word;
            }
            return null;
        }
    }



    public String replaceWords(List<String> dict, String sentence) {
        Node root = new Node();
        for (String s: dict){
            root.insert(s);
        }

        String[] words = sentence.split(" ");
        StringBuilder ret = new StringBuilder();
        for (String word:words){
            String candi = root.getCandidate(word);
            if(ret.length()>0) ret.append(" ");
            if(candi==null)
                ret.append(word);
            else
                ret.append(candi);
        }

        return ret.toString();
    }



    @Test
    public void test(){
        assertEquals("the cat was rat by the bat", replaceWords(Arrays.asList("cat","bat","rat"), "the cattle was rattled by the battery"));
    }
}
