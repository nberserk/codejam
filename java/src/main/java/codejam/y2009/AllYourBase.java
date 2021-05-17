package codejam.y2009;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;

class Base{
	
	static long pow(long base, long exp){		
		long result = 1;
		while (exp!= 0) {
			if ((exp & 1)!=0)
				result *= base;
			exp = exp >> 1;
			base *= base;
		}

		return result;		
	}
	
	static long calc(String in){
		long seconds = 0;
		int column = in.length();
		
		HashSet<Character> set = new HashSet<Character>();
		for (int i = 0; i < in.length()	; i++) {
			set.add(in.charAt(i));
			//System.out.println(in.charAt(i));
		}
		
		//System.out.println(set);
		int base = Math.max(set.size(), 2);
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		System.out.println(in+", base="+ base);
		int realValue = 0;
		int nextValue = 1; // 1 0 2 3 4 .. (base-1)
		
		for (int i = 0; i < in.length()	; i++) {
			char c = in.charAt(i);
			
			if (map.containsKey(c)) {
				realValue = map.get(c);
			} else {
				realValue = nextValue;
				map.put(c, realValue);
				
				if (nextValue == 1) {
					nextValue = 0;
				}else if (nextValue == 0){
					nextValue = 2;
				}else{
					nextValue++;
				}							
			}		
			
			long delta = pow(base, column-1-i)*realValue;
//			long delta2 = Math.round(Math.pow(base, column-1-i))*realValue;
//			if (delta != delta2) {
//				System.out.println("oops"+delta +":"+ delta2);
//			}
			seconds += delta;
			System.out.println(i+":"+c+"->"+realValue+", delta="+(long)delta+", seconds="+seconds);
		}		
		
		
		return seconds;		
	}
}



public class AllYourBase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		
		//double delta = Math.pow(5, 23);
//		long l = Base.pow(5, 23);
//		//l = Base.pow(5, 15);
				
		//System.out.println(delta);
//		System.out.println(l);
//		if (l != delta) {
//			return;
//		}
		
		
		try {
//			br = new BufferedReader( new FileReader("./data/A-small-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/A-small.out"));
			br = new BufferedReader( new FileReader("./data/A-large-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/A-large.out"));
			
			String str = br.readLine();
			String outStr = null;
			long result = 0;
			int lineCount = Integer.parseInt(str);
			for (int i = 1; i <= lineCount; i++) {
				str = br.readLine();
				str = str.trim();
				//System.out.println(str+":"+str.length());
				outStr = "Case #"+ i+": " + Base.calc(str) + "\n";				
				System.out.print(outStr);
				bw.write(outStr);
			}
						
			
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				br.close();
			} catch (Exception e2) {
				System.out.println(e2);
			}
			try {
				bw.flush();
				bw.close();
			} catch (Exception e2) {
				System.out.println(e2);

			}
		}
		


	}

}
