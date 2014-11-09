package codejam.lib;

import java.util.Arrays;

public class CheckUtil {

	public static void checkTrue(boolean b) {
		if (b == false) {
			System.out.println(String.format("expected:true, actual:%b", b));
		}
	}

	public static void check(int expected, int actual) {
		if (expected != actual) {
			System.out.println(String.format("expected:%d, actual:%d",
					expected, actual));
		}
	}

	public static void check(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println(String.format("expected:%b, actual:%b",
					expected, actual));
		}
	}

	public static void check(String expected, String actual) {
		if (!expected.equals(actual)) {
			System.out.println(String.format("expected:%s, actual:%s",
					expected, actual));
		}
	}

	public static void check(char[] expected, char[] actual) {
		if( expected.length != actual.length){
			System.out.println(String.format("expected:%s, actual:%s",		
					Arrays.toString(expected), Arrays.toString(actual)));
			return;
		}
		for (int i = 0; i < actual.length; i++) {
			if (expected[i] != actual[i]) {
				System.out.println(String.format("expected:%s, actual:%s",
						Arrays.toString(expected), Arrays.toString(actual)));
				return;
			}
		}
	}

	public static void check(double expected, double actual) {
		if (expected != actual) {
			System.out.println(String.format("expected:%f, actual:%f",
					expected, actual));
		}

	}

}
