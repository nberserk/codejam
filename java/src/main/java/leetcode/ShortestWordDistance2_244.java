package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/12/17.
 *
 *
 *
 * This is a follow up of Shortest Word Distance. The only difference is now you are given
 * the list of words and your method will be called repeatedly many times with different parameters.
 * How would you optimize it?

 Design a class which receives a list of words in the constructor,
 and implements a method that takes two words word1 and word2 and
 return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

 */
public class ShortestWordDistance2_244 {
    HashMap<String, List<Integer>> map = new HashMap<>();
    ShortestWordDistance2_244(){}

    public ShortestWordDistance2_244(String[] words) {
        for (int i=0;i<words.length;i++){
            String s = words[i];
            if (map.containsKey(s))
                map.get(s).add(i);
            else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(s, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        int min = Integer.MAX_VALUE;

        List<Integer> l = map.get(word1);
        List<Integer> l2 = map.get(word2);

        int i=0;
        int j=0;
        while(i<l.size() && j<l2.size()){
            int first = l.get(i);
            int second = l2.get(j);
            min = Math.min(min, Math.abs(first-second));
            if(first<second){
                i++;
            }else j++;
        }

        return min;
    }


    @Test
    public void test(){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        ShortestWordDistance2_244 dist = new ShortestWordDistance2_244(words);

        assertEquals(3, dist.shortest("coding", "practice"));
        assertEquals(1, dist.shortest("makes", "coding"));

    }
}
