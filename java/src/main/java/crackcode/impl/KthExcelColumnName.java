package main.java.crackcode.impl;

import junit.framework.TestCase;

import java.util.Stack;

/**
 * Created by darren on 9/18/16.
 *
 * from: http://www.geeksforgeeks.org/find-excel-column-name-given-number/
 *
 */
public class KthExcelColumnName extends TestCase {

    String columnName(int k){
        Stack<Character> stack =new Stack<>();
        while(k>0){
            int mod = (k-1)%26;
            char c = (char) ('A' + mod);
            stack.push(c);
            k = (k-1)/26;
        }

        StringBuilder sb = new StringBuilder();
        while(stack.size()>0){
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    public void testColumnName(){
        assertEquals("A", columnName(1));
        assertEquals("Z", columnName(26));
        assertEquals("AA", columnName(27));
        assertEquals("AAC", columnName(705));
    }
}
