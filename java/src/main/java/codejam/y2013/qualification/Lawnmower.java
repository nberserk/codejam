package codejam.y2013.qualification;

import codejam.lib.CodejamBase;

import java.io.BufferedReader;
import java.io.IOException;

public class Lawnmower extends CodejamBase {

	@Override
	public void parseAProblem(BufferedReader reader) {
		
		
		try {
			char[][] array = new char[4][4];
			for (int i = 0; i < 4; i++) {
				String line = reader.readLine();
				//String[] values = line.split(" ");
				for (int j = 0; j < line.length(); j++) {
					array[i][j] = line.charAt(j);
				}				
			}
			
			String sol = doSolve(array);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 4; i++) {			
				sb.append(String.format("%c%c%c%c\n", array[i][0], array[i][1], array[i][2], array[i][3]));			
			}
			sb.append(sol+"\n");			
			print(sb.toString());
			
			writeSolution(sol);
			
			reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private String doSolve(char[][] array) {

		String xWon= "X won";
		String oWon = "O won";
		String draw = "Draw";
		String yet= "Game has not completed";
		
		
		
		int x=0, o=0, t=0;
		// row check
		for (int i = 0; i < 4; i++) {
			x = o=t=0;
			for (int j = 0; j < 4; j++) {
		
				char value = array[i][j];
				if (value =='X'){
					x++;
				}else if (value == 'O') {
					o++;
				}else if (value == 'T') {
					t++;
				}
			}
			
			if (x==4 || (x==3 && t==1)) {
				return xWon;
			}
			if (o ==4 || (o==3 && t==1)) {
				return oWon;
			}
		}		
		
		// column check
		for (int i = 0; i < 4; i++) {
			x = o=t=0;
			for (int j = 0; j < 4; j++) {
				char value = array[j][i];
				if (value =='X'){
					x++;
				}else if (value == 'O') {
					o++;
				}else if (value == 'T') {
					t++;
				}
			}
			
			if (x==4 || (x==3 && t==1)) {
				return xWon;
			}
			if (o ==4 || (o==3 && t==1)) {
				return oWon;
			}
		}	
		
		// diagonal 
		x = o=t=0;
		for (int i = 0; i < 4; i++) {
			char value = array[i][i];
			if (value =='X'){
				x++;
			}else if (value == 'O') {
				o++;
			}else if (value == 'T') {
				t++;
			}
		}
		if (x==4 || (x==3 && t==1)) {
			return xWon;
		}
		if (o ==4 || (o==3 && t==1)) {
			return oWon;
		}
		
		x = o=t=0;
		for (int i = 0; i <4; i++) {
			int col = 3-i;
			char value = array[i][col];
			if (value =='X'){
				x++;
			}else if (value == 'O') {
				o++;
			}else if (value == 'T') {
				t++;
			}
		}
		if (x==4 || (x==3 && t==1)) {
			return xWon;
		}
		if (o ==4 || (o==3 && t==1)) {
			return oWon;
		}
		
		// yet check
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				char value = array[i][j];
				if (value =='.'){
					return yet;
				}
			}			
		}	
		
		return draw;		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		XOXT
//		XXOO
//		OXOX
//		XXOO
		Lawnmower tic = new Lawnmower();
		//String sol = tic.doSolve(new char[][] {{'X', 'O', 'X', 'T'}, {'X', 'X', 'O', 'O'}, {'O', 'X', 'O', 'X'}, {'X', 'X', 'O', 'O'} });
//		String sol = tic.doSolve(new char[][] {{'X', 'X', 'X', 'O'}, {'.', '.', 'O', '.'}, {'.', 'O', '.', '.'}, {'T', '.', '.', '.'} });
//		print(sol);
//		tic.solve("./src/codejam2013/qualification/A-small-attempt3.in", "./src/codejam2013/qualification/A-small.out");
		tic.load("./src/codejam2013/qualification/C-small-attempt0.in", "./src/codejam2013/qualification/C-large.out");
	}

}
