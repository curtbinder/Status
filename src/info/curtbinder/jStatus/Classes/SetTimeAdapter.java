package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SetTimeAdapter implements ActionListener {

	private Status m;
	
	public SetTimeAdapter ( Status m ) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed ( ActionEvent arg0 ) {
		try {
			m.sendSetDateTimeCommand();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error setting date & time",
					"SetDateTime Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
