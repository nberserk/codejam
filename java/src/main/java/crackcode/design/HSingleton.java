package crackcode.design;

public class HSingleton {

	static HSingleton sSingleton;

	HSingleton() {
		System.out.println("HSingleton created");
	}

	static class SingletonHolder {
		static HSingleton singleton = new HSingleton();
	}

	public static HSingleton getInstance() {
		return SingletonHolder.singleton;
	}

	public void log(String msg) {
		System.out.println(msg);
	}

	public static void main(String[] args) {
		HSingleton.getInstance().log("oops");
	}

}
