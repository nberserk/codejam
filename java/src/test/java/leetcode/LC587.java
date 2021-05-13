package leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LC587 {

    class Point {
        int x,y;
        Point(int a, int b) {
            x = a;            y = b;
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    // 0 : linear
    // 1 : b clockwise
    // -1 : counterclockwise
    int cross(Point a, Point b){
        return a.x*b.y-a.y*b.x;
    }

    int ccw(Point p, Point a, Point b){
        Point pa = new Point(a.x-p.x, a.y-p.y);
        Point pb = new Point(b.x-p.x, b.y-p.y);

        return cross(pa,pb);
    }

    public boolean inBetween(Point p, Point i, Point q) {
        boolean a = i.x >= p.x && i.x <= q.x || i.x <= p.x && i.x >= q.x;
        boolean b = i.y >= p.y && i.y <= q.y || i.y <= p.y && i.y >= q.y;
        return a && b;
    }

    public List<Point> outerTrees(Point[] points) {
        List<Point> ret = new ArrayList<>();

        int N = points.length;
        if (N < 4){
            for (Point p: points){
                ret.add(p);
            }
            return ret;
        }
        int xmin = 0;
        for (int i=0;i<N;i++){
            if(points[i].x < points[xmin].x)
                xmin =i;
        }

        int start = xmin;
        int p = start;

        while(true){
            ret.add(points[p]);
            int q = (p+1)%N;

            for (int i = 0; i < N; i++) {
                if(ccw(points[p],points[q],points[i])<0)
                    q=i;
            }

            // duplicate check

            for (int i = 0; i < N; i++) {
                if(i==p || i==q) continue;
                if(ccw(points[p], points[q], points[i])==0 && inBetween(points[p], points[i], points[q])){
                    ret.add(points[i]);
                }
            }

            p=q;
            if(start==p) break;
        }

        return ret;
    }

    @org.junit.Test
    public void test(){
        Point[] points = new Point[7];
        points[0] = new Point(0,3);
        points[1]= new Point(2,2);
        points[2]= new Point(1,1);
        points[3]= new Point(2,1);
        points[4]= new Point(3,0);
        points[5]= new Point(0,0);
        points[6]= new Point(3,3);


        assertEquals("[(0,3), (0,0), (3,0), (3,3)]", outerTrees(points).toString());
    }
}
