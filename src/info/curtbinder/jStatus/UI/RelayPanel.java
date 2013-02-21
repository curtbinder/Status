package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.Relay;
import info.curtbinder.jStatus.Classes.Status;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class RelayPanel extends JPanel {

	private static final long serialVersionUID = 7377000441986611539L;
	public JToggleButton[] portButtons = new JToggleButton[8];
	private JLabel[] portLabels = new JLabel[8];
	private JPanel[] portPanels = new JPanel[8];
	public JButton[] portGreen = new JButton[8];

	public RelayPanel () {
		super();
		// Relay panel
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		setAlignmentX( Component.LEFT_ALIGNMENT );
		JLabel lblMain = new JLabel( "Main:" );
		lblMain.setAlignmentY( Component.TOP_ALIGNMENT );
		add( lblMain );

		JPanel[] portRows = new JPanel[4];
		for ( int i = 0, j = 4; i < 4; i++, j++ ) {
			portRows[i] = new JPanel();

			portPanels[i] = new JPanel();
			portPanels[j] = new JPanel();

			portLabels[i] = new JLabel( String.format( "Port %d:", i + 1 ) );
			portLabels[i].setAlignmentY( Component.TOP_ALIGNMENT );
			portLabels[j] = new JLabel( String.format( "Port %d:", j + 1 ) );
			portLabels[j].setAlignmentY( Component.TOP_ALIGNMENT );

			portButtons[i] = new JToggleButton( Globals.labelOff );
			portButtons[i].addActionListener( new TogglePortAdapter( i + 1 ) );
			portButtons[j] = new JToggleButton( Globals.labelOff );
			portButtons[j].addActionListener( new TogglePortAdapter( j + 1 ) );

			portGreen[i] =
					new JButton( new ImageIcon(
						MainFrame.class.getResource( Globals.greenIconName ) ) );
			portGreen[i].setAlignmentY( Component.TOP_ALIGNMENT );
			portGreen[i].setAlignmentX( Component.LEFT_ALIGNMENT );
			portGreen[i].setContentAreaFilled( false );
			portGreen[i].setBorderPainted( false );
			portGreen[i].setBorder( null );
			portGreen[i].addActionListener( new ClearMaskAdapter( i + 1 ) );
			portGreen[i].setVisible( false );

			portGreen[j] =
					new JButton( new ImageIcon(
						MainFrame.class.getResource( Globals.greenIconName ) ) );
			portGreen[j].setAlignmentY( Component.TOP_ALIGNMENT );
			portGreen[j].setAlignmentX( Component.LEFT_ALIGNMENT );
			portGreen[j].setContentAreaFilled( false );
			portGreen[j].setBorderPainted( false );
			portGreen[j].setBorder( null );
			portGreen[j].addActionListener( new ClearMaskAdapter( j + 1 ) );
			portGreen[j].setVisible( false );

			portPanels[i].add( portLabels[i] );
			// portPanels[i].add( Box.createHorizontalStrut( 5 ) );
			portPanels[i].add( portButtons[i] );
			portPanels[i].add( portGreen[i] );
			// portPanels[i].add( Box.createHorizontalStrut(5) );

			portPanels[j].add( portLabels[j] );
			// portPanels[j].add( Box.createHorizontalStrut( 5 ) );
			portPanels[j].add( portButtons[j] );
			portPanels[j].add( portGreen[j] );
			// portPanels[j].add( Box.createHorizontalStrut(5) );

			portRows[i].add( portPanels[i] );
			portRows[i].add( Box.createHorizontalStrut( 5 ) );
			portRows[i].add( portPanels[j] );
			add( portRows[i] );
		}
	}

	public void enableRelayButtons ( ) {
		for ( int i = 0; i < 8; i++ ) {
			portButtons[i].setEnabled( true );
			portGreen[i].setEnabled( true );
		}
	}

	public void disableRelayButtons ( ) {
		for ( int i = 0; i < 8; i++ ) {
			portButtons[i].setEnabled( false );
			portGreen[i].setEnabled( false );
		}
	}

	public class TogglePortAdapter implements ActionListener {

		Status m;
		int port;

		public TogglePortAdapter ( int portNumber ) {
			this.m = StatusApp.statusClass;
			this.port = portNumber;
		}

		@Override
		public void actionPerformed ( ActionEvent arg0 ) {
			try {
				String txt = portButtons[port - 1].getText();
				byte mode = Relay.PORT_STATE_OFF;
				if ( txt.equals( Globals.labelOff ) ) {
					mode = Relay.PORT_STATE_ON;
				}
				// if it's not already visible, show the clear mask button
				if ( !portGreen[port - 1].isVisible() ) {
					portGreen[port - 1].setVisible( true );
				}
				m.sendRelayCommand( port, mode );
			} catch ( Exception e ) {
				JOptionPane
						.showMessageDialog( StatusApp.statusUI,
											"Error with Refresh",
											"Refresh Error",
											JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}

	public class ClearMaskAdapter implements ActionListener {

		Status m;
		int port;

		public ClearMaskAdapter ( int portNumber ) {
			this.m = StatusApp.statusClass;
			this.port = portNumber;
		}

		@Override
		public void actionPerformed ( ActionEvent arg0 ) {
			try {
				// hide the button
				portGreen[port - 1].setVisible( false );
				// clears the mask
				m.sendRelayCommand( port, Relay.PORT_STATE_AUTO );
			} catch ( Exception e ) {
				JOptionPane
						.showMessageDialog( StatusApp.statusUI,
											"Error with Refresh",
											"Refresh Error",
											JOptionPane.INFORMATION_MESSAGE );
			}
		}
	}
}
