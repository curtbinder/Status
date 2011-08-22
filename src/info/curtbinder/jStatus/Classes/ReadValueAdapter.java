package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadValueAdapter implements ActionListener {

	Status m;
	public ReadValueAdapter(Status m) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			m.SendCommand(false);
		} catch (Exception e) {
			System.out.print("Read: Error with URL\n");
		}
	}
}
