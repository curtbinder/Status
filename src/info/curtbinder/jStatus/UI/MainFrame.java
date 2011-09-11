package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.*;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int minWidth = 380; // 370;
	private static final int minHeight = 420;
	private JPanel contentPane;
	// private Status statusClass;
	// TODO should make these private and have a getter() for them
	private JTabbedPane tabbedPane;
	public MemoryTab tabMemory;
	public StatusTab tabStatus;
	public DateTimeTab tabDateTime;
	private CommunicationsPanel panelCommunication;

	public MainFrame ( Status m ) {
		setTitle( Globals.appTitle );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		// center on screen
		setBounds( 100, 100, minWidth, minHeight );
		setMinimumSize( new Dimension( minWidth, minHeight ) );
		// statusClass = m;
		setJMenuBar( new StatusMenuBar() );

		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BoxLayout( contentPane, BoxLayout.Y_AXIS ) );
		setContentPane( contentPane );

		panelCommunication = new CommunicationsPanel();

		// Tabbed window panel
		tabbedPane = new JTabbedPane( JTabbedPane.TOP );
		tabMemory = new MemoryTab( m );
		tabStatus = new StatusTab( m );
		tabDateTime = new DateTimeTab( m );
		tabbedPane.addTab( tabStatus.NAME, null, tabStatus, null );
		tabbedPane.addTab( tabMemory.NAME, null, tabMemory, null );
		tabbedPane.addTab( tabDateTime.NAME, null, tabDateTime, null );

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new BoxLayout( buttonPanel, BoxLayout.X_AXIS ) );
		JButton btnClose = new JButton( new CloseAction() );
		buttonPanel.add( Box.createHorizontalGlue() );
		buttonPanel.add( btnClose );

		// Add the panels to the main pane
		contentPane.add( panelCommunication );
		contentPane.add( tabbedPane );
		contentPane.add( Box.createVerticalStrut( 5 ) );
		contentPane.add( buttonPanel );
	}

	public String getCommMethod ( ) {
		return panelCommunication.getCommMethod();
	}

	public void setDefaults ( ) {
		setIP( Globals.defaultIP );
		setPort( Globals.defaultPort );
		tabMemory.setReadValue( "" );
		tabMemory.setWriteStatus( "" );
	}

	public void setIP ( String s ) {
		panelCommunication.getTextIP().setText( s );
	}

	public String getIP ( ) {
		return panelCommunication.getTextIP().getText();
	}

	public void setPort ( String s ) {
		panelCommunication.getTextPort().setText( s );
	}

	public String getPort ( ) {
		return panelCommunication.getTextPort().getText();
	}

	public void updateMemorySettings ( Memory m ) {
		if ( !tabbedPane.getSelectedComponent().toString()
				.equals( tabMemory.toString() ) ) {
			for ( Component c : tabbedPane.getComponents() ) {
				if ( c.toString().equals( tabMemory.toString() ) ) {
					tabbedPane.setSelectedComponent( c );
					break;
				}
			}
		}
		tabMemory.setMemoryLocation( m.getLocation() );
		tabMemory.setMemoryType( m.getType() );
	}
}
