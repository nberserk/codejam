package leetcode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LC2049 {
    class Node{
        Node left, right, parent;
        int countUnder;
        Node(){
            countUnder=0;
            left=right=parent=null;
        }
    }
    public int countHighestScoreNodes(int[] parents) {
        int N = parents.length;
        Node[] nodes = new Node[N];
        for(int i=0;i<N;i++){
            nodes[i] = new Node();
        }

        for(int i=0;i<N;i++){
            int p = parents[i];
            if(p==-1) continue;
            // cur -> parent
            nodes[i].parent = nodes[p];
            // parent -> cur
            if(nodes[p].left ==null)
                nodes[p].left = nodes[i];
            else
                nodes[p].right = nodes[i];
        }

        //
        int count = recordCount(nodes[0]);
        System.out.println("total count="+count);

        count=0;
        long curMax = -1;
        for(Node n: nodes){
            long parent = 1; //1, 4
            if(n.parent!=null){
                parent = nodes[0].countUnder-n.countUnder;
            }
            int left = 1;
            if(n.left!=null){
                left = n.left.countUnder; //3
            }
            int right = 1;
            if(n.right!=null){
                right = n.right.countUnder;//1
            }
            long cur=parent*left*right;//cur=3

            if(cur>curMax){
                curMax=cur;
                count=1;
            }else if(cur==curMax){
                count++;
            }
        }

        return count;
    }

    int recordCount(Node n){
        if(n==null) return 0;
        n.countUnder = 1 + recordCount(n.left) + recordCount(n.right);
        return n.countUnder;
    }

    @Test
    public void test(){
        assertEquals(3, countHighestScoreNodes(new int[]{-1,2,0,2,0}));
    }
}
