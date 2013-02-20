package info.curtbinder.Dialogs;

import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;
import info.curtbinder.jStatus.UI.CommunicationsPanel;
import info.curtbinder.jStatus.UI.SettingsPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Component;
import java.awt.Font;
import java.util.prefs.Preferences;

public class PrefsDialog extends JDialog {

	private static final long serialVersionUID = 432907898192448125L;
	private static final int minWidth = 300;
	private static final int minHeight = 270;
	private CommunicationsPanel panelCommunication;
	private SettingsPanel panelSettings;

	public PrefsDialog ( JFrame owner ) {
		super( owner );
		setMinimumSize( new Dimension( minWidth, minHeight ) );
		createWindow();
	}

	private void createWindow ( ) {
		JPanel contentPane = new JPanel();
		contentPane.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		contentPane.setLayout( new BoxLayout( contentPane, BoxLayout.Y_AXIS ) );
		setContentPane( contentPane );

		setTitle( Globals.prefsTitle );
		panelCommunication = new CommunicationsPanel();
		panelSettings = new SettingsPanel();

		// Button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout( new BoxLayout( buttonPanel, BoxLayout.X_AXIS ) );
		buttonPanel.setAlignmentY( Component.BOTTOM_ALIGNMENT );
		buttonPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		JButton btnSave = new JButton( Globals.btnSaveText );
		btnSave.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		btnSave.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent ev ) {
				saveSettings();
				// now close the window
				setVisible( false );
				dispose();
			}
		} );
		JButton btnClose = new JButton( Globals.btnCloseText );
		btnClose.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		btnClose.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent ev ) {
				setVisible( false );
				dispose();
			}
		} );
		buttonPanel.add( Box.createHorizontalGlue() );
		buttonPanel.add( btnSave );
		buttonPanel.add( Box.createHorizontalStrut( 5 ) );
		buttonPanel.add( btnClose );

		contentPane.add( panelCommunication );
		contentPane.add( Box.createVerticalStrut( 5 ) );
		contentPane.add( panelSettings );
		contentPane.add( Box.createVerticalStrut( 5 ) );
		contentPane.add( buttonPanel );
	}

	public void showDialog ( ) {
		setLocationRelativeTo( getParent() );
		setVisible( true );
	}

	protected void saveSettings ( ) {
		// store the values in preferences
		Preferences userprefs =
				Preferences.userNodeForPackage( StatusApp.class );
		String host = panelCommunication.getTextHost().getText();
		String port = panelCommunication.getTextPort().getText();
		String comtype = panelCommunication.getCommMethod();
		boolean fUsePre10Memory = panelSettings.getUsePre10Memory();
		boolean fDisableNotifications = panelSettings.getDisableNotifications();
		userprefs.put( Globals.keyHost, host );
		userprefs.put( Globals.keyPort, port );
		userprefs.put( Globals.keyComType, comtype );
		userprefs.putBoolean( Globals.keyPre10Memory, fUsePre10Memory );
		userprefs.putBoolean(	Globals.keyDisableNotifications,
								fDisableNotifications );
		// update mainframe with the new values
		StatusApp.statusUI.setHost( host );
		StatusApp.statusUI.setPort( port );
		StatusApp.statusUI.setComType( comtype );
		StatusApp.fUsePre10Memory = fUsePre10Memory;
		StatusApp.fDisableNotifications = fDisableNotifications;
	}

	public void setHost ( String s ) {
		panelCommunication.getTextHost().setText( s );
	}

	public void setPort ( String s ) {
		panelCommunication.getTextPort().setText( s );
	}

	public void setComType ( String s ) {
		if ( s == Globals.ComActionCommand ) {
			panelCommunication.setComMethod();
		} else {
			panelCommunication.setWifiMethod();
		}
	}

	public void setUsePre10Memory ( boolean fUse ) {
		panelSettings.setUsePre10Memory( fUse );
	}

	public void setDisableNotifications ( boolean fDisable ) {
		panelSettings.setDisableNotifications( fDisable );
	}
}
