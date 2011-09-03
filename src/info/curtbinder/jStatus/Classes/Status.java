package info.curtbinder.jStatus.Classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;

public class Status {

	private boolean writeValue = false;
	private String sendCmdErrorMessage = new String("");
	// TODO add thread for status & date/time tabs
	
	public Status() {
		// TODO Auto-generated constructor stub
	}

	private String SendCommandToController(URL u) {
		String s = "";
		try {
			BufferedReader bin = new BufferedReader( new InputStreamReader(u.openStream()) );
			String line;
			while ( (line = bin.readLine()) != null )
				s += line;
			
			// add in XML parsing here
			// create new handler class
		} catch (Exception e) {
			sendCmdErrorMessage = e.getMessage();
			if ( sendCmdErrorMessage.isEmpty() ) {
				sendCmdErrorMessage = "Unknown error communicating to controller";
			}
			s = Globals.errorMessage;
		}
		return s;
	}
	
	public void SendReadWriteCommand(boolean Write) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, 
					"COM not implemented yet", 
					"Comm Type", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String cmd = StatusApp.statusUI.getMemType();
		String loc = StatusApp.statusUI.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, 
					"Must specify a memory location", 
					"Missing Memory Location", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		cmd += loc;
		writeValue = Write;
		if ( Write ) {
			String value = StatusApp.statusUI.getWriteValue();
			if ( value.isEmpty() ) {
				JOptionPane.showMessageDialog(StatusApp.statusUI, 
						"Must specify a value to write", 
						"Missing Value", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cmd += "," + value;
		}
		final String s = url + cmd;
		System.out.print("Command: '" + s + "'\n");
		
		// blank out the status boxes
		if ( Write ) {
			StatusApp.statusUI.setWriteStatus("");
		} else {
			StatusApp.statusUI.setReadValue("");
		}
		
		// TODO figure out how to interrupt the thread if it's stuck waiting
		new Thread() {
			public void run() {
				System.out.println("Started thread read/write");
				threadReadWriteMemory(s); 
				System.out.println("Finished thread read/write");
			}
		}.start();
	}
	
	private void threadReadWriteMemory(String url) {
		// Disable the read/write buttons
		StatusApp.statusUI.enableReadWriteButtons(false);
		// send command to controller
		String res = new String("");
		try {
			res = SendCommandToController(new URL(url));
		} catch (MalformedURLException e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, 
					"Error with URL", 
					"Read Error", JOptionPane.INFORMATION_MESSAGE);
		}

		if ( writeValue ) {
			StatusApp.statusUI.setWriteStatus(res);
		} else {
			StatusApp.statusUI.setReadValue(res);
		}
		// Enable the read/write buttons
		StatusApp.statusUI.enableReadWriteButtons(true);
		
		// check if there was an error
		if ( res.equals(Globals.errorMessage)) {
			// encountered an error, display a popup message
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					sendCmdErrorMessage,
					"Error sending command", JOptionPane.INFORMATION_MESSAGE);
				
		}
	}
}
