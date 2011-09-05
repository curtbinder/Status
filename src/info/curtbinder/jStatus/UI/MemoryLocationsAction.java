package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Globals;

import java.awt.Window;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class MemoryLocationsAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public MemoryLocationsAction () {
		putValue( NAME, "Memory Locations" );
		putValue( SMALL_ICON, new ImageIcon( MainFrame.class
				.getResource( Globals.memoryLocationsIconName ) ) );
	}

	public void actionPerformed ( ActionEvent ae ) {
		Window[] dialogs = JDialog.getWindows();
		for ( Window dlg : dialogs ) {
			boolean canProceed = true;
			if ( dlg.getClass().equals( MemoryDialog.class ) ) {
				if ( dlg.isDisplayable() ) {
					System.out.println( "found textdialog" );
					MemoryDialog md = (MemoryDialog) dlg;
					md.setVisible( true );
					md.setWindowPosition();
					canProceed = false;
				}
				if ( !canProceed ) {
					return;
				}
			}
		}
		MemoryDialog d = new MemoryDialog( null );
		d.showDialog();
		d.setWindowPosition();
	}
}
