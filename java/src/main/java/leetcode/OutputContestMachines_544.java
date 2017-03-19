package main.java.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 3/19/17.
 */
public class OutputContestMachines_544 {

    String find(List<String> a){
        int N = a.size();
        if(N==0) return "";
        if(N<=2){
            return "("+a.get(0)+"," + a.get(1)+")";
        }

        List<String> f = new ArrayList<>();
        //List<String> s = new ArrayList<>();
        int end = N-1;
        for(int i=0;i<N/2;i++){
            f.add( String.format("(%s,%s)",a.get(i), a.get(end--) ) );
        }
        return find(f);

    }
    public String findContestMatch(int n) {
        ArrayList<String> f = new ArrayList<>();

        for(int i=1;i<=n;i++){
            f.add(String.valueOf(i));
        }
        return find(f);
    }

    @Test
    public void test(){

        assertEquals("(((1,8),(4,5)),((2,7),(3,6)))", findContestMatch(8));
        assertEquals("((1,4),(2,3))", findContestMatch(4));
        assertEquals("(1,2)", findContestMatch(2));


    }
}
