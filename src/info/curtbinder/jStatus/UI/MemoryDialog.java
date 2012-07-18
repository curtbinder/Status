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

	private static final Memory[] memoryPre10LocationArray =
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

	private static final Memory[] memoryLocationArray =
			{	new Memory( 200, Memory.locationBYTE, "MH On Hour" ),
				new Memory( 201, Memory.locationBYTE, "MH On Minute" ),
				new Memory( 202, Memory.locationBYTE, "MH Off Hour" ),
				new Memory( 203, Memory.locationBYTE, "MH Off Minute" ),
				new Memory( 204, Memory.locationBYTE, "StdLights On Hour" ),
				new Memory( 205, Memory.locationBYTE, "StdLights On Minute" ),
				new Memory( 206, Memory.locationBYTE, "StdLights Off Hour" ),
				new Memory( 207, Memory.locationBYTE, "StdLights Off Minute" ),
				new Memory( 208, Memory.locationINT, "Wavemaker 1 Timer" ),
				new Memory( 210, Memory.locationINT, "Wavemaker 2 Timer" ),
				new Memory( 212, Memory.locationBYTE, "Dosing Pump 1 Timer" ),
				new Memory( 213, Memory.locationBYTE, "Dosing Pump 2 Timer" ),
				new Memory( 214, Memory.locationINT, "Feeding Mode Timer" ),
				new Memory( 216, Memory.locationINT, "LCD Timeout" ),
				new Memory( 218, Memory.locationINT, "Overheat Temp" ),
				new Memory( 220, Memory.locationBYTE, "Daylight PWM Value" ),
				new Memory( 221, Memory.locationBYTE, "Actinic PWM Value" ),
				new Memory( 222, Memory.locationINT, "Heater On Temp" ),
				new Memory( 224, Memory.locationINT, "Heater Off Temp" ),
				new Memory( 226, Memory.locationINT, "Chiller On Temp" ),
				new Memory( 228, Memory.locationINT, "Chiller Off Temp" ),
				new Memory( 230, Memory.locationBYTE, "ATO Timeout" ),
				new Memory( 231, Memory.locationINT, "PH Max value (PH10)" ),
				new Memory( 233, Memory.locationINT, "PH Min value (PH7)" ),
				new Memory( 235, Memory.locationBYTE, "MH Delay" ),
				new Memory( 236, Memory.locationBYTE, "Dosing Pump 1 On Hour" ),
				new Memory( 237, Memory.locationBYTE, "Dosing Pump 1 On Minute" ),
				new Memory( 238, Memory.locationBYTE, "Dosing Pump 2 On Hour" ),
				new Memory( 239, Memory.locationBYTE, "Dosing Pump 2 On Minute" ),
				new Memory( 240, Memory.locationBYTE, "ATO Hour Interval" ),
				new Memory( 241, Memory.locationBYTE, "ATO High Hour Interval" ),
				new Memory( 242, Memory.locationBYTE, "ATO High Timeout" ),
				new Memory( 243, Memory.locationINT,
					"Dosing Pump 1 Repeat Interval" ),
				new Memory( 245, Memory.locationINT,
					"Dosing Pump 2 Repeat Interval" ),
				new Memory( 247, Memory.locationINT, "Salinity Max" ),
				new Memory( 249, Memory.locationBYTE,
					"PWM Slope Daylight Start %" ),
				new Memory( 250, Memory.locationBYTE,
					"PWM Slope Daylight End %" ),
				new Memory( 251, Memory.locationBYTE,
					"PWM Slope Daylight Duration" ),
				new Memory( 252, Memory.locationBYTE,
					"PWM Slope Actinic Start %" ),
				new Memory( 253, Memory.locationBYTE, "PWM Slope Actinic End %" ),
				new Memory( 254, Memory.locationBYTE,
					"PWM Slope Actinic Duration" ),
				new Memory( 255, Memory.locationBYTE, "RF Mode" ),
				new Memory( 256, Memory.locationBYTE, "RF Speed" ),
				new Memory( 257, Memory.locationBYTE, "RF Duration" ),
				new Memory( 258, Memory.locationBYTE,
					"PWM Slope Exp Ch 0 Start %" ),
				new Memory( 259, Memory.locationBYTE,
					"PWM Slope Exp Ch 0 End %" ),
				new Memory( 260, Memory.locationBYTE,
					"PWM Slope Exp Ch 0 Duration" ),
				new Memory( 261, Memory.locationBYTE,
					"PWM Slope Exp Ch 1 Start %" ),
				new Memory( 262, Memory.locationBYTE,
					"PWM Slope Exp Ch 1 End %" ),
				new Memory( 263, Memory.locationBYTE,
					"PWM Slope Exp Ch 1 Duration" ),
				new Memory( 264, Memory.locationBYTE,
					"PWM Slope Exp Ch 2 Start %" ),
				new Memory( 265, Memory.locationBYTE,
					"PWM Slope Exp Ch 2 End %" ),
				new Memory( 266, Memory.locationBYTE,
					"PWM Slope Exp Ch 2 Duration" ),
				new Memory( 267, Memory.locationBYTE,
					"PWM Slope Exp Ch 3 Start %" ),
				new Memory( 268, Memory.locationBYTE,
					"PWM Slope Exp Ch 3 End %" ),
				new Memory( 269, Memory.locationBYTE,
					"PWM Slope Exp Ch 3 Duration" ),
				new Memory( 270, Memory.locationBYTE,
					"PWM Slope Exp Ch 4 Start %" ),
				new Memory( 271, Memory.locationBYTE,
					"PWM Slope Exp Ch 4 End %" ),
				new Memory( 272, Memory.locationBYTE,
					"PWM Slope Exp Ch 4 Duration" ),
				new Memory( 273, Memory.locationBYTE,
					"PWM Slope Exp Ch 5 Start %" ),
				new Memory( 274, Memory.locationBYTE,
					"PWM Slope Exp Ch 5 End %" ),
				new Memory( 275, Memory.locationBYTE,
					"PWM Slope Exp Ch 5 Duration" ),
				new Memory( 276, Memory.locationINT, "ATO Extended Timeout" ),
				new Memory( 278, Memory.locationINT,
					"ATO High Extended Timeout" ),
				new Memory( 280, Memory.locationINT, "ORP Min Value" ),
				new Memory( 282, Memory.locationINT, "ORP Max Value" ),
				new Memory( 284, Memory.locationBYTE, "Actinic Offset Time" ),
				new Memory( 285, Memory.locationINT, "CO2 Control On Value" ),
				new Memory( 287, Memory.locationINT, "CO2 Control Off Value" ),
				new Memory( 289, Memory.locationINT, "PH Control On Value" ),
				new Memory( 291, Memory.locationINT, "PH Control Off Value" ),
				new Memory( 293, Memory.locationBYTE, "AI Slope Ch W Start %" ),
				new Memory( 294, Memory.locationBYTE, "AI Slope Ch W End %" ),
				new Memory( 295, Memory.locationBYTE, "AI Slope Ch W Duration" ),
				new Memory( 296, Memory.locationBYTE, "AI Slope Ch B Start %" ),
				new Memory( 297, Memory.locationBYTE, "AI Slope Ch B End %" ),
				new Memory( 298, Memory.locationBYTE, "AI Slope Ch B Duration" ),
				new Memory( 299, Memory.locationBYTE, "AI Slope Ch RB Start %" ),
				new Memory( 300, Memory.locationBYTE, "AI Slope Ch RB End %" ),
				new Memory( 301, Memory.locationBYTE, "AI Slope Ch RB Duration" ),
				new Memory( 302, Memory.locationBYTE,
					"Radion Slope Ch W Start %" ),
				new Memory( 303, Memory.locationBYTE, "Radion Slope Ch W End %" ),
				new Memory( 304, Memory.locationBYTE,
					"Radion Slope Ch W Duration" ),
				new Memory( 305, Memory.locationBYTE,
					"Radion Slope Ch RB Start %" ),
				new Memory( 306, Memory.locationBYTE,
					"Radion Slope Ch RB End %" ),
				new Memory( 307, Memory.locationBYTE,
					"Radion Slope Ch RB Duration" ),
				new Memory( 308, Memory.locationBYTE,
					"Radion Slope Ch R Start %" ),
				new Memory( 309, Memory.locationBYTE, "Radion Slope Ch R End %" ),
				new Memory( 310, Memory.locationBYTE,
					"Radion Slope Ch R Duration" ),
				new Memory( 311, Memory.locationBYTE,
					"Radion Slope Ch G Start %" ),
				new Memory( 312, Memory.locationBYTE, "Radion Slope Ch G End %" ),
				new Memory( 313, Memory.locationBYTE,
					"Radion Slope Ch G Duration" ),
				new Memory( 314, Memory.locationBYTE,
					"Radion Slope Ch B Start %" ),
				new Memory( 315, Memory.locationBYTE, "Radion Slope Ch B End %" ),
				new Memory( 316, Memory.locationBYTE,
					"Radion Slope Ch B Duration" ),
				new Memory( 317, Memory.locationBYTE,
					"Radion Slope Ch I Start %" ),
				new Memory( 318, Memory.locationBYTE, "Radion Slope Ch I End %" ),
				new Memory( 319, Memory.locationBYTE,
					"Radion Slope Ch I Duration" ),
				new Memory( 320, Memory.locationBYTE, "Delayed Start Time" ),
				new Memory( 321, Memory.locationINT, "PH Exp Min Value (PH7)" ),
				new Memory( 323, Memory.locationINT, "PH Exp Max Value (PH10)" ),
				new Memory( 325, Memory.locationINT, "Water Level Min Value" ),
				new Memory( 327, Memory.locationINT, "Water Level Max Value" ), };

	private JList memoryLocations;

	public MemoryDialog ( JDialog owner ) {
		super( owner, Globals.menuHelpMemoryText, Globals.memoryDescription,
				windowWidth, windowHeight );
		if ( StatusApp.fUsePre10Memory )
			memoryLocations = new JList( memoryPre10LocationArray );
		else
			memoryLocations = new JList( memoryLocationArray );
		memoryLocations.setCellRenderer( new MemoryCellRenderer() );
		memoryLocations.addMouseListener( new MemoryListMouseAdapter(
			memoryLocations ) );
		textWindow.setViewportView( memoryLocations );
	}

	public void setWindowPosition ( ) {
		Point p = StatusApp.statusUI.getLocation();
		int w = StatusApp.statusUI.getWidth();
		setBounds( p.x + w, p.y, windowWidth, windowHeight );
	}
}
