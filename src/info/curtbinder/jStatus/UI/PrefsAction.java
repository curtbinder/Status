package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.PrefsDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class PrefsAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public PrefsAction () {
		putValue( NAME, Globals.menuEditPrefsText );
	}

	public void actionPerformed ( ActionEvent ae ) {
		// launch preferences dialog
		PrefsDialog d = new PrefsDialog( StatusApp.statusUI );
		d.setHost( StatusApp.statusUI.getHost() );
		d.setPort( StatusApp.statusUI.getPort() );
		d.setComType( StatusApp.statusUI.getComType() );
		d.showDialog();
	}
}
