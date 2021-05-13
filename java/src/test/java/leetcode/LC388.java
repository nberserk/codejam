package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

import static org.junit.Assert.assertEquals;


public class LC388 {
    public int lengthLongestPath(String input) {
        int max=0;
        Stack<Integer> s = new Stack<>();
        s.push(0);
        for(String line : input.split("\n")){
            int i = line.lastIndexOf("\t");
            int depth = 0;
            if(i!=-1){
                depth = i+1;
                line = line.substring(i+1);
            }
            while(s.size()>depth+1)
                s.pop();

            //System.out.println(line);
            if(line.contains(".")){
                max=Math.max(max, s.peek()+s.size()-1+line.length());
            }else{
                s.push(s.peek()+line.length());
            }
        }

        return max;
    }

    public int lengthLongestPath_1th(String input) {

        String[] lines = input.split("\n");
        HashMap<Integer, String> map = new HashMap<>();
        int max=0;

        for (String line:lines){
            int depth=0;

            while(line.startsWith("\t")){
                depth++;
                line=line.substring(1);
            }
            if(line.contains(".")){
                StringBuilder sb = new StringBuilder();
                depth--;
                sb.append(line);
                while(depth>=0){
                    if(sb.length()>0) sb.append("/");
                    sb.append(map.get(depth));
                    depth--;
                }
                max=Math.max(max, sb.length());
            }else{
                map.put(depth, line);
            }
        }

        return max;
    }

    @Test
    public void test2(){
        String tep = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        for(char c : tep.toCharArray()){
            System.out.println(c);
        }
        assertEquals(20, lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));

    }
}
