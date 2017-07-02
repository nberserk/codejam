package leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**

 *
 */
public class LogSystem {

    static class Log{
        int id;
        String timestamp;
    }

    List<Log> logs = new ArrayList<>();

    public void put(int id, String timestamp) {
        Log l = new Log();
        l.id=id;
        l.timestamp=timestamp;
        logs.add(l);
    }

    public List<Integer> retrieve(String s, String e, String gra) {
        List<Integer> re = new ArrayList<>();
        String[] st = s.split(":");
        String[] en = e.split(":");

        long start=0;
        long end=0;

        String[] type = {"Year", "Month", "Day", "Hour", "Minute", "Second"};
        long[] multi = {3600*24*30*12,3600*24*30,3600*24,3600,60, 1};

        for (int i = 0; i < st.length; i++) {
            start += multi[i]*Long.valueOf(st[i]);
            end += multi[i]*Long.valueOf(en[i]);
            if(gra.equals(type[i])) break;
        }

        for (Log l: logs){
            String[] t = l.timestamp.split(":");
            long v =0;
            for (int i = 0; i < st.length; i++) {
                v += multi[i]*Long.valueOf(t[i]);
                if(gra.equals(type[i])) break;
            }
            if( start<= v && v<= end){
                re.add(l.id);
            }
        }
        return re;
    }

    @org.junit.Test
    public void test(){
        LogSystem ls = new LogSystem();

        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:01:22:59:59");
        ls.put(3, "2016:01:01:00:00:00");

        assertEquals("[1, 2, 3]", ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year").toString());
        assertEquals("[1, 2]", ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour").toString());
    }

    @org.junit.Test
    public void test2(){
        LogSystem ls = new LogSystem();

        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:02:23:59:59");

        assertEquals("[1]", ls.retrieve("2017:01:01:23:59:58","2017:01:02:23:59:58","Second").toString());
    }


}
