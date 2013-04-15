package info.curtbinder.jStatus.Classes;

public class Globals {
	public static final String comWifiLabel = "Wifi";
	public static final String comCOMLabel = "COM";
	public static final String WifiActionCommand = "WIFI";
	public static final String ComActionCommand = "COM";
	public static final String defaultStatusText = "-";
	public static final String responseCRLF = "\r\n\r\n";
	public static final String urlCOM = "GET ";
	public static final String urlWIFI = "http://";
	public static final String labelOn = " ON  ";
	public static final String labelOff = " OFF ";
	public static final String labelAuto = "AUTO";
	public static final String labelVersion = "Version";
	public static final String labelInstalledVersion = "Installed Version:";
	public static final String labelUnknown = "Unknown";
	public static final String labelGet = "Get";
	public static final String labelModes = "Modes";
	public static final String labelModesDescription = "Switch modes on the controller";
	public static final String labelFeedingMode = "Feeding Mode";
	public static final String labelWaterChange = "Water Change";
	public static final String labelExit = "Exit";
	public static final String labelClearATO = "Clear ATO";
	public static final String labelClearOverheat = "Clear Overheat";
	public static final String labelDateTime = "Date & Time";
	public static final String labelCurrentController = "Current Controller:";
	public static final String labelGetTime = "Get Time";
	public static final String labelSetCurrentTime = "Set Current Time";
	public static final String labelPre10Memory = "Use pre v1.0 memory locations";
	public static final String labelDisableNotifications = "Disable status popup notifications";

	// Command line flags
	public static final String[] cmdVerbose = { "-v", "verbose", "--verbose" };

	// Error messages
	public static final String errorMessage = "Error";
	public static final String errorSendingCommand = "Error sending command";
	public static final String errorParser = "Error with parser";
	public static final String errorConnectTimeout = "Timeout connecting";
	public static final String errorIO = "IO Exception";
	public static final String errorCancelled = "Cancelled";
	public static final String errorFeedMode = "Error entering Feeding Mode";
	public static final String errorWaterChange = "Error entering Water Change Mode";
	public static final String errorExit = "Error Exiting Mode";
	public static final String errorClearATO = "Error clearing ATO";
	public static final String errorClearOverheat = "Error clearing Overheat";
	public static final String errorPortInUse = "Port currently in use";
	public static final String errorException = "General exception";
	public static final String errorUnknown = "Unknown error communicating to controller";
	public static final String errorNoSuchPort = "No such port: ";

	// Preferences
	public static final String keyHost = "host";
	public static final String defaultHost = "10.0.42.40";
	public static final String keyPort = "port";
	public static final String defaultPort = "2000";
	public static final String keyComType = "comtype";
	public static final String defaultComType = WifiActionCommand;
	public static final String keyPre10Memory = "pre10memory";
	public static final String keyDisableNotifications = "disablenotifications";
	public static final String defaultComPortLinux = "/dev/ttyUSB0";
	public static final String defaultComPortWin = "COM1";
	public static final String defaultComPortMac = "";
	public static final String keyComPort = "comport";

	// Controller commands
	public static final String requestMemoryByte = "/mb";
	public static final String requestMemoryInt = "/mi";
	public static final String requestStatus = "/r99";
	public static final String requestDateTime = "/d";
	public static final String requestVersion = "/v";
	public static final String requestFeedingMode = "/mf";
	public static final String requestWaterMode = "/mw";
	public static final String requestExitMode = "/bp";
	public static final String requestClearATO = "/mt";
	public static final String requestClearOverheat = "/mo";
	public static final String requestRelay = "/r";
	public static final String requestNone = "";
	public static final String requestReefAngel = "ra";
	// XML tags
	public static final String xmlStatus = "RA";
	public static final String xmlMemory = "MEM";
	public static final String xmlMemorySingle = "M";
	public static final String xmlDateTime = "D";
	public static final String xmlVersion = "V";
	public static final String xmlMode = "MODE";
	public static final String xmlT1 = "T1";
	public static final String xmlT2 = "T2";
	public static final String xmlT3 = "T3";
	public static final String xmlPH = "PH";
	public static final String xmlATOLow = "ATOLOW";
	public static final String xmlATOHigh = "ATOHIGH";
	public static final String xmlSalinity = "SAL";
	public static final String xmlPWMDaylight = "PWMD";
	public static final String xmlPWMActinic = "PWMA";
	public static final String xmlRelay = "R";
	public static final String xmlRelayMaskOn = "RON";
	public static final String xmlRelayMaskOff = "ROFF";
	public static final String xmlLogDate = "LOGDATE";
	public static final String xmlHour = "HR";
	public static final String xmlMinute = "MIN";
	public static final String xmlMonth = "MON";
	public static final String xmlDay = "DAY";
	public static final String xmlYear = "YR";

	// App Version Information
	public static final String appTitle = "ReefAngel Status";
	public static final String appName = "RA Status";
	public static final String appDescription = "Monitor the status of the controller";
	public static final int versionMajor = 1;
	public static final int versionMinor = 1;
	public static final int versionRevision = 0;
	public static final String versionBuild = "";
	public static final String copyrightInfo = "Copyright 2011-13 Curt Binder";
	public static final String url = "http://curtbinder.info/";
	public static final String aboutTitle = "About ";
	public static final String prefsTitle = "Preferences";
	public static final String memoryDescription = "Location - Type - Reference";

	// Icons
	public static final String bannerIconName = "/images/cb_h_banner-medium.png";
	public static final String appIconName = "/images/ra-logo-64.png";
	public static final String refreshIconName = "/images/refresh-16.png";
	public static final String memoryLocationsIconName = "/images/help-16.png";
	public static final String greenIconName = "/images/greenbutton.png";

	// Menus
	public static final String menuFileText = "File";
	public static final String menuFileCloseText = "Close";
	public static final String menuEditText = "Edit";
	public static final String menuEditPrefsText = "Preferences";
	public static final String menuHelpText = "Help";
	public static final String menuHelpMemoryText = "Memory Locations";
	public static final String menuHelpAboutText = "About";
	public static final String btnCloseText = "Close";
	public static final String btnCreditsText = "Credits";
	public static final String btnLicenseText = "License";
	public static final String btnSaveText = "Save";

	// Credits & Legal Stuff
	public static final String contributorsText = "Contributors:";
	public static final String[] creditList = { "Curt Binder", "Dave Molton",
			"Roberto Imai" };
	public static final String legalText = "Legal Stuff:";
	public static final String legal = "This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License. "
			+ "To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send a letter to Creative Commons, 444 Castro Street, "
			+ "Suite 900, Mountain View, California, 94041, USA.";

}
