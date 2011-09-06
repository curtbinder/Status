package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;

import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class CommunicationsPanel extends JPanel {

	private static final long serialVersionUID = 7377000441986611539L;

	private ButtonGroup groupCommType;
	private WifiPanel panelWifiSettings;

	public CommunicationsPanel () {
		super();
		// Communication panel
		setBorder( new TitledBorder( null, "Communications:",
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );

		// Communication choices
		JPanel panelCommChoice = new JPanel();
		panelCommChoice.setBorder( new TitledBorder( new LineBorder( new Color(
			184, 207, 229 ) ), "Type:", TitledBorder.LEADING, TitledBorder.TOP,
			null, new Color( 51, 51, 51 ) ) );
		panelCommChoice.setLayout( new BoxLayout( panelCommChoice,
			BoxLayout.Y_AXIS ) );
		JRadioButton rdbtnWifi = new JRadioButton( "Wifi" );
		rdbtnWifi.setActionCommand( Globals.WifiActionCommand );
		JRadioButton rdbtnCom = new JRadioButton( "COM" );
		rdbtnCom.setActionCommand( Globals.ComActionCommand );
		panelCommChoice.add( Box.createVerticalGlue() );
		panelCommChoice.add( rdbtnWifi );
		panelCommChoice.add( Box.createVerticalGlue() );
		panelCommChoice.add( rdbtnCom );
		panelCommChoice.add( Box.createVerticalGlue() );
		groupCommType = new ButtonGroup();
		groupCommType.add( rdbtnWifi );
		rdbtnWifi.setSelected( true );
		groupCommType.add( rdbtnCom );

		// Communication settings
		panelWifiSettings = new WifiPanel();
		

		// Fill the communication panel
		add( Box.createHorizontalStrut( 5 ) );
		add( panelCommChoice );
		add( Box.createHorizontalGlue() );
		add( panelWifiSettings );
		add( Box.createHorizontalStrut( 5 ) );
	}

	public JTextField getTextIP ( ) {
		return panelWifiSettings.getTextIP();
	}

	public JTextField getTextPort ( ) {
		return panelWifiSettings.getTextPort();
	}

	public ButtonGroup getGroupCommType ( ) {
		return groupCommType;
	}

	public String getCommMethod ( ) {
		String s = groupCommType.getSelection().getActionCommand();
		String url;
		if ( s == "COM" )
			url = "GET ";
		else
			url = "http://" + getTextIP().getText() + ":" + getTextPort().getText();
		return url;
	}

}
