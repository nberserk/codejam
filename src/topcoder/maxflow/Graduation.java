package topcoder.maxflow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=2852&rd=5075
 */
public class Graduation {

	private Character[] classes;
	private int[] matchRow;
	private int[] matchCol;
	private boolean[] visited;
	private HashMap<Integer, Integer> rowToReq;

	public String moreClasses(String classesTaken, String[] requirements) {
		System.out.println("----------");

		int[] r = new int[requirements.length];
		Pattern p = Pattern.compile("\\d+");
		for (int i = 0; i < r.length; i++) {
			Matcher m = p.matcher(requirements[i]);
			if (m.find()) {
				String v = m.group();
				// System.out.println(v);
				r[i] = Integer.parseInt(v);
				requirements[i] = m.replaceAll("");
			} else {
				System.out.println("something wrong");
			}

			for (char c : classesTaken.toCharArray()) {
				if (requirements[i].indexOf(c) != -1) {
					requirements[i] = requirements[i].replace("" + c, "");

					if (r[i] > 0) {
						r[i] -= 1;
					}
				}
			}
		}
		
		TreeSet<Character> chars = new TreeSet<Character>();
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < requirements[i].length(); j++) {
				chars.add(requirements[i].charAt(j));
			}
		}


		int rowCount = 0;
		for (int i = 0; i < r.length; i++) {
			rowCount += r[i];
		}

		classes = chars.toArray(new Character[chars.size()]);
		matchCol = new int[classes.length]; // classes
		matchRow = new int[rowCount]; // req
		visited = new boolean[rowCount];
		Arrays.fill(matchRow, -1);
		Arrays.fill(matchCol, -1);

		rowToReq = new HashMap<Integer, Integer>();
		int index = 0;
		for (int i = 0; i < r.length; i++) {
			for (int j = 0; j < r[i]; j++) {
				Arrays.fill(visited, false);
				rowToReq.put(index, i);
				// if (bfs(index++, requirements) == false) {
				if (dfs(index++, requirements) == false) {
					System.out.println("0");
					return "0";
				}
			}
		}

		TreeSet<Character> re = new TreeSet<Character>();
		for (int i = 0; i < matchRow.length; i++) {
			if (matchRow[i] != -1) {
				re.add(classes[matchRow[i]]);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (Character character : re) {
			sb.append(character);
		}
		String result = sb.toString();
		System.out.println(result);
		return result;
	}

	private boolean dfs(int source, String[] reqs) {
		if (visited[source]) {
			return false;
		}
		visited[source] = true;

		// to match real requirement string
		int reqIndex = rowToReq.get(source);
		String req = reqs[reqIndex];
		for (int col = 0; col < classes.length; col++) {
			int idx = req.indexOf(classes[col]);
			if (idx == -1) {
				continue;
			}

			int nextRow = matchCol[col];
			if (nextRow == -1 || dfs(nextRow, reqs)) {
				matchRow[source] = col;
				matchCol[col] = source;
				return true;
			}

		}

		return false;
	}


	private boolean bfs(int source, String[] reqs) {
		ArrayList<Integer> q = new ArrayList<Integer>();
		int[] parent = new int[500];
		Arrays.fill(parent, -1);
		q.add(source);
		parent[source] = source;

		while (q.isEmpty() == false) {
			int cur = q.get(0);
			q.remove(0);

			// to match real requirement string
			int reqIndex = rowToReq.get(cur);
			String req = reqs[reqIndex];
			for (int col = 0; col < classes.length; col++) {
				int idx = req.indexOf(classes[col]);
				if (idx == -1) {
					continue;
				}

				int nextRow = matchCol[col];
				if (nextRow == -1) {
					while (parent[cur] != cur) {

						int temp = matchRow[cur];
						matchRow[cur] = col;
						matchCol[col] = cur;
						cur = parent[cur];
						col = temp;
					}
					matchRow[cur] = col;
					matchCol[col] = cur;
					return true;
				} else {
					if (parent[nextRow] == -1) {
						q.add(nextRow);
						parent[nextRow] = cur;
					}
				}

			}

		}

		return false;
	}

	public static void main(String[] args) {

		Graduation g = new Graduation();
		String r = null;

		r = g.moreClasses("A", new String[] { "2ABC", "3BDE" });
		if (!r.equals("BCDE")) {
			System.out.println("incorrect");
		}

		r = g.moreClasses("A", new String[] { "2ABC", "2CDE" });
		if (!r.equals("BCD")) {
			System.out.println("incorrect");
		}
		
		r = g.moreClasses("+/NAMT", new String[] { "3NAMT", "2+/", "1M" });
		if (!r.equals("")) {
			System.out.println("incorrect");
		}

		r = g.moreClasses("A", new String[] { "100%Klju" });
		if (!r.equals("0")) {
			System.out.println("incorrect");
		}

		r = g.moreClasses("", new String[] { "5ABCDE", "1BCDE," });
		if (!r.equals(",ABCDE")) {
			System.out.println("incorrect");
		}

		r = g.moreClasses("CDH", new String[] { "2AP", "3CDEF", "1CDEFH" });
		if (!r.equals("AEP")) {
			System.out.println("incorrect");
		}
		
		r = g.moreClasses("", new String[] {
				"4ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvw",
				"8xyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrst",
				"11uvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnop",
				"10qrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijkl" });
		if (!r.equals("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefg")) {
			System.out.println("incorrect");
		}
		
				
		r = g.moreClasses("NaGeb;q_WyX(P/BI!U}`FiVlJ<=w\"jH],>%S[Ov" , new String[] {"7oXjy\"(&,v/~Irf!)-wScp:>P<e+bZ",
				"1'T,}Bxu_*VUqP?rl>pea)zghCf", "5dBp}V", 
				"6dvB.!pun%/j@DLZ`N=PTO-RiW\"MCaS;?$csH*IK:'U\\z)m", 
						"1xfNi;U>Ro&:BHgG#E]tW'~\\ICpdabJ@",
						"3,KfDti-Zk/+>e\\d",
						"3aw`!|{\\X=H#<gpP(\"kdQtG@qSM:+m-f_As]DOE",
						"3o)_v!Qms*FKzXhW^D>a@-Ej|G\\{]yV\"BwPl[#",
						"5kR`mexra_/fzl~KQWV}u@gG^J,+E(<{IBY.!'iL)#MpOAvF" }
		);
		if (!r.equals("dp")) {
			System.out.println("incorrect");
		}

	}

}
