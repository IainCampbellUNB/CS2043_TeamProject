package project.group4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame{
	
	private JLabel idLabel,label2,hoursLabel,reasonLabel;
	private JButton addButton,submitButton;
	private JTextField idField,textfield2,hoursField;
	private JTable table;
	private JComboBox<String> comboBox;
	private DefaultTableModel model;
	
	public GUI() {
		createUI();
		
		setTitle("CS2043 Project - Group 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200,750);
		setLocationRelativeTo(null);
	}
	
	private void createUI() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		getContentPane().add(panel);
		
		//north panel
		JPanel panelNorth = new JPanel(new BorderLayout());
		panel.add(panelNorth, BorderLayout.NORTH);
		JLabel head = new JLabel("Teacher absences form", SwingConstants.CENTER);
		head.setFont(new Font("Times new roman", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		panelNorth.add(head, BorderLayout.CENTER);
		panelNorth.setBackground(Color.BLUE);
		
		//south panel
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		panel.add(panelSouth, BorderLayout.SOUTH);
		
		idLabel = new JLabel("Teacher ID", SwingConstants.RIGHT);
		idLabel.setPreferredSize(new Dimension(80,25));
		panelSouth.add(idLabel);
		
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(80,25));
		panelSouth.add(idField);
		
		label2 = new JLabel("something",SwingConstants.RIGHT);
		label2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(label2);
		
		textfield2 = new JTextField();
		textfield2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield2);
		
		hoursLabel = new JLabel("Hours",SwingConstants.RIGHT);
		hoursLabel.setPreferredSize(new Dimension(80,25));
		panelSouth.add(hoursLabel);
		
		hoursField = new JTextField();
		hoursField.setPreferredSize(new Dimension(80,25));
		panelSouth.add(hoursField);
		
		reasonLabel = new JLabel("Reason for absence",SwingConstants.RIGHT);
		reasonLabel.setPreferredSize(new Dimension(130,25));
		panelSouth.add(reasonLabel);
		
		String [] combo = {"Personal","Work related","Unknown"};
		comboBox = new JComboBox<String>(combo);
		panelSouth.add(comboBox);
		
		addButton = new JButton("Add teacher");
		addButton.setPreferredSize(new Dimension(120,25));
		addButton.addActionListener(new ButtonActionListener());
		panelSouth.add(addButton);
		
		submitButton = new JButton("Submit report");
		submitButton.setPreferredSize(new Dimension(120,25));
		submitButton.addActionListener(new ButtonActionListener());
		panelSouth.add(submitButton);
		
		//center panel
		JPanel panelCenter = new JPanel(new BorderLayout());
		panel.add(panelCenter, BorderLayout.CENTER);
		
		String [] header = {"a","b","c","d"};
		String [][] data = {};
		model = new DefaultTableModel(data,header);
		table = new JTable(model);
		table.setTableHeader(null);
		table.setFillsViewportHeight(true);
		table.setRowHeight(130);
	    JScrollPane scrollTable = new JScrollPane(table);
		panelCenter.add(scrollTable, BorderLayout.CENTER);
		
	}
		
	public static void main(String [] args) {	
		new GUI().setVisible(true);
	}
	
	private class ButtonActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == addButton) {
				String id = idField.getText();
				String something = textfield2.getText();
				String hours = hoursField.getText();
				String reason = (String) comboBox.getSelectedItem();
				
				//we should check if the inputs are valid and match a teacher in the master excel sheet
				
				String [] row = {id,something,hours,reason};
				model.addRow(row);
			}
			
			if(e.getSource() == submitButton) {
				//do something
			}
		}
	}
	
}
