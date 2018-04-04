package project.group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.ParseException;
import java.util.ArrayList;


public class TallyWorkbookReader extends WorkBook {
	
	public TallyWorkbookReader(File file, String selectedDate, String searchForSheetWithDate){	
		super(file, selectedDate, searchForSheetWithDate);
	}
	
	public  ArrayList<ArrayList<String>> readTallyCount() throws IOException, ParseException {

		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(getFile()));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheet(getSheetWithDate());
		
		//Include a NULLPOINTER TO CATCH NON-EXISTING ENTRY
		int monday = searchColIndex(getDate(),sheet);
		
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
	
	public void writeToTallyCoutner(ArrayList<OnCallTeacher> teacherList) throws IOException{
		FileInputStream file = new FileInputStream(getFile());
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(getSheetWithDate());
		
		int day = searchColIndex(getDate(),sheet);
		int week = searchColIndex("Weekly Tally" ,sheet);
		int month = searchColIndex("Month Tally", sheet);
		int term = searchColIndex("Per Term Tally", sheet);
		
		
		for(int i = 0; i < teacherList.size(); i++){
			 if(teacherList.get(i).getHasBeenAssigned()){	
				 int findRowIndexForTeacher = searchRowIndex(teacherList.get(i).getName(),sheet);
				 String weekValue = teacherList.get(i).getWeeklyTally();
				 String monthValue = teacherList.get(i).getMonthlyTally();
				 String termValue = teacherList.get(i).getTermTally();
				 sheet.getRow(findRowIndexForTeacher).getCell(week).setCellValue(weekValue);
				 sheet.getRow(findRowIndexForTeacher).getCell(month).setCellValue(monthValue);
				 sheet.getRow(findRowIndexForTeacher).getCell(term).setCellValue(termValue);
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