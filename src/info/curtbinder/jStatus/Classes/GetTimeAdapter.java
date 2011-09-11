package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GetTimeAdapter implements ActionListener {

	private Status m;
	
	public GetTimeAdapter ( Status m ) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed ( ActionEvent arg0 ) {
		try {
			m.sendGetDateTimeCommand();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error getting date & time",
					"GetDateTime Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
