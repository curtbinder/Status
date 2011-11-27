package info.curtbinder.jStatus.UI;

import info.curtbinder.Dialogs.AboutDialog;
import info.curtbinder.jStatus.Classes.Globals;
import info.curtbinder.jStatus.Classes.StatusApp;

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
	private PrefsAction prefsAction = new PrefsAction();

	public class AboutAction extends AbstractAction {
		private static final long serialVersionUID = 1L;

		public AboutAction () {
			putValue(NAME, Globals.menuHelpAboutText);
			// TODO add in about icon
		}

		public void actionPerformed ( ActionEvent ae ) {
			AboutDialog d = new AboutDialog(StatusApp.statusUI, new ImageIcon(
					MainFrame.class.getResource(Globals.appIconName)),
					Globals.appName, Globals.appDescription);
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

	public StatusMenuBar () {
		super();

		JMenu mnFile = new JMenu(Globals.menuFileText);
		add(mnFile);
		JMenuItem mntmClose = new JMenuItem(closeAction);
		mnFile.add(mntmClose);
		
		JMenu mnEdit = new JMenu(Globals.menuEditText);
		add(mnEdit);
		JMenuItem mntmPrefs = new JMenuItem(prefsAction);
		mnEdit.add(mntmPrefs);

		JMenu mnHelp = new JMenu(Globals.menuHelpText);
		add(mnHelp);
		JMenuItem mntmMemory = new JMenuItem(memoryAction);
		JMenuItem mntmAbout = new JMenuItem(aboutAction);
		mnHelp.add(mntmMemory);
		mnHelp.add(mntmAbout);
	}
}
