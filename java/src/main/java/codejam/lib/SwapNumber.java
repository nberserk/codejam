package codejam.lib;

public class SwapNumber {

    public static void main(String[] args) {

        int a = 1;
        int b = 100;
        swapWithoutTemp(a, b);
        swapWithoutTemp2(a,b);
    }

    public static void swapWithoutTemp(int x, int y){
        x = x^y;
        y = x^y;
        x = x^y;
        System.out.println("swapWithoutTemp " + x + "," + y);
    }

    public static void swapWithoutTemp2(int x, int y){
        x = y-x;
        y = y-x;
        x = y+x;
        System.out.println("swapWithoutTemp2 " + x + "," + y);
    }
}
