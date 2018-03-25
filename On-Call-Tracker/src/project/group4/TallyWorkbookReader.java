package project.group4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.text.ParseException;
import java.util.ArrayList;

public class TallyWorkbookReader
{
	private static File file;
	private static String selectedDate;
	
	public TallyWorkbookReader(File fileIn, String selectedDateIn){
		file = fileIn;
		selectedDate = selectedDateIn;
	}
	
	public ArrayList<ArrayList<String>> readTallyCount() throws IOException, ParseException
	{
		ArrayList<ArrayList<String>> allData = new ArrayList<ArrayList<String>>();
		ArrayList<String> perRowData = new ArrayList<String>();
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(file));

		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		int columnIndexWT = searchColIndex("Weekly Tally", sheet);
		int columnIndexMT = searchColIndex("Month Tally", sheet);
		int columnIndexTERM = searchColIndex("Per Term Tally", sheet);
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
	
	public static void writeToTallyCoutner(ArrayList<OnCallTeacher> teacherList, String selectedDate) throws IOException
	{
		FileInputStream file = new FileInputStream("TallyTest.xls");
		HSSFWorkbook workbook = new HSSFWorkbook(file);
		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		int day = searchColIndex("Thursday",sheet);
		
		for(OnCallTeacher onCaller: teacherList)
		{
			 if(onCaller.getHasBeenAssigned())
			 {	
				int col = onCaller.getSparePeriodByIndex();
				int insertAt = col + day;
				int findRowIndexForTeacher = searchRowIndex(onCaller.getName(), sheet);
				sheet.getRow(findRowIndexForTeacher).getCell(insertAt).setCellValue("1");
			}
		}
		
		file.close();

        FileOutputStream fileOut = new FileOutputStream("TallyOutput.xls");
        workbook.write(fileOut);
        workbook.close();
	}

	public static int searchColIndex(String searchWord, HSSFSheet sheet)
	{
		int col = 0;
		
		while(true)
		{
			if(sheet.getRow(0).getCell(col) == null){
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
		
		while(true){
			
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
	
}
