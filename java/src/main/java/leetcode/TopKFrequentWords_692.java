package leetcode;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * lessons leared:
 * - Map.Entry as a structure  2017.10.22
 */
public class TopKFrequentWords_692 {

    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> f = new HashMap<>();
        for(String s: words){
            if(f.containsKey(s))
                f.put(s, f.get(s)+1);
            else f.put(s,1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a,b)-> a.getValue()==b.getValue() ? -a.getKey().compareTo(b.getKey()):a.getValue()-b.getValue());

        for(Map.Entry<String, Integer> e: f.entrySet()){
            pq.offer(e);
            if(pq.size()>k) pq.poll();
        }

        LinkedList<String> ret = new LinkedList<>();
        while(pq.size()>0){
            ret.addFirst(pq.poll().getKey());
        }
        return ret;
    }


    @org.junit.Test
    public void test(){
        assertEquals(Arrays.asList("i", "love"), topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        assertEquals(Arrays.asList("the", "is", "sunny", "day"), topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));

    }
}
