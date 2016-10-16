package main.java.crackcode.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/16/16.
 *
 * https://leetcode.com/problems/word-search-ii/
 *
 * idea :
 *
 * warning : when handling visited. you have to reset visited state if recursive function calls ends.
 */
public class WordSearch2 {
    static class Node{
        char ch;
        String word;
        HashMap<Character, Node> child = new HashMap<>();
        Node(char c){
            ch=c;
        }
        Node(){
        }

        void insert(String in){
            Node cur = this;
            for(char ch: in.toCharArray() ){
                if(!cur.child.containsKey(ch) )
                    cur.child.put(ch,new Node(ch) );
                cur = cur.child.get(ch);
            }
            cur.word=in;
        }

        Node find(char c){
            return child.get(c);
        }
        public String toString(){
            return ch+"{"+child.toString()+"}";
        }
    }

    boolean find(Node prev, int x, int y, List<String> out, char[][] board){
        if(prev==null) return false;
        char ch = board[y][x];
        Node node = prev.find(ch);
        if(node==null) return false;
        //cur = cur + ch;

        board[y][x]='/';
        if(node.word!=null) {
            out.add(node.word);
        }

        int[][] d = {{1,0},{-1,0},{0,1},{0,-1} };
        int row = board.length;
        int col = board[0].length;
        boolean ret = false;
        for(int i=0;i<4;i++){
            int nx = x + d[i][0];
            int ny = y + d[i][1];
            if(nx<0 || ny<0 || nx>=col || ny>= row) continue;
            find(node, nx, ny, out, board);
            //if(ret) break;
        }
        board[y][x] = ch;  // this one is important
        return ret;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<>();
        int row = board.length;
        if(row==0) return ret;
        int col = board[0].length;

        Node trie = new Node();
        for(String w: words){
            trie.insert(w);
        }

        //System.out.println(trie.child.get('o') );
        HashSet<String> temp = new HashSet<>();
        for(int y=0;y<row;y++){
            for(int x=0;x<col;x++){
                List<String> list = new ArrayList<>();
                find(trie, x, y, list, board );
                if(list.size()>0 ){
                    for(String s: list){
                        temp.add(s);
                    }
                }
            }
        }
        for(String s: temp){
            ret.add(s);
        }
        return ret;
    }

    @Test
    public void test(){
        if((7&0b111)==0b111){
            System.out.println("oops");
        }

        char[][] board = {"abc".toCharArray(),"aed".toCharArray(),"afg".toCharArray()};
        String[] words = {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};

        assertEquals("[befa, abcdefg, eaabcdgfa, gfedcbaaa]", findWords(board, words).toString());

        char[][] b2 = {"oaan".toCharArray(),"etae".toCharArray(),"ihkr".toCharArray(),"iflv".toCharArray()};
        String[] w2 = {"oath","pea","eat","rain"};
        assertEquals("[oath, eat]", findWords(b2, w2).toString());

    }
}
