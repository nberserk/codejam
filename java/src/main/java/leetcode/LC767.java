package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 */
public class LC767 {
    public String reorganizeString(String S) {
        HashMap<Character,Integer> f = new HashMap<>();
        for(char c: S.toCharArray()){
            f.putIfAbsent(c, 0);
            f.put(c, f.get(c)+1);
        }
        List<Character> keys = new ArrayList<>(f.keySet());
        Collections.sort(keys, (a, b)->-f.get(a)+f.get(b));

        int N=S.length();
        char[] organized = new char[S.length()];
        int i=0;
        for(char k: keys){
            int times = f.get(k);
            if(times>(N+1)/2) return "";

            for (int j = 0; j < times; j++) {
                organized[i]=k;
                i+=2;
                if(i>=N)
                    i=1;
            }
        }
        return String.valueOf(organized);
    }

    @Test
    public void test(){
        assertEquals("babab", reorganizeString("tndsewnllhrtwsvxenkscbivijfqnysamckzoyfnapuotmdexzkkrpmppttficzerdndssuveompqkemtbwbodrhwsfpbmkafpwyedpcowruntvymxtyyejqtajkcjakghtdwmuygecjncxzcxezgecrxonnszmqmecgvqqkdagvaaucewelchsmebikscciegzoiamovdojrmmwgbxeygibxxltemfgpogjkhobmhwquizuwvhfaiavsxhiknysdghcawcrphaykyashchyomklvghkyabxatmrkmrfsppfhgrwywtlxebgzmevefcqquvhvgounldxkdzndwybxhtycmlybhaaqvodntsvfhwcuhvuccwcsxelafyzushjhfyklvghpfvknprfouevsxmcuhiiiewcluehpmzrjzffnrptwbuhnyahrbzqvirvmffbxvrmynfcnupnukayjghpusewdwrbkhvjnveuiionefmnfxao"));

        assertEquals("babab", reorganizeString("baabb"));
        assertEquals("aba", reorganizeString("aab"));
        assertEquals("", reorganizeString("aaab"));
    }
}
