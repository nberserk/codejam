package main.java.codejam.lib;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * from https://leetcode.com/problems/palindrome-partitioning/
 */
public class PalindromePartition {

    public boolean isPalindrome(String str){
        int s=0;
        int e=str.length()-1;
        while(s<e){
            if(str.charAt(s) != str.charAt(e))
                return false;
            s++;
            e--;
        }
        return true;
    }
    public void partition2(ArrayList<LinkedList<String>> agg, LinkedList<String> cur, String s, int start){
        if(start==s.length()){
            agg.add(new LinkedList(cur));
            return;
        }

        for(int i=start+1;i<=s.length();i++){
            String sub = s.substring(start, i);
            if (!isPalindrome(sub)) continue;

            cur.addLast(sub);
            partition2(agg, cur, s, i);
            cur.removeLast();
        }

    }
    public ArrayList<LinkedList<String>> partition(String s) {

        ArrayList<LinkedList<String>> ret = new ArrayList<LinkedList<String>>();
        partition2(ret, new LinkedList<String>(), s, 0);

        return ret;
    }

    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        ArrayList<LinkedList<String>> r = pp.partition("aab");
        r = pp.partition("atta");
        System.out.println(r);

    }
}
