package codejam.y2009;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.Arrays;


public class CenterOfMass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedReader br = null;
		BufferedWriter bw = null;
				
		try {
			br = new BufferedReader( new FileReader("./data/B-small-practice.in"));
			bw = new BufferedWriter(new FileWriter("./data/B-small.out"));
//			br = new BufferedReader( new FileReader("./data/B-large-practice.in"));
//			bw = new BufferedWriter(new FileWriter("./data/B-large.out"));
			
			String str = br.readLine();
//			String outStr = null;
//			long result = 0;
			int lineCount = Integer.parseInt(str);
			for (int i = 1; i <= lineCount; i++) {
				str = br.readLine();
				int flyCount = Integer.parseInt(str);
				double[] cof = new double[6];
				for (int j = 0; j < flyCount; j++) {
					str = br.readLine();
					str = str.trim() ;
					String[] args1 = str.split(" ");
					System.out.println("d\t" +Arrays.toString(args1));
					
					cof[0] += Double.parseDouble(args1[3]);
					cof[1] += Double.parseDouble(args1[0]);
					cof[2] += Double.parseDouble(args1[4]);
					cof[3] += Double.parseDouble(args1[1]);
					cof[4] += Double.parseDouble(args1[5]);
					cof[5] += Double.parseDouble(args1[2]);				
				}				
				for (int j = 0; j < 6; j++) {
					cof[j] /= (double)flyCount;
				}
				System.out.println(Arrays.toString(cof));
				
				// distance = sqrt(aax^2 +bbx + cc)
				double aa = cof[0]*cof[0]+cof[2]*cof[2]+cof[4]*cof[4];
				double bb = 2.0*( cof[0]*cof[1] + cof[2]*cof[3] + cof[4]*cof[5]);
				double cc = cof[1]*cof[1] + cof[3]*cof[3] + cof[5]*cof[5];
				System.out.println("aa=" + aa + ", bb=" + bb + ", cc=" +cc);
				
				double t = 0;
				if (aa == 0){
					if (bb>=0) {
						t = 0;						
					}else{
						System.err.println("sol is complex");
						System.exit(2);
					}
				}else if (aa >0) {
					if (-bb/(2*aa) >= 0) {
						t = -bb/(2*aa);
					}else{
						t = 0;
					}
				}else{
					System.err.println("sol is complex");
					System.exit(2);
				}
				
				double d = aa*t*t + bb*t + cc;
				if (d<0) {
					System.err.format("negative %f\n", d);
					if (d<0.0000001) {
						d = 0;
					}					
					//System.exit(3);
				}
				d = Math.sqrt(d);
				DecimalFormat df = new DecimalFormat("#0.00000000");
				String outStr = "Case #"+ i+": " + df.format(d) + " " + df.format(t) + "\n";				
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
