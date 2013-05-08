package topcoder.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=1918&rd=5006
 */
public class FlowerGarden {

	public static int[] getOrdering(int[] height, int[] bloom, int[] wilt) {

		ArrayList<TreeSet<Integer>> unblocked = new ArrayList<TreeSet<Integer>>();
		HashMap<Integer,Integer> vtoi = new HashMap<Integer, Integer>();
		for (int i = 0; i < height.length; i++) {
			vtoi.put(height[i], i);
		}
		
		int n = height.length;

		boolean[] used = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			if (used[i]) {
				continue;
			}
			TreeSet<Integer> newblock = new TreeSet<Integer>();
			newblock.add(height[i]);

			for (int j = i + 1; j < n; j++) {
				if (used[j]) {
					continue;
				}
				
				for (Integer c : newblock) {
					int idx = vtoi.get(c);
					if (bloom[idx] > wilt[j] || wilt[idx] < bloom[j]) {
						// not intersect
					} else {
						newblock.add(height[j]);
						used[j] = true;
						break;
					}
					// if (bloom[idx] <= bloom[j] && wilt[idx] >= bloom[j]) {
					// newblock.add(height[j]);
					// used[j] = true;
					// break;
					// }
					// if (bloom[idx] <= wilt[j] && wilt[idx] >= wilt[j]) {
					// newblock.add(height[j]);
					// used[j] = true;
					// break ;
					// }
					//
					// if (bloom[j] <= bloom[idx] && wilt[j] >= bloom[idx]) {
					// newblock.add(height[j]);
					// used[j] = true;
					// break;
					// }
					//
					// if (bloom[j] <= wilt[idx] && wilt[j] >= wilt[idx]) {
					// newblock.add(height[j]);
					// used[j] = true;
					// break;
					// }

				}
				
			}
			unblocked.add(newblock);
			used[i] = true;
		}


		int[] temp = new int[unblocked.size()];
		for (int i = 0; i < unblocked.size(); i++) {
			TreeSet<Integer> blocked = unblocked.get(i);
			temp[i] = blocked.first();
		}

		Arrays.sort(temp);

		int[] ret = new int[n];
		int index = 0;
		for (int i = temp.length - 1; i >= 0; i--) {
			for (TreeSet<Integer> blocked : unblocked) {
				if (temp[i] == blocked.first()) {
					for (Integer integer : blocked) {
						ret[index] = integer;
						index++;
					}
					break;
				}
			}
		}
		
		return ret;
	}

	public static void main(String[] args) {

		int[] r = null;

		r = FlowerGarden.getOrdering(new int[] { 5, 4, 3, 2, 1 }, new int[] { 1, 1, 1, 1, 1 }, new int[] { 365, 365,
				365, 365, 365 });
		System.out.println(Arrays.toString(r));

		r = FlowerGarden.getOrdering(new int[] { 5, 4, 3, 2, 1 }, new int[] { 1, 5, 10, 15, 20 }, new int[] { 4, 9, 14,
				19, 24 });
		System.out.println(Arrays.toString(r));

		r = FlowerGarden.getOrdering(new int[] { 5, 4, 3, 2, 1 }, new int[] { 1, 5, 10, 15, 20 }, new int[] { 5, 10,
				15, 20, 25 });
		System.out.println(Arrays.toString(r));

		r = FlowerGarden.getOrdering(new int[] { 5, 4, 3, 2, 1 }, new int[] { 1, 5, 10, 15, 20 }, new int[] { 5, 10,
				14, 20, 25 });
		System.out.println(Arrays.toString(r));

		r = FlowerGarden.getOrdering(new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 1, 3, 1, 3, 1, 3 }, new int[] { 2, 4,
				2, 4, 2, 4 });
		System.out.println(Arrays.toString(r));

		r = FlowerGarden.getOrdering(new int[] { 3, 2, 5, 4 }, new int[] { 1, 2, 11, 10 }, new int[] { 4, 3, 12, 13 });
		System.out.println(Arrays.toString(r));

	}

}
