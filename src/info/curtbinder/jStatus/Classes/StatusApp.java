package info.curtbinder.jStatus.Classes;

import java.awt.EventQueue;
import java.util.prefs.Preferences;

import info.curtbinder.jStatus.UI.*;

public class StatusApp {

	public static MainFrame statusUI;
	public static Status statusClass;
	public static boolean fUsePre10Memory;
	public static boolean fDisableNotifications;
	public static boolean fDisplayMessages;
	private static String host;
	private static String port;
	private static String comtype;
	private static String comport;

	public StatusApp() {
		fDisplayMessages = false;
	}

	public static void main(String[] args) {
		statusClass = new Status();
		// read any command line arguments
		readParams(args);
		// read any saved/stored default values
		initPrefs();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					statusUI = new MainFrame(statusClass);
					statusUI.setDefaults();

					// update any stored/saved values for the IP, PORT or COMM
					// method
					statusUI.setHost(host);
					statusUI.setPort(port);
					statusUI.setComType(comtype);
					statusUI.setComPort(comport);
					statusUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void initPrefs() {
		// initialize the preferences
		Preferences userprefs = Preferences.userNodeForPackage(StatusApp.class);
		host = userprefs.get(Globals.keyHost, Globals.defaultHost);
		port = userprefs.get(Globals.keyPort, Globals.defaultPort);
		comtype = userprefs.get(Globals.keyComType, Globals.defaultComType);
		comport = userprefs.get(Globals.keyComPort, "");
		fUsePre10Memory = userprefs.getBoolean(Globals.keyPre10Memory, false);
		fDisableNotifications = userprefs.getBoolean(
				Globals.keyDisableNotifications, true);
		Log.i("Load: '" + comtype + "'");
		Log.i("Com Port: " + comport);
	}

	private static void readParams(String[] args) {
		if (args.length > 0) {
			for ( String s : args ) {
				for ( String v : Globals.cmdVerbose ) {
					if ( s.equals(v) ) {
						fDisplayMessages = true;
					}
				}
			}
		}
	}
}
