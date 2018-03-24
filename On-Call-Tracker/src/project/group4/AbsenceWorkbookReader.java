package project.group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.ParseException;
import java.util.ArrayList;

public class AbsenceWorkbookReader 
{
	static boolean skillsFilled = false;
	private static File file;
	private static String selectedDate;
	
	public AbsenceWorkbookReader(File fileIn, String selectedDateIn)
	{
		file = fileIn;
		selectedDate = selectedDateIn;
	}
	
	public boolean getSkillsFilled()
	{
		return skillsFilled;
	}
	
	public  ArrayList<ArrayList<String>> readTermSchedule() throws IOException, ParseException
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
	
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int skillIndex = searchColIndex("Skills",sheet);
	
		if(!(sheet.getRow(1).getCell(skillIndex).toString().equals("")))
		{
			skillsFilled = true;
		}
		
		int i = 1;
		skillIndex++;
		
		while(true)
		{
			if(sheet.getRow(i) == null)
			{
				break;
			}
			
			perRowData = new ArrayList<String>();
				
			if(!skillsFilled)
			{
				for(int j = 0; j < skillIndex; j++) 
				{
					perRowData.add(sheet.getRow(i).getCell(j).toString());
				}
			}else
			{
				for(int j = 0; j < skillIndex; j++)
				{
					perRowData.add(sheet.getRow(i).getCell(j).toString());
				}
			}
			
			allData.add(perRowData);
			i++;
		}
	
		workbook.close();
		return allData;
	}
	
	
	public  ArrayList<ArrayList<String>> readSupplyList() throws IOException, ParseException 
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = workbook.getSheetAt(1);
		
		int i = 1;
		
		while(true)
		{
			if(sheet.getRow(i) == null)
			{
				break;
			}
			
			perRowData = new ArrayList<String>();
			
			for(int j = 0; j < 2; j++)
			{
				perRowData.add(sheet.getRow(i).getCell(j).toString());
			}	
			
			allData.add(perRowData);
			i++;
		}
		
		workbook.close();
		return allData;
	}
	
	public  ArrayList<ArrayList<String>> readAbsenceTracker() throws IOException, ParseException
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		HSSFSheet sheet = null;
		
		try
		{
			sheet = workbook.getSheet(selectedDate);
		}
		catch(NullPointerException e)
        {
            System.out.print("No such date found");
        }
		
		int i = 2;
		
		while(true)
		{
			if(sheet.getRow(i) == null)
			{
				break;
			}
			
			perRowData = new ArrayList<String>();
			perRowData.add(sheet.getRow(i).getCell(0).toString());
			
			for(int j = 1; j < 6; j++)
			{
				perRowData.add(sheet.getRow(i).getCell(j).toString());
			}
			
			allData.add(perRowData);
			i++;
		}

		workbook.close();
		return allData;
	}
	
	public static int searchRowIndex(String searchWord, HSSFSheet sheet)
	{
		int row = 0;
		
		while(true)
		{
			if(sheet.getRow(row).getCell(0) == null)
			{
				break;
			}
			
			String value = sheet.getRow(row).getCell(0).toString();
		
			if(value.equals(searchWord))
			{
				break;
			}
			
			row++;
		}
		return row;
	}
	
	public static int searchColIndex(String searchWord, HSSFSheet sheet)
	{
		int col = 0;
		
		while(true)
		{
			if(sheet.getRow(0).getCell(col) == null)
			{
				break;
			}
			
			String value = sheet.getRow(0).getCell(col).toString();
		
			if(value.equals(searchWord))
			{
				break;
			}
			
			col++;
		}
		
		return col;
	}
	
}
