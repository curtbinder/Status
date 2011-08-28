package info.curtbinder.jStatus.UI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class AboutDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int minWidth = 350;
	private static final int minHeight = 270;
	/*
	private String appName = "APP NAME";
	private String appIcon = "icon.png";
	private String appDescription = "Description of app";
	private String appCopyright = "Copyright 2011";
	private String appBanner = "banner.png";
	private String appURL = "http://curtbinder.info/";
	private int verMajor = 0;		// Major version
	private int verMinor = 0;		// Minor version
	private int verRev = 0;			// Revision version
	private String verBuild = "";	// Build / tag
	*/
	private JLabel lblAppName = new JLabel("APP NAME");
	private JLabel lblAppVersion = new JLabel("0.0.0-build");
	private JLabel lblDescription = new JLabel("Description of app");
	private JLabel lblCopyright = new JLabel("Copyright 2011");
	private JLabel lblUrl = new JLabel("http://curtbinder.info/");


	public AboutDialog(JFrame owner) {
		super(owner);
		setTitle("About " + lblAppName.getText());
		
		setPreferredSize(new Dimension(minWidth, minHeight));
		//setMaximumSize(new Dimension(minWidth, minHeight));
		setMinimumSize(new Dimension(minWidth, minHeight));
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		setContentPane(contentPane);
		
		// Info panel
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		// add icon
		ImageIcon appIcon = new ImageIcon(AboutDialog.class.getResource("/images/Rss-green-64.png"));
		JLabel lblIcon = new JLabel(appIcon);
		lblIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(lblIcon);
		infoPanel.add(Box.createVerticalStrut(5));
		// App Info panel - contains app name and version
		JPanel appInfoPanel = new JPanel();
		appInfoPanel.setLayout(new BoxLayout(appInfoPanel, BoxLayout.X_AXIS));
		appInfoPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
		appInfoPanel.add(lblAppName);
		appInfoPanel.add(Box.createHorizontalStrut(5));
		appInfoPanel.add(lblAppVersion);
		infoPanel.add(appInfoPanel);
		infoPanel.add(Box.createVerticalStrut(5));
		lblDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblDescription.setFont(new Font("Dialog", Font.PLAIN, 12));
		infoPanel.add(lblDescription);
		infoPanel.add(Box.createVerticalStrut(5));
		lblCopyright.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCopyright.setFont(new Font("Dialog", Font.PLAIN, 12));
		infoPanel.add(lblCopyright);
		infoPanel.add(Box.createVerticalStrut(5));
		// add banner
		ImageIcon banner = new ImageIcon(AboutDialog.class.getResource("/images/curtbinderlogo.png"));
		JLabel lblBanner = new JLabel(banner);
		lblBanner.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblBanner.setBorder(BorderFactory.createLineBorder(Color.black));
		infoPanel.add(lblBanner);
		infoPanel.add(Box.createVerticalStrut(5));
		lblUrl.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUrl.setForeground(Color.BLUE);
		//lblUrl.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUrl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.out.print("Clicked URL\n");
			}
		});
		infoPanel.add(lblUrl);
		
		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		JButton btnCredits = new JButton("Credits");
		btnCredits.setFont(new Font("Dialog", Font.PLAIN, 12));
		JButton btnClose = new JButton("Close");
		btnClose.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setVisible(false);
				dispose();
			}
		});
		buttonPanel.add(btnCredits);
		buttonPanel.add(Box.createHorizontalGlue());
		buttonPanel.add(btnClose);
		
		contentPane.add(infoPanel);
		contentPane.add(Box.createVerticalStrut(5));
		contentPane.add(buttonPanel);
	}
	
	void setAppName(String appName) {
		lblAppName.setText(appName);
		setTitle("About " + appName);
	}
	void setAppVersion(int major, int minor, int revision, String build) {
		String v = major + "." + minor + "." + revision;
		if ( ! build.isEmpty() ) {
			 v += "-" + build;
		}
		lblAppVersion.setText(v);
	}
	void setDescription(String desc) {
		lblDescription.setText(desc);
	}
	void setCopyright(String copyright) {
		lblCopyright.setText(copyright);
	}
	void setURL(String url) {
		lblUrl.setText(url);
	}
	public void showAbout() {
		setLocationRelativeTo(getParent());
		setVisible(true);
	}
}
