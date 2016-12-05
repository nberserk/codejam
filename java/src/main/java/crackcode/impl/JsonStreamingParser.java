package crackcode.impl;

import java.util.Stack;

/**
 * Created by darren on 2016. 12. 5..
 */
public class JsonStreamingParser {

    enum State {
        STATE_START_DOCUMENT,
        STATE_IN_OBJECT,
        STATE_IN_ARRAY
    }
    Stack<State> stack = new Stack<>();
    State state = State.STATE_START_DOCUMENT;
    String cur="";


    private void parse(char c){

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

                break;

        }

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

}
