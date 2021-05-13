package leetcode;

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

public class LC841 {

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size();
        Stack<Integer> s = new Stack<>();
        s.add(0);
        HashSet<Integer> visited = new HashSet<>();

        while(s.size()>0){
            int c = s.pop();
            if(visited.contains(c)) continue;
            visited.add(c);
            for(int next : rooms.get(c)){
                s.push(next);
            }
        }

        if(visited.size()==N) return true;
        return false;
    }
}
