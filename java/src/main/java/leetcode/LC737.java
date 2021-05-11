package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class SentenceSimilarityII_737 {

    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        int N1 = words1.length;
        int N2 = words2.length;
        if(N1!=N2 ) return false;
        HashMap<String, Set<String>> connected = new HashMap<String, Set<String>>();

        for (String[] p: pairs){
            connected.putIfAbsent(p[0], new HashSet<>());
            connected.putIfAbsent(p[1], new HashSet<>());
            connected.get(p[0]).add(p[1]);
            connected.get(p[1]).add(p[0]);
        }

        for (int i = 0; i < N1; i++) {
            String from = words1[i];
            String to = words2[i];
            if(!canTraverse(connected, from, to)) return false;
        }

        return true;
    }

    private boolean canTraverse(HashMap<String, Set<String>> connected, String from, String to) {
        if(from.equals(to)) return true;
        HashSet<String> visited = new HashSet<>();
        Stack<String> stack = new Stack<>();
        stack.add(from);
        while(stack.size()>0){
            String cur = stack.pop();
            if(visited.contains(cur)) continue;
            if(cur.equals(to)) return true;
            visited.add(cur);
            for (String next: connected.get(cur)){
                stack.add(next);
            }
        }
        return false;
    }

    @org.junit.Test
    public void test(){
        // transitive
        assertEquals(true, areSentencesSimilarTwo(new String[]{"a", "b"}, new String[]{"g", "d"}, new String[][]{{"a","c"},{"c","g"},{"b", "d"}}));
        assertEquals(true, areSentencesSimilarTwo(new String[]{"a", "b"}, new String[]{"c", "d"}, new String[][]{{"a","c"},{"b", "d"}}));


    }
}
