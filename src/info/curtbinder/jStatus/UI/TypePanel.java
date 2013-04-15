package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.SerialConn;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TypePanel extends JPanel {
	private static final long serialVersionUID = 1779461731674037567L;
	private static final int ipLabelWidth = 50;
	private static final int ipLabelHeight = 15;
	private JTextField textHost;
	private JTextField textPort;
	private JComboBox<?> cboComPort;

	public TypePanel() {
		super();
		setLayout(new CardLayout());
		add(createWifiPanel(), Globals.comWifiLabel);
		add(createComPanel(), Globals.comCOMLabel);
	}
	
	private JPanel createWifiPanel ( ) {
		JPanel p = new JPanel();
		p.setLayout( new BoxLayout( p,
				BoxLayout.Y_AXIS ) );
		JPanel boxIPCom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) boxIPCom.getLayout();
		flowLayout.setAlignment( FlowLayout.LEFT );
		boxIPCom.setAlignmentX( Component.TOP_ALIGNMENT );
		JLabel lblHost = new JLabel( "Host:" );
		lblHost.setPreferredSize( new Dimension( ipLabelWidth,
					ipLabelHeight ) );
		textHost = new JTextField();
		textHost.setAlignmentX( Component.TOP_ALIGNMENT );
		lblHost.setLabelFor( textHost );
		textHost.setHorizontalAlignment( SwingConstants.LEFT );
		textHost.setColumns( 10 );
		boxIPCom.add( lblHost );
		boxIPCom.add( textHost );

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

		p.add( Box.createVerticalGlue() );
		p.add( boxIPCom );
		p.add( Box.createVerticalGlue() );
		p.add( boxPortBaud );
		p.add( Box.createVerticalGlue() );
		return p;
	}
	
	private JPanel createComPanel ( ) {
		JPanel p = new JPanel();
		p.setLayout( new BoxLayout( p,
				BoxLayout.Y_AXIS ) );
		JPanel cp = new JPanel();
		FlowLayout flowLayout = (FlowLayout) cp.getLayout();
		flowLayout.setAlignment( FlowLayout.LEFT );
		cp.setAlignmentX( Component.TOP_ALIGNMENT );
		JLabel lblHost = new JLabel( "Port:" );
		lblHost.setPreferredSize( new Dimension( ipLabelWidth,
					ipLabelHeight ) );
		List portList = SerialConn.getPortList();
		cboComPort = new JComboBox<Object>(portList.getItems());
		cboComPort.setAlignmentX( Component.TOP_ALIGNMENT );
		lblHost.setLabelFor( cboComPort );
		cp.add( lblHost );
		cp.add( cboComPort );
		p.add( Box.createVerticalGlue() );
		p.add( cp );
		p.add( Box.createVerticalGlue() );
		return p;
	}

	public JTextField getTextWifiHost ( ) {
		return textHost;
	}

	public JTextField getTextWifiPort ( ) {
		return textPort;
	}
	
	public JComboBox<?> getCboComPort ( ) {
		return cboComPort;
	}
	
	public void setWifiPanel ( ) {
		CardLayout cl = (CardLayout)this.getLayout();
		cl.show(this, Globals.comWifiLabel);
	}
	public void setComPanel ( ) {
		CardLayout cl = (CardLayout)this.getLayout();
		cl.show(this, Globals.comCOMLabel);		
	}
}
