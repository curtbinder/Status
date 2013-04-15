package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.Memory;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;

public class MemoryCellRenderer extends JLabel implements ListCellRenderer<Object> {
	private static final long serialVersionUID = 4373466111505000343L;

	public MemoryCellRenderer () {
		setOpaque(true); 
	}

	@Override
	public Component getListCellRendererComponent (
			JList<?> list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus ) {
		Memory entry = (Memory) value;
		setText( entry.toString() );
		if ( isSelected ) {
			setBackground( UIManager.getColor( "Table.selectionBackground" ) );
			setForeground( UIManager.getColor( "Table.selectionForeground" ) );
		} else {
			setBackground( UIManager.getColor( "Table.background" ) );
			setForeground( UIManager.getColor( "Table.foreground" ) );
		}
		return this;
	}

}
