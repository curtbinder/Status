package info.curtbinder.jStatus.Classes;

public class Controller {
	public static final byte MAX_EXPANSION_RELAYS = 8;

	private Number t1;
	private Number t2;
	private Number t3;
	private Number pH;
	private boolean atoLow;
	private boolean atoHigh;
	private byte pwmA;
	private byte pwmD;
	private Relay main;
	private byte qtyExpansionRelays;
	private Relay [] expansionRelays;

	public Controller() {
		init();
	}
	public Controller(byte numExpRelays) {
		init();
		this.qtyExpansionRelays = numExpRelays;
		this.expansionRelays = new Relay[numExpRelays];
	}
	private void init() {
		t1 = new Number((byte)1);
		t2 = new Number((byte)1);
		t3 = new Number((byte)1);
		pH = new Number((byte)2);
		atoLow = false;
		atoHigh = false;
		pwmA = 0;
		pwmD = 0;
		main = new Relay();
		qtyExpansionRelays = 0;
	}

	public void setTemp1(int value) {
		t1.setValue(value);
	}
	public String getTemp1() {
		return t1.getNumberString();
	}
	public void setTemp2(int value) {
		t2.setValue(value);
	}
	public String getTemp2() {
		return t2.getNumberString();
	}
	public void setTemp3(int value) {
		t3.setValue(value);
	}
	public String getTemp3() {
		return t3.getNumberString();
	}
	public void setPH(int value) {
		pH.setValue(value);
	}
	public String getPH() {
		return pH.getNumberString();
	}
	public void setAtoLow(boolean v) {
		atoLow = v;
	}
	public boolean getAtoLow() {
		return atoLow;
	}
	public void setAtoHigh(boolean v) {
		atoHigh = v;
	}
	public boolean getAtoHigh() {
		return atoHigh;
	}
	public void setPwmA(byte v) {
		pwmA = v;
	}
	public String getPwmA() {
		return new String(String.format(pwmA + "%%"));
	}
	public void setPwmD(byte v) {
		pwmD = v;
	}
	public String getPwmD() {
		return new String(String.format(pwmD + "%%"));
	}
	public void setMainRelayData(short data, short maskOn, short maskOff) {
		main.setRelayData(data, maskOn, maskOff);
	}
	public void setMainRelayData(short data) {
		main.setRelayData(data);
	}
	public void setMainRelayDataMaskOn(short maskOn) {
		main.setRelayDataMaskOn(maskOn);
	}
	public void setMainRelayDataMaskOff(short maskOff) {
		main.setRelayDataMaskOff(maskOff);
	}
	public Relay getMainRelay() {
		return main;
	}
}
