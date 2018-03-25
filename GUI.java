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
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUI extends JFrame
{	
	private static final long serialVersionUID = 7214392589058560804L;
	
	private JPanel mainPanel, mainCenterPanel, southPanel;
	private JTable table1, table2, table3;
	private JComboBox<String> printOptions, dates;
	private JLabel dateLabel;
	private JButton printButton, updateOnCalls, selectFileButton, clearButton;
	private JFileChooser fileChooser;
	private ArrayList<File> fileList = new ArrayList<File>();
	private JTextArea textArea;
	
	private int rowHeight = 30;
	private Dimension dim;
	private DefaultTableModel model1, model2, model3;
	private GridBagConstraints gbc;
	
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
		dim = new Dimension(140,30);
		
		northPanelSetup();
		southPanelSetup();
		centerPanelInit();
	
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setIconImage(new ImageIcon("mainIcon.png").getImage());
	}
	
	private void northPanelSetup() 
	{
		JPanel panelNorth = new JPanel(new BorderLayout());
		JPanel headerEastPanel = new JPanel(new FlowLayout());
		JPanel headerWestPanel = new JPanel(new FlowLayout());
		
		JLabel header = new JLabel("On-Call Tracker", SwingConstants.CENTER);
		header.setFont(new Font("Arial", Font.BOLD, 30));
		header.setForeground(Color.WHITE);
		
		panelNorth.setBackground(Color.DARK_GRAY);
		panelNorth.add(headerEastPanel, BorderLayout.EAST);
		panelNorth.add(headerWestPanel, BorderLayout.WEST);
		panelNorth.add(header, BorderLayout.CENTER);
		mainPanel.add(panelNorth, BorderLayout.NORTH);
		
		updateOnCalls = new JButton("Update On-Calls");
		updateOnCalls.setPreferredSize(dim);
		updateOnCalls.addActionListener(new EventHandling());
		headerEastPanel.setBackground(panelNorth.getBackground());
		headerWestPanel.add(updateOnCalls);
		
		String[] dateOptions = {"Dates","date option 1", "date option 2"};
		dates = new JComboBox<String>(dateOptions);
		dates.setPreferredSize(dim);
		dates.addActionListener(new EventHandling());
		headerEastPanel.setBackground(panelNorth.getBackground());
		headerWestPanel.add(dates);

		clearButton = new JButton("Clear selected files");
		clearButton.setPreferredSize(dim);
		clearButton.addActionListener(new EventHandling());
		
		selectFileButton = new JButton("Open File");
		selectFileButton.setPreferredSize(dim);
		selectFileButton.addActionListener(new EventHandling());
		
		headerEastPanel.add(selectFileButton);
		headerEastPanel.add(clearButton);
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
		
		subCenterPanel.setBackground(mainSouthPanel.getBackground());
		subWestPanel.setBackground(mainSouthPanel.getBackground());
		
		String date = new SimpleDateFormat("dd/MM/YYYY").format(Calendar.getInstance().getTime());
		dateLabel = new JLabel("Today's date: " + date);
		dateLabel.setFont(new Font("Arial", Font.BOLD, 22));
		dateLabel.setForeground(Color.WHITE);
		dateLabel.setPreferredSize(new Dimension(260,25));
		subEastPanel.add(dateLabel);
		subEastPanel.setBackground(mainSouthPanel.getBackground());

		printButton = new JButton("Print");
		printButton.setPreferredSize(dim);
		printButton.addActionListener(new EventHandling());
		subWestPanel.add(printButton);
		
		String [] printList = {"Assignments","Coverage","Availability"};
		printOptions = new JComboBox<String>(printList);
		printOptions.setPreferredSize(dim);
		subWestPanel.add(printOptions);
		
		mainSouthPanel.add(subWestPanel, BorderLayout.WEST);
		mainSouthPanel.add(subEastPanel, BorderLayout.EAST);
		mainSouthPanel.add(subCenterPanel, BorderLayout.CENTER);
	}

	private void centerPanelInit() 
	{
		mainCenterPanel = new JPanel(new BorderLayout());
		mainPanel.add(mainCenterPanel, BorderLayout.CENTER);
		
		mainCenterPanel.setBackground(new Color(45,124,155));
		mainCenterPanel.setPreferredSize(new Dimension(2200,1375));
		JLabel info = new JLabel("Once the excel files are saved, find the files, "
				+ "select the date of interest, and press update On-Calls.");
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setFont(new Font("Arial", Font.PLAIN, 22));
		info.setForeground(Color.WHITE);
		mainCenterPanel.add(info, BorderLayout.NORTH);
		
		ImageIcon icon = new ImageIcon("mainIcon.png");
		JLabel iconLabel = new JLabel();
		iconLabel.setHorizontalAlignment(JLabel.CENTER);
		iconLabel.setIcon(icon);
		mainCenterPanel.add(iconLabel, BorderLayout.CENTER);
		
		southPanel = new JPanel();
		southPanel.setBackground(mainCenterPanel.getBackground());
		textArea = new JTextArea("                      Below are the currently selected files:\n\n");
		textArea.setEditable(false);
		textArea.setFont(new Font("Arial", Font.BOLD, 16));
		textArea.setPreferredSize(new Dimension(450,80));
		southPanel.add(textArea);
		mainCenterPanel.add(southPanel,BorderLayout.SOUTH);
	}
	
	private void centerPanelSetup()
	{
		mainCenterPanel.removeAll();
		mainCenterPanel.setBackground(mainCenterPanel.getBackground());
		mainCenterPanel.setLayout(new GridBagLayout());
		
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(15,15,15,15);
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH; 
	
		mainCenterPanel.revalidate();
		mainCenterPanel.repaint();
	}
	
	private void constructViewOne(Vector<Vector<String>> teacherData) 
	{
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
		
		String[] title = {"Name","Spare","Week","Month","Total/term"};
		Vector<String> titleVector = new Vector<String>();
		
		for(String item: title) {
			titleVector.add(item);
		}
		
		model1.setDataVector(teacherData, titleVector);
		
		table1 = new JTable(model1);
		table1.getColumn("Name").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Spare").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Week").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Month").setCellRenderer(new TextAreaRenderer());
		table1.getColumn("Total/term").setCellRenderer(new TextAreaRenderer());
		
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table1.setFillsViewportHeight(true);
		table1.setRowHeight(rowHeight);
		table1.getColumnModel().getColumn(0).setPreferredWidth(200);
		table1.getColumnModel().getColumn(0).setMinWidth(200);
		table1.setGridColor(Color.BLACK);
		
	   	JScrollPane scrollTable1 = new JScrollPane(table1);

	   	LineBorder border1 = new LineBorder(Color.DARK_GRAY, 4, true);
	    	TitledBorder tBorder1 = new TitledBorder (border1, "Coverage Counts to Date", TitledBorder.CENTER,
	        TitledBorder.DEFAULT_JUSTIFICATION, new Font ("Arial", Font.BOLD, 16), new Color(247,61,51));
	    
	   	scrollTable1.setBorder(tBorder1);
	    
		mainCenterPanel.add(scrollTable1, gbc);
	}

	private void constructViewTwo(Vector<Vector<String>> teacherData) 
	{
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		model2 = new DefaultTableModel() 
		{
			private static final long serialVersionUID = 5234114342913495413L;

			public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		
		String[] title = {"Period","Weekly","Monthly","Who's next in line?"};
		Vector<String> titleVector = new Vector<String>();
		
		for(String item: title) {
			titleVector.add(item);
		}
		
		model2.setDataVector(teacherData, titleVector);
		
		table2 = new JTable(model2);
		table2.getColumn("Period").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Weekly").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Monthly").setCellRenderer(new TextAreaRenderer());
		table2.getColumn("Who's next in line?").setCellRenderer(new TextAreaRenderer());
		
		table2.setFillsViewportHeight(true);
		table2.setRowHeight(rowHeight);
		table2.getColumnModel().getColumn(3).setPreferredWidth(175);
		table2.getColumnModel().getColumn(3).setMinWidth(175);
		table2.setGridColor(Color.BLACK);
		
	    	JScrollPane scrollTable2 = new JScrollPane(table2);

	    	LineBorder border2 = new LineBorder(Color.DARK_GRAY, 4, true);
	    	TitledBorder tBorder2 = new TitledBorder (border2, "Availability Counts", TitledBorder.CENTER,
	    	TitledBorder.DEFAULT_JUSTIFICATION, new Font ("Arial", Font.BOLD, 16), new Color(247,61,51));
	    
	    	scrollTable2.setBorder(tBorder2);
	   	mainCenterPanel.add(scrollTable2, gbc);
	} 

	private void constructViewThree(Vector<Vector<String>> teacherData) 
	{
		gbc.gridheight = 2;
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		model3 = new DefaultTableModel() 
		{
			private static final long serialVersionUID = 3072435220329162498L;

			public boolean isCellEditable(int row, int column)
		       {
		          return false;
		       }
		};
		
		String[] title = {"Period","Absentee","Covered by", "Room Number"};
		Vector<String> titleVector = new Vector<String>();
		
		for(String item: title) {
			titleVector.add(item);
		}
		
		model3.setDataVector(teacherData, titleVector);
		
		table3 = new JTable(model3);
		table3.getColumn("Period").setCellRenderer(new TextAreaRenderer());
		table3.getColumn("Absentee").setCellRenderer(new TextAreaRenderer());
		table3.getColumn("Covered by").setCellRenderer(new TextAreaRenderer());
		table3.getColumn("Room Number").setCellRenderer(new TextAreaRenderer());
		
		table3.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table3.setFillsViewportHeight(true);
		table3.setRowHeight(rowHeight);
		table3.getColumnModel().getColumn(1).setPreferredWidth(175);
		table3.getColumnModel().getColumn(1).setMinWidth(175);
		table3.getColumnModel().getColumn(2).setPreferredWidth(175);
		table3.getColumnModel().getColumn(2).setMinWidth(175);
		table3.setGridColor(Color.BLACK);
		
	    	JScrollPane scrollTable3 = new JScrollPane(table3);

	    	LineBorder border = new LineBorder(Color.DARK_GRAY, 4, true);
	    	TitledBorder border3 = new TitledBorder ( border, "Assignments", TitledBorder.CENTER,
	        TitledBorder.DEFAULT_JUSTIFICATION, new Font ( "Arial", Font.BOLD, 16), new Color(247,61,51));
	    
	    	scrollTable3.setBorder(border3);
	    
		mainCenterPanel.add(scrollTable3, gbc);
	}

	private class EventHandling implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == clearButton && table1 == null) 
			{
				fileList.clear();
				textArea.setText("                      Below are the currently selected files:\n\n");
				return;
			}
			
			if(e.getSource() == selectFileButton)
			{
				if(fileList.size() >= 2)
				{
					JOptionPane.showMessageDialog(null, "Already two files selected", null, JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xls", "xlsx");
				fileChooser.setFileFilter(filter);
				int returnedVal = fileChooser.showOpenDialog(GUI.this);
				
				if(returnedVal == JFileChooser.APPROVE_OPTION)
				{
					File file = fileChooser.getSelectedFile();
		
					fileList.add(file);
					JTextArea text = (JTextArea) southPanel.getComponent(0);
					text.append("File #" + fileList.size() + ": "+ file.getName() + "\n");
					revalidate();
				}
				return;
			}
			
			if(fileList.size() != 2) 
			{
				JOptionPane.showMessageDialog(null, "You must first select two excel files", null, JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			if(e.getSource() == updateOnCalls) 
			{
				AbsenceWorkbookReader AWreader = new AbsenceWorkbookReader(fileList.get(0),"2018-03-16");
				TallyWorkbookReader TWreader = new TallyWorkbookReader(fileList.get(1), "2018-03-19");
				
				DataProccess data = new DataProccess(AWreader,TWreader);
				ArrayList<OnCallTeacher> teacherList = new ArrayList<OnCallTeacher>();
				ArrayList<SupplyTeacher> supplyList = new ArrayList<SupplyTeacher>();
				
				try 
				{
					teacherList = data.createTeacherTermSchedule();
					supplyList = data.createSupplyList();
				} catch (IOException | ParseException e1)
				{
					System.out.println(e1.getMessage());
				}
				
				centerPanelSetup();
				
				constructViewOne(GenerateView.generateCountView(teacherList));
				constructViewTwo(GenerateView.generateAvailabilityView(teacherList));
				constructViewThree(GenerateView.generateCoverageView(teacherList, supplyList));
				
				revalidate();
				repaint();
				
				return;
			}

			if(e.getSource() == printButton)	printRequest();
		}

		private void printRequest() 
		{
			try
			{
				boolean complete = false;
				
				if(table1 == null && table2 == null && table3 == null) 
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
}
