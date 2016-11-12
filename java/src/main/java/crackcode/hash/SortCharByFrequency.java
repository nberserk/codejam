package main.java.crackcode.hash;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/11/16.
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/
 *
 * Given a string, sort it in decreasing order based on the frequency of characters.

 Example 1:

 Input:
 "tree"

 Output:
 "eert"

 Explanation:
 'e' appears twice while 'r' and 't' both appear once.
 So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.


 */
public class SortCharByFrequency {

    // if you do sort or priorityqueue it will be O(NlogN).
    // but below algorithm is O(N)
    public String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()){
            if(map.containsKey(c))
                map.put(c, 1+map.get(c));
            else map.put(c, 1);
        }

        int maxFrequency = 1;
        for(int f: map.values()){
            maxFrequency = Math.max(maxFrequency, f);
        }

        String[] ch = new String[maxFrequency+1];
        Arrays.fill(ch, "");

        for(char k: map.keySet() ){
            int f = map.get(k);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<f;i++)
                sb.append(k);
            ch[f] += sb.toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = maxFrequency; i >0; i--){
            sb.append(ch[i]);
        }
        return sb.toString();
    }


    @Test
    public void test(){
        assertEquals("eert", frequencySort("tree"));
        assertEquals("bbAa", frequencySort("Aabb"));
        assertEquals("aaaccc", frequencySort("cccaaa"));
    }
}
