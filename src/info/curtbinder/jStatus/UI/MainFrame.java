package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.AboutDialog;
import info.curtbinder.jStatus.Classes.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
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
	private static final int minWidth = 370;
	private static final int minHeight = 420;
	private static final int commLabelWidth = 50;
	private static final int commLabelHeight = 15;
	private JPanel contentPane;
	private JTextField textIP;
	private JTextField textPort;
	private JTextField textLocation;
	private JTextField textWriteValue;
	private JLabel lblReadValue;
	private JLabel lblWriteStatus;
	private ButtonGroup groupCommType;
	private ButtonGroup groupMemType;
	
	private CloseAction closeAction = new CloseAction();
	private AboutAction aboutAction = new AboutAction();

	public class CloseAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public CloseAction() {
			putValue(NAME, "Close");
		}
		public void actionPerformed(ActionEvent ae) {
			System.exit(0);
		}
	}
	public class AboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public AboutAction() {
			putValue(NAME, "About");
		}
		public void actionPerformed(ActionEvent ae) {
			AboutDialog d = new AboutDialog(StatusApp.statusUI,
					new ImageIcon(MainFrame.class.getResource(Globals.appIconName)),
					"RA Status", 
					"Monitor the status of the controller");
			d.setAppVersion(Globals.versionMajor, Globals.versionMinor,
					Globals.versionRevision, Globals.versionBuild);
			d.setCopyright(Globals.copyrightInfo);
			d.setBanner(new ImageIcon(MainFrame.class.getResource(Globals.bannerIconName)));
			d.setURL(Globals.url);
			d.setCreditors(Globals.creditList);
			d.setLicense(Globals.legal);
			d.showAbout();
		}
	}
	
	public MainFrame(Status m) {
		setTitle(Globals.appTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center on screen
		setBounds(100, 100, minWidth, minHeight);
		setMinimumSize(new Dimension(minWidth, minHeight));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenuItem mntmClose = new JMenuItem(closeAction);
		mnFile.add(mntmClose);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JMenuItem mntmAbout = new JMenuItem(aboutAction);
		mnHelp.add(mntmAbout);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		
		// Communication panel
		JPanel panelCommunication = new JPanel();
		panelCommunication.setBorder(new TitledBorder(null, "Communications:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCommunication.setLayout(new BoxLayout(panelCommunication, BoxLayout.X_AXIS));

		// Communication choices
		JPanel panelCommChoice = new JPanel();
		panelCommChoice.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Type:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panelCommChoice.setLayout(new BoxLayout(panelCommChoice, BoxLayout.Y_AXIS));
		JRadioButton rdbtnWifi = new JRadioButton("Wifi");
		rdbtnWifi.setActionCommand(Globals.WifiActionCommand);
		JRadioButton rdbtnCom = new JRadioButton("COM");
		rdbtnCom.setActionCommand(Globals.ComActionCommand);
		panelCommChoice.add(Box.createVerticalGlue());
		panelCommChoice.add(rdbtnWifi);
		panelCommChoice.add(Box.createVerticalGlue());
		panelCommChoice.add(rdbtnCom);
		panelCommChoice.add(Box.createVerticalGlue());
		groupCommType = new ButtonGroup();
		groupCommType.add(rdbtnWifi);
		rdbtnWifi.setSelected(true);
		groupCommType.add(rdbtnCom);
		
		// Communication settings
		JPanel panelCommSettings = new JPanel();
		panelCommSettings.setLayout(new BoxLayout(panelCommSettings, BoxLayout.Y_AXIS));
		JPanel boxIPCom = new JPanel();
		FlowLayout flowLayout = (FlowLayout) boxIPCom.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		boxIPCom.setAlignmentX(Component.TOP_ALIGNMENT);
		JLabel lblIp = new JLabel("IP:");
		lblIp.setPreferredSize(new Dimension(commLabelWidth, commLabelHeight));
		textIP = new JTextField();
		textIP.setAlignmentX(Component.TOP_ALIGNMENT);
		lblIp.setLabelFor(textIP);
		textIP.setHorizontalAlignment(SwingConstants.LEFT);
		textIP.setColumns(10);
		boxIPCom.add(lblIp);
		boxIPCom.add(textIP);
		
		JPanel boxPortBaud = new JPanel();
		flowLayout = (FlowLayout) boxPortBaud.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		boxPortBaud.setAlignmentX(Component.TOP_ALIGNMENT);
		JLabel lblPort = new JLabel("Port:");
		lblPort.setPreferredSize(new Dimension(commLabelWidth, commLabelHeight));
		textPort = new JTextField();
		lblPort.setLabelFor(textPort);
		textPort.setAlignmentX(Component.TOP_ALIGNMENT);
		textPort.setColumns(5);
		boxPortBaud.add(lblPort);
		boxPortBaud.add(textPort);
		
		panelCommSettings.add(Box.createVerticalGlue());
		panelCommSettings.add(boxIPCom);
		panelCommSettings.add(Box.createVerticalGlue());
		panelCommSettings.add(boxPortBaud);
		panelCommSettings.add(Box.createVerticalGlue());
		
		// Fill the communication panel
		panelCommunication.add(Box.createHorizontalStrut(5));
		panelCommunication.add(panelCommChoice);
		panelCommunication.add(Box.createHorizontalGlue());
		panelCommunication.add(panelCommSettings);
		panelCommunication.add(Box.createHorizontalStrut(5));
		
		// Tabbed window panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel tabMemory = new JPanel();
		tabbedPane.addTab("Memory", null, tabMemory, null);
		tabMemory.setLayout(new BoxLayout(tabMemory, BoxLayout.Y_AXIS));
		// Start of Memory panel
		// top half
		JPanel topHalf = new JPanel();
		topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.X_AXIS));
		
		// top left
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JPanel locationBox = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) locationBox.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		JLabel lblLocation = new JLabel("Location:");
		textLocation = new JTextField();
		textLocation.setColumns(10);
		lblLocation.setLabelFor(textLocation);
		locationBox.add(lblLocation);
		locationBox.add(textLocation);
		topLeft.add(Box.createVerticalGlue());
		topLeft.add(locationBox);
		topLeft.add(Box.createVerticalGlue());
		
		// top right
		JPanel topRight = new JPanel();
		topRight.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Status Type:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		topRight.setLayout(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JRadioButton rdbtnByte = new JRadioButton("Byte Value");
		rdbtnByte.setActionCommand("/mb");
		JRadioButton rdbtnInt = new JRadioButton("Integer Value");
		rdbtnInt.setActionCommand("/mi");
		topRight.add(Box.createVerticalGlue());
		topRight.add(rdbtnByte);
		topRight.add(Box.createVerticalGlue());
		topRight.add(rdbtnInt);
		topRight.add(Box.createVerticalGlue());
		groupMemType = new ButtonGroup();
		groupMemType.add(rdbtnByte);
		rdbtnByte.setSelected(true);
		groupMemType.add(rdbtnInt);
		
		topHalf.add(topLeft);
		topHalf.add(Box.createHorizontalGlue());
		topHalf.add(topRight);
		
		// bottom half
		JPanel bottomHalf = new JPanel();
		bottomHalf.setLayout(new BoxLayout(bottomHalf, BoxLayout.X_AXIS));
		
		// bottom left
		JPanel bottomLeft = new JPanel();
		bottomLeft.setBorder(new TitledBorder(null, "Read Value:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
		lblReadValue = new JLabel("");  // clear value first
		lblReadValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblReadValue.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnReadValue = new JButton("Read");
		btnReadValue.addActionListener(new ReadValueAdapter(m));
		btnReadValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomLeft.add(Box.createVerticalStrut(5));
		bottomLeft.add(Box.createVerticalGlue());
		bottomLeft.add(lblReadValue);
		bottomLeft.add(Box.createVerticalGlue());
		bottomLeft.add(btnReadValue);
		bottomLeft.add(Box.createVerticalStrut(5));
		
		// bottom right
		JPanel bottomRight = new JPanel();
		bottomRight.setBorder(new TitledBorder(null, "Write Value:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bottomRight.setLayout(new BoxLayout(bottomRight, BoxLayout.Y_AXIS));
		JPanel boxValue = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) boxValue.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		boxValue.setAlignmentX(0.5f);
		JLabel lblWrite = new JLabel("Value:");
		lblWrite.setAlignmentX(Component.TOP_ALIGNMENT);
		textWriteValue = new JTextField();
		textWriteValue.setColumns(5);
		textWriteValue.setAlignmentX(Component.TOP_ALIGNMENT);
		boxValue.add(lblWrite);
		boxValue.add(Box.createHorizontalStrut(5));
		boxValue.add(textWriteValue);
		boxValue.add(Box.createHorizontalGlue());
		JPanel boxStatus = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) boxStatus.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		boxStatus.setAlignmentX(0.5f);
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setAlignmentX(Component.TOP_ALIGNMENT);
		lblWriteStatus = new JLabel("");
		lblWriteStatus.setAlignmentX(Component.TOP_ALIGNMENT);
		boxStatus.add(lblStatus);
		boxValue.add(Box.createHorizontalStrut(5));
		boxStatus.add(lblWriteStatus);
		boxStatus.add(Box.createHorizontalGlue());
		JButton btnWriteValue = new JButton("Write Value");
		btnWriteValue.setAlignmentX(0.5f);
		btnWriteValue.addActionListener(new WriteValueAdapter(m));
		
		bottomRight.add(Box.createVerticalGlue());
		bottomRight.add(boxValue);
		bottomRight.add(boxStatus);
		bottomRight.add(Box.createVerticalGlue());
		bottomRight.add(btnWriteValue);
		bottomRight.add(Box.createVerticalStrut(5));
		
		bottomHalf.add(bottomLeft);
		bottomHalf.add(Box.createHorizontalGlue());
		bottomHalf.add(bottomRight);
		
		tabMemory.add(topHalf);
		tabMemory.add(Box.createVerticalGlue());
		tabMemory.add(bottomHalf);
		// end of Status panel
		
		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		JButton btnClose = new JButton(closeAction);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(btnClose);
		
		// Add the panels to the main pane
		contentPane.add(panelCommunication);
		contentPane.add(tabbedPane);
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(buttonPanel);
	}

	public String getCommMethod() {
		String s = groupCommType.getSelection().getActionCommand();
		String url;
		if ( s == "COM" )
			url = "GET ";
		else
			url = "http://" + textIP.getText() + ":" + textPort.getText();
		return url;
	}
	
	public String getMemType() {
		return groupMemType.getSelection().getActionCommand();
	}

	public String getTextLocation() {
		return textLocation.getText();
	}
	
	public void setReadValue(String s) {
		lblReadValue.setText(s);
	}
	
	public void setWriteStatus(String s) {
		lblWriteStatus.setText(s);
	}

	public void setDefaults() {
		setIP("10.0.42.40");
		setPort("2000");
		setReadValue("");
		setWriteStatus("");
	}
	
	public void setIP(String s) {
		textIP.setText(s);
	}
	
	public String getIP() {
		return textIP.getText();
	}
	
	public void setPort(String s) {
		textPort.setText(s);
	}
	
	public String getPort() {
		return textPort.getText();
	}
	
	public String getWriteValue() {
		return textWriteValue.getText();
	}
}
