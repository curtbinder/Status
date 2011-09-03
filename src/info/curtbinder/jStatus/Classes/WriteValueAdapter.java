package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class WriteValueAdapter implements ActionListener {

	Status m;
	public WriteValueAdapter(Status m) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			m.SendReadWriteCommand(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error with URL", "Write Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
