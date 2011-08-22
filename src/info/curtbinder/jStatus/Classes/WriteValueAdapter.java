package info.curtbinder.jStatus.Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriteValueAdapter implements ActionListener {

	Status m;
	public WriteValueAdapter(Status m) {
		this.m = m;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			m.SendCommand(true);
		} catch (Exception e) {
			System.out.print("Write: Error with URL\n");
		}
	}
}
