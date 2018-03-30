package team4.assn5;

import java.io.File;
//import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
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

import project.group4.OnCallTeacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TallyWorkbookReader {
	private File file;
	private String selectedDate;
	private String searchForSheetWithDate;
	
	public TallyWorkbookReader(File file, String selectedDate, String date){
		this.file = file;
		this.selectedDate = selectedDate;
		this.searchForSheetWithDate = date;

	}
	
	public  ArrayList<ArrayList<String>> readTallyCount() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheet(searchForSheetWithDate);
		
		//Include a NULLPOINTER TO CATCH NON-EXISTING ENTRY
		int monday = searchColIndex(selectedDate,sheet);
		
		int columnIndexWT = searchColIndex("Weekly Tally",sheet);
		int columnIndexMT = searchColIndex("Month Tally",sheet);
		int columnIndexTERM = searchColIndex("Per Term Tally",sheet);
		
		boolean done = false;
		int i = 2;
		while(!done){
		
			if(sheet.getRow(i) == null){
				done = true;
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
	
	public static void writeToTallyCoutner(ArrayList<OnCallTeacher> teacherList, String selectedDate) throws IOException
	{
		FileInputStream file = new FileInputStream("TallyTest.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		int day = searchColIndex("Thursday",sheet);
		
		for(int i = 0; i < teacherList.size(); i++)
		{
			 if(teacherList.get(i).getHasBeenAssigned())
			 {	
				int col = teacherList.get(i).getSparePeriodByIndex();
				int insertAt = col + day;
				int findRowIndexForTeacher = searchRowIndex(teacherList.get(i).getName(),sheet);
				sheet.getRow(findRowIndexForTeacher).getCell(insertAt).setCellValue("1");
			}
		}
		
		
       file.close();

        FileOutputStream fileOut = new FileOutputStream("TallyOutput.xls");
        workbook.write(fileOut);
        workbook.close();
	}
	
	public static int searchColIndex(String searchWord, HSSFSheet sheet){
		boolean done = false;
		int col = 0;
		while(!done){
			if(sheet.getRow(0).getCell(col) == null){
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
	
	public static int searchRowIndex(String searchWord, HSSFSheet sheet){
		boolean done = false;
		int row = 0;
		while(!done){
			if(sheet.getRow(row).getCell(0) == null){
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
	
	
	public void printData(ArrayList<ArrayList<String>> allData)
	{
		for(int i = 0; i < allData.size(); i++){
			for(int j = 0; j < allData.get(i).size(); j++){
			System.out.print(" " + allData.get(i).get(j));
			}
			System.out.println("");
		}
	}
}