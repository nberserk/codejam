package main.java.crackcode.graph;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 10/20/16.
 */
public class WordLadder2 {
    boolean canGo(String from, String to){
        int N = from.length();
        int diff=0;
        for (int i = 0; i < N; i++){
            if (from.charAt(i)==to.charAt(i)){
                continue;
            }
            diff++;
            if (diff>1){
                return false;
            }
        }
        return diff==1;
    }

    void find(String cur, String dest, Set<String> words, Set<String> used, LinkedList<String> path, List<List<String>> out){
        if(dest.equals(cur) ){
            if(out.size()>0 && out.get(0).size()> path.size()){
                out.clear();
            }
            if(out.size()==0 || out.get(0).size()==path.size())
                out.add(new LinkedList<String>(path));
            return;
        }

        for(String s:words){
            if(used.contains(s)) continue;
            if(!canGo(cur, s)) continue;
            used.add(s);
            path.addLast(s);
            find(s, dest, words, used, path, out);
            used.remove(s);
            path.removeLast();
        }
    }

    static class Node{
        String word;
        Node parent;
        int depth;

        Node(String word, Node p, int depth){
            this.word=word;
            parent=p;
            this.depth=depth;
        }
    }
    List<String> calcPath(Node cur){
        Stack<String> path = new Stack<>();
        Node temp = cur;
        while(temp!=null){
            path.push(temp.word);
            temp=temp.parent;
        }
        List<String> r = new ArrayList<>();
        while(path.size()>0){
            r.add(path.pop());
        }
        return r;
    }
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String w:wordList){
            List<String> list = new ArrayList<>();
            for(String o:wordList){
                if(w.equals(o)) continue;
                if(canGo(w,o)){
                    list.add(o);
                }
            }
            map.put(w, list);
        }
        List<List<String>> ret = new ArrayList<>();

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.push(new Node(beginWord, null, 0));
        HashSet<String> visited = new HashSet<>();
        HashSet<String> buffer = new HashSet<>();
        int shortestDepth=Integer.MAX_VALUE;
        int depth=0;
        while(queue.size()>0){
            Node cur = queue.removeFirst();
            if(visited.contains(cur.word))continue;
            buffer.add(cur.word);
            if( cur.depth>shortestDepth) continue;
            if(endWord.equals(cur.word)){
                if(ret.size()==0){
                    ret.add(calcPath(cur));
                    shortestDepth=cur.depth;
                }else if(cur.depth == shortestDepth ){
                    ret.add(calcPath(cur));
                }else if(shortestDepth>cur.depth){
                    ret.clear();
                    ret.add(calcPath(cur));
                    shortestDepth=cur.depth;
                }
                continue;
            }
            if(cur.depth>depth){ // we are finding min path. so comes visited path at later stage, can't be answer. so we can ignore that.
                visited.addAll(buffer);
                buffer.clear();
                depth= cur.depth;
            }
            List<String> linked = map.get(cur.word);
            for(String next:linked){
                if(visited.contains(next))continue;
                queue.addLast(new Node(next, cur, cur.depth + 1));
            }
        }
        //find(beginWord, endWord, wordList, new HashSet<String>(), path, ret);
        return ret;
    }

    @Test
    public void test(){
        String[] words = {"hot","cog","dog","tot","hog","hop","pot","dot"};
        HashSet<String> set = new HashSet<>();
        for(String w: words)
            set.add(w);
        assertEquals("[[hot, hog, dog], [hot, dot, dog]]", findLadders("hot", "dog", set).toString());
    }

}
