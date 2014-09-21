package crackcode;

public class CheckUtil {

	static void check(int expected, int actual) {
		if (expected != actual) {
			System.out.println(String.format("expected:%d, actual:%d",
					expected, actual));
		}
	}

	static void check(String expected, String actual) {
		if (!expected.equals(actual)) {
			System.out.println(String.format("expected:%s, actual:%s",
					expected, actual));
		}
	}

}
