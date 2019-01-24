package leetcode;

import org.junit.Test;

import java.util.*;

/**
 *
 */
public class LC952 {
    ArrayList<Integer> reduce(int n){
        HashSet<Integer> set = new HashSet<>();
        int d=2;
        while(n>1){
            if(n%d==0){
                set.add(d);
                n/=d;
            }else d++;
        }
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(set);
        return list;
    }

    int getParent(int[] p, int i){
        int org =i;
        while(p[i]!=i){
            i=p[i];
        }
        p[org]=i;
        return i;
    }

    final int N = 100001;
    int[] parent = new int[N];
    int[] size=new int[N];

    public int largestComponentSize(int[] A) {
        ArrayList<Integer> prime = new ArrayList<>();
        for (int i = 2; i < N; i++) {
            boolean isPrime=true;
            for (int j = 0; j < prime.size(); j++) {
                if (i%prime.get(j)==0){
                    isPrime=false;
                    break;
                }
            }
            parent[i]=i;
            if(isPrime)
                prime.add(i);
        }
        Arrays.fill(size,0);

        int largestComponent=0;
        Stack<Integer> stack = new Stack<>();
        ArrayList<Integer> divider = new ArrayList<>();
        for(int a: A){
            if(a==1){
                size[1]++;
                continue;
            }

            //stack.clear();
            divider.clear();
            int d=prime.get(0);
            int di=0;
            while(a>1){
                if (parent[a]!=a){
                    divider.add(a);
                    break;
                }
                if(a%d==0){
                    stack.push(a);
                    a/=d;
                    if(divider.size()==0 || divider.get(divider.size()-1)!=d)
                        divider.add(d);
                }else {
                    di++;
                    d=prime.get(di);
                }
            }

            //
            assert divider.size()>0;

            int root=getParent(parent, divider.get(0));
            size[root]++;
            // merge dividers

            for (int i = 1; i < divider.size(); i++) {
                int p = getParent(parent,divider.get(i));
                if(root==p) continue;
                size[root]+=size[p];
                size[p]=0;
                parent[p]=root;
            }
            //System.out.println(String.format("root=%d,size=%d,driver=%s", root,size[root], divider.toString()));
            // update cache
            if(stack.size()>0){
                stack.pop();
                while(stack.size()>0){
                    int cur =stack.pop();
                    parent[cur]=root;
                }
            }
        }

        for (int i = 1; i < 100001; i++) {
            largestComponent=Math.max(largestComponent, size[i]);
        }
        return largestComponent;
    }

    static class Node{
        List<Integer> pos;
        int distance;
        public Node(List<Integer> p, int distance){
            this.pos=p;
            this.distance=distance;
        }
    }
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<List<Integer>> nearestVegetarianRestaurant(int totalRestaurants,
                                                    List<List<Integer>> allLocations,
                                                    int numRestaurants)
    {
        // WRITE YOUR CODE HERE
        PriorityQueue<Node> pq = new PriorityQueue<Node>(totalRestaurants, (a, b) -> a.distance-b.distance);
        for(List<Integer> location: allLocations){
            int x = location.get(0);
            int y = location.get(1);
            int distance = x*x+y*y;
            Node n = new Node(location, distance);
            pq.add(n);
        }
        List<List<Integer>> result = new ArrayList<>();
        while(numRestaurants>0){
            Node cur = pq.poll();
            result.add(cur.pos);
            numRestaurants--;
        }
        return result;
    }

    @Test
    public void test(){
        List<List<Integer>> in = new ArrayList<>();
        List<Integer> v1 = new ArrayList<>();
        v1.add(1);
        v1.add(-3);
        in.add(v1);

        nearestVegetarianRestaurant(3, in, 1);

//        assertEquals(14, largestComponentSize(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19}));
//        assertEquals(16, largestComponentSize(new int[]{84,171,548,709,455,967,328,779,79,16,168,675,276,473,697,26,731,668,634,607}));
//        assertEquals(8, largestComponentSize(new int[]{2,3,6,7,4,12,21,39}));
//        assertEquals(6, largestComponentSize(new int[]{100,68,70,79,80,20,25,27}));
//        assertEquals(2, largestComponentSize(new int[]{4,6}));
//        assertEquals(4, largestComponentSize(new int[]{4,6,15,35}));
//
//        assertEquals(2, largestComponentSize(new int[]{20,50,9,63}));



    }
}
