package info.curtbinder.jStatus.Classes;

public class Relay {
	public static final byte PORT_OFF = 0;
	public static final byte PORT_ON = 1;

	public static final byte PORT_STATE_OFF = 0;
	public static final byte PORT_STATE_ON = 1;
	public static final byte PORT_STATE_AUTO = 2;

	private short data;
	private short maskOn;
	private short maskOff;

	public Relay () {
		data = 0;
		maskOn = 0;
		maskOff = 0;
	}

	public void setRelayData ( short data, short maskOn, short maskOff ) {
		this.data = data;
		this.maskOn = maskOn;
		this.maskOff = maskOff;
	}

	public void setRelayData ( short data ) {
		this.data = data;
	}

	public void setRelayOnMask ( short maskOn ) {
		this.maskOn = maskOn;
	}

	public void setRelayOffMask ( short maskOff ) {
		this.maskOff = maskOff;
	}
	
	public short getRelayData ( ) {
		return this.data;
	}
	
	public short getRelayOnMask ( ) {
		return this.maskOn;
	}
	
	public short getRelayOffMask ( ) {
		return this.maskOff;
	}

	// Get port statuses
	/*
	 * maskOn = 1 & maskOff = 1 == PORT_ON 
	 * maskOn = 0 & maskOff = 1 == PORT_AUTO
	 * maskOn = 0 & maskOff = 0 == PORT_OFF
	 */
	public short getPort1Status ( ) {
		return getPortStatus(1);
	}
	public short getPort2Status ( ) {
		return getPortStatus(2);
	}
	public short getPort3Status ( ) {
		return getPortStatus(3);
	}
	public short getPort4Status ( ) {
		return getPortStatus(4);
	}
	public short getPort5Status ( ) {
		return getPortStatus(5);
	}
	public short getPort6Status ( ) {
		return getPortStatus(6);
	}
	public short getPort7Status ( ) {
		return getPortStatus(7);
	}
	public short getPort8Status ( ) {
		return getPortStatus(8);
	}
	public short getPortStatus ( int port ) {
		short status = PORT_STATE_OFF;
		if ( (getPortMaskOnValue(port) == PORT_ON) && 
			 (getPortMaskOffValue(port) == PORT_ON) ) {
			status = PORT_STATE_ON;
		} else if ( (getPortMaskOnValue(port) == PORT_OFF) && 
					(getPortMaskOffValue(port) == PORT_ON) ) {
			status = PORT_STATE_AUTO;
		}
		return status;		
	}
	public short getPortMaskOnValue ( int port) {
		return (short) ((maskOn & (1 << (port-1)))>>(port-1));
	}
	public short getPortMaskOffValue ( int port ) {
		return (short) ((maskOff & (1 << (port-1)))>>(port-1));
	}
	public short getPortValue( int port ) {
		return (short) (data & (1 << (port-1)));
	}

	// ports
	public boolean isPort1On ( boolean usemask ) {
		return isPortOn(1, usemask);
	}
	public boolean isPort2On ( boolean usemask ) {
		return isPortOn(2, usemask);
	}
	public boolean isPort3On ( boolean usemask ) {
		return isPortOn(3, usemask);
	}
	public boolean isPort4On ( boolean usemask ) {
		return isPortOn(4, usemask);
	}
	public boolean isPort5On ( boolean usemask ) {
		return isPortOn(5, usemask);
	}
	public boolean isPort6On ( boolean usemask ) {
		return isPortOn(6, usemask);
	}
	public boolean isPort7On ( boolean usemask ) {
		return isPortOn(7, usemask);
	}
	public boolean isPort8On ( boolean usemask ) {
		return isPortOn(8, usemask);
	}
	public boolean isPortOn ( int port, boolean usemask ) {
		boolean b = false;
		if ( usemask ) {
			short status = getPortStatus(port);
			if ( status == PORT_STATE_ON ) {
				// masked on
				b = true;
			} else if ( status == PORT_STATE_AUTO ) {
				// auto - based on controller settings
				b = (getPortValue(port) != PORT_OFF);
			} // else masked off
		} else {
			b = (getPortValue(port) != PORT_OFF);
		}
		return b;
	}
}
