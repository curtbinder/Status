package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int minWidth = 380; // 370;
	private static final int minHeight = 420;
	private static final int commLabelWidth = 50;
	private static final int commLabelHeight = 15;
	private JPanel contentPane;
	private JTextField textIP;
	private JTextField textPort;
	private ButtonGroup groupCommType;
	// private Status statusClass;
	// TODO should make these private and have a getter() for them
	private JTabbedPane tabbedPane;
	public MemoryTab tabMemory;
	public StatusTab tabStatus;

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

		// TODO create communications panel class
		// Communication panel
		JPanel panelCommunication = new JPanel();
		panelCommunication.setBorder( new TitledBorder( null,
			"Communications:", TitledBorder.LEADING, TitledBorder.TOP, null,
			null ) );
		panelCommunication.setLayout( new BoxLayout( panelCommunication,
			BoxLayout.X_AXIS ) );

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
		JPanel panelCommSettings = new JPanel();
		panelCommSettings.setLayout( new BoxLayout( panelCommSettings,
			BoxLayout.Y_AXIS ) );
		JPanel boxIPCom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) boxIPCom.getLayout();
		flowLayout.setAlignment( FlowLayout.LEFT );
		boxIPCom.setAlignmentX( Component.TOP_ALIGNMENT );
		JLabel lblIp = new JLabel( "IP:" );
		lblIp
				.setPreferredSize( new Dimension( commLabelWidth,
					commLabelHeight ) );
		textIP = new JTextField();
		textIP.setAlignmentX( Component.TOP_ALIGNMENT );
		lblIp.setLabelFor( textIP );
		textIP.setHorizontalAlignment( SwingConstants.LEFT );
		textIP.setColumns( 10 );
		boxIPCom.add( lblIp );
		boxIPCom.add( textIP );

		JPanel boxPortBaud = new JPanel();
		flowLayout = (FlowLayout) boxPortBaud.getLayout();
		flowLayout.setAlignment( FlowLayout.LEFT );
		boxPortBaud.setAlignmentX( Component.TOP_ALIGNMENT );
		JLabel lblPort = new JLabel( "Port:" );
		lblPort.setPreferredSize( new Dimension( commLabelWidth,
			commLabelHeight ) );
		textPort = new JTextField();
		lblPort.setLabelFor( textPort );
		textPort.setAlignmentX( Component.TOP_ALIGNMENT );
		textPort.setColumns( 5 );
		boxPortBaud.add( lblPort );
		boxPortBaud.add( textPort );

		panelCommSettings.add( Box.createVerticalGlue() );
		panelCommSettings.add( boxIPCom );
		panelCommSettings.add( Box.createVerticalGlue() );
		panelCommSettings.add( boxPortBaud );
		panelCommSettings.add( Box.createVerticalGlue() );

		// Fill the communication panel
		panelCommunication.add( Box.createHorizontalStrut( 5 ) );
		panelCommunication.add( panelCommChoice );
		panelCommunication.add( Box.createHorizontalGlue() );
		panelCommunication.add( panelCommSettings );
		panelCommunication.add( Box.createHorizontalStrut( 5 ) );

		// Tabbed window panel
		tabbedPane = new JTabbedPane( JTabbedPane.TOP );
		tabMemory = new MemoryTab( m );
		tabStatus = new StatusTab( m );
		// JPanel tabDateTime = createDateTimeTab();
		tabbedPane.addTab( tabStatus.NAME, null, tabStatus, null );
		tabbedPane.addTab( tabMemory.NAME, null, tabMemory, null );
		// tabbedPane.addTab("Date & Time", null, tabDateTime, null);

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
		String s = groupCommType.getSelection().getActionCommand();
		String url;
		if ( s == "COM" )
			url = "GET ";
		else
			url = "http://" + textIP.getText() + ":" + textPort.getText();
		return url;
	}

	public void setDefaults ( ) {
		setIP( Globals.defaultIP );
		setPort( Globals.defaultPort );
		tabMemory.setReadValue( "" );
		tabMemory.setWriteStatus( "" );
	}

	public void setIP ( String s ) {
		textIP.setText( s );
	}

	public String getIP ( ) {
		return textIP.getText();
	}

	public void setPort ( String s ) {
		textPort.setText( s );
	}

	public String getPort ( ) {
		return textPort.getText();
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
