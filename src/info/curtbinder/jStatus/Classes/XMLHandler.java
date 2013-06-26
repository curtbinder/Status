package info.curtbinder.jStatus.Classes;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {

	private String currentElementText = "";
	private String requestType = "";
	private Controller ra;
	private String version = "";
	private String memoryResponse = "";
	private DateTime dt;

	public Controller getRa ( ) {
		return ra;
	}

	public String getVersion ( ) {
		return version;
	}

	public String getDateTime ( ) {
		return dt.getDateTimeString();
	}

	public String getDateTimeUpdateStatus ( ) {
		return dt.getUpdateStatus();
	}

	public String getMemoryResponse ( ) {
		return memoryResponse;
	}

	public String getRequestType ( ) {
		return requestType;
	}

	public XMLHandler () {
		super();
		this.ra = new Controller();
		this.dt = new DateTime();
	}

	@Override
	public void characters ( char[] ch, int start, int length )
			throws SAXException {
		String s = new String( ch, start, length );
		currentElementText += s;
	}

	@Override
	public void endElement ( String uri, String localName, String qName )
			throws SAXException {
		if ( requestType.equals( Globals.requestStatus ) ) {
			if ( qName.equals( XMLTags.Status ) ) {
				return;
			} else {
				processStatusXml( qName );
			}
		} else if ( requestType.equals( Globals.requestMemoryByte ) ) {
			if ( qName.equals( XMLTags.Memory ) ) {
				return;
			} else {
				processMemoryXml( qName );
			}
		} else if ( requestType.equals( Globals.requestDateTime ) ) {
			if ( qName.equals( XMLTags.DateTime ) ) {
				if ( !currentElementText.isEmpty() ) {
					// not empty meaning we have a status to report
					// either OK or ERR
					dt.setStatus( currentElementText );
				}
				return;
			} else {
				processDateTimeXml( qName );
			}
		} else if ( requestType.equals( Globals.requestVersion ) ) {
			processVersionXml( qName );
		} else {
			// TODO request none, set an error?
		}
		currentElementText = "";
	}

	// @Override
	public void startElement (
			String uri,
			String localName,
			String qName,
			Attributes attributes ) throws SAXException {
		if ( requestType.isEmpty() ) {
			// no request type, so set it based on the first element we process
			if ( qName.equals( XMLTags.Status ) ) {
				requestType = Globals.requestStatus;
			} else if ( qName.equals( XMLTags.DateTime ) ) {
				requestType = Globals.requestDateTime;
			} else if ( qName.equals( XMLTags.Version ) ) {
				requestType = Globals.requestVersion;
			} else if ( qName.startsWith( XMLTags.MemorySingle ) ) {
				// can be either type, just chose to use Bytes
				requestType = Globals.requestMemoryByte;
			} else if ( qName.equals( XMLTags.Mode ) ) {
				// all modes return the same response, just chose to use Exit
				// Mode
				requestType = Globals.requestExitMode;
			} else {
				requestType = Globals.requestNone;
			}
		}
	}

	private void processStatusXml ( String tag ) {
		try {
			if ( tag.equals( XMLTags.T1 ) ) {
				ra.setTemp1( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.T2 ) ) {
				ra.setTemp2( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.T3 ) ) {
				ra.setTemp3( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.PH ) ) {
				ra.setPH( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.PHExpansion ) ) {
				ra.setPHExp( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.ATOLow ) ) {
				boolean f = false;
				if ( Short.parseShort( currentElementText ) == 1 ) {
					f = true;
				}
				ra.setAtoLow( f );
			} else if ( tag.equals( XMLTags.ATOHigh ) ) {
				boolean f = false;
				if ( Short.parseShort( currentElementText ) == 1 ) {
					f = true;
				}
				ra.setAtoHigh( f );
			} else if ( tag.equals( XMLTags.PWMActinic ) ) {
				short v = Short.parseShort( currentElementText );
				ra.setPwmA( (byte) v );
			} else if ( tag.equals( XMLTags.PWMDaylight ) ) {
				short v = Short.parseShort( currentElementText );
				ra.setPwmD( (byte) v );
			} else if ( tag.startsWith( XMLTags.PWMExpansion )
						&& !tag.endsWith( XMLTags.Override ) ) {
				short channel =
						Short.parseShort( tag.substring( XMLTags.PWMExpansion
								.length() ) );
				short v = Short.parseShort( currentElementText );
				ra.setPwmExpansion( channel, v );
			} else if ( tag.equals( XMLTags.Salinity ) ) {
				ra.setSalinity( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.ORP ) ) {
				ra.setORP( Integer.parseInt( currentElementText ) );
			} else if ( tag.equals( XMLTags.WaterLevel ) ) {
				short v = Short.parseShort( currentElementText );
				ra.setWaterLevel( v );
			} else if ( tag.equals( XMLTags.Relay ) ) {
				ra.setMainRelayData( Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RelayMaskOn ) ) {
				ra.setMainRelayOnMask( Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RelayMaskOff ) ) {
				ra.setMainRelayOffMask( Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.LogDate ) ) {
				ra.setLogDate( currentElementText );
			} else if ( tag.equals( XMLTags.RelayExpansionModules ) ) {
				short v = Short.parseShort( currentElementText );
				ra.setRelayExpansionModules( v );
			} else if ( tag.equals( XMLTags.ExpansionModules ) ) {
				short v = Short.parseShort( currentElementText );
				ra.setExpansionModules( v );
			} else if ( tag.equals( XMLTags.AIBlue ) ) {
				ra.setAIChannel(	Controller.AI_BLUE,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.AIRoyalBlue ) ) {
				ra.setAIChannel(	Controller.AI_ROYALBLUE,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.AIWhite ) ) {
				ra.setAIChannel(	Controller.AI_WHITE,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFMode ) ) {
				ra.setVortechValue( Controller.VORTECH_MODE,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFSpeed ) ) {
				ra.setVortechValue( Controller.VORTECH_SPEED,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFDuration ) ) {
				ra.setVortechValue( Controller.VORTECH_DURATION,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFWhite ) ) {
				ra.setRadionChannel(	Controller.RADION_WHITE,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFBlue ) ) {
				ra.setRadionChannel(	Controller.RADION_BLUE,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFGreen ) ) {
				ra.setRadionChannel(	Controller.RADION_GREEN,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFRed ) ) {
				ra.setRadionChannel(	Controller.RADION_RED,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFRoyalBlue ) ) {
				ra.setRadionChannel(	Controller.RADION_ROYALBLUE,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.RFIntensity ) ) {
				ra.setRadionChannel(	Controller.RADION_INTENSITY,
										Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.IO ) ) {
				ra.setIOChannels( Short.parseShort( currentElementText ) );
			} else if ( tag.endsWith( XMLTags.Override ) ) {
			} else if ( tag.startsWith( XMLTags.Custom ) ) {
				short v =
						Short.parseShort( tag.substring( XMLTags.Custom
								.length() ) );
				short c = Short.parseShort( currentElementText );
				ra.setCustomVariable( v, c );
			} else if ( tag.startsWith( XMLTags.RelayMaskOn ) ) {
				int relay =
						Integer.parseInt( tag.substring( XMLTags.RelayMaskOn
								.length() ) );
				// if ( fUse085XRelays )
				// relay += 1;
				ra.setExpRelayOnMask(	relay,
										Short.parseShort( currentElementText ) );
			} else if ( tag.startsWith( XMLTags.RelayMaskOff ) ) {
				int relay =
						Integer.parseInt( tag.substring( XMLTags.RelayMaskOff
								.length() ) );
				// if ( fUse085XRelays )
				// relay += 1;
				ra.setExpRelayOffMask(	relay,
										Short.parseShort( currentElementText ) );
			} else if ( tag.startsWith( XMLTags.Relay ) ) {
				int relay =
						Integer.parseInt( tag.substring( XMLTags.Relay.length() ) );
				// if ( fUse085XRelays )
				// relay += 1;
				ra.setExpRelayData( relay,
									Short.parseShort( currentElementText ) );
			} else if ( tag.equals( XMLTags.MyReefAngelID ) ) {
				// Log.d( TAG, "Reefangel ID: " + currentElementText );
			} else {
				// Log.d( TAG, "Unhandled XML tag (" + tag + ") with data: "
				// + currentElementText );
			}
		} catch ( NumberFormatException e ) {
		}

	}

	private void processDateTimeXml ( String tag ) {
		/*
		 * Response will be more XML data or OK
		 */
		if ( tag.equals( XMLTags.Hour ) ) {
			dt.setHour( Integer.parseInt( currentElementText ) );
		} else if ( tag.equals( XMLTags.Minute ) ) {
			dt.setMinute( Integer.parseInt( currentElementText ) );
		} else if ( tag.equals( XMLTags.Month ) ) {
			// controller uses 1 based for month
			// java uses 0 based for month
			dt.setMonth( Integer.parseInt( currentElementText ) - 1 );
		} else if ( tag.equals( XMLTags.Day ) ) {
			dt.setDay( Integer.parseInt( currentElementText ) );
		} else if ( tag.equals( XMLTags.Year ) ) {
			dt.setYear( Integer.parseInt( currentElementText ) );
		}
	}

	private void processVersionXml ( String tag ) {
		/*
		 * Response will be the Version
		 */
		if ( tag.equals( XMLTags.Version ) ) {
			version = currentElementText;
		}
	}

	private void processMemoryXml ( String tag ) {
		/*
		 * Responses will be either: OK, value, ERR
		 */
		if ( tag.startsWith( XMLTags.MemorySingle ) ) {
			memoryResponse = currentElementText;
		}
	}
}
