package info.curtbinder.jStatus.Classes;

public class Log {

	public static void i(String msg) {
		System.out.println(msg);
	}
	public static void i(String format, Object... args){
		System.out.printf(format, args);
	}
}
