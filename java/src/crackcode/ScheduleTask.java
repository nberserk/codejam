package crackcode;

import codejam.lib.CheckUtil;

public class ScheduleTask {

	public static void main(String[] args) {

		int[] c = { 8, 16, 8, 32 };
		int[] t = { 18, 4, 8, 4, 6, 6, 8, 8 };
		CheckUtil.check(true, schedule(c, t, 0));

		int[] c2 = { 1, 3 };
		int[] t2 = { 4 };
		CheckUtil.check(false, schedule(c2, t2, 0));

	}

	static boolean schedule(int[] capacity, int[] task, int taskIdx) {
		if (taskIdx >= task.length) {
			for (int i = 0; i < capacity.length; i++) {
				System.out.print(capacity[i] + " ");
			}
			System.out.println(": found a way");
			return true;
		}

		for (int i = 0; i < capacity.length; i++) {
			if (task[taskIdx] <= capacity[i]) {
				capacity[i] -= task[taskIdx];
				if (schedule(capacity, task, taskIdx + 1))
					return true;
				capacity[i] += task[taskIdx];
			}
		}

		return false;
	}
}
