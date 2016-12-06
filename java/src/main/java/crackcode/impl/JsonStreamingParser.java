package crackcode.impl;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by darren on 2016. 12. 5..
 */
public class JsonStreamingParser {

    enum State {
        STATE_START_DOCUMENT,
        STATE_IN_OBJECT,
        STATE_IN_ARRAY,
        STATE_IN_STRING,
        STATE_KEY,
        STATE_ENDKEY,
    }
    Stack<State> stack = new Stack<>();
    State state = State.STATE_START_DOCUMENT;
    String cur="";

    private void parse(char c){

        if ((c == ' ' || c == '\t' || c == '\n' || c == '\r')
            && !(state!=State.STATE_IN_STRING ) )
            return;

        switch (state){
            case STATE_START_DOCUMENT:
            {
                if(c=='{')
                    startObject();
                else if(c=='[')
                    startArray();
            }
            break;
            case STATE_IN_OBJECT:
                if(c=='"')
                    startKey();
                else if(c=='}')
                    endObject();
                break;
            case STATE_IN_STRING:
                if (c=='"')
                    endString();
                else
                    cur+=c;
                break;
            case STATE_ENDKEY:
                if(c==':'){
                    startValue();
                }
                break;


        }
    }

    private void startValue() {

    }

    private void endString() {
        state = stack.pop();
        if (state==State.STATE_KEY) {
            System.out.println("key:" + cur);

        }else
            System.out.println("value:" + cur);
        cur="";
    }

    private void endObject() {
        System.out.println("endObject:" + cur);
        state = stack.pop();
    }

    private void startKey() {
        stack.push(state);
        stack.push(State.STATE_KEY);
        state = State.STATE_IN_STRING;
    }

    private void startArray() {
        System.out.println("startArray");
        stack.push(state);
        state=State.STATE_IN_ARRAY;
    }


    private void startObject() {
        System.out.println("startObject");
        stack.push(state);
        state=State.STATE_IN_OBJECT;
    }

    @Test
    public void test(){

    }

}
