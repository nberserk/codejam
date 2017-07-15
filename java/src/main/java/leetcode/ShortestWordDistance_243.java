package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/12/17.
 *
 *
 *
 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

 */
public class ShortestWordDistance_243 {

    int shortestDistance(String[] words, String word1, String word2) {
        int iw1 = -1;
        int iw2 = -1;
        int r = Integer.MAX_VALUE;

        for (int i = 0; i <words.length; i++) {
            if(words[i].equals(word1)){
                if(iw2!=-1)
                   r = Math.min(r, i-iw2);
                iw1=i;
            }else if (words[i].equals(word2)){
                if(iw1!=-1)
                    r = Math.min(r, i-iw1);
                iw2 = i;
            }
        }
        return r;
    }

    @Test
    public void test(){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};

        assertEquals(3, shortestDistance(words, "coding", "practice"));
        assertEquals(1, shortestDistance(words, "makes", "coding"));
    }
}
