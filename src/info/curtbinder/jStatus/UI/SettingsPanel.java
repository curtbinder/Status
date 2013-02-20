package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = 7377000441986611539L;

	private JCheckBox checkUsePre10Memory;
	private JCheckBox checkDisableNotifications;

	public SettingsPanel () {
		super();
		// Settings panel
		setBorder( new TitledBorder( null, "Settings:", TitledBorder.LEADING,
			TitledBorder.TOP, null, null ) );
		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		JPanel p = new JPanel();
		p.setLayout( new BoxLayout( p, BoxLayout.Y_AXIS ) );
		checkUsePre10Memory = new JCheckBox( Globals.labelPre10Memory );
		checkDisableNotifications =
				new JCheckBox( Globals.labelDisableNotifications );
		p.add( checkUsePre10Memory );
		p.add( Box.createVerticalGlue() );
		p.add( checkDisableNotifications );

		// Fill the settings panel
		add( Box.createHorizontalStrut( 5 ) );
		// Add the settings here
		add( p );
		// add( Box.createHorizontalStrut( 5 ) );
		add( Box.createHorizontalGlue() );
	}

	public boolean getUsePre10Memory ( ) {
		return checkUsePre10Memory.isSelected();
	}

	public void setUsePre10Memory ( boolean fUse ) {
		checkUsePre10Memory.setSelected( fUse );
	}

	public boolean getDisableNotifications ( ) {
		return checkDisableNotifications.isSelected();
	}

	public void setDisableNotifications ( boolean fDisable ) {
		checkDisableNotifications.setSelected( fDisable );
	}
}
