package crackcode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class MemoryLimit {

    public static void main(String[] args) {
        int n = (1 << 31) | (1 << 30);
        String s = Integer.toHexString(n);
        System.out.println(s);
        System.out.println(Integer.toBinaryString(n));

		ArrayList<Integer> a = new ArrayList<Integer>();
		LinkedList<Integer> list = new LinkedList<Integer>();



    }

    void FileRead() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("test.txt"));
            while (br.readLine() != null) {

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
