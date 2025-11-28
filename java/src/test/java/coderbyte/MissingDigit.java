package coderbyte;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MissingDigit {

    public int findX(String x, int num) {
        for (int i = x.length()-1; i >=0; i--) {
            char c = x.charAt(i);
            if (c =='x') {
                return num%10;
            }
            if (c - '0' != num%10) {
                throw new RuntimeException("doesn't match");
            }
            num /= 10;
        }
        return -1;
    }
    public int missingDigit(String input){
        String[] split = input.split(" ");

        if (split[0].contains("x")){
            int y = Integer.parseInt(split[2]);
            int z = Integer.parseInt(split[4]);
            int rightSide;
            switch (split[1]){
                case "*":
                    rightSide = z/y;
                    break;
                case "/":
                    rightSide = z*y;
                    break;
                case "+":
                    rightSide = z-y;
                    break;
                case "-":
                    rightSide = z+y;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + split[1]);
            }
            return findX(split[0], rightSide);
        }else if (split[2].contains("y")){
            int x = Integer.parseInt(split[0]);
            int z = Integer.parseInt(split[4]);
            int rightSide;
            switch (split[1]){
                case "*":
                    rightSide = z/x;
                    break;
                case "/":
                    rightSide = z*x;
                    break;
                case "+":
                    rightSide = z-x;
                    break;
                case "-":
                    rightSide = x-z;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + split[1]);
            }
            return findX(split[2], rightSide);
        }else{
            int x = Integer.parseInt(split[0]);
            int y = Integer.parseInt(split[2]);
            int rightSide;
            switch (split[1]){
                case "*":
                    rightSide = x*y;
                    break;
                case "/":
                    rightSide = x/y;
                    break;
                case "+":
                    rightSide = x+y;
                    break;
                case "-":
                    rightSide = x-y;
                    break;
                default:
                    throw new IllegalArgumentException("Unknown operator: " + split[1]);
            }
            return findX(split[4], rightSide);
        }

    }

    @Test
    public void test(){
        assertEquals(2, missingDigit("4 - 2 = x"));
        assertEquals(0, missingDigit("1x0 * 12 = 1200"));

    }
}
