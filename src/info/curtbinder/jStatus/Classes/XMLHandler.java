package info.curtbinder.jStatus.Classes;

//import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHandler extends DefaultHandler {
	
	//private String currentElementName = "";
	private String currentElementText = "";
	private String requestType = "";
	private Controller ra;
	// TODO add in request types
	// TODO add in parse functions to parse specific types
	
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String s = new String(ch, start, length);
		currentElementText += s;
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if ( requestType.equals("r99") ) {
			// move to process RA
			if ( qName.equals("RA") ) {
				return;
			} else { 
				if ( qName.equals("T1") ) {
					ra.setTemp1(Integer.parseInt(currentElementText));
				} else if ( qName.equals("T2") ) {
					ra.setTemp2(Integer.parseInt(currentElementText));
				} else if ( qName.equals("T3") ) {
					ra.setTemp3(Integer.parseInt(currentElementText));
				} else if ( qName.equals("PH") ) {
					ra.setPH(Integer.parseInt(currentElementText));
				} else if ( qName.equals("ATOLOW") ) {
					boolean f = false;
					if ( Integer.parseInt(currentElementText) == 1 ) {
						f = true;
					}
					ra.setAtoLow(f);
				} else if ( qName.equals("ATOHIGH") ) {
					boolean f = false;
					if ( Integer.parseInt(currentElementText) == 1 ) {
						f = true;
					}
					ra.setAtoHigh(f);
				} else if ( qName.equals("PWMA") ) {
					ra.setPwmA(Byte.parseByte(currentElementText));
				} else if ( qName.equals("PWMD") ) {
					ra.setPwmD(Byte.parseByte(currentElementText));
				} else if ( qName.equals("R") ) {
					ra.setMainRelayData(Short.parseShort(currentElementText));
				} else if ( qName.equals("RON") ) {
					ra.setMainRelayDataMaskOn(Short.parseShort(currentElementText));
				} else if ( qName.equals("ROFF") ) {
					ra.setMainRelayDataMaskOff(Short.parseShort(currentElementText));
				}
				// process expansion relays
			}
		}
		//currentElementName = "";
		currentElementText = "";
	}

	//@Override
	/*
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if ( requestType.equals("r99") ) {
			if ( qName.equals("RA") ) {
				return;
			} else { 
				currentElementName = qName;
			}
		}
	}
	*/
}
