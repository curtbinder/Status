package info.curtbinder.jStatus.Classes;

import java.text.DecimalFormatSymbols;

public class Number {
	private int value;
	private int whole;
	private int fraction;
	private byte decimalPlaces;

	public Number () {
		value = 0;
		whole = 0;
		fraction = 0;
		decimalPlaces = 0;
	}

	public Number ( byte decimalPlaces ) {
		this.decimalPlaces = decimalPlaces;
	}

	public Number ( int value, byte decimalPlaces ) {
		this.value = value;
		this.decimalPlaces = decimalPlaces;
		computeNumber();
	}

	private void computeNumber ( ) {
		int divisor = 1;
		switch ( decimalPlaces ) {
			case 2:
				divisor = 100;
				break;
			case 1:
				divisor = 10;
				break;
			default:
				divisor = 1;
				break;
		}
		whole = value / divisor;
		fraction = value % divisor;
	}

	public void setValue ( int value ) {
		this.value = value;
		computeNumber();
	}

	public void setValue ( int value, byte decimalPlaces ) {
		this.value = value;
		this.decimalPlaces = decimalPlaces;
		computeNumber();
	}

	public void setDecimalPlaces ( byte decimalPlaces ) {
		this.decimalPlaces = decimalPlaces;
		computeNumber();
	}

	public String toString ( ) {
		String s = "";
		switch ( decimalPlaces ) {
			case 2:
				s =
						String.format( "%d%c%02d", whole, DecimalFormatSymbols
								.getInstance().getDecimalSeparator(), fraction );
				break;
			case 1:
				s =
						String.format( "%d%c%01d", whole, DecimalFormatSymbols
								.getInstance().getDecimalSeparator(), fraction );
				break;
			default:
				s = String.format( "%d", whole );
				break;
		}
		return s;
	}
}
