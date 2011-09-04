package info.curtbinder.jStatus.UI;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class CloseAction extends AbstractAction {
	private static final long serialVersionUID = 1L;

	public CloseAction () {
		putValue(NAME, "Close");
	}

	public void actionPerformed ( ActionEvent ae ) {
		System.exit(0);
	}
}
