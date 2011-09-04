package info.curtbinder.jStatus.Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Status {

	private boolean writeValue = false;
	private String sendCmdErrorMessage = "";

	// TODO add thread for status & date/time tabs

	public Status () {
		// TODO Auto-generated constructor stub
	}

	private String SendCommandToController ( URL u ) {
		String s = "";
		try {
			BufferedReader bin = new BufferedReader(new InputStreamReader(u
					.openStream()));
			String line;
			while ( (line = bin.readLine()) != null ) {
				s += line;
			}
		} catch (Exception e) {
			sendCmdErrorMessage = e.getMessage();
			if ( sendCmdErrorMessage.isEmpty() ) {
				sendCmdErrorMessage = "Unknown error communicating to controller";
			}
			s = Globals.errorMessage;
		}
		return s;
	}

	public void SendReadWriteCommand ( boolean Write ) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					"COM not implemented yet", "Comm Type",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String cmd = StatusApp.statusUI.tabMemory.getMemType();
		String loc = StatusApp.statusUI.tabMemory.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					"Must specify a memory location",
					"Missing Memory Location", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		cmd += loc;
		writeValue = Write;
		if ( Write ) {
			String value = StatusApp.statusUI.tabMemory.getWriteValue();
			if ( value.isEmpty() ) {
				JOptionPane.showMessageDialog(StatusApp.statusUI,
						"Must specify a value to write", "Missing Value",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cmd += "," + value;
		}
		final String s = url + cmd;
		System.out.print("Command: '" + s + "'\n");

		// blank out the status boxes
		if ( Write ) {
			StatusApp.statusUI.tabMemory.setWriteStatus("");
		} else {
			StatusApp.statusUI.tabMemory.setReadValue("");
		}

		// TODO figure out how to interrupt the thread if it's stuck waiting
		new Thread() {
			public void run ( ) {
				System.out.println("Started thread read/write");
				threadReadWriteMemory(s);
				System.out.println("Finished thread read/write");
			}
		}.start();
	}

	private void threadReadWriteMemory ( String url ) {
		// Disable the read/write buttons
		StatusApp.statusUI.tabMemory.enableReadWriteButtons(false);
		// send command to controller
		String res = new String("");
		long start = System.currentTimeMillis();
		try {
			res = SendCommandToController(new URL(url));
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error with URL",
					"Read Error", JOptionPane.INFORMATION_MESSAGE);
		}
		long end = System.currentTimeMillis();
		System.out.printf("Took %d ms to send command\n", end - start);

		// Enable the read/write buttons
		StatusApp.statusUI.tabMemory.enableReadWriteButtons(true);

		// check if there was an error
		if ( res.equals(Globals.errorMessage) ) {
			// encountered an error, display a popup message
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					sendCmdErrorMessage, "Error sending command",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			// no error
			// parse the returned xml string
			XMLHandler h = new XMLHandler();
			if ( !parseXML(h, res) ) {
				// TODO consider displaying a popup window
				System.out.println("Error with parser");
				return;
			}
			if ( writeValue ) {
				StatusApp.statusUI.tabMemory.setWriteStatus(h.getMemoryResponse());
			} else {
				StatusApp.statusUI.tabMemory.setReadValue(h.getMemoryResponse());
			}
		}
	}

	public void SendRefreshCommand ( ) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					"COM not implemented yet", "Comm Type",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		final String s = url + Globals.requestStatus;
		System.out.println("Refresh: '" + s + "'");
		new Thread() {
			public void run ( ) {
				System.out.println("Started thread refresh");
				threadRefreshStatus(s);
				System.out.println("Finished thread refresh");
			}
		}.start();
	}

	private void threadRefreshStatus ( String url ) {
		// Disable the refresh button
		StatusApp.statusUI.tabStatus.disableRefreshButton();
		// send command to controller
		String res = "";
		long start = System.currentTimeMillis();
		try {
			res = SendCommandToController(new URL(url));
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error with URL",
					"Read Error", JOptionPane.INFORMATION_MESSAGE);
		}
		long end = System.currentTimeMillis();
		System.out.printf("Took %d ms to send command\n", end - start);

		// Enable the refresh button
		StatusApp.statusUI.tabStatus.enableRefreshButton();

		// check if there was an error
		if ( res.equals(Globals.errorMessage) ) {
			// encountered an error, display a popup message
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					sendCmdErrorMessage, "Error sending command",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			// no error
			// parse the returned xml string
			// set values in tabStatus
			// call updateDisplay
			XMLHandler h = new XMLHandler();
			if ( !parseXML(h, res) ) {
				// TODO consider displaying a popup window
				System.out.println("Error with parser");
				return;
			}
			StatusApp.statusUI.tabStatus.setControllerInformation(h.getRa());
		}
	}

	private boolean parseXML ( XMLHandler h, String res ) {
		XMLReader xr;
		long start, end;
		System.out.println("Parsing");
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}
		xr.setContentHandler(h);
		xr.setErrorHandler(h);
		start = System.currentTimeMillis();
		try {
			xr.parse(new InputSource(new StringReader(res)));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SAXException e) {
			e.printStackTrace();
			return false;
		}
		end = System.currentTimeMillis();
		System.out.printf("Took %d ms to parse\n", end - start);
		System.out.println("Parsed");
		return true;
	}
}
