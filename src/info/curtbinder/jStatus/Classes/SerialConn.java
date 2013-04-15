package info.curtbinder.jStatus.Classes;

import gnu.io.*;

public class SerialConn {
	
	public static void listPorts() {
		java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier
				.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier portIdentifier = portEnum.nextElement();
			Log.i(portIdentifier.getName() + " - "
					+ getPortTypeName(portIdentifier.getPortType()));
		}
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
	
	public SerialPort getPort(String portName) throws Exception {
		CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
		SerialPort p = null;
		if ( portIdentifier.isCurrentlyOwned() ) {
			Log.i("Error: Port in use");
			throw new Exception();
		}
		CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);
		if ( commPort instanceof SerialPort ) {
			p = (SerialPort) commPort;
			p.setSerialPortParams(57600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} else {
			Log.i("Error: Only serial ports are allowed");
		}
		return p;
	}

}
