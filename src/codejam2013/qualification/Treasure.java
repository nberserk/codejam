package codejam2013.qualification;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

import javax.management.openmbean.OpenDataException;

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
	
	class State{
		int index ;
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
			
			print();			
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
		}
		@Override
		public String toString() {			
			return String.format("keys: %s, chest: %s ", Arrays.toString(keys), Arrays.toString(chestOpened));
		}

		public void print(){
			System.out.println(toString());			
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
			}
			
			ArrayList<State> opened = new ArrayList<State>(); 
			opened.add(new State(currentKeys));
			State currentState = null;
			State sol=null;
			while(!opened.isEmpty()){
				
				currentState = opened.get(0);
				opened.remove(0);
				if (currentState.isComplete()) {
					sol = currentState;
					break;
				}else{
					for (int i = 0; i < currentState.chestOpened.length; i++) {
						if (currentState.chestOpened[i]==true) {
							continue;
						}
						
						for (int j = 0; j < currentState.keys.length; j++) {
							if (currentState.keys[j] == mChest[i].requiredKey) {
								State newState = new State(currentState, i, j);
								if (newState.keys.length==0 && newState.isComplete()==false) {									
								}else{
									opened.add(newState);
								}
								
								
							}
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
		treasure.solve("./src/codejam2013/qualification/D-small-attempt0.in", "./src/codejam2013/qualification/D-small-attempt0.out");
	}

}
