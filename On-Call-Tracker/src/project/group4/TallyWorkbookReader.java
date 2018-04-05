package project.group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;


public class TallyWorkbookReader extends WorkBook 
{
	public TallyWorkbookReader(File file, String selectedDate, String searchForSheetWithDate)
	{	
		super(file, selectedDate, searchForSheetWithDate);
	}
	
	public  ArrayList<ArrayList<String>> readTallyCount() throws IOException, ParseException 
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getFile()));
		HSSFSheet sheet = null;
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
            System.out.print("No such sheet found");
        }
		
		int columnIndexWT = searchColIndex("Weekly Tally",sheet);
		int columnIndexMT = searchColIndex("Month Tally",sheet);
		int columnIndexTERM = searchColIndex("Per Term Tally",sheet);
		
		int i = 2;
		
		while(true)
		{
		
			if(sheet.getRow(i) == null)
			{
				break;
			}
			perRowData = new ArrayList<String>();
			perRowData.add(sheet.getRow(i).getCell(0).toString());
			perRowData.add(sheet.getRow(i).getCell(columnIndexWT).toString());
			perRowData.add(sheet.getRow(i).getCell(columnIndexMT).toString());
			perRowData.add(sheet.getRow(i).getCell(columnIndexTERM).toString());
			
			allData.add(perRowData);
			i++;
		}

		workbook.close();
		return allData;
	}
	
	public void writeToTallyCounter(ArrayList<OnCallTeacher> teacherList) throws IOException
	{
		FileInputStream file = new FileInputStream(getFile());
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = null;
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
            System.out.print("No such sheet found");
        }
		
		int sheetnum = workbook.getNumberOfSheets();
		int index = workbook.getSheetIndex(sheet);
		
		System.out.println(sheetnum + "  " + index);
		
		int day = searchColIndex(getDate(),sheet);
		int week = searchColIndex("Weekly Tally" ,sheet);
		int month = searchColIndex("Month Tally", sheet);
		int term = searchColIndex("Per Term Tally", sheet);
		
		String monthCheck =  getMonth(getSheetWithDate());
		HSSFSheet sheet2 = null;
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			System.out.println(teacherList.get(i).getName() + " " + teacherList.get(i).getHasBeenAssigned());
		
			int findRowIndexForTeacher = searchRowIndex(teacherList.get(i).getName(),sheet);
			String weekValue = teacherList.get(i).getWeeklyTally();
			String monthValue = teacherList.get(i).getMonthlyTally();
			String termValue = teacherList.get(i).getTermTally();
			sheet.getRow(findRowIndexForTeacher).getCell(week).setCellValue(weekValue);
			sheet.getRow(findRowIndexForTeacher).getCell(month).setCellValue(monthValue);
			/*
			* Finishing this bit 
			*/
			String nextdate = calculateFutureDates(getSheetWithDate());
			String newValue = getMonth(nextdate);
			
			if(newValue.equals(monthCheck))
			{
				try
				{
					sheet2 = workbook.getSheet(nextdate);
				}
				catch(NullPointerException e)
				{
						System.out.print("No such sheet found");
				}
				
				if(sheet2 != null)
				{
					 System.out.println("month Term Assigned");
					 sheet2.getRow(findRowIndexForTeacher).getCell(month).setCellValue(monthValue);
				}
			}
		
			sheet.getRow(findRowIndexForTeacher).getCell(term).setCellValue(termValue);
			 
			for(int j = index+1; j < sheetnum; j++)
			{
				System.out.println("Term Assigned");
				HSSFSheet sheet1 = workbook.getSheetAt(j);
				sheet1.getRow(findRowIndexForTeacher).getCell(term).setCellValue(termValue);
			}
			
			if(teacherList.get(i).getHasBeenAssigned())
			{	
				int col = teacherList.get(i).getSparePeriodByIndex();
				int insertAt = col + day;
				sheet.getRow(findRowIndexForTeacher).getCell(insertAt).setCellValue("1");
			}
		}
		
       file.close();

       FileOutputStream fileOut = new FileOutputStream(getFile());
       workbook.write(fileOut);
       workbook.close();
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
	
	private String getMonth(String date)
	{
		Scanner sc = new Scanner(date);
		sc.useDelimiter("-");
		String year = sc.next();
		String monthi = sc.next();
		
		return monthi;
	}
	
	private String calculateFutureDates(String date)
	{
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		Scanner sc = new Scanner(date);
		sc.useDelimiter("-");
		
		String year = sc.next();
		int iyear = Integer.parseInt(year);
		
		String monthi = sc.next();
		int imonth = Integer.parseInt(monthi);
		
		String dayi = sc.next();
		int iday = Integer.parseInt(dayi);
		
		Calendar c = Calendar.getInstance();
		c.set(iyear,imonth-1,iday);
		c.add(Calendar.WEEK_OF_MONTH, 1);
		
		return s.format(c.getTime());
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
