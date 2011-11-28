package info.curtbinder.jStatus.UI;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;


public class RelayPanel extends JPanel {

	private static final long serialVersionUID = 7377000441986611539L;
	public JToggleButton[] portButtons = new JToggleButton[8];
	private JLabel[] portLabels = new JLabel[8];
	private JPanel[] portPanels = new JPanel[8];

	public RelayPanel () {
		super();
		// Relay panel
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
		setAlignmentX( Component.LEFT_ALIGNMENT );
		JLabel lblMain = new JLabel( "Main:" );
		lblMain.setAlignmentY( Component.TOP_ALIGNMENT );
		add( lblMain );
		
		JPanel[] portRows = new JPanel[4];
		for ( int i = 0, j = 3; i < 4; i++, j++ ) {
			portRows[i] = new JPanel();
			
			portPanels[i] = new JPanel();
			portPanels[j] = new JPanel();
			
			portLabels[i] = new JLabel( String.format( "Port %d:", i + 1 ) );
			portLabels[i].setAlignmentY( Component.TOP_ALIGNMENT );
			portLabels[j] = new JLabel( String.format( "Port %d:", j + 1 ) );
			portLabels[j].setAlignmentY( Component.TOP_ALIGNMENT );
			
			portButtons[i] = new JToggleButton( "OFF" );
			portButtons[j] = new JToggleButton( "OFF" );
			// add in action listener
			
			portPanels[i].add( portLabels[i] );
			portPanels[i].add( Box.createHorizontalStrut(5) );
			portPanels[i].add( portButtons[i] );
			// Add on the mask button
			//portPanels[i].add( Box.createHorizontalStrut(5) );
			portPanels[j].add( portLabels[j] );
			portPanels[j].add( Box.createHorizontalStrut(5) );
			portPanels[j].add( portButtons[j] );
			// Add on the mask button
			//portPanels[j].add( Box.createHorizontalStrut(5) );
			
			portRows[i].add( portPanels[i] );
			portRows[i].add( Box.createHorizontalStrut(5) );
			portRows[i].add( portPanels[j] );
			add( portRows[i] );
		}
	}

}
