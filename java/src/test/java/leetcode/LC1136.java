package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 *
 */
public class LC1136 {
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] incomingEdge = new int[n+1];
        for(int[] relation: relations){
            int preCourse = relation[0];
            int nextCourse = relation[1];
            graph.putIfAbsent(preCourse, new HashSet<>());
            graph.get(preCourse).add(nextCourse);
            incomingEdge[nextCourse]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i=1;i<n+1;i++){
            if(incomingEdge[i] == 0 )
                queue.add(i);
        }

        int courseCount = 0;
        int semester = 0;
        while(queue.size()>0){
            Queue<Integer> nextQueue = new LinkedList<>();
            for(int course : queue){
                courseCount++;
                if(graph.get(course)!=null){
                    for(int next: graph.get(course))    {
                        incomingEdge[next]--;
                        if(incomingEdge[next]==0){
                            nextQueue.add(next);
                        }
                    }
                }
            }
            semester++;
            queue=nextQueue;
        }

        return courseCount==n? semester:-1;


    }


}
