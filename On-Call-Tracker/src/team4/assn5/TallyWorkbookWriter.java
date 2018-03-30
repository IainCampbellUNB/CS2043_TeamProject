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

public class TallyWorkbookWriter {
	private File file;
	private String selectedDate;
	private String searchForSheetWithDate;
	
	public TallyWorkbookWriter(File file, String selectedDate, String date){
		this.file = file;
		this.selectedDate = selectedDate;
		this.searchForSheetWithDate = date;
		
	}
	
	
	public void writeToTallyCoutner(ArrayList<TallyCount> obj) throws IOException{
		FileInputStream filew = new FileInputStream(file);
		HSSFWorkbook workbook = new HSSFWorkbook(filew);
		HSSFSheet sheet = workbook.getSheet(searchForSheetWithDate);
		
		int week = searchColIndex("Weekly Tally" ,sheet);
		int month = searchColIndex("Month Tally", sheet);
		int term = searchColIndex("Per Term Tally", sheet);
		
		for(int i = 0; i < obj.size(); i++){
			
			int findRowIndexForTeacher = searchRowIndex(obj.get(i).getTeacher(),sheet);
			sheet.getRow(findRowIndexForTeacher).getCell(week).setCellValue(obj.get(i).getWeeklyCount());
			sheet.getRow(findRowIndexForTeacher).getCell(month).setCellValue(obj.get(i).getMonthlyCount());
			sheet.getRow(findRowIndexForTeacher).getCell(term).setCellValue(obj.get(i).getTermCount());
			
		}
		
		filew.close();

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
		
			if(value.equals(searchWord)){
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
			if(value.equals(searchWord)){
				done = true;
				break;
			}
			row++;
		}
		return row;
	}
}