package info.curtbinder.jStatus.Classes;

public class Log {

	public static void i(String msg) {
		if (StatusApp.fDisplayMessages)
			System.out.println(msg);
	}

	public static void i(String format, Object... args) {
		if (StatusApp.fDisplayMessages)
			System.out.printf(format, args);
	}

	public static void e(String msg) {
		System.out.println(msg);
	}

	public static void e(String format, Object... args) {
		System.out.printf(format, args);
	}
}
