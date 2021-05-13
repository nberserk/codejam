package leetcode;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class LC715 {
    class Interval {
        int s,e;
        Interval(int s, int e){
            this.s=s;
            this.e=e;
        }

        @Override
        public String toString() {
            return String.format("%d,%d\n", s,e);
        }
    }

    TreeMap<Integer, Interval> tree = new TreeMap<>();

    public void addRange(int left, int right) {
        if(tree.get(left)!=null){
            right = Math.max(right, tree.get(left).e);
            tree.remove(left);
        }else{
            Map.Entry<Integer, Interval> low = tree.lowerEntry(left);
            if(low!=null && left<=low.getValue().e){
                tree.remove(low.getKey());
                left = Math.min(left,low.getValue().s);
                right = Math.max(right, low.getValue().e);
            }
        }

        Map.Entry<Integer, Interval> high = tree.higherEntry(left);
        while(high!=null){
            if(high.getValue().s > right) break;

            right = Math.max(right, high.getValue().e);
            tree.remove(high.getKey());
            high = tree.higherEntry(high.getKey());
        }

        tree.put(left, new Interval(left, right));
    }

    public boolean queryRange(int left, int right) {
        if(tree.get(left)!=null){
            if (right<=tree.get(left).e) return true;
            return false;
        }else{
            Map.Entry<Integer, Interval> low = tree.lowerEntry(left);
            if(low!=null && left>=low.getValue().s && right<=low.getValue().e){
                return true;
            }
        }

        return false;
    }

    void printTree(){
        System.out.println("----------");
        for(Integer a : tree.keySet()){
            System.out.println(tree.get(a).toString());
        }
    }



    public void removeRange(int left, int right) {
        if(tree.get(left)!=null){
            if(right<tree.get(left).e){
                tree.put(right, new Interval(right, tree.get(left).e));
            }
            tree.remove(left);
        }else{
            Map.Entry<Integer, Interval> low = tree.lowerEntry(left);
            if(low!=null){
                if(left<low.getValue().e && right<low.getValue().e){
                    tree.put(right, new Interval(right, low.getValue().e));
                    low.getValue().e = left;
                }else if(left<low.getValue().e){
                    low.getValue().e = left;
                }
            }
        }

        Map.Entry<Integer, Interval> high = tree.higherEntry(left);
        while(high!=null){
            if(high.getValue().s > right) break;
            tree.remove(high.getKey());

            if(right<high.getValue().e){
                tree.put(right, new Interval(right, high.getValue().e));
            }

            high = tree.higherEntry(high.getKey());
        }
    }


    @org.junit.Test
    public void test_treeset() {
        TreeSet<Interval> tree = new TreeSet<>((a,b)-> a.s==b.s? a.e-b.e:a.s-b.s);
        tree.add(new Interval(1,4));
        tree.add(new Interval(1,3));
        assertEquals(2, tree.size());

        tree.clear();
        tree.add(new Interval(2,3));
        Interval i4 =new Interval(4,5);
        tree.add(i4);
        assertEquals(i4 ,tree.floor(new Interval(5,7)));

        // strange
        TreeSet<int[]> tree2 = new TreeSet<>((a,b)->a[0]-b[0]);
        tree2.add(new int[]{1,2});
        tree2.add(new int[]{1,4});
        assertEquals(1, tree2.size());
    }

        @org.junit.Test
    public void test_addRange(){

        LC715 rm = new LC715();
        rm.addRange(1, 2);
        rm.addRange(2, 3);
        rm.addRange(4, 6);
        rm.addRange(5, 7);

        //rm.printTree();

        assertEquals(true, rm.queryRange(4, 7));

        rm.removeRange(4, 7);
        rm.printTree();
        assertEquals(false, rm.queryRange(4, 7));
        rm.addRange(4, 7);
        rm.removeRange(6,7);
        rm.printTree();



//        rm.addRange(10,20);
//        rm.removeRange(14, 16);
//        assertEquals(true, rm.queryRange(10, 14));
//        assertEquals(false, rm.queryRange(13, 15));
//        assertEquals(true, rm.queryRange(16, 17));
//        rm.addRange(14, 16);
//        assertEquals(true, rm.queryRange(14, 15));
    }

    @org.junit.Test
    public void test_(){
        LC715 rm = new LC715();

        rm.addRange(10,20);
        rm.removeRange(14, 16);
        assertEquals(true, rm.queryRange(10, 14));
        assertEquals(false, rm.queryRange(13, 15));
        assertEquals(true, rm.queryRange(16, 17));
        rm.addRange(14, 16);
        assertEquals(true, rm.queryRange(14, 15));
    }


    @org.junit.Test
    public void test2(){
        LC715 rm = new LC715();
        rm.addRange(6,8);
        rm.removeRange(7,8);
        rm.removeRange(8,9);
        rm.addRange(8,9);
        rm.removeRange(1,3);
        rm.printTree();
        rm.addRange(1,8);
        rm.printTree();
        assertEquals(true, rm.queryRange(2,4));
        assertEquals(true, rm.queryRange(2,9));
        assertEquals(true, rm.queryRange(4,6));
    }

    @org.junit.Test
    public void test3(){
        LC715 rm = new LC715();
        rm.removeRange(4, 8);
        rm.addRange(1,10);
        assertEquals(true, rm.queryRange(1,7));
        rm.addRange(2,3);
        rm.removeRange(2,3);
        assertEquals(true, rm.queryRange(8,9));
    }

    //["RangeModule","removeRange","addRange","queryRange","addRange","removeRange","queryRange","queryRange","addRange","removeRange"]
      //      [[],[4,8],             [1,10],    [1,7],       [2,3],   [2,3],          [8,9],[6,9],[2,3],[1,8]]



//    ["RangeModule","addRange","removeRange","removeRange","addRange","removeRange","addRange","queryRange","queryRange","queryRange"]
//            [[],[6,8],[7,8],[8,9],[8,9],[1,3],[1,8],[2,4],[2,9],[4,6]]

}
