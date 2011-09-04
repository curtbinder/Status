package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ReadValueAdapter implements ActionListener {

	Status m;

	public ReadValueAdapter ( Status m ) {
		this.m = m;
	}

	@Override
	public void actionPerformed ( ActionEvent arg0 ) {
		try {
			m.SendReadWriteCommand(false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error with URL",
					"Read Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
