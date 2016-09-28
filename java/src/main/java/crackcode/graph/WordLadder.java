package crackcode.graph;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 2016. 9. 28..
 *
 * https://leetcode.com/problems/word-ladder/
 * bidirectional bfs,
 */
public class WordLadder {
    int ladder(Set<String> from, Set<String> to, Set<String> dict, int depth){
        if (from.size()==0 || to.size()==0){
            return 0;
        }

        HashSet<String> set = new HashSet<>();
        for (String s: from){

            int len = s.length();

            for (int i = 0; i < len; i++){
                for (char j = 'a'; j <= 'z'; j++){
                    char[] c = s.toCharArray();
                    c[i]=j;
                    String temp = String.valueOf(c);
                    if (to.contains(temp)){
                        return depth+1;
                    }else if (dict.contains(temp)){
                        dict.remove(temp);
                        set.add(temp);
                    }

                }
            }
        }

        if (set.size() < to.size()){
            return ladder(set, to, dict, depth+1);
        }else
            return ladder(to, set, dict, depth+1);
    }

    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {


        Set<String> from = new HashSet<>();
        from.add(beginWord);
        Set<String> to = new HashSet<>();
        to.add(endWord);

        wordList.remove(beginWord);
        wordList.remove(endWord);
        return ladder(from, to , wordList, 1);
    }


    @Test
    public void test(){
        String[] words = {"hot","dot","dog","lot","log"};
        HashSet<String> set = new HashSet<>();
        for (String s: words)
            set.add(s);
        assertEquals(5, ladderLength("hit", "cog", set));
    }



}
