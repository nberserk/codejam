package leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class _166 {
    public String fractionToDecimal(int numer, int denomi) {
        assert denomi != 0;
        if(numer==0) return "0";
        StringBuilder sb = new StringBuilder();


        long num=Math.abs((long)numer);
        long denum=Math.abs((long)denomi);
        if(num*denum<0){
            sb.append("-");
        }

        sb.append(numer/denomi);
        num%=denum;
        if(num==0){
            return sb.toString();
        }
        sb.append(".");
        HashMap<Long,Integer> map = new HashMap<>();
        while(num!=0){
            num*=10;
            if(map.containsKey(num)){
                sb.insert(map.get(num), "(");
                sb.append(")");
                break;
            }else{
                map.put(num,sb.length());
                sb.append(num/denum);
                num%=denum;
            }
        }
        return sb.toString();
    }

    @Test
    public void test2(){
        assertEquals("0", fractionToDecimal(0,2));
        assertEquals("0.(3)", fractionToDecimal(1,3));
        assertEquals("0.5", fractionToDecimal(1,2));

        assertEquals("0.(003)", fractionToDecimal(1,333));
        assertEquals("0.0000000004656612873077392578125", fractionToDecimal(1,Integer.MIN_VALUE));
    }
}
