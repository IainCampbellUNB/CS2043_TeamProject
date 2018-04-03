package project.group4;

import java.io.File;

public class WorkBook{
	private File file;
	private String selectedDate;
	private String searchForSheetWithDate;
	
	public WorkBook(File file, String selectedDate, String searchForSheetWithDate){
		this.file = file;
		this.selectedDate = selectedDate;
		this.searchForSheetWithDate = searchForSheetWithDate;
	}
	
	public File getFile() {
		return file;
	}
	
	public String getDate(){
		return selectedDate;
	}
	
	public String getSheetWithDate() {
		return searchForSheetWithDate;
	}
}
