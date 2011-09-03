package info.curtbinder.jStatus.UI;

import info.curtbinder.jStatus.Classes.ReadValueAdapter;
import info.curtbinder.jStatus.Classes.Status;
import info.curtbinder.jStatus.Classes.WriteValueAdapter;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MemoryTab extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6731707778978028199L;
	private JTextField textLocation;
	private JTextField textWriteValue;
	private JLabel lblReadValue;
	private JLabel lblWriteStatus;
	private ButtonGroup groupMemType;
	private JButton btnReadValue;
	private JButton btnWriteValue;
	private Status statusClass;
	
	public MemoryTab(Status s) {
		statusClass = s;
		JPanel tabMemory = new JPanel();
		tabMemory.setLayout(new BoxLayout(tabMemory, BoxLayout.Y_AXIS));

		// Start of Memory panel
		// top half
		JPanel topHalf = new JPanel();
		topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.X_AXIS));
		
		// top left
		JPanel topLeft = new JPanel();
		topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.Y_AXIS));
		JPanel locationBox = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) locationBox.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		JLabel lblLocation = new JLabel("Location:");
		textLocation = new JTextField();
		textLocation.setColumns(10);
		lblLocation.setLabelFor(textLocation);
		locationBox.add(lblLocation);
		locationBox.add(textLocation);
		topLeft.add(Box.createVerticalGlue());
		topLeft.add(locationBox);
		topLeft.add(Box.createVerticalGlue());
		
		// top right
		JPanel topRight = new JPanel();
		topRight.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Status Type:", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		topRight.setLayout(new BoxLayout(topRight, BoxLayout.Y_AXIS));
		JRadioButton rdbtnByte = new JRadioButton("Byte Value");
		rdbtnByte.setActionCommand("/mb");
		JRadioButton rdbtnInt = new JRadioButton("Integer Value");
		rdbtnInt.setActionCommand("/mi");
		topRight.add(Box.createVerticalGlue());
		topRight.add(rdbtnByte);
		topRight.add(Box.createVerticalGlue());
		topRight.add(rdbtnInt);
		topRight.add(Box.createVerticalGlue());
		groupMemType = new ButtonGroup();
		groupMemType.add(rdbtnByte);
		rdbtnByte.setSelected(true);
		groupMemType.add(rdbtnInt);
		
		topHalf.add(topLeft);
		topHalf.add(Box.createHorizontalGlue());
		topHalf.add(topRight);
		
		// bottom half
		JPanel bottomHalf = new JPanel();
		bottomHalf.setLayout(new BoxLayout(bottomHalf, BoxLayout.X_AXIS));
		
		// bottom left
		JPanel bottomLeft = new JPanel();
		bottomLeft.setBorder(new TitledBorder(null, "Read Value:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bottomLeft.setLayout(new BoxLayout(bottomLeft, BoxLayout.Y_AXIS));
		lblReadValue = new JLabel("");  // clear value first
		lblReadValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblReadValue.setHorizontalAlignment(SwingConstants.CENTER);
		JButton btnReadValue = new JButton("Read");
		btnReadValue.addActionListener(new ReadValueAdapter(statusClass));
		btnReadValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		bottomLeft.add(Box.createVerticalStrut(5));
		bottomLeft.add(Box.createVerticalGlue());
		bottomLeft.add(lblReadValue);
		bottomLeft.add(Box.createVerticalGlue());
		bottomLeft.add(btnReadValue);
		bottomLeft.add(Box.createVerticalStrut(5));
		
		// bottom right
		JPanel bottomRight = new JPanel();
		bottomRight.setBorder(new TitledBorder(null, "Write Value:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		bottomRight.setLayout(new BoxLayout(bottomRight, BoxLayout.Y_AXIS));
		JPanel boxValue = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) boxValue.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		boxValue.setAlignmentX(0.5f);
		JLabel lblWrite = new JLabel("Value:");
		lblWrite.setAlignmentX(Component.TOP_ALIGNMENT);
		textWriteValue = new JTextField();
		textWriteValue.setColumns(5);
		textWriteValue.setAlignmentX(Component.TOP_ALIGNMENT);
		boxValue.add(lblWrite);
		boxValue.add(Box.createHorizontalStrut(5));
		boxValue.add(textWriteValue);
		boxValue.add(Box.createHorizontalGlue());
		JPanel boxStatus = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) boxStatus.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		boxStatus.setAlignmentX(0.5f);
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setAlignmentX(Component.TOP_ALIGNMENT);
		lblWriteStatus = new JLabel("");
		lblWriteStatus.setAlignmentX(Component.TOP_ALIGNMENT);
		boxStatus.add(lblStatus);
		boxValue.add(Box.createHorizontalStrut(5));
		boxStatus.add(lblWriteStatus);
		boxStatus.add(Box.createHorizontalGlue());
		btnWriteValue = new JButton("Write Value");
		btnWriteValue.setAlignmentX(0.5f);
		btnWriteValue.addActionListener(new WriteValueAdapter(statusClass));
		
		bottomRight.add(Box.createVerticalGlue());
		bottomRight.add(boxValue);
		bottomRight.add(boxStatus);
		bottomRight.add(Box.createVerticalGlue());
		bottomRight.add(btnWriteValue);
		bottomRight.add(Box.createVerticalStrut(5));
		
		bottomHalf.add(bottomLeft);
		bottomHalf.add(Box.createHorizontalGlue());
		bottomHalf.add(bottomRight);
		
		tabMemory.add(topHalf);
		tabMemory.add(Box.createVerticalGlue());
		tabMemory.add(bottomHalf);
		// end of Status panel
	}

	public String getMemType() {
		return groupMemType.getSelection().getActionCommand();
	}

	public String getTextLocation() {
		return textLocation.getText();
	}
	
	public void setReadValue(String s) {
		lblReadValue.setText(s);
	}
	
	public void setWriteStatus(String s) {
		lblWriteStatus.setText(s);
	}
	
	public String getWriteValue() {
		return textWriteValue.getText();
	}
	public void enableReadWriteButtons(boolean bEnable) {
		btnReadValue.setEnabled(bEnable);
		btnWriteValue.setEnabled(bEnable);
	}
}
