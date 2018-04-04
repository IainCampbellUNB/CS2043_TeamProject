package project.group4;
import java.io.File;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.CellStyle;
//import org.apache.poi.ss.usermodel.DataFormat;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
			System.out.print("no such date found");
		}
		
		int lastcol = searchColIndex("P4",sheet);
		boolean done = false;
		int i = 1;
		lastcol++;
		while(!done)
		{

			if(sheet.getRow(i) == null)
			{
				done = true;
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

		//Get the sheet
		HSSFSheet sheet = workbook.getSheetAt(1);
		
		try
		{
			sheet = workbook.getSheet(getSheetWithDate());
		}
		catch(NullPointerException e)
		{
			System.out.print("no such date found");
		}
		
		boolean done = false;
		int i = 1;
		while(!done)
		{
			if(sheet.getRow(i) == null)
			{
				done = true;
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
            System.out.print("No such date found");
        }
	
		boolean done = false;
		int i = 2;
		while(!done){
			
			if(sheet.getRow(i) == null)
			{
				done = true;
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
		boolean done = false;
		int row = 0;
		while(!done)
		{
			if(sheet.getRow(row).getCell(0) == null)
			{
				done = true;
				break;
			}
			String value = sheet.getRow(row).getCell(0).toString();
		
			if(value.equals(searchWord))
			{
				done = true;
				break;
			}
			row++;
		}
		return row;
	}
	
	public static int searchColIndex(String searchWord, HSSFSheet sheet)
	{
		boolean done = false;
		int col = 0;
		while(!done)
		{
			if(sheet.getRow(0).getCell(col) == null)
			{
				done = true;
				break;
			}
			String value = sheet.getRow(0).getCell(col).toString();
		
			if(value.equals(searchWord))
			{
				done = true;
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