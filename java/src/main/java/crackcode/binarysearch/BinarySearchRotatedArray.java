package codejam.lib.binarysearch;

import junit.framework.TestCase;

import java.util.LinkedList;

/**
 * Created by darren on 2016. 2. 1..
 */
public class BinarySearchRotatedArray extends TestCase {

    static int bsearchRotated(int[] a, int key){
        int lo = 0;
        int hi = a.length-1;
        while(lo<=hi){
            int m = (lo+hi)/2;
            //System.out.println( String.format("lo=%d,hi=%d,mid=%d", lo,hi,m) );
            if(a[m]>key){ // alo am  ahi
                if (a[lo]<=a[m]) { // alo ~ am is normal
                    if (a[lo]<=key)
                        hi = m - 1;
                    else
                        lo = m+1;
                }else { // am ~ ahi is normal
                    hi=m-1;
                }
            }else if (a[m]<key){ //  alo key am
                if(a[lo]<=a[m])
                    lo=m+1;
                else{
                    if(a[lo]>=key)
                        hi=m-1;
                    else lo=m+1;
                }
            }else return m;

        }

        return -1;
    }

    static int bsearchRotatedSimpler(int[] a, int key){
        int lo = 0;
        int hi = a.length-1;
        while(lo<=hi){
            int m = (lo+hi)/2;
            //System.out.println( String.format("lo=%d,hi=%d,mid=%d", lo,hi,m) );
            if(key==a[m]) return m;

            if(a[lo]<=a[m]){
                if(a[lo] <= key && key <= a[m])
                    hi=m-1;
                else lo=m+1;
            }else{
                if(a[m] <=key && key <=a[hi])
                    lo=m+1;
                else hi=m-1;
            }
        }

        return -1;
    }


    public void testBsearchRotated(){
        int[] a= {5,6,7,8,1,2,3};

        assertEquals(6, bsearchRotated(a, 3));
        assertEquals(5, bsearchRotated(a, 2));
        assertEquals(4, bsearchRotated(a, 1));
        assertEquals(1, bsearchRotated(a, 6));
        assertEquals(-1, bsearchRotated(a, 10));


        assertEquals(6, bsearchRotatedSimpler(a, 3));
        assertEquals(5, bsearchRotatedSimpler(a, 2));
        assertEquals(4, bsearchRotatedSimpler(a, 1));
        assertEquals(1, bsearchRotatedSimpler(a, 6));
        assertEquals(-1, bsearchRotatedSimpler(a, 10));

    }




//    public static void main(String[] args) {
////        int[] a = {4,5,6,7,0,1,2};
////
////        int r = search(a, 5);
////        assertEquals(1, r);
////        r = search(a, 0);
////        assertEquals(4, r);
//
//
//        String r = removeDuplicateLetters("bcabc");
//        System.out.println(r);
//    }


    static String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] count = new int[27];
        for(int i=0;i<n; i++){
            count[s.charAt(i)-'a' ]++;
        }

        LinkedList<Character> l = new LinkedList<Character>();
        for(char c : s.toCharArray() ){

            for(int i=l.size()-1;i>=0;i-- ){
                if(count[l.get(i)-'a']>0 && l.get(i)-c>0 )

                    l.remove(i);
            }
            l.add(c);
            count[c-'a']--;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<l.size(); i++){
            sb.append(l.get(i) );
        }

        return sb.toString();
    }
}
