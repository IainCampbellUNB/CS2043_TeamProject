package project.group4;


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

public class AbsenceWorkerReader {
	static boolean skillsFilled = false;
	
	public  boolean getSkillsFilled(){
		return skillsFilled;
	}
	
	public static ArrayList<ArrayList<String>> readTermSchedule() throws IOException, ParseException {
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
	
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkbook.xls"));
		
		HSSFSheet sheet = workbook.getSheetAt(0);
		
		int skillIndex = searchColIndex("Skills",sheet);
	
		if(!(sheet.getRow(1).getCell(skillIndex).toString().equals("")))
		{
			
			skillsFilled = true;

		}
		
		boolean done = false;
		int i = 1;
		skillIndex++;
		while(!done){
		
			if(sheet.getRow(i) == null){
				done = true;
				break;
			}
			perRowData = new ArrayList<String>();
				
			if(!skillsFilled)
			{
		    	System.out.println("SkillFilled");
				for(int j = 0; j < (skillIndex); j++) {
					perRowData.add(sheet.getRow(i).getCell(j).toString());
					
					System.out.println(j);
				}
				String value = sheet.getRow(i).getCell(7).toString();
				System.out.println("value" + value);
			}else{
				for(int j = 0; j < skillIndex; j++) {
					perRowData.add(sheet.getRow(i).getCell(j).toString());
				}
			}
			allData.add(perRowData);
			i++;
		}
	
		workbook.close();
		return allData;
	}
	
	
	public static ArrayList<ArrayList<String>> readSupplyList() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkbook.xls"));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheetAt(1);
		boolean done = false;
		int i = 1;
		while(!done){
		
			if(sheet.getRow(i) == null){
				done = true;
				break;
			}
			perRowData = new ArrayList<String>();
			for(int j = 0; j < 2; j++) {
			perRowData.add(sheet.getRow(i).getCell(j).toString());
			}	
			allData.add(perRowData);
			i++;
		}
		
		workbook.close();
		return allData;
		
	}
	
	public static ArrayList<ArrayList<String>> readAbsenceTracker(String selectedDate) throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("AbsenceWorkbook.xls"));
	
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
		
		
		boolean done = false;
		int i = 2;
		while(!done){
		
			if(sheet.getRow(i) == null){
				done = true;
				break;
			}
			perRowData = new ArrayList<String>();
			perRowData.add(sheet.getRow(i).getCell(0).toString());
			for(int j = 1; j < 6; j++){
				perRowData.add(sheet.getRow(i).getCell(j).toString());
			}
			allData.add(perRowData);
			i++;
		}

		workbook.close();
		return allData;
		
	}
	
	public static void writeToAbsenceTracker(ArrayList<OnCallTeacher> teacherList, String selectedDate) throws IOException
	{
		FileInputStream file = new FileInputStream("AbsenceWorkbook.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		/************************************************
		 * SET AT RIGHT INDEX
		 ************************************************/
		
		for(int i = 2; i < 10; i++) {
		
				
				sheet.getRow(i).getCell(1).setCellValue("Hi");
				sheet.getRow(i).getCell(2).setCellValue("HI");
				sheet.getRow(i).getCell(3).setCellValue("hi");
				sheet.getRow(i).getCell(4).setCellValue("HI");
			
			
		}
		
       file.close();

        FileOutputStream fileOut = new FileOutputStream("A.xls");
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