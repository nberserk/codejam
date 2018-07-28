package leetcode;

import org.junit.Test;

import java.util.Random;

public class _883 {
    Random random = new Random();
    private double radius;
    private double y_center;
    private double x_center;


    public void Solution(double radius, double x_center, double y_center) {
        this.radius=radius;
        this.x_center=x_center;
        this.y_center=y_center;
    }

    public double[] randPoint() {
        double x0 = -radius;
        double y0 = -radius;

        while(true){
            double x = x0 + random.nextDouble()*(this.radius*2.0);
            double y = y0+ random.nextDouble()*this.radius*2.0;
            if(Math.sqrt(x*x+y*y)<=this.radius){
                return new double[]{x+x_center,y+y_center};
            }
        }
    }

    @Test
    public void test(){
        _883 Sol = new _883();
        Sol.Solution(1, 0, 0);
        double[] c = Sol.randPoint();
    }
}
