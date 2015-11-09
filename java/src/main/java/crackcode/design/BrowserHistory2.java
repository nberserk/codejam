package crackcode.design;

import java.util.TreeSet;

import codejam.lib.CheckUtil;

public class BrowserHistory2 {
	TreeSet<History> mTree = new TreeSet<History>();

	void addHistory(History history) {
		for (History h : mTree) {
			if (h.getUrl().equals(history.getUrl())) {
				h.timestamp = history.timestamp;
				return;
			}
		}

		if (mTree.size() > 4) {
			mTree.remove(mTree.last());
		}
		mTree.add(history);
	}

	@Override
	public String toString() {
		return mTree.toString();
	}

	static class History implements Comparable<History> {
		private String url;
		long timestamp;

		History(String u, int t) {
			url = u;
			timestamp = t;
		}

		public String getUrl() {
			return url;
		}

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof History)) {
				return false;
			}
			History o = (History) obj;
			return url.equals(o.url);
		}

		@Override
		public int hashCode() {
			return url.hashCode();
		}

		@Override
		public int compareTo(History o) {
			return (int) -(timestamp - o.timestamp);
		}

		@Override
		public String toString() {
			return url + ":" + timestamp;
		}

	}

	public static void main(String[] args) {
		BrowserHistory2 h = new BrowserHistory2();
		h.addHistory(new History("A", 0));
		h.addHistory(new History("A", 1));

		System.out.println(h);
		CheckUtil.check("A", h.mTree.first().getUrl());
		CheckUtil.check(1, h.mTree.size());

		h.addHistory(new History("C", 3));
		h.addHistory(new History("G", 4));
		h.addHistory(new History("Y", 5));
		h.addHistory(new History("R", 6));
		h.addHistory(new History("Z", 7));
		System.out.println(h);

	}

}
