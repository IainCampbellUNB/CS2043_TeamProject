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

public class GUI extends JFrame
{
	private JLabel idLabel,label2,periodsLabel,reasonLabel;
	private JButton addButton,submitButton, deleteButton,mainButton, mainButton2, absenceButton, onCallButton;
	private JTextField idField,textfield2;
	private JTable table;
	private JComboBox<String> reasonBox;
	private DefaultTableModel model;
	private JPanel containerPanel, absencePanel, mainPanel, onCallPanel;
	private CardLayout cl = new CardLayout();
	private JRadioButton p1,p2,p3a,p3b,p4,p5,p6;
	
	public GUI() 
	{
		setTitle("CS2043 Project - Group 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1400,875);
		setLocationRelativeTo(null);
		setVisible(true);
		
		containerPanel = new JPanel();
		absencePanel = new JPanel(new BorderLayout());
		mainPanel = new JPanel();
		onCallPanel = new JPanel();
		
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
		
		northPanelSetup();
		southPanelSetup();
		centerPanelSetup();
		mainMenuPanelSetup();
		onCallMenuPanelSetup();
		revalidateFrame();
	}
	
	private void northPanelSetup() 
	{
		JPanel panelNorth = new JPanel(new BorderLayout());
		absencePanel.add(panelNorth, BorderLayout.NORTH);
		JLabel head = new JLabel("Teacher absences form", SwingConstants.CENTER);
		head.setFont(new Font("Times new roman", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		panelNorth.add(head, BorderLayout.CENTER);
		
		mainButton = new JButton("Main menu");
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
	}
	
	private void southPanelSetup() 
	{
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		absencePanel.add(panelSouth, BorderLayout.SOUTH);
		
		idLabel = new JLabel("Teacher ID", SwingConstants.RIGHT);
		idLabel.setPreferredSize(new Dimension(80,25));
		panelSouth.add(idLabel);
		
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(80,25));
		panelSouth.add(idField);
		
		label2 = new JLabel("Name",SwingConstants.RIGHT);
		label2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(label2);
		
		textfield2 = new JTextField();
		textfield2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield2);
		
		periodsLabel = new JLabel("Period(s) absent: ",SwingConstants.RIGHT);
		periodsLabel.setPreferredSize(new Dimension(130,25));
		panelSouth.add(periodsLabel);
		
		p1 = new JRadioButton("1");
		p2 = new JRadioButton("2");
		p3a = new JRadioButton("3a");
		p3b = new JRadioButton("3b");
		p4 = new JRadioButton("4");
		p5 = new JRadioButton("5");
		p6 = new JRadioButton("6");
		
		panelSouth.add(p1);
		panelSouth.add(p2);
		panelSouth.add(p3a);
		panelSouth.add(p3b);
		panelSouth.add(p4);
		panelSouth.add(p5);
		panelSouth.add(p6);
		
		reasonLabel = new JLabel("Reason for absence: ",SwingConstants.RIGHT);
		reasonLabel.setPreferredSize(new Dimension(130,25));
		panelSouth.add(reasonLabel);
		
		String [] combo = {"","Personal","Work related","Unknown"};
		reasonBox = new JComboBox<String>(combo);
		panelSouth.add(reasonBox);
		
		addButton = new JButton("Add teacher");
		addButton.setPreferredSize(new Dimension(140,25));
		addButton.addActionListener(new ButtonActionListener());
		panelSouth.add(addButton);
		
		deleteButton = new JButton("Delete selected");
		deleteButton.setPreferredSize(new Dimension(140,25));
		deleteButton.addActionListener(new ButtonActionListener());
		panelSouth.add(deleteButton);
	}

		private void centerPanelSetup()
		{
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
		
		//Card layout stuff
		containerPanel.add(absencePanel,"absence");
		containerPanel.add(mainPanel,"main menu");
		containerPanel.add(onCallPanel,"on call");
		cl.show(containerPanel,"main menu");
	}
		
	private void mainMenuPanelSetup() 
	{

		absenceButton = new JButton("Absence menu");
		onCallButton = new JButton("On call form");
		onCallButton.addActionListener(new ButtonActionListener());
		absenceButton.addActionListener(new ButtonActionListener());
		mainPanel.setBackground(Color.BLUE);
		mainPanel.add(onCallButton);
		mainPanel.add(absenceButton);
	}
	
	private void onCallMenuPanelSetup() 
	{
		mainButton2 = new JButton("main menu");
		mainButton2.addActionListener(new ButtonActionListener());
		onCallPanel.add(mainButton2);
		onCallPanel.setBackground(Color.GRAY);
	}
	
	private void revalidateFrame() {
		revalidate();
	}
	
	private class ButtonActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			
			if(e.getSource() == addButton)
			{
				addAbsence();
			}
			
			if(e.getSource() == submitButton) 
			{
				submitRequest();
			}
			
			if(e.getSource() == mainButton || e.getSource() == mainButton2) 
			{
				cl.show(containerPanel, "main menu");
			}
			
			if(e.getSource() == absenceButton)
			{
				cl.show(containerPanel, "absence");
			}
			
			if(e.getSource() == onCallButton)
			{
				cl.show(containerPanel, "on call");
			}
			
			if(e.getSource() == deleteButton && table.getSelectedRow() != -1)
			{
				model.removeRow(table.getSelectedRow()); 
			}
		}
		
		private void addAbsence() {
			String id = idField.getText();
			String something = textfield2.getText();
			String [] periods = new String[7];
			
			if(p1.isSelected()) {
				periods[0] = "period 1";
			}
			
			if(p2.isSelected()) {
				periods[1] = "period 2";
			}
			
			if(p3a.isSelected()) {
				periods[2] = "period 3a";
			}
			
			if(p3b.isSelected()) {
				periods[3] = "period 3b";
			}
			
			if(p4.isSelected()) {
				periods[4] = "period 4";
			}
			
			if(p5.isSelected()) {
				periods[5] = "period 5";
			}
			
			if(p6.isSelected()) {
				periods[6] = "period 6";
			}
			
			String reason = (String) reasonBox.getSelectedItem();
			idField.setText("");
			textfield2.setText("");
			reasonBox.setSelectedItem("");
			
			p1.setSelected(false);
			p2.setSelected(false);
			p3a.setSelected(false);
			p3b.setSelected(false);
			p4.setSelected(false);
			p5.setSelected(false);
			p6.setSelected(false);
			
			//we should check if the inputs are valid and match a teacher in the master excel sheet
			
			String [] row = {id,something,"stuff will go here later...",reason};
			model.addRow(row);
		}
		
		private void submitRequest() 
		{
			try
			{
				boolean complete = table.print();
				
				if(complete) 
				{
					JOptionPane.showMessageDialog(null, "Print successful", null, JOptionPane.INFORMATION_MESSAGE);
				}else 
				{
					JOptionPane.showMessageDialog(null, "Failed to print...", null, JOptionPane.INFORMATION_MESSAGE);
				}
				
			}catch(PrinterException pe)
			{
				JOptionPane.showMessageDialog(null, pe.getCause(), null, JOptionPane.INFORMATION_MESSAGE);
			}
			
			//will need to update the excel system later so we can make up who covers what class
		}
	}
	
	public static void main(String [] args) throws Exception{	
		new GUI();
	}
}
