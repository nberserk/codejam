package codejam.y2009;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


public class BribeThePrisoners {
	
	
	static int fCount = 0;

	static int calcBribe2(int start, int P, ArrayList<Integer> q, int depth){
		StringBuilder prefixBuilder = new StringBuilder();		
		for (int i = 0; i < depth; i++) {
			prefixBuilder.append("  ");
		}
		System.out.println(prefixBuilder.toString() + "range["+start+"-"+P+ "]"+", q:"+ q.toString());

		int bribe = P-start;
		if (q.size() > 1) {
			
			// determine best candidate			
			
			int idx = -1, v, left, right;
			int max = Integer.MIN_VALUE;
			int size = q.size();
			for (int i = 0; i <= size; i++) {
				//v = q.get(i);
				
				left =  (i==0) ? start : q.get(i-1);
				right = (i==size) ? P : q.get(i) ; 
				int weight = right-left;
				
//				left = (i==0) ? (v-start) : (v-q.get(i-1));
//				right = (i==size-1) ? (P-v) : (q.get(i+1)-v);
//				left = left * (size-1-i);
//				right = right * i ;

				//int weight = left + right;
				int currentMax = Math.max(max, weight);
				if (weight == max) {
					System.out.println("same candidate");
				}
				if ( currentMax!= max)	{
					max = currentMax;
					idx = i;
				}
			}
			
			if (idx!=0 && idx!=size-1) {
				
			}
			
			
//			if (fCount == 9) {
//				System.out.println("calculated idx=" + idx);
//				Scanner s = new Scanner(System.in);
//				int myIdx = s.nextInt();
//				idx = myIdx;				
//			}
			
			
			int splitValue = q.get(idx);		
			System.out.println(prefixBuilder.toString() + "split value: "+splitValue+", idx: "+ idx);
			if (idx != 0) {
				ArrayList<Integer> lq = new ArrayList<Integer>();
				for (int i = 0; i < idx; i++) {
					lq.add(q.get(i));
				}
//				System.arraycopy(q, 0, lq, 0, idx);				
				
				bribe += calcBribe2(start, splitValue-1, lq, depth+1);			
			}
			
			if (idx != q.size()-1){
				ArrayList<Integer> rq = new ArrayList<Integer>();
				for (int i = idx+1; i < q.size(); i++) {
					rq.add(q.get(i));
				}
				bribe += calcBribe2(splitValue+1, P, rq, depth+1);
			}			
		}else if (q.size() == 0){
			//System.out.println("error: Q" + q.size());
		}		
		
		
		System.out.println(prefixBuilder.toString() + "bribe:"+bribe);
		return bribe;		
	}

	
	static int calcBribe(int start, int P, ArrayList<Integer> q, int depth){
		StringBuilder prefixBuilder = new StringBuilder();		
		for (int i = 0; i < depth; i++) {
			prefixBuilder.append("  ");
		}
		System.out.println(prefixBuilder.toString() + "range["+start+"-"+P+ "]"+", q:"+ q.toString());

		int bribe = P-start;
		if (q.size() > 1) {			
			double middle = (start + P)/2.0;
			double min = Double.MAX_VALUE;
			int idx = -1;
			for (int i = 0; i < q.size(); i++) {
				double diff = Math.abs(middle - q.get(i));
				double currentMin = Math.min(diff, min);
				if ( currentMin!= min)	{
					min = currentMin;
					idx = i;
				}		
			}
			
			
			int splitValue = q.get(idx);		
			System.out.println(prefixBuilder.toString() + "split value: "+splitValue+", idx: "+ idx);
			if (idx != 0) {
				ArrayList<Integer> lq = new ArrayList<Integer>();
				for (int i = 0; i < idx; i++) {
					lq.add(q.get(i));
				}
//				System.arraycopy(q, 0, lq, 0, idx);				
				
				bribe += calcBribe(start, splitValue-1, lq, depth+1);			
			}
			
			if (idx != q.size()-1){
				ArrayList<Integer> rq = new ArrayList<Integer>();
				for (int i = idx+1; i < q.size(); i++) {
					rq.add(q.get(i));
				}
				bribe += calcBribe(splitValue+1, P, rq, depth+1);
			}			
		}else if (q.size() == 0){
			//System.out.println("error: Q" + q.size());
		}		
		
		
		System.out.println(prefixBuilder.toString() + "bribe:"+bribe);
		return bribe;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
				
		try {
			br = new BufferedReader(new FileReader("./data/C-small-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/C-small.out"));
//			br = new BufferedReader( new FileReader("./data/B-large-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/B-large.out"));
			
			String str = br.readLine();
//			String outStr = null;
//			long result = 0;
			int count = Integer.parseInt(str);			
			for (int i = 1; i <= count; i++) {
				fCount = i;
				str = br.readLine();
				String[] arg1 = str.split(" ");
				int P = Integer.parseInt(arg1[0]);
				int Q = Integer.parseInt(arg1[1]);
				
				ArrayList<Integer> q = new ArrayList<Integer>(Q);
				
				arg1 = br.readLine().split(" ");
				for (int j = 0; j < Q; j++) {
					
					q.add(Integer.parseInt(arg1[j]));
				}
				
				int coins = calcBribe2(1, P, q, 0);
				//System.out.println("total coin = "+ coins);
				
				String outStr = "Case #"+ i+": " + coins + "\n";
				System.out.print(outStr+"\n");
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
