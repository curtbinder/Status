package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.TextDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class MemoryLocationsAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	private static final int windowHeight = 300;
	private static final int windowWidth = 300;

	public MemoryLocationsAction () {
		putValue( NAME, "Memory Locations" );
		putValue( SMALL_ICON, new ImageIcon( MainFrame.class
				.getResource( Globals.memoryLocationsIconName ) ) );
	}

	public void actionPerformed ( ActionEvent ae ) {
		Window[] dialogs = JDialog.getWindows();
		for ( Window dlg : dialogs ) {
			boolean canProceed = true;
			if ( dlg.getClass().equals( TextDialog.class ) ) {
				if ( dlg.isDisplayable() ) {
					System.out.println( "found textdialog" );
					TextDialog td = (TextDialog) dlg;
					td.setVisible( true );
					setPosition( td );
					canProceed = false;
				}
				if ( !canProceed ) {
					return;
				}
			}
		}
		TextDialog d = new TextDialog( null, "Memory Locations",
			"Location - Type - Reference", 300, 300 );
		d.setWindowList( Globals.memoryLocationList );
		d.showDialog();
		setPosition( d );
	}

	private void setPosition ( TextDialog d ) {
		Point p = StatusApp.statusUI.getLocation();
		int w = StatusApp.statusUI.getWidth();
		d.setBounds( p.x + w, p.y, windowWidth, windowHeight );
	}
}
