package leetcode;

public class Test {

    public boolean judgeCircle(String moves) {
        int h=0;
        int v=0;
        for (char c : moves.toCharArray()){
            switch (c){
                case 'U':
                    v++;
                    break;
                case 'D':
                    v--;
                    break;
                case 'L':
                    h--;
                    break;
                case 'R':
                    h++;
                    break;
            }
        }

        if(h==0 && v==0 ) return true;
        return false;
    }

    @org.junit.Test
    public void test(){
    }
}
