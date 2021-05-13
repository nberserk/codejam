package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC833 {
    public String findReplaceString(String S, int[] indexes, String[] sources, String[] targets) {
        List<Integer> matched = new ArrayList<>();
        for(int i=0;i<indexes.length;i++){
            int offset = indexes[i];
            boolean match=true;
            for(int j=0;j<sources[i].length();j++){
                if(S.charAt(offset+j)!=sources[i].charAt(j)){
                    match=false;
                    break;
                }
            }
            if(match) matched.add(i);
        }
        Collections.sort(matched, (a, b) -> indexes[a]-indexes[b]);
        int delta=0;
        StringBuilder sb = new StringBuilder(S);
        for(int i=0;i<matched.size();i++){
            int idx = matched.get(i);
            int start = indexes[idx]+delta;
            int end = start+ sources[idx].length();
            sb.replace(start,end, targets[idx] );
            delta += -sources[idx].length()+targets[idx].length();
        }
        return sb.toString();
    }

    @Test
    public void test(){
        assertEquals("aaqbqaqq", findReplaceString("vyeqmeyggv", new int[]{4,0,7},
                new String[]{"mey","vye","ggv"}
                , new String[]{"bq","aa","aqq"}));

    }
}
