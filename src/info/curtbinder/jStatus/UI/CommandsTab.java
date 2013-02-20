package info.curtbinder.jStatus.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import info.curtbinder.jStatus.Classes.GetTimeAdapter;
import info.curtbinder.jStatus.Classes.GetVersionAdapter;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.SetTimeAdapter;
import info.curtbinder.jStatus.Classes.Status;
import info.curtbinder.jStatus.Classes.StatusApp;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class CommandsTab extends JPanel {
	private static final long serialVersionUID = 3232336269648231347L;
	public final String NAME = "Commands";
	private JLabel lblVersion;
	private JLabel lblDateTime;
	private JButton btnCheckVersion;
	private JButton btnGetTime;
	private JButton btnSetCurrentTime;
	private JButton btnFeedMode;
	private JButton btnWaterChangeMode;
	private JButton btnExitMode;
	private JButton btnClearATO;
	private JButton btnClearOverheat;

	public CommandsTab ( final Status s ) {
		super();

		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		JPanel versionPanel = new JPanel();
		versionPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		// contains the version information
		versionPanel.setBorder( new TitledBorder( null, Globals.labelVersion,
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		versionPanel
				.setLayout( new BoxLayout( versionPanel, BoxLayout.X_AXIS ) );

		JLabel lblVersionLabel = new JLabel( Globals.labelInstalledVersion );
		lblVersionLabel.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		lblVersion = new JLabel( Globals.labelUnknown );
		btnCheckVersion = new JButton( Globals.labelGet );
		btnCheckVersion.addActionListener( new GetVersionAdapter( s ) );

		versionPanel.add( lblVersionLabel );
		versionPanel.add( Box.createRigidArea( new Dimension( 20, 20 ) ) );
		versionPanel.add( lblVersion );
		versionPanel.add( Box.createHorizontalGlue() );
		versionPanel.add( btnCheckVersion );

		JPanel modesPanel = new JPanel();
		modesPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		modesPanel.setBorder( new TitledBorder( null, Globals.labelModes,
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		modesPanel.setLayout( new BoxLayout( modesPanel, BoxLayout.Y_AXIS ) );

		JPanel panelText = new JPanel();
		panelText.setAlignmentX( Component.LEFT_ALIGNMENT );
		panelText.setLayout( new BoxLayout( panelText, BoxLayout.X_AXIS ) );
		JLabel lblModesDescription = new JLabel( Globals.labelModesDescription );
		lblModesDescription.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		lblModesDescription.setHorizontalAlignment( SwingConstants.LEFT );
		panelText.add( lblModesDescription );
		panelText.add( Box.createHorizontalGlue() );

		JPanel panelModesButton = new JPanel();
		panelModesButton.setAlignmentX( Component.LEFT_ALIGNMENT );
		panelModesButton.setLayout( new BoxLayout( panelModesButton,
			BoxLayout.X_AXIS ) );
		btnFeedMode = new JButton( Globals.labelFeedingMode );
		btnFeedMode.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent arg0 ) {
				try {
					s.sendEnterFeedModeCommand();
				} catch ( Exception e ) {
					JOptionPane
							.showMessageDialog( StatusApp.statusUI,
												Globals.errorFeedMode,
												Globals.errorMessage,
												JOptionPane.INFORMATION_MESSAGE );
				}
			}
		} );
		btnWaterChangeMode = new JButton( Globals.labelWaterChange );
		btnWaterChangeMode.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent arg0 ) {
				try {
					s.sendEnterWaterChangeModeCommand();
				} catch ( Exception e ) {
					JOptionPane
							.showMessageDialog( StatusApp.statusUI,
												Globals.errorWaterChange,
												Globals.errorMessage,
												JOptionPane.INFORMATION_MESSAGE );
				}
			}
		} );
		btnExitMode = new JButton( Globals.labelExit );
		btnExitMode.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent arg0 ) {
				try {
					s.sendExitModeCommand();
				} catch ( Exception e ) {
					JOptionPane
							.showMessageDialog( StatusApp.statusUI,
												Globals.errorExit,
												Globals.errorMessage,
												JOptionPane.INFORMATION_MESSAGE );
				}
			}
		} );

		panelModesButton.add( btnFeedMode );
		panelModesButton.add( Box.createHorizontalGlue() );
		panelModesButton.add( btnWaterChangeMode );
		panelModesButton.add( Box.createHorizontalGlue() );
		panelModesButton.add( btnExitMode );

		JPanel panelClearButton = new JPanel();
		panelClearButton.setAlignmentX( Component.LEFT_ALIGNMENT );
		panelClearButton.setLayout( new BoxLayout( panelClearButton,
			BoxLayout.X_AXIS ) );
		btnClearATO = new JButton( Globals.labelClearATO );
		btnClearATO.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent arg0 ) {
				try {
					s.sendClearATOCommand();
				} catch ( Exception e ) {
					JOptionPane
							.showMessageDialog( StatusApp.statusUI,
												Globals.errorClearATO,
												Globals.errorMessage,
												JOptionPane.INFORMATION_MESSAGE );
				}
			}
		} );
		btnClearOverheat = new JButton( Globals.labelClearOverheat );
		btnClearOverheat.addActionListener( new ActionListener() {
			public void actionPerformed ( ActionEvent arg0 ) {
				try {
					s.sendClearOverheatCommand();
				} catch ( Exception e ) {
					JOptionPane
							.showMessageDialog( StatusApp.statusUI,
												Globals.errorClearOverheat,
												Globals.errorMessage,
												JOptionPane.INFORMATION_MESSAGE );
				}
			}
		} );
		panelClearButton.add( btnClearATO );
		panelClearButton.add( Box.createHorizontalGlue() );
		panelClearButton.add( btnClearOverheat );

		modesPanel.add( panelText );
		modesPanel.add( Box.createRigidArea( new Dimension( 0, 20 ) ) );
		modesPanel.add( panelModesButton );
		modesPanel.add( Box.createVerticalGlue() );
		modesPanel.add( panelClearButton );

		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		dateTimePanel.setBorder( new TitledBorder( null, Globals.labelDateTime,
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		dateTimePanel
				.setLayout( new BoxLayout( dateTimePanel, BoxLayout.Y_AXIS ) );

		JPanel panel = new JPanel();
		panel.setAlignmentX( Component.LEFT_ALIGNMENT );
		panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );
		JLabel lblCurrentController =
				new JLabel( Globals.labelCurrentController );
		panel.add( lblCurrentController );
		lblCurrentController.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		lblCurrentController.setHorizontalAlignment( SwingConstants.LEFT );
		panel.add( Box.createRigidArea( new Dimension( 20, 20 ) ) );
		lblDateTime = new JLabel( Globals.defaultStatusText );
		panel.add( lblDateTime );
		lblDateTime.setHorizontalAlignment( SwingConstants.CENTER );
		panel.add( Box.createHorizontalGlue() );

		JPanel panelButton = new JPanel();
		panelButton.setAlignmentX( Component.LEFT_ALIGNMENT );
		panelButton.setLayout( new BoxLayout( panelButton, BoxLayout.X_AXIS ) );
		btnGetTime = new JButton( Globals.labelGetTime );
		btnGetTime.addActionListener( new GetTimeAdapter( s ) );
		panelButton.add( btnGetTime );
		btnGetTime.setHorizontalAlignment( SwingConstants.LEFT );
		panelButton.add( Box.createHorizontalStrut( 5 ) ); // 20
		btnSetCurrentTime = new JButton( Globals.labelSetCurrentTime );
		btnSetCurrentTime.addActionListener( new SetTimeAdapter( s ) );
		panelButton.add( btnSetCurrentTime );

		dateTimePanel.add( panel );
		dateTimePanel.add( Box.createRigidArea( new Dimension( 0, 20 ) ) );
		dateTimePanel.add( panelButton );
		dateTimePanel.add( Box.createVerticalGlue() );

		// Add components to panel
		add( versionPanel );
		add( Box.createVerticalGlue() );
		add( modesPanel );
		add( Box.createVerticalGlue() );
		add( dateTimePanel );
		add( Box.createVerticalGlue() );
	}

	public void enableButtons ( ) {
		btnCheckVersion.setEnabled( true );
		btnGetTime.setEnabled( true );
		btnSetCurrentTime.setEnabled( true );
		btnFeedMode.setEnabled( true );
		btnWaterChangeMode.setEnabled( true );
		btnExitMode.setEnabled( true );
		btnClearATO.setEnabled( true );
		btnClearOverheat.setEnabled( true );
	}

	public void disableButtons ( ) {
		btnCheckVersion.setEnabled( false );
		btnGetTime.setEnabled( false );
		btnSetCurrentTime.setEnabled( false );
		btnFeedMode.setEnabled( false );
		btnWaterChangeMode.setEnabled( false );
		btnExitMode.setEnabled( false );
		btnClearATO.setEnabled( false );
		btnClearOverheat.setEnabled( false );
	}

	public void setVersionText ( String version ) {
		lblVersion.setText( version );
	}

	public void setDateTimeText ( String s ) {
		lblDateTime.setText( s );
	}
}
