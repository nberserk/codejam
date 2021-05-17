package codejam.y2013.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;

import codejam.lib.CodejamBase;

public class Treasure extends CodejamBase {
	class Chest{
		int requiredKey;
		int[] keys;
		
		public void print() {
			System.out.println(requiredKey + "|" + Arrays.toString(keys));
		}
	}
	Chest[] mChest;
	
	class State implements Comparable<State>{
		int index ;
		int depth ;
		boolean[] chestOpened;
		int[] keys ;
		State mParent;
		
		public State() {
			
		}
		
		public State(State parent, int chest, int  usedKeyIndex){
			mParent = parent;
			this.chestOpened = Arrays.copyOf(parent.chestOpened, parent.chestOpened.length);
			this.chestOpened[chest] = true;
			
			Chest openingChest = mChest[chest];
			
			int size = parent.keys.length -1 + openingChest.keys.length;
			keys = new int[size];
			index = chest+1;
			depth = parent.depth +1;
			
			for (int i = 0; i < parent.keys.length; i++) {
				if (i<usedKeyIndex) {
					keys[i] = parent.keys[i];
				}else if (i>usedKeyIndex) {
					keys[i-1] = parent.keys[i];
				}
			}
			for (int i = 0; i < openingChest.keys.length; i++) {
				keys[parent.keys.length-1+i] = openingChest.keys[i];				
			}
			
			
		}
		
		public boolean isComplete(){
			for (int i = 0; i < chestOpened.length; i++) {
				if (chestOpened[i]==false) {
					return false;
				}
				
			}
			return true;
		}
		
		public State(int[] currentKeys) {
			keys = currentKeys;
			chestOpened = new boolean[mChest.length];
			for (int i = 0; i < chestOpened.length; i++) {
				chestOpened[i] = false;
			}
			depth = 0;
		}
		@Override
		public String toString() {			
			StringBuilder builder =new StringBuilder();
			builder.append(String.format("depth=%d, keys: %s", depth, Arrays.toString(keys)));
			builder.append(", opened(");
			for (int i = 0; i < chestOpened.length; i++) {
				if (chestOpened[i]) {
					builder.append(i +",");
				}
			}
			return builder.toString();
		}

		public void print(){
			System.out.println(toString());			
		}

		@Override
		public int compareTo(State o) {
			if (this.index > o.index)
				return -1;
			else if (this.index < o.index)
				return 1;
			
			return 0;
		}
	}

	@Override
	public void parseAProblem(BufferedReader reader) {		
		try {
			
			String line = reader.readLine();
			String[] values = line.split(" ");
			
			int keyCount = Integer.valueOf(values[0]);
			int chestCount = Integer.valueOf(values[1]);
			mChest = new Chest[chestCount];
			
			values = reader.readLine().split(" ");			
			int[] currentKeys = new int[keyCount];
			for (int i = 0; i < keyCount; i++) {
				currentKeys[i] = Integer.valueOf(values[i]);
			}
			
			for (int i = 0; i < chestCount; i++) {
				values = reader.readLine().split(" ");
				Chest chest = new Chest();
				chest.requiredKey = Integer.valueOf(values[0]);
				int containingKeyCount = Integer.valueOf(values[1]);
				
				chest.keys = new int[containingKeyCount];
				for (int j = 0; j < containingKeyCount; j++) {
					chest.keys[j] = Integer.valueOf(values[2+j]);
				}
			
				mChest[i] = chest;
				mChest[i].print();				
			}
			print("keys" + Arrays.toString(currentKeys));
			
			TreeSet<State> opened = new TreeSet<State>(); 
			opened.add(new State(currentKeys));
			State currentState = null;
			State sol=null;
			while(!opened.isEmpty()){
				
								
				currentState = opened.pollFirst();
//				for (State state : opened) {
//					state.print();
//				}
				
				if (currentState.isComplete()) {
					sol = currentState;
					break;
				}else{
					for (int i = 0; i < currentState.chestOpened.length; i++) {
						if (currentState.chestOpened[i]==true) {
							continue;
						}
						
						HashSet<Integer> processedKey = new HashSet<Integer>();
						for (int j = 0; j < currentState.keys.length; j++) {
							if (processedKey.contains(currentState.keys[j])) {
								continue;
							}
							
							if (currentState.keys[j] == mChest[i].requiredKey) {
								State newState = new State(currentState, i, j);
								if (newState.keys.length==0 && newState.isComplete()==false) {									
								}else{
									//newState.print();			
									opened.add(newState); 							
								}
								
								
							}
							processedKey.add(currentState.keys[j]);
						}
						
					}		
				} // isComplete				
							
			}
			
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> seq = new ArrayList<Integer>(100);
			if (sol!=null) {
				State cur = sol;
				do {
					if (cur.index ==0) {
						break;
					}
					seq.add(cur.index);
					cur = cur.mParent;
				} while (cur.mParent!=null);
				
				Collections.reverse(seq);			
				for (Integer in : seq) {
					sb.append(in + " ");
				}		
			}else{
				sb.append("IMPOSSIBLE");
			}			
			
			print(sb.toString());
			
			writeSolution(sb.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Treasure treasure = new Treasure();
		//String sol = tic.doSolve(new char[][] {{'X', 'O', 'X', 'T'}, {'X', 'X', 'O', 'O'}, {'O', 'X', 'O', 'X'}, {'X', 'X', 'O', 'O'} });
//		String sol = tic.doSolve(new char[][] {{'X', 'X', 'X', 'O'}, {'.', '.', 'O', '.'}, {'.', 'O', '.', '.'}, {'T', '.', '.', '.'} });
//		print(sol);
//		tic.solve("./src/codejam2013/qualification/A-small-attempt3.in", "./src/codejam2013/qualification/A-small.out");
		treasure.load("./src/codejam2013/qualification/D-small-practice.in", "./src/codejam2013/qualification/D-small-practice.out");
	}

}
