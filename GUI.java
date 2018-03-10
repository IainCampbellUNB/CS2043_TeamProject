package project.group4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 7214392589058560804L;
	
	private JPanel mainPanel, mainCenterPanel;
	private JTable table1, table2, table3;
	private JComboBox<String> dateSelecter, printOptions;
	private JLabel dateLabel;
	private JButton printButton, updateOnCalls, SelectFileButton;
	private JFileChooser fileChooser;
	
	private Dimension dim;
	private DefaultTableModel model1, model2, model3;
	private String [] dates;
	private String date;
	
	public GUI() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("CS2043 Project - Group 4");
		
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
	
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setPreferredSize(new Dimension(1200,750));
		getContentPane().add(mainPanel);
		dim = new Dimension(140,25);
		
		northPanelSetup();
		southPanelSetup();
		centerPanelInit();
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void northPanelSetup() 
	{
		JPanel panelNorth = new JPanel(new BorderLayout());
		JPanel headerEastPanel = new JPanel(new FlowLayout());
		JPanel headerWestPanel = new JPanel(new FlowLayout());
		
		JLabel header = new JLabel("On-Call form", SwingConstants.CENTER);
		header.setFont(new Font("Times new roman", Font.BOLD, 30));
		header.setForeground(Color.WHITE);
		
		panelNorth.setBackground(Color.DARK_GRAY);
		panelNorth.add(headerEastPanel, BorderLayout.WEST);
		panelNorth.add(headerWestPanel, BorderLayout.EAST);
		panelNorth.add(header, BorderLayout.CENTER);
		mainPanel.add(panelNorth, BorderLayout.NORTH);
		
		printButton = new JButton("Print");
		printButton.setPreferredSize(dim);
		printButton.addActionListener(new EventHandling());
		
		String [] printList = {"Assignments","Coverage","Availability"};
		printOptions = new JComboBox<String>(printList);
		printOptions.setPreferredSize(dim);

		SelectFileButton = new JButton("Open File");
		SelectFileButton.setPreferredSize(dim);
		SelectFileButton.addActionListener(new EventHandling());
		
		headerWestPanel.add(SelectFileButton);
		headerEastPanel.add(printOptions);
		headerEastPanel.add(printButton);
		headerWestPanel.setBackground(panelNorth.getBackground());
		headerEastPanel.setBackground(panelNorth.getBackground());
	}
	
	private void southPanelSetup() 
	{
		JPanel mainSouthPanel = new JPanel(new BorderLayout());
		mainSouthPanel.setBackground(Color.DARK_GRAY);
		mainPanel.add(mainSouthPanel, BorderLayout.SOUTH);
		
		JPanel subWestPanel = new JPanel();
		JPanel subEastPanel = new JPanel();
		JPanel subCenterPanel = new JPanel();
		
		date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		dates = new String[] {"",date};
		dateLabel = new JLabel("Today's date: " + date);
		dateLabel.setFont(new Font("Times new roman", Font.BOLD, 18));
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setPreferredSize(new Dimension(200,25));
		subEastPanel.add(dateLabel);
		subEastPanel.setBackground(mainSouthPanel.getBackground());
		
		JLabel dateSelectorLabel = new JLabel("Please select the date (dd/mm/yyyy):");
		dateSelectorLabel.setPreferredSize(new Dimension(240,25));
		dateSelectorLabel.setForeground(Color.WHITE);
		dateSelecter = new JComboBox<String>(dates);
		dateSelecter.setPreferredSize(dim);
		subCenterPanel.add(dateSelectorLabel);
		subCenterPanel.add(dateSelecter);
		subCenterPanel.setBackground(mainSouthPanel.getBackground());
		
		updateOnCalls = new JButton("Update On-Calls");
		updateOnCalls.setPreferredSize(dim);
		updateOnCalls.addActionListener(new EventHandling());
		subWestPanel.setBackground(mainSouthPanel.getBackground());
		subWestPanel.add(updateOnCalls);
		
		mainSouthPanel.add(subWestPanel, BorderLayout.WEST);
		mainSouthPanel.add(subEastPanel, BorderLayout.EAST);
		mainSouthPanel.add(subCenterPanel, BorderLayout.CENTER);
	}

	private void centerPanelInit() 
	{
		mainCenterPanel = new JPanel(new BorderLayout());
		mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
		
		JPanel temp = new JPanel();
		temp.setBackground(Color.GRAY);
		mainCenterPanel.setBackground(Color.BLACK);
		mainCenterPanel.add(temp, BorderLayout.CENTER);
		mainCenterPanel.setPreferredSize(new Dimension(2200,1375));
		temp.add(new JLabel("Once the excel file is saved, find the file, "
				+ "select the date of interest, and press update On-Calls."));
	}
	
	private void centerPanelSetup()
	{
		//Absent teacher info will be placed inside the .setDataVector method
		mainCenterPanel.removeAll();
		mainCenterPanel.setBackground(Color.GRAY);
		mainCenterPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,15,15,15);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH; 

		//---------------------------------------------------------
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		model1 = new DefaultTableModel() 
		{
			private static final long serialVersionUID = 8650052207374641604L;

			public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		
		model1.setDataVector(new Object[][] {{ "Example\ntext","...","...","...","..."},{"...","...","...","...","..."},
				{"...","...","...","...","..."},{"...","...","...","...","..."},{"...","...","...","...","..."},{"...","...","...","...","..."}},
				new Object[]{ "Name","Spare","Week","Month","Total/term"});
		
		table1 = new JTable(model1);
		table1.getColumn("Name").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Spare").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Week").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Month").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Total/term").setCellRenderer(new TextAreaRenderer());
		
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setFillsViewportHeight(true);
		table1.setRowHeight(100);
		table1.setGridColor(Color.BLACK);
		
	    JScrollPane scrollTable1 = new JScrollPane(table1);
	    TitledBorder border = new TitledBorder("Coverage Counts to Date");
	    scrollTable1.setBorder(border);
	    
		mainCenterPanel.add(scrollTable1, gbc);
		
		//------------------------------------------------------------
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		model2 = new DefaultTableModel() 
		{
			private static final long serialVersionUID = 8650052207374641604L;

			public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		
		model2.setDataVector(new Object[][] {{ "Example\ntext","Week will\ngo here","Month here...","next in line here.."},
				{"...","...","...","..."},{"...","...","...","..."}},
				new Object[]{ "Period","Week","Month","Who's next in line?"});
		
		table2 = new JTable(model2);
		table2.getColumn("Period").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Week").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Month").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Who's next in line?").setCellRenderer(new TextAreaRenderer());
		
		table2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table2.setFillsViewportHeight(true);
		table2.setRowHeight(100);
		table2.setGridColor(Color.BLACK);
		
	    JScrollPane scrollTable2 = new JScrollPane(table2);
	    TitledBorder border2 = new TitledBorder("Availability Counts");
	    scrollTable2.setBorder(border2);
		
		mainCenterPanel.add(scrollTable2, gbc);
		
		//------------------------------------------------------------
		
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		model3 = new DefaultTableModel() 
		{
			private static final long serialVersionUID = 8650052207374641604L;

			public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		
		model3.setDataVector(new Object[][] {{ "period here...","Absentee name","name of teacher\ncovering them"},
				{"...","...","..."},{"...","...","..."},{"...","...","..."},{"...","...","..."},{"...","...","..."},
				{"...","...","..."},{"...","...","..."}},
				new Object[]{ "Period","Absentee","Covered by"});
		
		table3 = new JTable(model3);
		table3.getColumn("Period").setCellRenderer(new TextAreaRenderer());
		table3.getColumn("Absentee").setCellRenderer(new TextAreaRenderer());
		table3.getColumn("Covered by").setCellRenderer(new TextAreaRenderer());
		
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table3.setFillsViewportHeight(true);
		table3.setRowHeight(100);
		table3.setGridColor(Color.BLACK);
		
	    JScrollPane scrollTable3 = new JScrollPane(table3);
	    TitledBorder border3 = new TitledBorder("Assignments");
	    scrollTable3.setBorder(border3);
	    
		mainCenterPanel.add(scrollTable3, gbc);
		revalidate();
	}
	
	private class EventHandling implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{	
			if(e.getSource() == updateOnCalls) 
			{
				//this is how we know which excel sheet to go into
				String selectedDate = (String) dateSelecter.getSelectedItem();
				if(selectedDate.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please select a date first", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				System.out.println(selectedDate);
				centerPanelSetup();
			}
			
			if(e.getSource() == SelectFileButton)
			{
				fileChooser = new JFileChooser();
				int returnedVal = fileChooser.showOpenDialog(GUI.this);
				
				if(returnedVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
					System.out.println(file.getName());
				}
			}

			if(e.getSource() == printButton)	printRequest();
		}
		
		private void printRequest() 
		{
			try
			{
				boolean complete = false;
				
				if(table1 == null || table2 == null || table3 == null) 
				{
					JOptionPane.showMessageDialog(null, "Please select \"Update On-Calls\" first", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				if(printOptions.getSelectedItem().equals("Coverage"))
				{
					complete = table1.print();
				}
				
				if(printOptions.getSelectedItem().equals("Availability"))
				{
					complete = table2.print();
				}
				
				if(printOptions.getSelectedItem().equals("Assignments")) 
				{
					complete = table3.print();
				}
				
				if(!complete) 
				{
					JOptionPane.showMessageDialog(null, "Failed To Print...", null, JOptionPane.INFORMATION_MESSAGE);
				}
				
			}catch(PrinterException pe)
			{
				JOptionPane.showMessageDialog(null, pe.getCause(), null, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	//credit for TextAreaRenderer: http://esus.com/embedding-a-jtextarea-in-a-jtable-cell/
	private class TextAreaRenderer extends JScrollPane implements TableCellRenderer
	{
		private static final long serialVersionUID = -1342133979182047460L;
		JTextArea textArea;
	
		public TextAreaRenderer() {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			getViewport().add(textArea);
		}
		  
		   public Component getTableCellRendererComponent(JTable table, Object value,
		                                  boolean isSelected, boolean hasFocus,
		                                  int row, int column)
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
		  
		      textArea.setText((String) value);
		      textArea.setCaretPosition(0);
		      return this;
		   }
	}
	
	public static void main(String [] args)
	{	
		try 
		{
			new GUI();
		}
		catch(Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
