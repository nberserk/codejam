package codejam.y2017;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 *
 * problem : https://code.google.com/codejam/contest/3264486/dashboard#s=p1
 *
 */


public class TidyNumbers {
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

                String v = reader.readLine();

                String r = solve(v);
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

	private String solve(String in) {
        char[] ch = in.toCharArray();


        int N = ch.length;
        char[] out = Arrays.copyOf(ch, N);

        for (int i = 0; i<N-1; i++) {
            char first = ch[i];
            char second = ch[i+1];
            if(first>second){
                out[i]--;
                out[i+1]='9';

                // look back
                for (int j=i-1;j>=0;j--){
                    if(out[j]>out[j+1]){
                        out[j]--;
                        out[j+1]='9';
                    }else
                        break;
                }

                //
                for(int j=i+2;j<N;j++)
                    out[j]='9';
                break;
            }


        }

        String r = String.valueOf(out);
        long t = Long.valueOf(r);
        return String.valueOf(t);
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
		TidyNumbers b = new TidyNumbers();
		String dir = "/Users/darren/Downloads/";
		String small = "A-small-practice.in";
        String large = "B-large-practice.in";

        //b.fromFile(dir + small, dir + small.replace(".in", ".out"));
        b.fromFile(dir + large, dir + large.replace(".in", ".out"));
	}

    @Test
    public void testTidyNumbers(){
        TidyNumbers b = new TidyNumbers();
        assertEquals("129", b.solve("132"));
        assertEquals("999", b.solve("1000"));
        assertEquals("9999", b.solve("11110"));

    }

}
