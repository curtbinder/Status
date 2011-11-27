package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.TextDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.Memory;
import info.curtbinder.jStatus.Classes.MemoryListMouseAdapter;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.Point;

import javax.swing.JDialog;
import javax.swing.JList;

public class MemoryDialog extends TextDialog {
	private static final long serialVersionUID = -3767150728825591955L;
	private static final int windowHeight = 300;
	private static final int windowWidth = 325;

	private static final Memory[] memoryLocationArray =
			{	new Memory( 800, Memory.locationBYTE, "MH On Hour" ),
				new Memory( 801, Memory.locationBYTE, "MH On Minute" ),
				new Memory( 802, Memory.locationBYTE, "MH Off Hour" ),
				new Memory( 803, Memory.locationBYTE, "MH Off Minute" ),
				new Memory( 804, Memory.locationBYTE, "StdLights On Hour" ),
				new Memory( 805, Memory.locationBYTE, "StdLights On Minute" ),
				new Memory( 806, Memory.locationBYTE, "StdLights Off Hour" ),
				new Memory( 807, Memory.locationBYTE, "StdLights Off Minute" ),
				new Memory( 808, Memory.locationINT, "Wavemaker 1 Timer" ),
				new Memory( 810, Memory.locationINT, "Wavemaker 2 Timer" ),
				new Memory( 812, Memory.locationBYTE, "Dosing Pump 1 Timer" ),
				new Memory( 813, Memory.locationBYTE, "Dosing Pump 2 Timer" ),
				new Memory( 814, Memory.locationINT, "Feeding Mode Timer" ),
				new Memory( 816, Memory.locationINT, "LCD Timeout" ),
				new Memory( 818, Memory.locationINT, "Overheat Temp" ),
				new Memory( 820, Memory.locationBYTE, "Daylight PWM Value" ),
				new Memory( 821, Memory.locationBYTE, "Actinic PWM Value" ),
				new Memory( 822, Memory.locationINT, "Heater On Temp" ),
				new Memory( 824, Memory.locationINT, "Heater Off Temp" ),
				new Memory( 826, Memory.locationINT, "Chiller On Temp" ),
				new Memory( 828, Memory.locationINT, "Chiller Off Temp" ),
				new Memory( 830, Memory.locationBYTE, "ATO Timeout" ),
				new Memory( 831, Memory.locationINT, "PH Max value (PH10)" ),
				new Memory( 833, Memory.locationINT, "PH Min value (PH7)" ),
				new Memory( 835, Memory.locationBYTE, "MH Delay" ),
				new Memory( 836, Memory.locationBYTE, "Dosing Pump 1 On Hour" ),
				new Memory( 837, Memory.locationBYTE, "Dosing Pump 1 On Minute" ),
				new Memory( 838, Memory.locationBYTE, "Dosing Pump 2 On Hour" ),
				new Memory( 839, Memory.locationBYTE, "Dosing Pump 2 On Minute" ),
				new Memory( 840, Memory.locationBYTE, "ATO Hour Interval" ),
				new Memory( 841, Memory.locationBYTE, "ATO High Hour Interval" ),
				new Memory( 842, Memory.locationBYTE, "ATO High Timeout" ),
				new Memory( 843, Memory.locationINT,
					"Dosing Pump 1 Repeat Interval" ),
				new Memory( 845, Memory.locationINT,
					"Dosing Pump 2 Repeat Interval" ), };

	private JList memoryLocations;
	
	public MemoryDialog ( JDialog owner ) {
		super( owner, Globals.menuHelpMemoryText, Globals.memoryDescription,
				windowWidth, windowHeight );
		memoryLocations  = new JList(memoryLocationArray);
		memoryLocations.setCellRenderer( new MemoryCellRenderer() );
		memoryLocations.addMouseListener( new MemoryListMouseAdapter(memoryLocations) );
		textWindow.setViewportView( memoryLocations );
	}

	public void setWindowPosition ( ) {
		Point p = StatusApp.statusUI.getLocation();
		int w = StatusApp.statusUI.getWidth();
		setBounds( p.x + w, p.y, windowWidth, windowHeight );
	}
}
