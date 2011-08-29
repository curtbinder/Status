package info.curtbinder.jStatus.Classes;

import java.awt.EventQueue;
import info.curtbinder.jStatus.UI.*;

public class StatusApp {

	public static MainFrame statusUI;
	static Status statusClass;
	
	public StatusApp() {
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		statusClass = new Status();
		// read any saved/stored default values 
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
