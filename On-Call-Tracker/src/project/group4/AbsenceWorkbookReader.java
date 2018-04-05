package project.group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.ParseException;
import java.util.ArrayList;

public class AbsenceWorkbookReader extends WorkBook 
{
	public AbsenceWorkbookReader(File file, String selectedDate, String searchForSheetWithDate)
	{
		super(file, selectedDate, searchForSheetWithDate);
	}
	
	public  ArrayList<ArrayList<String>> readTermSchedule() throws IOException, ParseException
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getFile()));	
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int lastcol = searchColIndex("P4",sheet);
		int i = 1;
		lastcol++;
		
		while(true)
		{
			if(sheet.getRow(i) == null)
			{
				break;
			}
			
			perRowData = new ArrayList<String>();
			
			for(int j = 0; j < (lastcol); j++) 
			{
				perRowData.add(sheet.getRow(i).getCell(j).toString());
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
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getFile()));
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
		HSSFWorkbook workbook = null;
		workbook = new HSSFWorkbook(new FileInputStream(getFile()));
		HSSFSheet sheet = null;
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
			System.out.print("No such sheet found");
		}
		
		String searchWord = getDate();
		int day = searchColIndex(searchWord,sheet);
		
		int i = 2;
		
		while(true)
		{
			if(sheet.getRow(i) == null)
			{
				break;
			}
			perRowData = new ArrayList<String>();
			perRowData.add(sheet.getRow(i).getCell(0).toString());
			
			for(int j = day; j < day+5; j++)
			{
				perRowData.add(sheet.getRow(i).getCell(j).toString());
			}
			allData.add(perRowData);
			i++;
		}

		workbook.close();
		return allData;
	}
	
	public void writeToAbsenceTracker(ArrayList<OnCallTeacher> teacherList) throws IOException
	{
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		FileInputStream file1 = new FileInputStream(getFile());
		workbook = new HSSFWorkbook(file1);
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
			System.out.print("No such date found");
        	}
		
		String searchWord = getDate();
		int day = searchColIndex(searchWord,sheet);
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			 if(teacherList.get(i).getAbsentStatus())
			 {	
				int findRowIndexForTeacher = searchRowIndex(teacherList.get(i).getName(),sheet);
				AbsenceTracker schedule = teacherList.get(i).getSubmittedAbsenceSchedule();
				int c = 0;
				
				for(int j = day; j < day+5; j++)
				{
					String value = schedule.getPeriodValueAtIndex(c);
					sheet.getRow(findRowIndexForTeacher).getCell(j).setCellValue(value);
					c++;
				}	
			}
		}
		
	file1.close();
        FileOutputStream fileOut = new FileOutputStream(getFile());
        workbook.write(fileOut);
        workbook.close();
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
			String value1 = sheet.getRow(0).getCell(col).toString();
			System.out.println(value1);
			
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
	
	
	public void printData(ArrayList<ArrayList<String>> allData)
	{
		for(int i = 0; i < allData.size(); i++)
		{
			for(int j = 0; j < allData.get(i).size(); j++)
			{
				System.out.print(" " + allData.get(i).get(j));
			}
			System.out.println("");
		}
	}
}
