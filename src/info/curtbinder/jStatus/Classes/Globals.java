package info.curtbinder.jStatus.Classes;

public class Globals {
	public static final String defaultIP = "10.0.42.40";
	public static final String defaultPort = "2000";
	public static final String WifiActionCommand = "WIFI";
	public static final String ComActionCommand = "COM";
	public static final String errorMessage = "Error";
	public static final String defaultStatusText = "-";

	// Controller commands
	public static final String requestMemoryByte = "/mb";
	public static final String requestMemoryInt = "/mi";
	public static final String requestStatus = "/r99";
	public static final String requestDateTime = "/d";
	public static final String requestVersion = "/v";
	// XML tags
	public static final String xmlStatus = "RA";
	public static final String xmlMemory = "MEM";
	public static final String xmlMemorySingle = "M";
	public static final String xmlDateTime = "D";
	public static final String xmlVersion = "V";

	// App Version Information
	public static final String appTitle = "ReefAngel Status";
	public static final int versionMajor = 1;
	public static final int versionMinor = 0;
	public static final int versionRevision = 0;
	public static final String versionBuild = "alpha 2";
	public static final String copyrightInfo = "Copyright 2011 Curt Binder";
	public static final String url = "http://curtbinder.info/";

	// Icons
	public static final String bannerIconName = "/images/curtbinderlogo.png";
	public static final String appIconName = "/images/Rss-green-64.png";
	public static final String refreshIconName = "/images/refresh-16.png";
	public static final String memoryLocationsIconName = "/images/help-16.png";

	// Credits & Legal Stuff
	public static final String[] creditList = { "Curt Binder", "Dave Molton" };
	public static final String legal = "This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. "
			+ "To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter to Creative Commons, 444 Castro Street, "
			+ "Suite 900, Mountain View, California, 94041, USA.";

	public static final String[] memoryLocationList = {
			"800 - Byte - MH On Hour", "801 - Byte - MH On Minute",
			"802 - Byte - MH Off Hour", "803 - Byte - MH Off Minute",
			"804 - Byte - StdLights On Hour",
			"805 - Byte - StdLights On Minute",
			"806 - Byte - StdLights Off Hour",
			"807 - Byte - StdLights Off Minute",
			"808 - Int  - Wavemaker 1 Timer", "810 - Int  - Wavemaker 2 Timer",
			"812 - Byte - Dosing Pump 1 Timer",
			"813 - Byte - Dosing Pump 2 Timer",
			"814 - Int  - Feeding Mode Timer", "816 - Int  - LCD Timeout",
			"818 - Int  - Overheat Temp", "820 - Byte - Daylight PWM Value",
			"821 - Byte - Actinic PWM Value", "822 - Int  - Heater On Temp",
			"824 - Int  - Heater Off Temp", "826 - Int  - Chiller On Temp",
			"828 - Int  - Chiller Off Temp", "830 - Byte - ATO Timeout",
			"831 - Int  - PH Max value (PH10)",
			"833 - Int  - PH Min value (PH7)", "835 - Byte - MH Delay",
			"836 - Byte - Dosing Pump 1 On Hour",
			"837 - Byte - Dosing Pump 1 On Minute",
			"838 - Byte - Dosing Pump 2 On Hour",
			"839 - Byte - Dosing Pump 2 On Minute",
			"840 - Byte - ATO Hour Interval",
			"841 - Byte - ATO High Hour Interval",
			"842 - Byte - ATO High Timeout",
			"843 - Int  - Dosing Pump 1 Repeat Interval",
			"845 - Int  - Dosing Pump 2 Repeat Interval" };
}
