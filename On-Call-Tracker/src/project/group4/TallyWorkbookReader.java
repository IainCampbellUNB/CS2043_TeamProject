
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TallyWorkbookReader {

	public static void main(String [] args) throws ParseException
	{
		try 
		{
			//readExcel(Calendar.getInstance().getTime());
			readExcel("2018-03-19");
		}
		catch(IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	

	private static ArrayList<String> readExcel(String selectedDate) throws IOException, ParseException {

		ArrayList<String> data = new ArrayList<String>();
		//File file = new File("b.xls");
		//System.out.println(file.exists());
		//Pass the file
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream("b.xls"));

		//Get the sheet
		HSSFSheet sheet = workbook.getSheet(selectedDate);
		
		//Include a NULLPOINTER TO CATCH NON-EXISTING ENTRY
		
		System.out.println(selectedDate);
		//Convert String to Date
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		//Convertdate to Weekday
		Date date = formatter.parse(selectedDate);
		System.out.println(date.toString());
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		System.out.println("day of the Week " + dayOfWeek);
		//Monday is 5
		
		
		//Skip header
		//HSSFRow row = sheet.getRow(2);
		
		
		
		for(int i = 2; i < 25; i++) {
			data.add(sheet.getRow(i).getCell(0).toString());
			for(int j = 1; j < 5; j++)
			{
				data.add(sheet.getRow(i).getCell(j).toString());
			}
			data.add(sheet.getRow(i).getCell(26).toString());
			data.add(sheet.getRow(i).getCell(27).toString());
			data.add(sheet.getRow(i).getCell(28).toString());
			
			
		}

		
		for(int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i));
		}
		
		return data;
	}
}