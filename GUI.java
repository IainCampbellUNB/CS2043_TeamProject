package project.group4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUI extends JFrame{
	
	private JLabel label1,label2,label3,label4;
	private JButton button1,button2;
	private JTextField textfield1,textfield2,textfield3,textfield4;
	private JTable table;
	
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
		
		label1 = new JLabel("Teacher ID", SwingConstants.RIGHT);
		label1.setPreferredSize(new Dimension(80,25));
		panelSouth.add(label1);
		
		textfield1 = new JTextField();
		textfield1.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield1);
		
		label2 = new JLabel("something",SwingConstants.RIGHT);
		label2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(label2);
		
		textfield2 = new JTextField();
		textfield2.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield2);
		
		label3 = new JLabel("Hours",SwingConstants.RIGHT);
		label3.setPreferredSize(new Dimension(80,25));
		panelSouth.add(label3);
		
		textfield3 = new JTextField();
		textfield3.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield3);
		
		label4 = new JLabel("Reason for absence",SwingConstants.RIGHT);
		label4.setPreferredSize(new Dimension(130,25));
		panelSouth.add(label4);
		
		textfield4 = new JTextField();
		textfield4.setPreferredSize(new Dimension(80,25));
		panelSouth.add(textfield4);
		
		button1 = new JButton("Add teacher");
		button1.setPreferredSize(new Dimension(120,25));
		button1.addActionListener(new ButtonActionListener());
		panelSouth.add(button1);
		
		button2 = new JButton("Submit report");
		button2.setPreferredSize(new Dimension(120,25));
		button2.addActionListener(new ButtonActionListener());
		panelSouth.add(button2);
		
		//center panel
		JPanel panelCenter = new JPanel(new BorderLayout());
		panelCenter.setBackground(Color.BLACK);
		panel.add(panelCenter, BorderLayout.CENTER);
		
		String [] header = {"a","b","c","d"};
		String [][] data = {{"Each row will have the info of whoever is absent","1","2","3"},{"Teacher 2 info..","4","5","6"}};
		DefaultTableModel model = new DefaultTableModel(data,header);
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
			//String input = textfield.getText();
			//textfield.setText("");
		}
	}
	
}
