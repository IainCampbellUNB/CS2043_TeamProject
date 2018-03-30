package team4.assn5;

public class TallyCount {

	private String weeklyCount;
	private String monthlyCount;
	private String termCount;
	private String teacherName;
	
	public TallyCount(String weeklyCount, String monthlyCount, String termCount, String teacherName){
		this.weeklyCount = weeklyCount;
		this.monthlyCount = monthlyCount;
		this.termCount = termCount;
		this.teacherName = teacherName;
	}
	
	public void setTalleys(String week, String month, String term){
		this.weeklyCount = week;
		this.monthlyCount = month;
		this.termCount = term;
	}
	
	public String getWeeklyCount(){
		return this.weeklyCount;
	}
	
	public String getMonthlyCount(){
		return this.monthlyCount;
	}
	public String getTermCount(){
		return this.termCount;
	}
	
	public String getTeacher(){
		return this.teacherName;
	}
}
