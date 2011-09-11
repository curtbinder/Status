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
	private boolean enableButtonsOnThreadFinish = true;
	private String sendCmdErrorMessage = "";
	private Thread threadSender;

	public Status () {
	}

	private String sendCommandToController ( URL u ) {
		String s = "";
		try {
			BufferedReader bin =
					new BufferedReader( new InputStreamReader( u.openStream() ) );
			String line;
			while ( (line = bin.readLine()) != null ) {
				s += line;
			}
		} catch ( Exception e ) {
			sendCmdErrorMessage = e.getMessage();
			if ( sendCmdErrorMessage.isEmpty() ) {
				sendCmdErrorMessage =
						"Unknown error communicating to controller";
			}
			s = Globals.errorMessage;
		}
		return s;
	}

	public void sendReadMemoryCommand ( ) {
		sendMemoryCommand("ReadMemory");
	}
	
	public void sendWriteMemoryCommand ( ) {
		writeValue = true;
		if ( ! sendMemoryCommand("WriteMemory") ) {
			// error with the command for whatever reason
			// most likely due to an unavailable option
			writeValue = false;
		}
	}
	
	private boolean sendMemoryCommand(String outputPrefix) {
		String cmd = StatusApp.statusUI.tabMemory.getMemType();
		String loc = StatusApp.statusUI.tabMemory.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											"Must specify a memory location",
											"Missing Memory Location",
											JOptionPane.INFORMATION_MESSAGE );
			return false;
		}
		cmd += loc;
		if ( writeValue ) {
			String value = StatusApp.statusUI.tabMemory.getWriteValue();
			if ( value.isEmpty() ) {
				JOptionPane
						.showMessageDialog( StatusApp.statusUI,
											"Must specify a value to write",
											"Missing Value",
											JOptionPane.INFORMATION_MESSAGE );
				return false;
			}
			cmd += "," + value;
		}
		sendCommand(outputPrefix, cmd);
		return true;
	}
	/*
	public void sendReadWriteCommand ( boolean Write ) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											"COM not implemented yet",
											"Comm Type",
											JOptionPane.INFORMATION_MESSAGE );
			return;
		}
		String cmd = StatusApp.statusUI.tabMemory.getMemType();
		String loc = StatusApp.statusUI.tabMemory.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											"Must specify a memory location",
											"Missing Memory Location",
											JOptionPane.INFORMATION_MESSAGE );
			return;
		}
		cmd += loc;
		writeValue = Write;
		if ( Write ) {
			String value = StatusApp.statusUI.tabMemory.getWriteValue();
			if ( value.isEmpty() ) {
				JOptionPane
						.showMessageDialog( StatusApp.statusUI,
											"Must specify a value to write",
											"Missing Value",
											JOptionPane.INFORMATION_MESSAGE );
				return;
			}
			cmd += "," + value;
		}
		final String s = url + cmd;
		System.out.print( "Command: '" + s + "'\n" );

		// blank out the status boxes
		if ( Write ) {
			StatusApp.statusUI.tabMemory.setWriteStatus( "" );
		} else {
			StatusApp.statusUI.tabMemory.setReadValue( "" );
		}

		new Thread() {
			public void run ( ) {
				System.out.println( "Started thread read/write" );
				threadReadWriteMemory( s );
				System.out.println( "Finished thread read/write" );
			}
		}.start();
	}

	private void threadReadWriteMemory ( String url ) {
		// Disable the read/write buttons
		StatusApp.statusUI.tabMemory.disableButtons();
		// send command to controller
		String res = new String( "" );
		long start = System.currentTimeMillis();
		try {
			res = sendCommandToController( new URL( url ) );
		} catch ( MalformedURLException e ) {
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											"Error with URL", "Read Error",
											JOptionPane.INFORMATION_MESSAGE );
		}
		long end = System.currentTimeMillis();
		System.out.printf( "Took %d ms to send command\n", end - start );

		// Enable the read/write buttons
		StatusApp.statusUI.tabMemory.enableButtons();

		// check if there was an error
		if ( res.equals( Globals.errorMessage ) ) {
			// encountered an error, display a popup message
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											sendCmdErrorMessage,
											"Error sending command",
											JOptionPane.INFORMATION_MESSAGE );

		} else {
			// no error
			// parse the returned xml string
			XMLHandler h = new XMLHandler();
			if ( !parseXML( h, res ) ) {
				// TODO consider displaying a popup window
				System.out.println( "Error with parser" );
				return;
			}
			if ( writeValue ) {
				StatusApp.statusUI.tabMemory.setWriteStatus( h
						.getMemoryResponse() );
			} else {
				StatusApp.statusUI.tabMemory.setReadValue( h
						.getMemoryResponse() );
			}
		}
	}
	*/

	public void sendRefreshCommand ( ) {
		sendCommand( "Refresh", Globals.requestStatus );
	}

	public void sendVersionCommand ( ) {
		sendCommand( "Version", Globals.requestVersion );
	}

	public void sendSetDateTimeCommand ( ) {
		new Thread() {
			public void run ( ) {
				// set date and time
				DateTime dt = new DateTime();
				dt.setWithCurrentDateTime();
				String req = Globals.requestDateTime + dt.getSetCommand();
				// indicate we are writing a value to look for the proper response
				writeValue = true;
				// indicate we don't want the buttons re-enabled just yet
				enableButtonsOnThreadFinish = false;
				sendCommand( "SetDateTime", req );
				try {
					threadSender.join();
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
				enableButtonsOnThreadFinish = true;
				if ( ! sendCmdErrorMessage.isEmpty() ) {
					enableButtons();
					return;
				}
				// delay for about 2 seconds
				try {
					java.util.concurrent.TimeUnit.SECONDS.sleep( 2 );
				} catch ( InterruptedException e ) {
				}
				// get date and time from controller
				sendCommand( "GetDateTime", Globals.requestDateTime );
			}
		}.start();
	}

	public void sendGetDateTimeCommand ( ) {
		sendCommand( "Get DateTime", Globals.requestDateTime );
	}

	private void updateDisplay ( XMLHandler h ) {
		String req = h.getRequestType();
		if ( req.equals( Globals.requestStatus ) ) {
			StatusApp.statusUI.tabStatus.setControllerInformation( h.getRa() );
		} else if ( req.startsWith( Globals.requestDateTime ) ) {
			if ( writeValue ) {
				StatusApp.statusUI.tabDateTime.setDateTimeText( h
						.getDateTimeUpdateStatus() );
			} else {
				StatusApp.statusUI.tabDateTime
						.setDateTimeText( h.getDateTime() );
			}
		} else if ( req.equals( Globals.requestVersion ) ) {
			StatusApp.statusUI.tabDateTime.setVersionText( h.getVersion() );
		} else if ( req.startsWith( Globals.requestMemoryByte.substring( 0, 2 ) ) ) {
			if ( writeValue ) {
				StatusApp.statusUI.tabMemory.setWriteStatus( h
				                     						.getMemoryResponse() );
			} else {
				StatusApp.statusUI.tabMemory.setReadValue( h
				                   						.getMemoryResponse() );
			}
		}
	}

	private void disableButtons ( ) {
		// disable all buttons on the forms
		StatusApp.statusUI.tabMemory.disableButtons();
		StatusApp.statusUI.tabStatus.disableRefreshButton();
		StatusApp.statusUI.tabDateTime.disableButtons();
	}

	private void enableButtons ( ) {
		// enable all buttons on the forms
		StatusApp.statusUI.tabMemory.enableButtons();
		StatusApp.statusUI.tabStatus.enableRefreshButton();
		StatusApp.statusUI.tabDateTime.enableButtons();
	}

	public void sendCommand ( String outputPrefix, String request ) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											"COM not implemented yet",
											"Comm Type",
											JOptionPane.INFORMATION_MESSAGE );
			return;
		}
		final String s = url + request;
		System.out.println( outputPrefix + ": '" + s + "'" );
		threadSender = new Thread() {
			public void run ( ) {
				System.out.println( "Started thread" );
				threadSendCommand( s );
				System.out.println( "Finished thread" );
			}
		};
		threadSender.start();
	}

	private void threadSendCommand ( String url ) {
		// Disable the buttons
		disableButtons();
		// send command to controller
		String res = "";
		sendCmdErrorMessage = "";
		long start = System.currentTimeMillis();
		try {
			res = sendCommandToController( new URL( url ) );
		} catch ( MalformedURLException e ) {
			sendCmdErrorMessage = "Error sending command";
			res = Globals.errorMessage;
		}
		long end = System.currentTimeMillis();
		System.out.printf( "Took %d ms to send command\n", end - start );

		// Enable the buttons only if we are supposed to
		if ( enableButtonsOnThreadFinish ) {
			enableButtons();
		}

		// check if there was an error
		if ( res.equals( Globals.errorMessage ) ) {
			// encountered an error, display a popup message
			JOptionPane.showMessageDialog( StatusApp.statusUI,
											sendCmdErrorMessage,
											"Error sending command",
											JOptionPane.INFORMATION_MESSAGE );

		} else {
			XMLHandler h = new XMLHandler();
			if ( !parseXML( h, res ) ) {
				// TODO consider displaying a popup window
				System.out.println( "Error with parser" );
				return;
			}
			updateDisplay( h );
		}
		// always turn off write value when we finish the threads
		writeValue = false;
	}

	private boolean parseXML ( XMLHandler h, String res ) {
		XMLReader xr;
		long start, end;
		System.out.println( "Parsing" );
		try {
			xr = XMLReaderFactory.createXMLReader();
		} catch ( SAXException e ) {
			e.printStackTrace();
			return false;
		}
		xr.setContentHandler( h );
		xr.setErrorHandler( h );
		start = System.currentTimeMillis();
		try {
			xr.parse( new InputSource( new StringReader( res ) ) );
		} catch ( IOException e ) {
			e.printStackTrace();
			return false;
		} catch ( SAXException e ) {
			e.printStackTrace();
			return false;
		}
		end = System.currentTimeMillis();
		System.out.printf( "Took %d ms to parse\n", end - start );
		System.out.println( "Parsed" );
		return true;
	}
}
