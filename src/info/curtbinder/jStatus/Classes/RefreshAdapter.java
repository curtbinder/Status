package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class RefreshAdapter implements ActionListener {

	Status m;

	public RefreshAdapter ( Status m ) {
		this.m = m;
	}

	@Override
	public void actionPerformed ( ActionEvent arg0 ) {
		try {
			m.sendRefreshCommand();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI,
					"Error with Refresh", "Refresh Error",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
