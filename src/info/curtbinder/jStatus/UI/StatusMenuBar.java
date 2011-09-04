package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.AboutDialog;
import info.curtbinder.Dialogs.TextDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;

import java.awt.Point;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class StatusMenuBar extends JMenuBar {

	private static final long serialVersionUID = 5865634731226374187L;

	private CloseAction closeAction = new CloseAction();
	private MemoryLocationsAction memoryAction = new MemoryLocationsAction();
	private AboutAction aboutAction = new AboutAction();

	public class AboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public AboutAction () {
			putValue(NAME, "About");
		}

		public void actionPerformed ( ActionEvent ae ) {
			AboutDialog d = new AboutDialog(StatusApp.statusUI, new ImageIcon(
					MainFrame.class.getResource(Globals.appIconName)),
					"RA Status", "Monitor the status of the controller");
			d.setAppVersion(Globals.versionMajor, Globals.versionMinor,
					Globals.versionRevision, Globals.versionBuild);
			d.setCopyright(Globals.copyrightInfo);
			d.setBanner(new ImageIcon(MainFrame.class
					.getResource(Globals.bannerIconName)));
			d.setURL(Globals.url);
			d.setCreditors(Globals.creditList);
			d.setLicense(Globals.legal);
			d.showAbout();
		}
	}

	public class MemoryLocationsAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public MemoryLocationsAction () {
			putValue(NAME, "Memory Locations");
		}

		public void actionPerformed ( ActionEvent ae ) {
			TextDialog d = new TextDialog(null, "Memory Locations",
					"Location - Type - Reference", 300, 300);
			d.setWindowList(Globals.memoryLocationList);
			d.showDialog();
			Point p = StatusApp.statusUI.getLocation();
			int w = StatusApp.statusUI.getWidth();
			d.setBounds(p.x + w, p.y, 300, 300);
		}
	}

	public StatusMenuBar () {
		super();

		JMenu mnFile = new JMenu("File");
		add(mnFile);
		JMenuItem mntmClose = new JMenuItem(closeAction);
		mnFile.add(mntmClose);

		JMenu mnHelp = new JMenu("Help");
		add(mnHelp);
		JMenuItem mntmMemory = new JMenuItem(memoryAction);
		JMenuItem mntmAbout = new JMenuItem(aboutAction);
		mnHelp.add(mntmMemory);
		mnHelp.add(mntmAbout);
	}
}
