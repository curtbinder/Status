package info.curtbinder.jStatus.UI;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import info.curtbinder.jStatus.Classes.GetTimeAdapter;
import info.curtbinder.jStatus.Classes.GetVersionAdapter;
import info.curtbinder.jStatus.Classes.SetTimeAdapter;
import info.curtbinder.jStatus.Classes.Status;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class DateTimeTab extends JPanel {
	private static final long serialVersionUID = 3232336269648231347L;
	public final String NAME = "Date & Time";
	private JLabel lblVersion;
	private JLabel lblDateTime;
	private JButton btnCheckVersion;
	private JButton btnGetTime;
	private JButton btnSetCurrentTime;

	public DateTimeTab ( Status s ) {
		super();

		setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
		setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );

		JPanel versionPanel = new JPanel();
		versionPanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		// contains the version information
		versionPanel.setBorder( new TitledBorder( null, "Version",
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		versionPanel
				.setLayout( new BoxLayout( versionPanel, BoxLayout.X_AXIS ) );

		JLabel lblVersionLabel = new JLabel( "Installed Version:" );
		lblVersionLabel.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		lblVersion = new JLabel( "Unknown" );
		btnCheckVersion = new JButton( "Get" );
		btnCheckVersion.addActionListener( new GetVersionAdapter( s ) );

		versionPanel.add( lblVersionLabel );
		versionPanel.add( Box.createRigidArea( new Dimension( 20, 20 ) ) );
		versionPanel.add( lblVersion );
		versionPanel.add( Box.createHorizontalGlue() );
		versionPanel.add( btnCheckVersion );

		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setAlignmentX( Component.LEFT_ALIGNMENT );
		dateTimePanel.setBorder( new TitledBorder( null, "Date & Time",
			TitledBorder.LEADING, TitledBorder.TOP, null, null ) );
		dateTimePanel
				.setLayout( new BoxLayout( dateTimePanel, BoxLayout.Y_AXIS ) );

		JPanel panel = new JPanel();
		panel.setAlignmentX( Component.LEFT_ALIGNMENT );
		panel.setLayout( new BoxLayout( panel, BoxLayout.X_AXIS ) );
		JLabel lblCurrentController = new JLabel( "Current Controller:" );
		panel.add( lblCurrentController );
		lblCurrentController.setFont( new Font( "Dialog", Font.PLAIN, 12 ) );
		lblCurrentController.setHorizontalAlignment( SwingConstants.LEFT );
		panel.add( Box.createRigidArea( new Dimension( 20, 20 ) ) );
		lblDateTime = new JLabel( "-" );
		panel.add( lblDateTime );
		lblDateTime.setHorizontalAlignment( SwingConstants.CENTER );
		panel.add( Box.createHorizontalGlue() );

		JPanel panelButton = new JPanel();
		panelButton.setAlignmentX( Component.LEFT_ALIGNMENT );
		panelButton.setLayout( new BoxLayout( panelButton, BoxLayout.X_AXIS ) );
		btnGetTime = new JButton( "Get Time" );
		btnGetTime.addActionListener( new GetTimeAdapter( s ) );
		panelButton.add( btnGetTime );
		btnGetTime.setHorizontalAlignment( SwingConstants.LEFT );
		panelButton.add( Box.createHorizontalStrut( 5 ) ); // 20
		btnSetCurrentTime = new JButton( "Set Current Time" );
		btnSetCurrentTime.addActionListener( new SetTimeAdapter( s ) );
		panelButton.add( btnSetCurrentTime );

		dateTimePanel.add( panel );
		dateTimePanel.add( Box.createRigidArea( new Dimension( 0, 20 ) ) );
		dateTimePanel.add( panelButton );
		dateTimePanel.add( Box.createVerticalGlue() );

		// Add components to panel
		add( versionPanel );
		add( Box.createVerticalGlue() );
		add( dateTimePanel );
		add( Box.createVerticalGlue() );
	}

	public void enableButtons ( ) {
		btnCheckVersion.setEnabled( true );
		btnGetTime.setEnabled( true );
		btnSetCurrentTime.setEnabled( true );
	}

	public void disableButtons ( ) {
		btnCheckVersion.setEnabled( false );
		btnGetTime.setEnabled( false );
		btnSetCurrentTime.setEnabled( false );
	}

	public void setVersionText ( String version ) {
		lblVersion.setText( version );
	}

	public void setDateTimeText ( String s ) {
		lblDateTime.setText( s );
	}
}
