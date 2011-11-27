package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.PrefsDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PrefsAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public PrefsAction () {
		putValue(NAME, Globals.menuEditPrefsText);
	}

	public void actionPerformed ( ActionEvent ae ) {
		// launch preferences dialog
		PrefsDialog d =
				new PrefsDialog( StatusApp.statusUI, "Preferences", "Configure the preferences here", 300,
					200 );
		d.setWindowText( "This is the preferences window" );
		d.showDialog();
	}
}
