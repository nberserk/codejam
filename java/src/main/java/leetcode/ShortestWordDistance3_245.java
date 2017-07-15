package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *
 *
 *
 This is a follow-up problem of Shortest Word Distance. The only difference is now word1 could be the same as word2.

 Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

 word1 and word2 may be the same and they represent two individual words in the list.

 For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Given word1 = “makes”, word2 = “coding”, return 1.
 Given word1 = "makes", word2 = "makes", return 3.

 */
public class ShortestWordDistance3_245 {

    int shortestDistance(String[] words, String word1, String word2) {
        int iw1 = -1;
        int iw2 = -1;
        int r = Integer.MAX_VALUE;

        boolean same = word1.equals(word2);

        for (int i = 0; i <words.length; i++) {
            if (same){
                if(words[i].equals(word1)){
                    if(iw1!=-1)
                        r = Math.min(r, i-iw1);
                    iw1=i;
                }
            }else{
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
        }
        return r;
    }


    @Test
    public void test(){
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};

        assertEquals(3, shortestDistance(words, "makes", "makes"));

        assertEquals(3, shortestDistance(words, "coding", "practice"));
        assertEquals(1, shortestDistance(words, "makes", "coding"));

    }
}
