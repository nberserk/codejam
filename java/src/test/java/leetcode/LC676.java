package leetcode;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LC676 {

    static class Node{
        char ch;
        String word;
        Map<Character, Node> child = new HashMap<>();
        Node(){}
        Node(char c){
            ch=c;
        }

        void insert(String in){
            char[] c = in.toCharArray();
            Node node = this;
            for (char i: c){
                if (!node.child.containsKey(i))
                    node.child.put(i, new Node(i));
                node = node.child.get(i);
            }
            node.word=in;
        }

        boolean search(String target, int idx, boolean used){
            if(idx>target.length()) return false;

            if(idx==target.length()){
                if(word!=null && used) return true;
                return false;
            }

            char c = target.charAt(idx);
            for(char key: child.keySet()){
                if(key==c){
                    if (  child.get(key).search(target, idx+1, used))
                        return true;
                }else{
                    if(!used){
                        if( child.get(key).search(target, idx+1, true))
                            return true;
                    }
                }
            }

            return false;
        }
    }

    Node root;
    public LC676() {
        root = new Node();
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String s: dict){
            root.insert(s);
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return root.search(word, 0, false);
    }

    @org.junit.Test
    public void test(){
        LC676 dict = new LC676();
        dict.buildDict(new String[]{"hello", "leetcode"});

        assertEquals(false, dict.search("hello"));
        assertEquals(true, dict.search("hhllo"));
        assertEquals(false, dict.search("hell"));
        assertEquals(false, dict.search("leetcoded"));
    }

    @org.junit.Test
    public void test_overlap(){
        LC676 dict = new LC676();
        dict.buildDict(new String[]{"hello","hallo", "leetcode"});

        assertEquals(true, dict.search("hello"));
        assertEquals(true, dict.search("hhllo"));
    }
}
