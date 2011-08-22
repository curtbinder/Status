package info.curtbinder.jStatus.Classes;

import java.awt.EventQueue;
import info.curtbinder.jStatus.UI.*;

public class StatusApp {

	static MainFrame statusUI;
	static Status statusClass;
	
	public StatusApp() {
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					statusClass = new Status();
					statusUI = new MainFrame(statusClass);
					statusUI.setDefaults();
					
					// update any stored/saved values for the IP, PORT or COMM method
					statusUI.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
