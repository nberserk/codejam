package codejam.lib.binarysearch;

import java.util.LinkedList;

/**
 * Created by darren on 2016. 2. 1..
 */
public class BinarySearchRotatedArray {

    static int search(int[] a, int target){
        int l = 0;
        int h = a.length-1;
        while(l<h){
            int m = l + (h-l)/2;
            if(target==a[m]) return m;

            if(a[l]<=a[m]){
                if(target >= a[l] && target <= a[m])
                    h=m;
                else l=m+1;
            }else if(a[m]<=a[h]){
                if(target>=a[m] && target <=a[h])
                    l = m;
                else
                    h = m-1;
            }


        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] a = {4,5,6,7,0,1,2};
//
//        int r = search(a, 5);
//        assertEquals(1, r);
//        r = search(a, 0);
//        assertEquals(4, r);


        String r = removeDuplicateLetters("bcabc");
        System.out.println(r);
    }


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
