package info.curtbinder.jStatus.Classes;

public class Memory {
	public static final byte locationBYTE = 0;
	public static final byte locationINT = 1;

	private int location;
	private byte type;
	private String description;

	public Memory ( int location, byte type, String description ) {
		this.location = location;
		this.type = type;
		this.description = description;
	}

	public int getLocation ( ) {
		return location;
	}

	public byte getType ( ) {
		return type;
	}

	public String toString ( ) {
		return String.format( "%s - %-4s - %s", location,
								(type == locationBYTE) ? "Byte" : "Int",
								description );
	}
}
