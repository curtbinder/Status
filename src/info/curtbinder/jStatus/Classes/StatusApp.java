package info.curtbinder.jStatus.Classes;

import java.awt.EventQueue;
import java.util.prefs.Preferences;

import info.curtbinder.jStatus.UI.*;

public class StatusApp {

	public static MainFrame statusUI;
	public static Status statusClass;
	public static boolean fUsePre10Memory;
	public static boolean fDisableNotifications;
	private static String host;
	private static String port;
	private static String comtype;

	public StatusApp () {
	}

	public static void main ( String[] args ) {
		statusClass = new Status();
		// read any saved/stored default values
		SerialConn.listPorts();
		initPrefs();

		EventQueue.invokeLater( new Runnable() {
			public void run ( ) {
				try {
					statusUI = new MainFrame( statusClass );
					statusUI.setDefaults();

					// update any stored/saved values for the IP, PORT or COMM
					// method
					statusUI.setHost( host );
					statusUI.setPort( port );
					statusUI.setComType( comtype );
					statusUI.setVisible( true );
				} catch ( Exception e ) {
					e.printStackTrace();
				}
			}
		} );
	}

	private static void initPrefs ( ) {
		// initialize the preferences
		Preferences userprefs =
				Preferences.userNodeForPackage( StatusApp.class );
		host = userprefs.get( Globals.keyHost, Globals.defaultHost );
		port = userprefs.get( Globals.keyPort, Globals.defaultPort );
		comtype = userprefs.get( Globals.keyComType, Globals.defaultComType );
		fUsePre10Memory = userprefs.getBoolean( Globals.keyPre10Memory, false );
		fDisableNotifications =
				userprefs.getBoolean( Globals.keyDisableNotifications, true );
		Log.i("Load: '" + comtype + "'");
	}

}
