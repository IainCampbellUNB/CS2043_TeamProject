package project.group4;


//import java.io.File;
import java.io.FileInputStream;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AbsenceWorkerReader {

	public static void main(String [] args) throws ParseException
	{
		/*try 
		{
		
			//readAbsenceTracker("2018-03-16");
		}
	*/
	}
	

	public static ArrayList<ArrayList<String>> readTermSchedule() throws IOException, ParseException {
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		//CreateWork
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkBook.xls"));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheetAt(0);
	
		for(int i = 1; i < 25; i++) {
			//This is needed to restart with a fresh ArrayList.
			perRowData = new ArrayList<String>();
			for(int j = 0; j < 7; j++) {
			perRowData.add(sheet.getRow(i).getCell(j).toString());
			}	
			allData.add(perRowData);
		}

		workbook.close();
		return allData;
	}
	
	
	public static ArrayList<ArrayList<String>> readSupplyList() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkBook.xls"));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheetAt(1);
		
		for(int i = 1; i < 12; i++) {
			perRowData = new ArrayList<String>();
			for(int j = 0; j < 2; j++) {
			perRowData.add(sheet.getRow(i).getCell(j).toString());
			}	
			allData.add(perRowData);
		}
		
		workbook.close();
		return allData;
		
	}
	
	public static ArrayList<ArrayList<String>> readAbsenceTracker(String selectedDate) throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkBook.xls"));
	
		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		//Include a NULLPOINTER TO CATCH NON-EXISTING ENTRY
		
		//Convert String to Date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		/***************************************************************
		 * BUG		Convert Date Object to the selected Date and not current date
		 ***************************************************************/
		
		//Convertdate to Weekday
		Date date = formatter.parse(selectedDate);
		System.out.println(date.toString());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		
		for(int i = 2; i < 14; i++) {
			perRowData = new ArrayList<String>();
			perRowData.add(sheet.getRow(i).getCell(0).toString());
			for(int j = 1; j < 6; j++){
				perRowData.add(sheet.getRow(i).getCell(j).toString());
			}
			allData.add(perRowData);
		}

		workbook.close();
		return allData;
		
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