package crackcode.design;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

public class BrowserHistory {
	Deque<String> mQueue = new LinkedList<String>();
	Map<String, Iterator<String>> mMap = new HashMap<String, Iterator<String>>();

	public void visit(String url){
		//
		if (mMap.containsKey(url)) {
			mQueue.remove(url);
			mMap.remove(url);
		}
		mQueue.addFirst(url);
		mMap.put(url, mQueue.iterator());
		if (mQueue.size() > 4) {
			mQueue.removeLast();
		}
	}

	@Override
	public String toString() {
		return mQueue.toString();
	}

	public static void main(String[] args) {
		String[] ss = {"A", "G", "C", "D", "A", "Z"};
		BrowserHistory h = new BrowserHistory();
		for (String s : ss) {
			h.visit(s);
			System.out.println(h);
		}
	}

}
