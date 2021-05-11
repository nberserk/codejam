package leetcode;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class EncodeAndDecodeTinyURL_535 {

    String chars = "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String, String> shortToLong = new HashMap<>();
    HashMap<String, String> longToShort = new HashMap<>();
    int idx=1;

    String prefix = "https://tynyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if(longToShort.containsKey(longUrl))
            return prefix+longToShort.get(longUrl);

        String conv = convert(idx++);

        shortToLong.put(conv, longUrl);
        longToShort.put(longUrl, conv);

        return prefix+conv;
    }

    String convert(int i){
        int MOD = chars.length();
        StringBuilder sb = new StringBuilder();
        while(i>0){
            sb.append(i%MOD);
            i/=MOD;
        }
        return sb.reverse().toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return shortToLong.get(shortUrl.replaceAll(prefix, ""));
    }


    @Test
    public void test(){
        EncodeAndDecodeTinyURL_535 e = new EncodeAndDecodeTinyURL_535();
        String l = "naver.com";
        String s= e.encode(l);
        assertEquals(l, e.decode(s));
    }
}
