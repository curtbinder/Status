package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.Log;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
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
	private JRadioButton rdbtnWifi;
	private JRadioButton rdbtnCom;
	
	private ComAction ca = new ComAction();
	private WifiAction wa = new WifiAction();
	
	private class ComAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public ComAction() {
			putValue( NAME, Globals.ComActionCommand );
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			setComMethod();
			
		}
		
	};
	private class WifiAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public WifiAction() {
			putValue( NAME, Globals.WifiActionCommand );
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setWifiMethod();
		}
	};

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
		rdbtnWifi = new JRadioButton( Globals.comWifiLabel );
		rdbtnWifi.setAction(wa);
		rdbtnWifi.setActionCommand(Globals.WifiActionCommand);
		rdbtnCom = new JRadioButton( Globals.comCOMLabel );
		rdbtnCom.setAction(ca);
		rdbtnCom.setActionCommand(Globals.ComActionCommand);
		panelCommChoice.add( Box.createVerticalGlue() );
		panelCommChoice.add( rdbtnWifi );
		panelCommChoice.add( Box.createVerticalGlue() );
		panelCommChoice.add( rdbtnCom );
		panelCommChoice.add( Box.createVerticalGlue() );
		groupCommType = new ButtonGroup();
		groupCommType.add( rdbtnWifi );
		//rdbtnWifi.setSelected( true );
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

	public JTextField getTextHost ( ) {
		return panelWifiSettings.getTextHost();
	}

	public JTextField getTextPort ( ) {
		return panelWifiSettings.getTextPort();
	}

	public ButtonGroup getGroupCommType ( ) {
		return groupCommType;
	}

	public String getCommMethod ( ) {
		String s = groupCommType.getSelection().getActionCommand();
		Log.i("Group Selection: " + s);
		return s;
	}

	public void setWifiMethod ( ) {
		Log.i("setWifiMethod");
		rdbtnWifi.setSelected( true );
	}

	public void setComMethod ( ) {
		Log.i("setComMethod");
		rdbtnCom.setSelected( true );
	}
}
