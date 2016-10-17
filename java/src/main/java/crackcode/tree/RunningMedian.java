package crackcode.tree;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Created by darren on 2016. 10. 17..
 *
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class RunningMedian {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, Collections.reverseOrder());

    // Adds a number into the data structure.
    public void addNum(int num) {

        int min = minHeap.size();
        int max = maxHeap.size();

        if(max==0) {
            maxHeap.add(num);
            return;
        }

        if(num<=maxHeap.peek()){
            maxHeap.add(num);
            if(maxHeap.size()> minHeap.size()+1){
                minHeap.add(maxHeap.poll());
            }
        }else {
            minHeap.add(num);
            if (minHeap.size() > maxHeap.size())
                maxHeap.add(minHeap.poll());
        }
    }

    // Returns the median of current data stream
    public double findMedian() {
        int size = minHeap.size() + maxHeap.size();
        if(size==0) return 0.0;

        if(size%2==0)
            return (minHeap.peek()+maxHeap.peek())/2.0;
        else
            return maxHeap.peek();
    }

}
