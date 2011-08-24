package info.curtbinder.jStatus.Classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JOptionPane;

public class Status {

	public Status() {
		// TODO Auto-generated constructor stub
	}

	private String SendCommandToController(URL u) {
		// should put this in it's own thread
		String s = "";
		try {
			BufferedReader bin = new BufferedReader( new InputStreamReader(u.openStream()) );
			String line;
			while ( (line = bin.readLine()) != null )
				s += line;
		} catch (Exception e) {
			s = "Caught exception";
		}
		return s;
	}
	
	public void SendCommand(boolean Write) throws Exception {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "COM not implemented yet", "Comm Type", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String cmd = StatusApp.statusUI.getMemType();
		String loc = StatusApp.statusUI.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Must specify a memory location", "Missing Memory Location", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		cmd += loc;
		if ( Write ) {
			String value = StatusApp.statusUI.getWriteValue();
			if ( value.isEmpty() ) {
				JOptionPane.showMessageDialog(StatusApp.statusUI, "Must specify a value to write", "Missing Value", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			cmd += "," + value;
		}
		String s = url + cmd;
		System.out.print("Command: '" + s + "'\n");
		// launch SendCommandToController in own thread
		String res = SendCommandToController(new URL(s));
		// when thread completes, signal this function to update
		if ( Write ) {
			StatusApp.statusUI.setWriteStatus(res);
		} else {
			StatusApp.statusUI.setReadValue(res);
		}
	}
}
