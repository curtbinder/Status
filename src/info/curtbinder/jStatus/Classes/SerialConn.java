package info.curtbinder.jStatus.Classes;

import gnu.io.*;

import java.awt.List;
import java.util.Enumeration;

public class SerialConn {

	public static void listSerialPorts() {
		List l = getPortList();
		for (String s : l.getItems())
			Log.i(s);
	}

	public static List getPortList() {
		List l = new List();
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier
				.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			if (portIdentifier.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				l.add(portIdentifier.getName());
			}
		}
		return l;
	}

	public static boolean isValidPort(String port) {
		List l = getPortList();
		boolean fRet = false;
		for (String s : l.getItems()) {
			if (s.equalsIgnoreCase(port)) {
				fRet = true;
				break;
			}
		}
		return fRet;
	}

	public static String getPortTypeName(int portType) {
		switch (portType) {
		case CommPortIdentifier.PORT_I2C:
			return "I2C";
		case CommPortIdentifier.PORT_PARALLEL:
			return "Parallel";
		case CommPortIdentifier.PORT_RAW:
			return "Raw";
		case CommPortIdentifier.PORT_RS485:
			return "RS485";
		case CommPortIdentifier.PORT_SERIAL:
			return "Serial";
		default:
			return "Unknown type";
		}
	}

	public SerialPort getPort(String portName) throws Exception,
			PortInUseException, NoSuchPortException {
		CommPortIdentifier portIdentifier = CommPortIdentifier
				.getPortIdentifier(portName);
		SerialPort p = null;
		if (portIdentifier.isCurrentlyOwned()) {
			Log.i("Error: Port in use");
			throw new PortInUseException();
		}
		CommPort commPort = portIdentifier.open(this.getClass().getName(), 500);
		if (commPort instanceof SerialPort) {
			p = (SerialPort) commPort;
			p.setSerialPortParams(57600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} else {
			Log.i("Error: Only serial ports are allowed");
			throw new Exception();
		}
		return p;
	}

}
