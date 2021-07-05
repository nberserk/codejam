package leetcode;

import javafx.util.Pair;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2/11/17.
 *
 * https://leetcode.com/problems/longest-valid-parentheses/?tab=Description
 * tags: stack, impl
 */
public class LC269 {

    Pair<Character,Character> order(String first, String second){
        int len = Math.min(first.length(), second.length());
        for(int i=0;i<len;i++){
            char charFirst = first.charAt(i);
            char charSecond = second.charAt(i);
            if(charFirst==charSecond) continue;
            return new Pair<>(charFirst, charSecond);
        }
        return null;
    }

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character,Integer> incomingEdge = new HashMap<>();

        for(String w: words){
            for(char c: w.toCharArray()){
                incomingEdge.put(c,0);
            }
        }

        for(int i=0;i<words.length-1;i++){
            Pair<Character,Character> singleOrder = order(words[i], words[i+1]);
            if(singleOrder==null) continue;
            Character from = singleOrder.getKey();
            Character to = singleOrder.getValue();

            graph.putIfAbsent(from, new HashSet<>());
            if(!graph.get(from).contains(to)){
                graph.get(from).add(to);
                incomingEdge.put(to, incomingEdge.get(to)+1);

            }

        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList();
        for(Character ch: incomingEdge.keySet()){
            if(incomingEdge.get(ch)==0)
                queue.add(ch);
        }

        while(queue.size()>0){
            // find
            Character key = queue.poll();
            sb.append(key);
            //incomingEdge.remove(key);
            //System.out.println(key);

            if(graph.containsKey(key)){
                for(Character next: graph.get(key)){
                    incomingEdge.put(next, incomingEdge.get(next)-1);
                    if(incomingEdge.get(next)==0){
                        queue.add(next);
                    }
                }
            }
        }

        for (Character key: incomingEdge.keySet()){
            if (incomingEdge.get(key)>0)
                return "";
        }
        return sb.toString();
    }

    @Test
    public void test(){
        assertEquals("acbz", alienOrder(new String[]{"ac","ab","zc", "zb"}));
    }

    @Test
    public void testNoLink(){
        assertEquals("z", alienOrder(new String[]{"z","z"}));
    }


}
