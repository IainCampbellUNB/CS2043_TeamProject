package project.group4;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame{
	
	private JLabel idLabel,label2,hoursLabel,reasonLabel;
	private JButton addButton,submitButton, deleteButton,mainButton, absenceButton;
	private JTextField idField,textfield2,hoursField;
	private JTable table;
	private JComboBox<String> comboBox;
	private DefaultTableModel model;
	private JPanel containerPanel, absencePanel, mainPanel;
	private CardLayout cl = new CardLayout();
	
	public GUI() {
		
		setTitle("CS2043 Project - Group 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,875);
		setLocationRelativeTo(null);
		setVisible(true);
		
		containerPanel = new JPanel();
		absencePanel = new JPanel(new BorderLayout());
		mainPanel = new JPanel();
		
		containerPanel.setLayout(cl);
		add(containerPanel);
		
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
				
		getContentPane().add(absencePanel);
		
		//north panel----------------------------------
		JPanel panelNorth = new JPanel(new BorderLayout());
		absencePanel.add(panelNorth, BorderLayout.NORTH);
		JLabel head = new JLabel("Teacher absences form", SwingConstants.CENTER);
		head.setFont(new Font("Times new roman", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		panelNorth.add(head, BorderLayout.CENTER);
		
		mainButton = new JButton("Main menu");
		mainButton.setPreferredSize(new Dimension(140,25));
		mainButton.addActionListener(new ButtonActionListener());
		JPanel mainMenuPanel = new JPanel(new FlowLayout());
		panelNorth.add(mainMenuPanel, BorderLayout.WEST);
		mainMenuPanel.add(mainButton);
		
		submitButton = new JButton("Submit form");
		submitButton.setPreferredSize(new Dimension(140,25));
		submitButton.addActionListener(new ButtonActionListener());
		JPanel submitPanel = new JPanel(new FlowLayout());
		panelNorth.add(submitPanel, BorderLayout.EAST);
		submitPanel.add(submitButton);
		
		//south panel----------------------------------
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		absencePanel.add(panelSouth, BorderLayout.SOUTH);
		
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
		addButton.setPreferredSize(new Dimension(140,25));
		addButton.addActionListener(new ButtonActionListener());
		panelSouth.add(addButton);
		
		deleteButton = new JButton("Delete selected");
		deleteButton.setPreferredSize(new Dimension(140,25));
		deleteButton.addActionListener(new ButtonActionListener());
		panelSouth.add(deleteButton);
		
		//center panel----------------------------------
		JPanel panelCenter = new JPanel(new BorderLayout());
		absencePanel.add(panelCenter, BorderLayout.CENTER);
		
		String [] header = {"","","",""};
		String [][] data = {};
		model = new DefaultTableModel(data,header);
		table = new JTable(model);
		table.setTableHeader(null);
		table.setFillsViewportHeight(true);
		table.setRowHeight(130);
	    JScrollPane scrollTable = new JScrollPane(table);
		panelCenter.add(scrollTable, BorderLayout.CENTER);
		
		containerPanel.add(absencePanel,"absence");
		containerPanel.add(mainPanel,"main menu");
		cl.show(containerPanel,"absence");
		
		//main menu stuff
		mainPanel.setBackground(Color.BLUE);
		absenceButton = new JButton("Absence menu");
		absenceButton.addActionListener(new ButtonActionListener());
		mainPanel.add(absenceButton);
		

		
		
	}
		
	public static void main(String [] args) throws Exception{	
		new GUI();
	}
	
	private class ButtonActionListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == addButton)
			{
				String id = idField.getText();
				String something = textfield2.getText();
				String hours = hoursField.getText();
				String reason = (String) comboBox.getSelectedItem();
				
				//we should check if the inputs are valid and match a teacher in the master excel sheet
				
				String [] row = {id,something,hours,reason};
				model.addRow(row);
			}
			
			if(e.getSource() == submitButton) 
			{
				try {
					boolean complete = table.print();
					
					if(complete) {
						JOptionPane.showMessageDialog(null, "Printing...", null, JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "Failed to print...", null, JOptionPane.INFORMATION_MESSAGE);
					}
					
				}catch(PrinterException pe) {
					JOptionPane.showMessageDialog(null, pe.getCause(), null, JOptionPane.INFORMATION_MESSAGE);
				}
				
				//will need to update the excel system later so we can make up who covers what class
			}
			
			if(e.getSource() == deleteButton)
			{
				if (table.getSelectedRow() != -1) 
				{
		            model.removeRow(table.getSelectedRow());
		        }
			}
			
			if(e.getSource() == mainButton) {
				cl.show(containerPanel, "main menu");
			}
			
			if(e.getSource() == absenceButton) {
				cl.show(containerPanel, "absence");
			}
		}
	}
}
