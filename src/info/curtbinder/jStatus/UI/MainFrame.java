package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.Memory;
import info.curtbinder.jStatus.Classes.Status;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int minWidth = 390; // 370;
	private static final int minHeight = 465; // 435
	private JPanel contentPane;
	// private Status statusClass;
	// TODO should make these private and have a getter() for them
	private JTabbedPane tabbedPane;
	public MemoryTab tabMemory;
	public StatusTab tabStatus;
	public CommandsTab tabCommands;
	private String host;
	private String port;
	private String comtype;

	public MainFrame ( Status m ) {
		setTitle( Globals.appTitle );
		// set the application icon
		Image img = null;
		try {
			img = ImageIO.read( getClass().getResource( Globals.appIconName ) );
			setIconImage( img );
		} catch ( IOException e ) {
			// error getting app icon, so don't set one
		}
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		// center on screen
		setBounds( 100, 100, minWidth, minHeight );
		setMinimumSize( new Dimension( minWidth, minHeight ) );
		setJMenuBar( new StatusMenuBar() );

		contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BoxLayout( contentPane, BoxLayout.Y_AXIS ) );
		setContentPane( contentPane );

		// panelCommunication = new CommunicationsPanel();

		// Tabbed window panel
		tabbedPane = new JTabbedPane( JTabbedPane.TOP );
		tabMemory = new MemoryTab( m );
		tabStatus = new StatusTab( m );
		tabCommands = new CommandsTab( m );
		tabbedPane.addTab( tabStatus.NAME, null, tabStatus, null );
		tabbedPane.addTab( tabMemory.NAME, null, tabMemory, null );
		tabbedPane.addTab( tabCommands.NAME, null, tabCommands, null );

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new BoxLayout( buttonPanel, BoxLayout.X_AXIS ) );
		JButton btnClose = new JButton( new CloseAction() );
		buttonPanel.add( Box.createHorizontalGlue() );
		buttonPanel.add( btnClose );

		// Add the panels to the main pane
		// contentPane.add( panelCommunication );
		contentPane.add( tabbedPane );
		contentPane.add( Box.createVerticalStrut( 5 ) );
		contentPane.add( buttonPanel );

	}

	public String getCommMethod ( ) {
		String url;
		if ( comtype == Globals.ComActionCommand )
			url = "GET ";
		else
			url = "http://" + host + ":" + port;
		return url;
	}

	public void setComType ( String s ) {
		comtype = s;
	}

	public String getComType ( ) {
		return comtype;
	}

	public void setDefaults ( ) {
		setHost( Globals.defaultHost );
		setPort( Globals.defaultPort );
		setComType( Globals.defaultComType );
		tabMemory.setReadValue( "" );
		tabMemory.setWriteStatus( "" );
	}

	public void setHost ( String s ) {
		host = s;
	}

	public String getHost ( ) {
		return host;
	}

	public void setPort ( String s ) {
		port = s;
	}

	public String getPort ( ) {
		return port;
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
