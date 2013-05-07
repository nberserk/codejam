package topcoder.graph.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

import codejam.lib.CodejamBase;

/*
 * 
 * 
 */
public class Region extends CodejamBase{
	
	static class Point{
		int x;
		int y;
		public Point(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) {
		Region ocean = new Region();		
		String dir = "./input/codejam2013/";
		ocean.solve(new int[][] {{1,2,3,3}, {2,1,4,4}});
//		ocean.solve(new int[][] {{1,2,3,3}});
		// ocean.solve(dir + "/C-small-practice.in", dir + "C-small.out");
	}

	private boolean[][] mVisited;
	private boolean[][] mBlocked;
	private int mMinX=Integer.MAX_VALUE;
	private int mMinY=Integer.MAX_VALUE;
	int mMaxX = -1;
	int mMaxY = -1;
	private int mCurrentRectIndex;
	
	public void solve(int[][] rect){
				
		
		for (int i = 0; i < rect.length; i++) {
			mMinX = Math.min(mMinX, rect[i][0]);
			mMinY = Math.min(mMinY, rect[i][1]);
			mMaxX = Math.max(mMaxX, rect[i][2]);
			mMaxY = Math.max(mMaxY, rect[i][3]);
		}
		
		print(String.format("%d,%d -> %d,%d", mMinX,mMinY,mMaxX,mMaxY));
		mVisited = new boolean[mMaxX][mMaxY];
		mBlocked = new boolean[mMaxX*2+1][mMaxY*2+1];
				
		for (int i = 0; i < rect.length; i++) {
			for (int x = rect[i][0]; x < rect[i][2]; x++) {
				mBlocked[x*2+1][rect[i][1]*2] = true;
				mBlocked[x*2+1][rect[i][3]*2] = true;
				
			}
			for (int y = rect[i][1]; y < rect[i][3]; y++) {
				mBlocked[rect[i][0]*2][y*2+1] = true;
				mBlocked[rect[i][2]*2][y*2+1] = true;
			}
		}
		
		ArrayList<Integer> areas = new ArrayList<Integer>();
		
		for (int i = 0; i < rect.length; i++) {
			mCurrentRectIndex = i;
			for (int x = rect[i][0]; x < rect[i][2]; x++) {
				for (int y = rect[i][1]; y < rect[i][3]; y++) {
					if (mVisited[x][y]==false) {
						areas.add(expand(x,y, rect[i]));	
					}					
				}
			}
		}
		
		int[] raw = new int[areas.size()];
		for (int i = 0; i < raw.length; i++) {
			raw[i] = areas.get(i);
		}
		print(String.format("#ares=%d, %s", raw.length, Arrays.toString(raw)));
		print("\n");
		
		
	}

	private int expand(int x, int y, int[] curRect) {
		int area = 0;
		Stack<Point> stack = new Stack<Point>();
		stack.push(new Point(x,y));
		
		while (!stack.isEmpty()) {
			Point point = stack.pop();
			if (point.x < curRect[0] || point.x >= curRect[2] ) 
				continue;
			if (point.y < curRect[1] || point.y >= curRect[3] ) 
				continue;
			if (mVisited[point.x][point.y]) {
				continue;
			}
			
			mVisited[point.x][point.y] = true;
			area++;
			
			if (!mBlocked[point.x*2+1][point.y*2]) {
				stack.push(new Point(point.x, point.y-1));
			}
			if (!mBlocked[point.x*2+2][point.y*2+1]) {
				stack.push(new Point(point.x+1, point.y));
			}
			if (!mBlocked[point.x*2+1][point.y*2+2]) {
				stack.push(new Point(point.x, point.y+1));
			}
			if (!mBlocked[point.x*2][point.y*2+1]) {
				stack.push(new Point(point.x-1, point.y));
			}
		}
		
		return area;
	}

	
}
