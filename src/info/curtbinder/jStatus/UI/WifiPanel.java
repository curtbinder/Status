package info.curtbinder.jStatus.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WifiPanel extends JPanel {
	private static final long serialVersionUID = 1779461731674037567L;
	private static final int ipLabelWidth = 50;
	private static final int ipLabelHeight = 15;
	private JTextField textIP;
	private JTextField textPort;

	public WifiPanel() {
		super();
		
		setLayout( new BoxLayout( this,
			BoxLayout.Y_AXIS ) );
		JPanel boxIPCom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) boxIPCom.getLayout();
		flowLayout.setAlignment( FlowLayout.LEFT );
		boxIPCom.setAlignmentX( Component.TOP_ALIGNMENT );
		JLabel lblIp = new JLabel( "IP:" );
		lblIp.setPreferredSize( new Dimension( ipLabelWidth,
					ipLabelHeight ) );
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
		lblPort.setPreferredSize( new Dimension( ipLabelWidth,
			ipLabelHeight ) );
		textPort = new JTextField();
		lblPort.setLabelFor( textPort );
		textPort.setAlignmentX( Component.TOP_ALIGNMENT );
		textPort.setColumns( 5 );
		boxPortBaud.add( lblPort );
		boxPortBaud.add( textPort );

		add( Box.createVerticalGlue() );
		add( boxIPCom );
		add( Box.createVerticalGlue() );
		add( boxPortBaud );
		add( Box.createVerticalGlue() );
	}

	public JTextField getTextIP ( ) {
		return textIP;
	}

	public JTextField getTextPort ( ) {
		return textPort;
	}
	
	
}
