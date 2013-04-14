package info.curtbinder.jStatus.Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

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

	private String sendCommandToController ( InputStream is ) {
		StringBuilder s = new StringBuilder( 8192 );
		try {
			if ( Thread.interrupted() )
				throw new InterruptedException();

			BufferedReader bin =
					new BufferedReader( new InputStreamReader( is ) );
			String line;
			while ( (line = bin.readLine()) != null ) {
				if ( Thread.interrupted() )
					throw new InterruptedException();

				s.append( line );
			}
		} catch ( InterruptedException e ) {
			s = new StringBuilder( "Cancelled" );
		} catch ( Exception e ) {
			sendCmdErrorMessage = e.getMessage();
			if ( sendCmdErrorMessage.isEmpty() ) {
				sendCmdErrorMessage =
						"Unknown error communicating to controller";
			}
			s = new StringBuilder( Globals.errorMessage );
		}
		return s.toString();
	}

	public void sendReadMemoryCommand ( ) {
		sendMemoryCommand( "ReadMemory" );
	}

	public void sendWriteMemoryCommand ( ) {
		writeValue = true;
		if ( !sendMemoryCommand( "WriteMemory" ) ) {
			// error with the command for whatever reason
			// most likely due to an unavailable option
			writeValue = false;
		}
	}

	private boolean sendMemoryCommand ( String outputPrefix ) {
		String cmd = StatusApp.statusUI.tabMemory.getMemType();
		String loc = StatusApp.statusUI.tabMemory.getTextLocation();
		if ( loc.isEmpty() ) {
			JOptionPane.showMessageDialog(	StatusApp.statusUI,
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
		sendCommand( outputPrefix, cmd );
		return true;
	}

	public void sendRefreshCommand ( ) {
		sendCommand( "Refresh", Globals.requestStatus );
	}

	public void sendRelayCommand ( int port, byte mode ) {
		String cmd = String.format( "%s%d%d", Globals.requestRelay, port, mode );
		Log.i( "RelayCommand: " + cmd );
		sendCommand( "Relay", cmd );
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
				// indicate we are writing a value to look for the proper
				// response
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
				if ( !sendCmdErrorMessage.isEmpty() ) {
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

	public void sendEnterFeedModeCommand ( ) {
		sendCommand( "Feeding Mode", Globals.requestFeedingMode );
	}

	public void sendEnterWaterChangeModeCommand ( ) {
		sendCommand( "Water Change Mode", Globals.requestWaterMode );
	}

	public void sendExitModeCommand ( ) {
		sendCommand( "Exit Mode", Globals.requestExitMode );
	}

	public void sendClearATOCommand ( ) {
		sendCommand( "Clear ATO", Globals.requestClearATO );
	}

	public void sendClearOverheatCommand ( ) {
		sendCommand( "Clear Overheat", Globals.requestClearOverheat );
	}

	private void updateDisplay ( final XMLHandler h ) {
		// Run this updating in the GUI thread
		SwingUtilities.invokeLater( new Runnable() {
			public void run ( ) {
				String req = h.getRequestType();
				if ( req.equals( Globals.requestStatus ) ) {
					StatusApp.statusUI.tabStatus.setControllerInformation( h
							.getRa() );
				} else if ( req.startsWith( Globals.requestDateTime ) ) {
					if ( writeValue ) {
						StatusApp.statusUI.tabCommands.setDateTimeText( h
								.getDateTimeUpdateStatus() );
					} else {
						StatusApp.statusUI.tabCommands.setDateTimeText( h
								.getDateTime() );
					}
				} else if ( req.equals( Globals.requestVersion ) ) {
					StatusApp.statusUI.tabCommands.setVersionText( h
							.getVersion() );
				} else if ( req.startsWith( Globals.requestMemoryByte
						.substring( 0, 2 ) ) ) {
					if ( writeValue ) {
						StatusApp.statusUI.tabMemory.setWriteStatus( h
								.getMemoryResponse() );
					} else {
						StatusApp.statusUI.tabMemory.setReadValue( h
								.getMemoryResponse() );
					}
				}
			}
		} );
	}

	private void disableButtons ( ) {
		// disable all buttons on the forms
		StatusApp.statusUI.tabMemory.disableButtons();
		StatusApp.statusUI.tabStatus.disableButtons();
		StatusApp.statusUI.tabCommands.disableButtons();
	}

	private void enableButtons ( ) {
		// enable all buttons on the forms
		StatusApp.statusUI.tabMemory.enableButtons();
		StatusApp.statusUI.tabStatus.enableButtons();
		StatusApp.statusUI.tabCommands.enableButtons();
	}

	public void sendCommand ( String outputPrefix, String request ) {
		String url = StatusApp.statusUI.getCommMethod();
		if ( url == "GET " ) {
			JOptionPane.showMessageDialog(	StatusApp.statusUI,
											"COM not implemented yet",
											"Comm Type",
											JOptionPane.INFORMATION_MESSAGE );
			return;
		}
		final String s = url + request;
		final String r = request;
		Log.i( outputPrefix + ": '" + s + "'" );
		threadSender = new Thread() {
			public void run ( ) {
				Log.i( "Started thread" );
				threadSendCommand( s, r );
				Log.i( "Finished thread" );
			}
		};
		threadSender.start();
	}

	private void threadSendCommand ( String url, String req ) {
		// Disable the buttons
		disableButtons();
		// send command to controller
		String res = "";
		HttpURLConnection con = null;
		sendCmdErrorMessage = "";
		long start = System.currentTimeMillis();
		try {
			InputStream is;
			if ( url.startsWith( "GET " ) ) {
				// this is COM method
				is = null;
				throw new InterruptedException();
			} else {
				URL u = new URL( url );
				con = (HttpURLConnection) u.openConnection();
				con.setReadTimeout( 10000 );
				con.setConnectTimeout( 15000 );
				con.setRequestMethod( "GET" );
				con.connect();
				is = con.getInputStream();
			}

			if ( Thread.interrupted() )
				throw new InterruptedException();

			res = sendCommandToController( is );
		} catch ( MalformedURLException e ) {
			sendCmdErrorMessage = Globals.errorSendingCommand;
			res = Globals.errorMessage;
		} catch ( SocketTimeoutException e ) {
			sendCmdErrorMessage = Globals.errorConnectTimeout;
			res = Globals.errorMessage;
		} catch ( IOException e ) {
			sendCmdErrorMessage = Globals.errorIO;
			res = Globals.errorMessage;
		} catch ( InterruptedException e ) {
			sendCmdErrorMessage = Globals.errorCancelled;
			res = Globals.errorMessage;
		} finally {
			if ( con != null ) {
				con.disconnect();
			}
		}
		long end = System.currentTimeMillis();
		Log.i( "Took %d ms to send command\n", end - start );

		// Enable the buttons only if we are supposed to
		if ( enableButtonsOnThreadFinish ) {
			enableButtons();
		}

		// check if there was an error
		if ( res.equals( Globals.errorMessage ) ) {
			// encountered an error
			handleError( req, Globals.errorSendingCommand + ": "
								+ sendCmdErrorMessage );
		} else {
			XMLHandler h = new XMLHandler();
			if ( !parseXML( h, res ) ) {
				handleError( req, Globals.errorParser );
				return;
			}
			updateDisplay( h );
		}
		// always turn off write value when we finish the threads
		writeValue = false;
	}

	private void handleError ( String req, String consoleErrorMessage ) {
		Log.i( consoleErrorMessage );
		boolean fShowPopups = true;
		if ( req.equals( Globals.requestStatus ) ) {
			// update status text
			StatusApp.statusUI.tabStatus
					.setUpdateErrorText( sendCmdErrorMessage );
			if ( StatusApp.fDisableNotifications ) {
				// Only display popup if notifications are not disabled
				fShowPopups = false;
			}
		}
		// always show popups on every tab BUT the status tab
		// unless popup notifications are enabled
		if ( fShowPopups ) {
			JOptionPane.showMessageDialog(	StatusApp.statusUI,
											sendCmdErrorMessage,
											Globals.errorMessage,
											JOptionPane.INFORMATION_MESSAGE );
		}
	}

	private boolean parseXML ( XMLHandler h, String res ) {
		XMLReader xr;
		long start, end;
		Log.i( "Parsing" );
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
		Log.i( "Took %d ms to parse\n", end - start );
		Log.i( "Parsed" );
		return true;
	}
}
