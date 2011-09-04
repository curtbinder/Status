package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Controller;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.RefreshAdapter;
import info.curtbinder.jStatus.Classes.Status;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StatusTab extends JPanel {
	private static final long serialVersionUID = 3563032372620661375L;
	private JButton btnRefresh;
	private JLabel lblLastUpdateTime;
	private JLabel lblT1;
	private JLabel lblT2;
	private JLabel lblT3;
	private JLabel lblPH;
	private JLabel lblPwmActinic;
	private JLabel lblPwmDaylight;
	private JLabel lblLow;
	private JLabel lblHigh;
	//private Status statusClass;
	private Controller ra;

	public StatusTab ( Status s ) {
		super();
		//statusClass = s;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setAlignmentY(Component.TOP_ALIGNMENT);
		buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		btnRefresh = new JButton(new ImageIcon(MainFrame.class
				.getResource(Globals.refreshIconName)));
		btnRefresh.setAlignmentY(Component.TOP_ALIGNMENT);
		btnRefresh.setBorderPainted(false);
		btnRefresh.setContentAreaFilled(false);
		btnRefresh.addActionListener(new RefreshAdapter(s));
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
		gbl_status.columnWidths = new int[] { 60, 72, 36, 67, 64, 0 };
		gbl_status.rowHeights = new int[] { 0, 0, 0, 0, 20, 0, 0 };
		gbl_status.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_status.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				Double.MIN_VALUE };
		status.setLayout(gbl_status);

		JLabel lblTemp1Label = new JLabel("Temp1:");
		lblTemp1Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp1Label = new GridBagConstraints();
		gbc_lblTemp1Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp1Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp1Label.gridx = 0;
		gbc_lblTemp1Label.gridy = 0;
		status.add(lblTemp1Label, gbc_lblTemp1Label);

		lblT1 = new JLabel(Globals.defaultStatusText);
		GridBagConstraints gbc_lblT1 = new GridBagConstraints();
		gbc_lblT1.insets = new Insets(0, 0, 5, 5);
		gbc_lblT1.gridx = 1;
		gbc_lblT1.gridy = 0;
		status.add(lblT1, gbc_lblT1);

		JLabel lblPwmDaylightLabel = new JLabel("DP:");
		lblPwmDaylightLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPwmDaylightLabel = new GridBagConstraints();
		gbc_lblPwmDaylightLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPwmDaylightLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPwmDaylightLabel.gridx = 3;
		gbc_lblPwmDaylightLabel.gridy = 0;
		status.add(lblPwmDaylightLabel, gbc_lblPwmDaylightLabel);

		lblPwmDaylight = new JLabel(Globals.defaultStatusText);
		GridBagConstraints gbc_lblPwmDaylight = new GridBagConstraints();
		gbc_lblPwmDaylight.insets = new Insets(0, 0, 5, 0);
		gbc_lblPwmDaylight.gridx = 4;
		gbc_lblPwmDaylight.gridy = 0;
		status.add(lblPwmDaylight, gbc_lblPwmDaylight);
		
		JLabel lblTemp2Label = new JLabel("Temp2:");
		lblTemp2Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp2Label = new GridBagConstraints();
		gbc_lblTemp2Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp2Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp2Label.gridx = 0;
		gbc_lblTemp2Label.gridy = 1;
		status.add(lblTemp2Label, gbc_lblTemp2Label);

		lblT2 = new JLabel(Globals.defaultStatusText);
		GridBagConstraints gbc_lblT2 = new GridBagConstraints();
		gbc_lblT2.insets = new Insets(0, 0, 5, 5);
		gbc_lblT2.gridx = 1;
		gbc_lblT2.gridy = 1;
		status.add(lblT2, gbc_lblT2);

		JLabel lblPwmActinicLabel = new JLabel("AP:");
		lblPwmActinicLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblPwmActinicLabel = new GridBagConstraints();
		gbc_lblPwmActinicLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPwmActinicLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPwmActinicLabel.gridx = 3;
		gbc_lblPwmActinicLabel.gridy = 1;
		status.add(lblPwmActinicLabel, gbc_lblPwmActinicLabel);

		lblPwmActinic = new JLabel(Globals.defaultStatusText);
		GridBagConstraints gbc_lblPwmActinic = new GridBagConstraints();
		gbc_lblPwmActinic.insets = new Insets(0, 0, 5, 0);
		gbc_lblPwmActinic.gridx = 4;
		gbc_lblPwmActinic.gridy = 1;
		status.add(lblPwmActinic, gbc_lblPwmActinic);

		JLabel lblTemp3Label = new JLabel("Temp3:");
		lblTemp3Label.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTemp3Label = new GridBagConstraints();
		gbc_lblTemp3Label.anchor = GridBagConstraints.EAST;
		gbc_lblTemp3Label.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemp3Label.gridx = 0;
		gbc_lblTemp3Label.gridy = 2;
		status.add(lblTemp3Label, gbc_lblTemp3Label);

		lblT3 = new JLabel(Globals.defaultStatusText);
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

		lblLow = new JLabel(Globals.defaultStatusText);
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

		lblPH = new JLabel(Globals.defaultStatusText);
		GridBagConstraints gbc_lblPh = new GridBagConstraints();
		gbc_lblPh.insets = new Insets(0, 0, 5, 5);
		gbc_lblPh.gridx = 1;
		gbc_lblPh.gridy = 3;
		status.add(lblPH, gbc_lblPh);

		JLabel lblAtoHigh = new JLabel("ATO High:");
		lblAtoHigh.setFont(new Font("Dialog", Font.PLAIN, 12));
		GridBagConstraints gbc_lblAtoHigh = new GridBagConstraints();
		gbc_lblAtoHigh.insets = new Insets(0, 0, 5, 5);
		gbc_lblAtoHigh.gridx = 3;
		gbc_lblAtoHigh.gridy = 3;
		status.add(lblAtoHigh, gbc_lblAtoHigh);

		lblHigh = new JLabel(Globals.defaultStatusText);
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
		JLabel[] lblMainRelays = new JLabel[8];

		relayPanel.add(lblMain);
		for ( int i = 0; i < 8; i++ ) {
			// change strings to be the icons, start with OFF icons first
			lblMainRelays[i] = new JLabel(String.format("#%d", i + 1));
			lblMainRelays[i].setAlignmentY(Component.TOP_ALIGNMENT);
			relayPanel.add(lblMainRelays[i]);
		}
		// TODO convert relay panel to being created and have an array of them
		// placed inside a panel

		status.add(relayPanel, gbc_panel);
		relayPanel.setVisible(false);

		add(buttonPanel);
		add(Box.createVerticalStrut(5));
		add(status);
	}

	public void enableRefreshButton ( ) {
		btnRefresh.setEnabled(true);
	}

	public void disableRefreshButton ( ) {
		btnRefresh.setEnabled(false);
	}

	public void setControllerInformation ( Controller ra ) {
		this.ra = ra;
		DateFormat dft = DateFormat.getDateTimeInstance(DateFormat.DEFAULT,
				DateFormat.DEFAULT, Locale.getDefault());
		lblLastUpdateTime.setText(dft.format(new Date()));
		refreshData();
	}

	private void refreshData ( ) {
		// update the data on the screen
		lblT1.setText(ra.getTemp1());
		lblT2.setText(ra.getTemp2());
		lblT3.setText(ra.getTemp3());
		lblPH.setText(ra.getPH());
		lblPwmActinic.setText(ra.getPwmA());
		lblPwmDaylight.setText(ra.getPwmD());
		lblLow.setText(ra.getAtoLowText());
		lblHigh.setText(ra.getAtoHighText());
	}
}
