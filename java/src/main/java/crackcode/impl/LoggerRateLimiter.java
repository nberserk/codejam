package main.java.crackcode.impl;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 11/28/16.
 */
public class LoggerRateLimiter {
    class Log{
        int timestamp;
        String msg;
        Log(String m, int t){
            msg=m;
            timestamp=t;
        }
    }
    LinkedList<Log> queue = new LinkedList<>();
    HashSet<String> recentMsg = new HashSet<>();

    public boolean shouldPrintMessage(int timestamp, String message) {
        while(queue.size()>0 && timestamp - queue.getFirst().timestamp >=10){
            recentMsg.remove(queue.getFirst().msg);
            queue.removeFirst();
        }

        if(recentMsg.contains(message)) return false;
        queue.add(new Log(message,timestamp));
        recentMsg.add(message);
        return true;
    }

    @Test
    public void test(){
        LoggerRateLimiter logger = new LoggerRateLimiter();

        assertEquals(true, logger.shouldPrintMessage(1, "foo"));
        assertEquals(true, logger.shouldPrintMessage(2,"bar"));
        assertEquals(false, logger.shouldPrintMessage(3,"foo"));
        assertEquals(false, logger.shouldPrintMessage(8,"bar"));
        assertEquals(false, logger.shouldPrintMessage(10,"foo"));
        assertEquals(true, logger.shouldPrintMessage(11,"foo"));
    }
}
