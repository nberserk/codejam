package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class WordFilter {

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

        void insert(String word, String org){

            char[] in = word.toCharArray();
            Node node = this;
            for (int i = 0; i < in.length; i++) {
                if(!node.child.containsKey(in[i]))
                    node.add(in[i]);
                node = node.child.get(in[i]);
            }
            if (org==null)
                node.word = word;
            else
                node.word=org;
        }

        public Set<String> getCandidate(String input){
            Set<String> ret = new HashSet<>();
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

        private void collectCandidate(Set<String> out) {
            if(word!=null)
                out.add(word);
            for(Node n: child.values()){
                n.collectCandidate(out);
            }
        }
    }

    Node root = new Node();
    Node reverse = new Node();
    HashMap<String, Integer> weight = new HashMap<>();



    public void setWords(String[] words) {
        for (int i = 0; i < words.length; i++) {
            root.insert(words[i], null);
            reverse.insert(reverse(words[i]), words[i]);
            weight.put(words[i], i);
        }
    }

    String reverse(String s){
        StringBuilder sb = new StringBuilder(s);
        String ret = sb.reverse().toString();
        return ret;

    }

    public int f(String prefix, String suffix) {
        Set<String> pre = root.getCandidate(prefix);
        Set<String> suf = reverse.getCandidate(reverse(suffix));
        int ret=-1;
        for (String s: pre){
            if (suf.contains(s)){
                int cur = weight.get(s);
                ret=Math.max(ret, cur);
            }
        }

        return ret;
    }


    @org.junit.Test
    public void test(){
        WordFilter wf = new WordFilter();
        wf.setWords(new String[]{"apple", "appkk", "apktou"});

        assertEquals(2, wf.f("ap", "ou"));
//        assertEquals(false, isOneBitCharacter(new int[]{1,1,1,0}));
    }
}
