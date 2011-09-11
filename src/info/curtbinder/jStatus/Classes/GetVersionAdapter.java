package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class GetVersionAdapter implements ActionListener {

	private Status m;
	
	public GetVersionAdapter ( Status m ) {
		this.m  = m;
	}
	
	@Override
	public void actionPerformed ( ActionEvent arg0 ) {
		try {
			m.sendVersionCommand();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(StatusApp.statusUI, "Error getting version",
					"Version Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
