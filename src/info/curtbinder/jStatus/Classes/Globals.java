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
	public static final String versionBuild = "beta 1";
	public static final String copyrightInfo = "Copyright 2011 Curt Binder";
	public static final String url = "http://curtbinder.info/";

	// Icons
	public static final String bannerIconName = "/images/cb_h_banner-medium.png";
	public static final String appIconName = "/images/Rss-green-64.png";
	public static final String refreshIconName = "/images/refresh-16.png";
	public static final String memoryLocationsIconName = "/images/help-16.png";

	// Credits & Legal Stuff
	public static final String[] creditList =
			{ "Curt Binder", "Dave Molton", "Roberto Imai" };
	public static final String legal =
			"This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. "
					+ "To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter to Creative Commons, 444 Castro Street, "
					+ "Suite 900, Mountain View, California, 94041, USA.";

}
