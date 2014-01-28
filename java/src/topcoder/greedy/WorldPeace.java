package topcoder.greedy;

import java.util.Arrays;

/*
 * http://community.topcoder.com/stat?c=problem_statement&pm=2420&rd=5850
 */
public class WorldPeace {

	public static long numGroups(int k, long countries[]) {
		long c = 0;
		if (countries.length < k) {
			return c;
		}
		Arrays.sort(countries);
		int n = countries.length;

		while (countries[n - k] > 0) {

			long d = countries[n - k];
			for (int i = 0; i < k; i++) {
				countries[n - k + i] -= d;
				if (countries[n - k + i] < 0) {
					System.out.println("can't be here");
				}
			}
			c += d;
			Arrays.sort(countries);
		}

		return c;
	}
	public static void main(String[] args) {
		
		long c=0;
		c = WorldPeace.numGroups(5, new long[] { 1, 2, 3, 4, 5, 6 });
		System.out.println(c);

		c = WorldPeace.numGroups(2,
				new long[] { 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000 });
		System.out.println(c);

		c = WorldPeace.numGroups(7,
				new long[] { 96, 17, 32, 138, 112, 50, 7, 19, 412, 23, 14, 50, 47, 343, 427, 22, 39 });
		System.out.println(c);

		c = WorldPeace.numGroups(10, new long[] { 638074479, 717901019, 910893151, 924124222, 991874870, 919392444,
				729973192, 607898881, 838529741, 907090878, 632877562, 678638852, 749258866, 949661738, 784641190,
				815740520, 689809286, 711327114, 658017649, 636727234, 871088534, 964608547, 867960437, 964911023,
				642411618, 868318236, 793328473, 849540177, 960039699, 998262224, 775720601, 634685437, 743766982,
				826321850, 846671921, 712570181, 676890302, 814283264, 958273130, 899003369, 909973864, 921987721,
				978601888, 633027021, 896400011, 725078407, 662183572, 629843174, 617774786, 695823011 });
		System.out.println(c);

	}

}
