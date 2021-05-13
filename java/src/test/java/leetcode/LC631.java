package leetcode;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by darren on 6/23/2017.
 *
 *
 * Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, you need to implement the following functions:

 Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to W. The Excel form content is represented by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of C starts from 1, and the first column of C starts from 'A'.


 void Set(int row, char column, int val): Change the value at C(row, column) to be val.


 int Get(int row, char column): Return the value at C(row, column).


 int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), where the value should be the sum of cells represented by numbers. This function return the sum result at C(row, column). This sum formula should exist until this cell is overlapped by another value or another sum formula.

 numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format : ColRow. For example, "F7" represents the cell at (7, F).

 If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell.


 Example 1:
 Excel(3,"C");
 // construct a 3*3 2D array with all zero.
 //   A B C
 // 1 0 0 0
 // 2 0 0 0
 // 3 0 0 0

 Set(1, "A", 2);
 // set C(1,"A") to be 2.
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 0

 Sum(3, "C", ["A1", "A1:B2"]);
 // set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4.
 //   A B C
 // 1 2 0 0
 // 2 0 0 0
 // 3 0 0 4

 Set(2, "B", 2);
 // set C(2,"B") to be 2. Note C(3, "C") should also be changed.
 //   A B C
 // 1 2 0 0
 // 2 0 2 0
 // 3 0 0 6
 Note:
 You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
 The test cases are using double-quotes to represent a character.
 Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted across multiple test cases. Please see here for more details.


 */
public class LC631 {
    static class Cell{
        int v;
        Set<Cell> dependency = new HashSet<>();
        String[] eq; // sum equation
        String key;

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Cell other = (Cell)obj;
            return key.equals(other.key);
        }

        Cell(String key){
            this.key=key;
        }
    }

    HashMap<String, Cell> cells = new HashMap<>();

    public void init(int H, char W) {
        for (int i = 1; i <= H; i++) {
            for (char j = 'A'; j <= W; j++) {
                String key = toKey(i, j);
                cells.put(key, new Cell(key));
            }
        }
    }

    String toKey(int h, char c){
        return String.format("%c%d", c,h);
    }

    Cell getCell(int row, char col){
        String key = toKey(row, col);
        return cells.get(key);
    }

    Cell getCell(String key){
        return cells.get(key);
    }

    public void set(int row, char col, int v) {

        Cell cell = getCell(row, col);
        cell.v = v;

        if (cell.eq!=null){  // reset cell equation
            for (String range: cell.eq){
                if(range.contains(":")){
                    String[] keys = range.split(":");
                    getCell(keys[0]).dependency.remove(cell);
                    getCell(keys[1]).dependency.remove(cell);
                }else{
                    getCell(range).dependency.remove(cell);
                }
            }
            cell.eq = null;
        }

        // update dependency
        for (Cell c: cell.dependency){
            recalc(c);
        }
    }

    void recalc(Cell cell){
        int sum =0;
        for (String s: cell.eq){
            if(s.contains(":")){
                String[] temp = s.split(":");
                int r = getRow(temp[0]);
                int c = getColumn(temp[0]);
                int r2 = getRow(temp[1]);
                int c2 = getColumn(temp[1]);

                int rfrom = Math.min(r, r2);
                int rto = Math.max(r, r2);

                int cfrom = Math.min(c, c2);
                int cto = Math.max(c, c2);

                for (r=rfrom;r<=rto;r++){
                    for (c=cfrom;c<=cto;c++){
                        sum += getCell(r, (char)c).v;
                    }
                }
            }else{
                sum += getCell(s).v;
            }
        }

        cell.v = sum;
        for (Cell c: cell.dependency){
            recalc(c);
        }
    }

    public int get(int r, char c) {
        return getCell(r,c).v;
    }

    int getRow(String key){
        int r=0;
        for (int i = 1; i < key.length(); i++) {
            r = r*10 + key.charAt(i)-'0';
        }
        return r;
    }

    char getColumn(String key){
        return key.charAt(0);
    }

    public int sum(int row, char col2, String[] strs) {

        Cell cell = getCell(row, col2);
        int sum=0;
        for (String s: strs){
            if(s.contains(":")){
                String[] temp = s.split(":");
                int r = getRow(temp[0]);
                int c = getColumn(temp[0]);
                int r2 = getRow(temp[1]);
                int c2 = getColumn(temp[1]);

                int rfrom = Math.min(r, r2);
                int rto = Math.max(r, r2);

                int cfrom = Math.min(c, c2);
                int cto = Math.max(c, c2);

                for (r=rfrom;r<=rto;r++){
                    for (c=cfrom;c<=cto;c++){
                        Cell ce = getCell(r, (char) c);
                        sum += ce.v;

                        ce.dependency.add(cell);
                    }
                }

            }else{
                sum += getCell(s).v;
                getCell(s).dependency.add(cell);
            }
        }

        cell.eq = strs;
        cell.v = sum;
        return sum;
    }

    @Test
    public void test2(){
        LC631 e = new LC631();
        e.init(3, 'C');
        e.set(1, 'A', 2);
        assertEquals(4, e.sum(3, 'C', new String[]{"A1", "A1:B2"}));
        e.set(2, 'B', 2);

        assertEquals(6, e.get(3, 'C'));
    }

    @Test
    public void test_overlap(){
        LC631 e = new LC631();
        e.init(5, 'E');
        e.set(1, 'A', 1);

        e.sum(2, 'B', new String[]{"A1"});
        e.set(2, 'B', 0);
        assertEquals(0, e.get(2, 'B'));
        e.set(1, 'A', 5);
        assertEquals(0, e.get(2, 'B'));
    }

    @Test
    public void test_complex_dependency(){
        LC631 e = new LC631();
        e.init(5, 'E');
        e.set(1, 'A', 5);
        e.set(1, 'B', 3);
        e.set(1, 'C', 2);
        assertEquals(13, e.sum(1, 'C', new String[]{"A1", "A1:B1"}));
        assertEquals(13, e.get(1, 'C'));
        e.set(1, 'B', 5);
        assertEquals(15, e.get(1, 'C'));
        assertEquals(5, e.sum(1, 'B', new String[]{"A1:A5"}));
        e.set(5, 'A', 10);
        assertEquals(15, e.get(1, 'B'));
        assertEquals(25, e.get(1, 'C'));

        assertEquals(60, e.sum(3, 'C', new String[]{"A1:C1", "A1:A5"}));
        e.set(3, 'A', 3);

        assertEquals(18, e.get(1, 'B'));
        assertEquals(28, e.get(1, 'C'));
        assertEquals(69, e.get(3, 'C'));
        assertEquals(10, e.get(5, 'A'));
    }
}
