package leetcode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**


 You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.

 Design a log storage system to implement the following functions:

 void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.


 int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.

 Example 1:
 put(1, "2017:01:01:23:59:59");
 put(2, "2017:01:01:22:59:59");
 put(3, "2016:01:01:00:00:00");
 retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 Note:
 There will be at most 300 operations of Put or Retrieve.
 Year ranges from [2000,2017]. Hour ranges from [00,23].
 Output for Retrieve has no order required.

 *
 */
public class LC635 {

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
        String[] types = {"Year", "Month", "Day", "Hour", "Minute", "Second"};
        // 2017:01:01:23:59:59
        //
        int[] idx= {4, 7,10,13,16,19 };
        int type=0;
        for (int i = 0; i < types.length; i++) {
            if(types[i].equals(gra)){
                type=i;
            }
        }
        int to = idx[type];
        String start = s.substring(0,to);
        String end = e.substring(0,to);

        for (Log l: logs){
            String v = l.timestamp.substring(0, to);
            if( start.compareTo(v)<=0 && end.compareTo(v)>=0 ){
                re.add(l.id);
            }
        }
        return re;
    }

    public List<Integer> retrieve_first(String s, String e, String gra) {
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
        LC635 ls = new LC635();

        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:01:22:59:59");
        ls.put(3, "2016:01:01:00:00:00");

        assertEquals("[1, 2, 3]", ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Year").toString());
        assertEquals("[1, 2]", ls.retrieve("2016:01:01:01:01:01", "2017:01:01:23:00:00", "Hour").toString());
    }

    @org.junit.Test
    public void test2(){
        LC635 ls = new LC635();

        ls.put(1, "2017:01:01:23:59:59");
        ls.put(2, "2017:01:02:23:59:59");

        assertEquals("[1]", ls.retrieve("2017:01:01:23:59:58","2017:01:02:23:59:58","Second").toString());
    }


}
