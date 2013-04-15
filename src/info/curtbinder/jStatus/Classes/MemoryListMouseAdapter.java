package info.curtbinder.jStatus.Classes;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

public class MemoryListMouseAdapter implements MouseListener {

	private JList<?> list;

	public MemoryListMouseAdapter ( JList<?> list ) {
		this.list = list;
	}

	@Override
	public void mouseClicked ( MouseEvent e ) {
		if ( (e.getClickCount() == 2) && (!e.isConsumed()) ) {
			// double click event
			e.consume();
			StatusApp.statusUI.updateMemorySettings( (Memory) list
					.getSelectedValue() );
		}
	}

	@Override
	public void mouseEntered ( MouseEvent e ) {
	}

	@Override
	public void mouseExited ( MouseEvent e ) {
	}

	@Override
	public void mousePressed ( MouseEvent e ) {
	}

	@Override
	public void mouseReleased ( MouseEvent e ) {
	}
}
