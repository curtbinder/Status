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

	public SettingsPanel () {
		super();
		// Settings panel
		setBorder( new TitledBorder( null, "Settings:", TitledBorder.LEADING,
			TitledBorder.TOP, null, null ) );
		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		checkUsePre10Memory = new JCheckBox( Globals.labelPre10Memory );
		// Fill the settings panel
		add( Box.createHorizontalStrut( 5 ) );
		// Add the settings here
		add( checkUsePre10Memory );
		add( Box.createHorizontalStrut( 5 ) );
	}

	public boolean getUsePre10Memory ( ) {
		return checkUsePre10Memory.isSelected();
	}

	public void setUsePre10Memory ( boolean fUse ) {
		checkUsePre10Memory.setSelected( fUse );
	}
}
