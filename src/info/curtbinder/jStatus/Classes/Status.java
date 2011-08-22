package info.curtbinder.jStatus.Classes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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
			System.out.print("COM not implemented yet\n");
			return;
		}
		String cmd = StatusApp.statusUI.getMemType();
		String loc = StatusApp.statusUI.getTextLocation();
		if ( loc.isEmpty() ) {
			System.out.print("Must specify a location\n");
			return;
		}
		cmd += loc;
		if ( Write ) {
			String value = StatusApp.statusUI.getWriteValue();
			if ( value.isEmpty() ) {
				System.out.print("Must specify a value\n");
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
