package project.group4;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends JFrame
{
	private JLabel idLabel,nameLabel,periodsLabel,reasonLabel, mainLabel;
	private JButton addButton,submitButton, deleteButton,mainButton, mainButton2, absenceButton, onCallButton;
	private JTextField idField,nameField;
	private JTable table;
	private JComboBox<String> reasonBox;
	private DefaultTableModel model;
	private JPanel containerPanel, absencePanel, mainPanel, onCallPanel, mainPanelNorth;
	private CardLayout cl = new CardLayout();
	private JRadioButton p1,p2,p3a,p3b,p4,p5,p6, allDay;
	private JTextArea textArea;
	
	private String id;
	private String name;
	private String reason;
	
	public GUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CS2043 Project - Group 4");
		setSize(1500,920);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		
		containerPanel = new JPanel();
		absencePanel = new JPanel(new BorderLayout());
		mainPanel = new JPanel();
		onCallPanel = new JPanel();
		
		containerPanel.setLayout(cl);
		add(containerPanel);
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e)
		{
			e.printStackTrace();
		}
	
		getContentPane().add(mainPanel);
		
		cardLayout();
		northPanelSetup();
		southPanelSetup();
		centerPanelSetup();
		westPanelSetup();
		mainMenuPanelSetup();
		onCallMenuPanelSetup();
	}
	
	private void northPanelSetup() 
	{
		JPanel panelNorth = new JPanel(new BorderLayout());
		panelNorth.setBackground(Color.GRAY);
		absencePanel.add(panelNorth, BorderLayout.NORTH);
		JLabel head = new JLabel("Teacher absences form", SwingConstants.CENTER);
		head.setFont(new Font("Times new roman", Font.BOLD, 30));
		head.setForeground(Color.BLACK);
		panelNorth.add(head, BorderLayout.CENTER);
		
		mainButton = new JButton("Main menu");
		mainButton.addActionListener(new EventHandling());
		JPanel mainMenuPanel = new JPanel(new FlowLayout());
		panelNorth.add(mainMenuPanel, BorderLayout.WEST);
		mainMenuPanel.add(mainButton);
		mainMenuPanel.setBackground(panelNorth.getBackground());
		
		submitButton = new JButton("Submit form");
		submitButton.setPreferredSize(new Dimension(140,25));
		submitButton.addActionListener(new EventHandling());
		JPanel submitPanel = new JPanel(new FlowLayout());
		panelNorth.add(submitPanel, BorderLayout.EAST);
		submitPanel.add(submitButton);
		submitPanel.setBackground(panelNorth.getBackground());
	}
	
	private void westPanelSetup() 
	{
		JPanel panelWest = new JPanel(new BorderLayout());
		JPanel north = new JPanel();
		JPanel south = new JPanel();
		JPanel center = new JPanel();
		
		panelWest.setMaximumSize(new Dimension(300,800));
		panelWest.setPreferredSize(new Dimension(300,800));
		
		north.setMinimumSize(new Dimension(300,340));
		north.setPreferredSize(new Dimension(300,340));
		north.setBackground(Color.GRAY);
		
		south.setMinimumSize(new Dimension(300,340));
		south.setPreferredSize(new Dimension(300,340));
		south.setBackground(Color.GRAY);
		
		center.setMinimumSize(new Dimension(300,120));
		center.setPreferredSize(new Dimension(300,120));
		center.setBackground(Color.GRAY);
		
		absencePanel.add(panelWest, BorderLayout.WEST);
		panelWest.add(north, BorderLayout.NORTH);
		panelWest.add(south, BorderLayout.SOUTH);
		panelWest.add(center, BorderLayout.CENTER);

		idLabel = new JLabel("Teacher ID");
		idLabel.setMaximumSize(new Dimension(130,25));
		idLabel.setPreferredSize(new Dimension(130,25));
		center.add(idLabel);
		
		idField = new JTextField();
		idField.setPreferredSize(new Dimension(130,25));
		center.add(idField);
		
		nameLabel = new JLabel("Name");
		nameLabel.setPreferredSize(new Dimension(130,25));
		center.add(nameLabel);
		
		nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(130,25));
		center.add(nameField);
		
		reasonLabel = new JLabel("Reason for absence: ");
		reasonLabel.setPreferredSize(new Dimension(130,25));
		center.add(reasonLabel);
		
		String [] combo = {"","Personal","Work related","Unknown","Personal illness"};
		reasonBox = new JComboBox<String>(combo);
		reasonBox.setMaximumSize(new Dimension(130,25));
		reasonBox.setPreferredSize(new Dimension(130,25));
		center.add(reasonBox);
	        
		periodsLabel = new JLabel("Period(s) absent: ");
		periodsLabel.setPreferredSize(new Dimension(275,25));
		center.add(periodsLabel);
		
		p1 = new JRadioButton("1");
		p2 = new JRadioButton("2");
		p3a = new JRadioButton("3a");
		p3b = new JRadioButton("3b");
		p4 = new JRadioButton("4");
		p5 = new JRadioButton("5");
		p6 = new JRadioButton("6");
		allDay = new JRadioButton("All");
		
		p1.setBackground(south.getBackground());
		p2.setBackground(south.getBackground());
		p3a.setBackground(south.getBackground());
		p3b.setBackground(south.getBackground());
		p4.setBackground(south.getBackground());
		p5.setBackground(south.getBackground());
		p6.setBackground(south.getBackground());
		allDay.setBackground(south.getBackground());
		
		Box box1 = Box.createVerticalBox();
		box1.add(p1);
		box1.add(p2);
		
		Box box2 = Box.createVerticalBox();
		box2.add(p3a);
		box2.add(p3b);
		
		Box box3 = Box.createVerticalBox();
		box3.add(p4);
		box3.add(p5);
		
		Box box4 = Box.createVerticalBox();
		box4.add(p6);
		box4.add(allDay);
		
		south.setLayout(new GridLayout(1, 4));
		
		south.add(box1);
		south.add(box2);
		south.add(box3);
		south.add(box4);
	}
	
	private void southPanelSetup() 
	{
		JPanel panelSouth = new JPanel();
		panelSouth.setLayout(new FlowLayout());
		panelSouth.setBackground(Color.GRAY);
		absencePanel.add(panelSouth, BorderLayout.SOUTH);
		
		
		addButton = new JButton("Add Absentee");
		addButton.setPreferredSize(new Dimension(140,25));
		addButton.addActionListener(new EventHandling());
		panelSouth.add(addButton);
		
		deleteButton = new JButton("Delete selected");
		deleteButton.setPreferredSize(new Dimension(140,25));
		deleteButton.addActionListener(new EventHandling());
		panelSouth.add(deleteButton);
	}

	private void centerPanelSetup()
	{
		JPanel panelCenter = new JPanel(new BorderLayout());
		absencePanel.add(panelCenter, BorderLayout.CENTER);
		
		String [] header = {"col1","col2","col3"};
		String [][] data = {};
		
		model = new DefaultTableModel(data,header) 
		{
		       public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		    
		table = new JTable(model);	
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setTableHeader(null);
		table.setFillsViewportHeight(true);
		table.setRowHeight(120);
		table.setGridColor(Color.BLACK);
		table.getColumnModel().getColumn(1).setMaxWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		
	    JScrollPane scrollTable = new JScrollPane(table);
		panelCenter.add(scrollTable, BorderLayout.CENTER);
	}
	
	private void cardLayout() 
	{
		containerPanel.add(absencePanel,"absence");
		containerPanel.add(mainPanel,"main menu");
		containerPanel.add(onCallPanel,"on call");
		cl.show(containerPanel,"main menu");
	}
		
	private void mainMenuPanelSetup() 
	{
		mainPanelNorth = new JPanel(new BorderLayout());
		absenceButton = new JButton("Absence menu");
		onCallButton = new JButton("On call form");
		mainLabel = new JLabel("Main menu", SwingConstants.CENTER);
		mainLabel.setFont(new Font("Times new roman", Font.BOLD, 30));
		
		onCallButton.addActionListener(new EventHandling());
		absenceButton.addActionListener(new EventHandling());
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(mainPanelNorth, BorderLayout.NORTH);

		mainPanelNorth.add(onCallButton, BorderLayout.WEST);
		mainPanelNorth.add(mainLabel, BorderLayout.CENTER);
		mainPanelNorth.add(absenceButton, BorderLayout.EAST);
		mainPanel.setBackground(Color.GRAY);
		
		revalidate();
	}
	
	private void onCallMenuPanelSetup() 
	{
		mainButton2 = new JButton("main menu");
		mainButton2.addActionListener(new EventHandling());
		onCallPanel.add(mainButton2);
		onCallPanel.setBackground(Color.GRAY);
	}
	
	private class EventHandling implements ActionListener
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
			
			id = idField.getText();
			name = nameField.getText();
			reason = (String) reasonBox.getSelectedItem();
			
			idField.setText("");
			nameField.setText("");
			reasonBox.setSelectedItem("");
			
			p1.setSelected(false);
			p2.setSelected(false);
			p3a.setSelected(false);
			p3b.setSelected(false);
			p4.setSelected(false);
			p5.setSelected(false);
			p6.setSelected(false);
			allDay.setSelected(false);
			
			String [] temp = new String[3];
			model.addRow(temp);
			
			table.getColumn("col1").setCellRenderer(new TextAreaRenderer());
			table.getColumn("col2").setCellRenderer(new TextAreaRenderer());
			table.getColumn("col3").setCellRenderer(new TextAreaRenderer());
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
	
	private class TextAreaRenderer extends JScrollPane implements TableCellRenderer
	{
	   JTextArea textArea;
	   
	   public TextAreaRenderer()
	   {
	      textArea = new JTextArea();
	      textArea.setLineWrap(true);
		  viewport.add(textArea);
	   }
	  
	   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	   {
	      if (isSelected) {
	         setForeground(table.getSelectionForeground());
	         setBackground(table.getSelectionBackground());
	         textArea.setForeground(table.getSelectionForeground());
	         textArea.setBackground(table.getSelectionBackground());
	      } else {
	         setForeground(table.getForeground());
	         setBackground(table.getBackground());
	         textArea.setForeground(table.getForeground());
	         textArea.setBackground(table.getBackground());
	      }
	      
	      switch(column)
	      {
	      case 0: textArea.setText("Name: " + name + "\t\t\t\tTeacher ID: " + id); break;
	      case 1: textArea.setText("Sched-days\nMon: \nTue: \nWed: \nThu: \nFri: "); break;
	      case 2: textArea.setText("period(s) absent: " + "\nReason: " + reason); break;
	      }
	      
	      return this;
	   }
	}
	
	public static void main(String [] args) throws Exception
	{	
		new GUI();
	}
}