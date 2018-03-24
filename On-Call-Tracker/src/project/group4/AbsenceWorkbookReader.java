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

public class AbsenceWorkbookReader {
	static boolean skillsFilled = false;
	private File file;
	private String selectedDate;
	private String searchForSheetWithDate;
	private HSSFWorkbook workbook;
	
	

	public AbsenceWorkbookReader(File file,String selectedDate, String date)
	{
		this.file = file;
		this.selectedDate = selectedDate;
		this.searchForSheetWithDate = date;
	
	}
	public  boolean getSkillsFilled(){
		return skillsFilled;
	}
	
	public  ArrayList<ArrayList<String>> readTermSchedule() throws IOException, ParseException {
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
	
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));
		
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
	
	
	public  ArrayList<ArrayList<String>> readSupplyList() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));

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
	
	public  ArrayList<ArrayList<String>> readAbsenceTracker() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = null;
		
		workbook = new HSSFWorkbook(new FileInputStream(file));
	
		HSSFSheet sheet = null;
		
		try
		{
			sheet = workbook.getSheet(searchForSheetWithDate);
			System.out.println((sheet == null) + " error....");
		}
		catch(NullPointerException e)
        {
            System.out.print("No such date found");
        }
	
		
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
	
	/*public void writeToAbsenceTracker(ArrayList<OnCallTeacher> teacherList, String selectedDate) throws IOException
	{
		FileInputStream file1 = new FileInputStream(file)
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.getSheet(searchForSheetWithDate);
	
		int day = searchColIndex(selectedDate,sheet);
		
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
		
		
		
       file1.close();

        FileOutputStream fileOut = new FileOutputStream("A.xls");
        workbook.write(fileOut);
        workbook.close();
	}*/
	
	
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