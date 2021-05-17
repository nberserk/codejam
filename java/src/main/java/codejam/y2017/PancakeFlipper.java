package codejam.y2017;

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 *
 * problem : https://code.google.com/codejam/contest/3264486/dashboard#s=p0
 *
 */


public class PancakeFlipper {
	BufferedWriter mWriter;
	int	mCurrentProblem;
	
	public void fromFile(String inFile, String outFile){
		BufferedReader reader = null;		
		// long start = System.currentTimeMillis();
				
		try {
			reader = new BufferedReader(new FileReader(inFile));
			mWriter = new BufferedWriter(new FileWriter(outFile));
			
			String str = reader.readLine();
			int problemCount = Integer.parseInt(str);			
			for (int i = 1; i <= problemCount; i++) {
				mCurrentProblem = i;

                String[] v = reader.readLine().split(" ");

				int K = Integer.valueOf(v[1]);

                String r = solve(v[0], K);
                writeSolution(r);
			}
						
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				reader.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			try {
				mWriter.flush();
				mWriter.close();
			} catch (Exception e2) {
				System.out.println(e2);

			}
		}

		// long elapsedTimeMillis = System.currentTimeMillis()-start;
		// float elapsedTimeSec = elapsedTimeMillis/1000F;
		// System.out.println("elapsed time(sec) :"+ elapsedTimeSec);
	}

	private void solveLargeProblem() {
	}

	private String solve(String in, int K) {
        char[] ch = in.toCharArray();

        int ret = 0;
        int N = ch.length;
        for (int i = 0; i <N; i++) {
            if (i<=N-K){
                if(ch[i]=='-'){
                    flip(ch,i, K);
                    ret++;
                }
            }else{
                if(ch[i]=='-'){
                    ret=Integer.MAX_VALUE;
                    break;
                }
            }
        }

        if(ret==Integer.MAX_VALUE)
            return "IMPOSSIBLE";
        else return String.valueOf(ret);
	}

    private void flip(char[] ch,int start,  int k) {
        for(int i=start;i<start+k;i++){
            if (ch[i]=='-') ch[i]='+';
            else ch[i]='-';
        }
    }

    public void writeSolution(String s){
		String outStr = "Case #"+ mCurrentProblem+": " +s + "\n";
		print(outStr);
		try {
			mWriter.write(outStr);
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}

	public static void print(String str){
		System.out.println(str);
	}

	public static void main(String[] args) {
		PancakeFlipper b = new PancakeFlipper();
		String dir = "/Users/darren/Downloads/";
		String small = "A-small-practice.in";
        String large = "A-large-practice.in";

        //b.fromFile(dir + small, dir + small.replace(".in", ".out"));
        b.fromFile(dir + large, dir + large.replace(".in", ".out"));
	}

    @Test
    public void testFlipper(){
        PancakeFlipper b = new PancakeFlipper();
        assertEquals("3", b.solve("---+-++-", 3));
        assertEquals("0", b.solve("+++++", 4));
        assertEquals("IMPOSSIBLE", b.solve("-+-+-", 4));

    }

}
