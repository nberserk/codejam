package main.java.crackcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/29/16.
 */
public class WordSquares {
    static class Node{
        char ch;
        String word;
        boolean used;
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

        void used(String word, boolean b){
            char[] in = word.toCharArray();
            Node node = this;
            for (int i = 0; i < in.length; i++) {
                if(!node.child.containsKey(in[i]))
                    return;
                node = node.child.get(in[i]);
            }
            node.used = b;
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
            if(word!=null&&!used)
                out.add(word);
            for(Node n: child.values()){
                n.collectCandidate(out);
            }
        }
    }

    boolean isValid(List<String> cur){
        int row = cur.size();
        if(row==0) return true;
        int col = cur.get(0).length();
        for(int y=0;y<row;y++){
            for(int x=0;x<col;x++){
                if(x==y) break;
                if(cur.get(y).charAt(x)!= cur.get(x).charAt(y)) return false;
            }
        }
        return true;
    }

    void find(Node trie, List<List<String>> ret,LinkedList<String> cur, ArrayList<String> remain, int N){
        if(cur.size()==N){
            ret.add(new LinkedList(cur));
            return;
        }

        //System.out.println(cur.toString());
        if(cur.size()==0){
            for(int i=remain.size()-1;i>=0;i--){
                String s = remain.get(i);
                cur.add(s);
                //remain.remove(i);
                //trie.used(s, true);
                find(trie, ret, cur, remain, N);
                //trie.used(s, false);
                //remain.add(s);
                cur.removeLast();
            }
        }else{
            String searchword = "";
            for(String t : cur){
                searchword += t.charAt(cur.size());
                if(searchword.length()>=cur.size() -1) break;
            }
            List<String> candi = trie.getCandidate(searchword);
            for(String s: candi){
                cur.add(s);
                //trie.used(s, true);
                find(trie, ret, cur, remain, N);
                //trie.used(s, false);
                cur.removeLast();
            }
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ret = new ArrayList<>();
        int N = words.length;
        if(N==0) return ret;

        Node root = new Node();
        ArrayList<String> remain = new ArrayList<>();
        for(String s: words){
            root.insert(s);
            remain.add(s);
        }

        LinkedList<String> cur = new LinkedList<>();
        int n = words[0].length();
        find(root, ret, cur, remain, n);
        return ret;
    }

    @Test
    public void test(){
        String[] s = {"abat","baba","atan","atal"};

        assertEquals("[[baba, abat, baba, atal], [baba, abat, baba, atan]]", wordSquares(s).toString() );
    }
}
