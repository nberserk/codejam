package leetcode;

import static org.junit.Assert.assertEquals;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.

 Example 1:
 Input:
 s = "abcxyz123"
 dict = ["abc","123"]
 Output:
 "<b>abc</b>xyz<b>123</b>"
 Example 2:
 Input:
 s = "aaabbcc"
 dict = ["aaa","aab","bc"]
 Output:
 "<b>aaabbc</b>c"
 Note:
 The given dict won't contain duplicates, and its length won't exceed 100.
 All the strings in input have length in range [1, 1000].
 
 */
public class LC616 {

    String addBoldTag(String s, String[] dict) {
        int N = s.length();
        int[] p = new int[N+1];
        for(String d: dict){
            int from=0;
            while(true){
                int idx = s.indexOf(d, from);
                if(idx==-1) {
                    break;
                }
                p[idx]++;
                p[idx+d.length()]--;
                from=idx+1;
            }
        }

        String ret="";
        int bold=0;
        for(int i=0;i<=N;i++){
            int next = bold + p[i];
            if(bold==0 && next>0){
                ret += "<b>";
            }else if(bold>0 && next==0){
                ret += "</b>";
            }
            if(i!=N)
                ret += s.charAt(i);
            bold=next;
        }
        return ret;
    }

    @org.junit.Test
    public void test(){
        assertEquals("<b>qrzjsorbkmyzzzvoqxefvxkcwtpk</b>hzbakuufbpgdky<b>km</b>ojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmwey<b>yzz</b>mgcoiupjnidphvzlnxtcogufozlenjf<b>vo</b>kztghwckzyvmktduqkizixzxpanjwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm",
                addBoldTag("qrzjsorbkmyzzzvoqxefvxkcwtpkhzbakuufbpgdkykmojwuennrjeciqvvacpzrrczfhxnsmginzwinzihpomxtmweyyzzmgcoiupjnidphvzlnxtcogufozlenjfvokztghwckzyvmktduqkizixzxpanjwrdeudjyftxksjgdklwxrhmudhrtemuvelykqaafzlqmennttkighcdxfozdcoqkyshhajipnsdrljrnlwmyjuwxsebpqm", new String []{"qr","zj","so","rb","km","yz","zz","vo","qx","ef","vx","kc","wt","pk"})
        );


    }
}

