package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.AboutDialog;
import info.curtbinder.Dialogs.TextDialog;
import info.curtbinder.jStatus.Classes.*;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private JButton btnReadValue;
	private JButton btnWriteValue;
	private Status statusClass;
	private JButton btnRefresh;
	private JLabel lblLastUpdateTime;
	
	private CloseAction closeAction = new CloseAction();
	private MemoryLocationsAction memoryAction= new MemoryLocationsAction();
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
	public class MemoryLocationsAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public MemoryLocationsAction() {
			putValue(NAME, "Memory Locations");
		}
		public void actionPerformed(ActionEvent ae) {
			TextDialog d = new TextDialog(null, "Memory Locations", "Location - Type - Reference", 300, 300);
			d.setWindowList(Globals.memoryLocationList);
			d.showDialog();
			Point p = StatusApp.statusUI.getLocation();
			int w = StatusApp.statusUI.getWidth();
			d.setBounds(p.x + w, p.y, 300, 300);
		}
	}
	
	public MainFrame(Status m) {
		setTitle(Globals.appTitle);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// center on screen
		setBounds(100, 100, minWidth, minHeight);
		setMinimumSize(new Dimension(minWidth, minHeight));
		statusClass = m;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenuItem mntmClose = new JMenuItem(closeAction);
		mnFile.add(mntmClose);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JMenuItem mntmMemory = new JMenuItem(memoryAction);
		JMenuItem mntmAbout = new JMenuItem(aboutAction);
		mnHelp.add(mntmMemory);
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
		JPanel tabMemory = createMemoryTab();
		JPanel tabStatus = createStatusTab();
		JPanel tabDateTime = createDateTimeTab();
		tabbedPane.addTab("Status", null, tabStatus, null);
		tabbedPane.addTab("Memory", null, tabMemory, null);
		tabbedPane.addTab("Date & Time", null, tabDateTime, null);
		
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

	private JPanel createStatusTab() {
		JPanel tabStatus = new JPanel();
		
		tabStatus.setBorder(new EmptyBorder(5, 5, 5, 5));
		tabStatus.setLayout(new BoxLayout(tabStatus, BoxLayout.Y_AXIS));
		
		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnRefresh = new JButton(new ImageIcon(MainFrame.class.getResource(Globals.refreshIconName)));
		btnRefresh.setAlignmentY(Component.TOP_ALIGNMENT);
		//btnRefresh.setVerticalAlignment(SwingConstants.TOP);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				btnRefresh.setEnabled(false);
			}
		});
		JLabel dateLabel = new JLabel("Last Updated:");
		dateLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		dateLabel.setAlignmentY(Component.TOP_ALIGNMENT);
		lblLastUpdateTime = new JLabel("Never");
		lblLastUpdateTime.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.add(dateLabel);
		buttonPanel.add(Box.createHorizontalStrut(5));
		buttonPanel.add(lblLastUpdateTime);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(btnRefresh);
		
		JPanel status = new JPanel();
		status.setAlignmentY(Component.TOP_ALIGNMENT);
		status.setAlignmentX(Component.LEFT_ALIGNMENT);
		GridBagLayout gbl_status = new GridBagLayout();
		gbl_status.columnWidths = new int[]{60, 72, 36, 67, 64, 0};
		gbl_status.rowHeights = new int[]{0, 0, 0, 0, 20, 0, 0};
		gbl_status.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_status.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		status.setLayout(gbl_status);
		
		JLabel lblTemp1Label = new JLabel("Temp1:");
		lblTemp1Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp1Label = new GridBagConstraints();
		gbc_lblTemp1Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp1Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp1Label.gridx = 0;
		gbc_lblTemp1Label.gridy = 0;
		status.add(lblTemp1Label, gbc_lblTemp1Label);
		
		JLabel lblT1 = new JLabel("T1");
		GridBagConstraints gbc_lblT1 = new GridBagConstraints();
		gbc_lblT1.insets = new Insets(0, 0, 5, 5);
		gbc_lblT1.gridx = 1;
		gbc_lblT1.gridy = 0;
		status.add(lblT1, gbc_lblT1);
		
		JLabel lblPwmActinicLabel = new JLabel("AP:");
		lblPwmActinicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPwmActinicLabel = new GridBagConstraints();
		gbc_lblPwmActinicLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPwmActinicLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPwmActinicLabel.gridx = 3;
		gbc_lblPwmActinicLabel.gridy = 0;
		status.add(lblPwmActinicLabel, gbc_lblPwmActinicLabel);
		
		JLabel lblPwmActinic = new JLabel("A%");
		GridBagConstraints gbc_lblPwmActinic = new GridBagConstraints();
		gbc_lblPwmActinic.insets = new Insets(0, 0, 5, 0);
		gbc_lblPwmActinic.gridx = 4;
		gbc_lblPwmActinic.gridy = 0;
		status.add(lblPwmActinic, gbc_lblPwmActinic);
		
		JLabel lblTemp2Label = new JLabel("Temp2:");
		lblTemp2Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp2Label = new GridBagConstraints();
		gbc_lblTemp2Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp2Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp2Label.gridx = 0;
		gbc_lblTemp2Label.gridy = 1;
		status.add(lblTemp2Label, gbc_lblTemp2Label);
		
		JLabel lblT2 = new JLabel("T2");
		GridBagConstraints gbc_lblT2 = new GridBagConstraints();
		gbc_lblT2.insets = new Insets(0, 0, 5, 5);
		gbc_lblT2.gridx = 1;
		gbc_lblT2.gridy = 1;
		status.add(lblT2, gbc_lblT2);
		
		JLabel lblPwmDaylightLabel = new JLabel("DP:");
		lblPwmDaylightLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPwmDaylightLabel = new GridBagConstraints();
		gbc_lblPwmDaylightLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPwmDaylightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPwmDaylightLabel.gridx = 3;
		gbc_lblPwmDaylightLabel.gridy = 1;
		status.add(lblPwmDaylightLabel, gbc_lblPwmDaylightLabel);
		
		JLabel lblPwmDaylight = new JLabel("D%");
		GridBagConstraints gbc_lblPwmDaylight = new GridBagConstraints();
		gbc_lblPwmDaylight.insets = new Insets(0, 0, 5, 0);
		gbc_lblPwmDaylight.gridx = 4;
		gbc_lblPwmDaylight.gridy = 1;
		status.add(lblPwmDaylight, gbc_lblPwmDaylight);
		
		JLabel lblTemp3Label = new JLabel("Temp3:");
		lblTemp3Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp3Label = new GridBagConstraints();
		gbc_lblTemp3Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp3Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp3Label.gridx = 0;
		gbc_lblTemp3Label.gridy = 2;
		status.add(lblTemp3Label, gbc_lblTemp3Label);
		
		JLabel lblT3 = new JLabel("T3");
		GridBagConstraints gbc_lblT3 = new GridBagConstraints();
		gbc_lblT3.insets = new Insets(0, 0, 5, 5);
		gbc_lblT3.gridx = 1;
		gbc_lblT3.gridy = 2;
		status.add(lblT3, gbc_lblT3);
		
		JLabel lblAtoLow = new JLabel("ATO Low:");
		lblAtoLow.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAtoLow = new GridBagConstraints();
		gbc_lblAtoLow.insets = new Insets(0, 0, 5, 5);
		gbc_lblAtoLow.gridx = 3;
		gbc_lblAtoLow.gridy = 2;
		status.add(lblAtoLow, gbc_lblAtoLow);
		
		JLabel lblLow = new JLabel("low");
		GridBagConstraints gbc_lblLow = new GridBagConstraints();
		gbc_lblLow.insets = new Insets(0, 0, 5, 0);
		gbc_lblLow.gridx = 4;
		gbc_lblLow.gridy = 2;
		status.add(lblLow, gbc_lblLow);
		
		JLabel lblPhLabel = new JLabel("PH:");
		lblPhLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPhLabel = new GridBagConstraints();
		gbc_lblPhLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPhLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhLabel.gridx = 0;
		gbc_lblPhLabel.gridy = 3;
		status.add(lblPhLabel, gbc_lblPhLabel);
		
		JLabel lblPh = new JLabel("PH");
		GridBagConstraints gbc_lblPh = new GridBagConstraints();
		gbc_lblPh.insets = new Insets(0, 0, 5, 5);
		gbc_lblPh.gridx = 1;
		gbc_lblPh.gridy = 3;
		status.add(lblPh, gbc_lblPh);
		
		JLabel lblAtoHigh = new JLabel("ATO High:");
		lblAtoHigh.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAtoHigh = new GridBagConstraints();
		gbc_lblAtoHigh.insets = new Insets(0, 0, 5, 5);
		gbc_lblAtoHigh.gridx = 3;
		gbc_lblAtoHigh.gridy = 3;
		status.add(lblAtoHigh, gbc_lblAtoHigh);
		
		JLabel lblHigh = new JLabel("high");
		GridBagConstraints gbc_lblHigh = new GridBagConstraints();
		gbc_lblHigh.insets = new Insets(0, 0, 5, 0);
		gbc_lblHigh.gridx = 4;
		gbc_lblHigh.gridy = 3;
		status.add(lblHigh, gbc_lblHigh);
		
		JPanel relayPanel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 5;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 5;
		relayPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		relayPanel.setLayout(new BoxLayout(relayPanel, BoxLayout.X_AXIS));
		JLabel lblMain = new JLabel("Main:");
		lblMain.setAlignmentY(Component.TOP_ALIGNMENT);
		JLabel [] lblMainRelays = new JLabel[8];
		
		relayPanel.add(lblMain);
		for ( int i = 0; i < 8; i++ ) {
			// change strings to be the icons, start with OFF icons first
			lblMainRelays[i] = new JLabel(String.format("#%d", i+1));
			lblMainRelays[i].setAlignmentY(Component.TOP_ALIGNMENT);
			relayPanel.add(lblMainRelays[i]);
		}
		// TODO convert relay panel to being created and have an array of them placed inside a panel

		status.add(relayPanel, gbc_panel);
		
		tabStatus.add(buttonPanel);
		tabStatus.add(Box.createVerticalStrut(5));
		tabStatus.add(status);
		
		return tabStatus;
	}
	
	private JPanel createDateTimeTab() {
		JPanel tabDateTime = new JPanel();
		return tabDateTime;
	}
	
	private JPanel createMemoryTab() {
		JPanel tabMemory = new JPanel();
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
		btnReadValue = new JButton("Read");
		btnReadValue.addActionListener(new ReadValueAdapter(statusClass));
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
		btnWriteValue = new JButton("Write Value");
		btnWriteValue.setAlignmentX(0.5f);
		btnWriteValue.addActionListener(new WriteValueAdapter(statusClass));
		
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
		
		return tabMemory;
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
	
	public void enableReadWriteButtons(boolean bEnable) {
		btnReadValue.setEnabled(bEnable);
		btnWriteValue.setEnabled(bEnable);
	}
}
