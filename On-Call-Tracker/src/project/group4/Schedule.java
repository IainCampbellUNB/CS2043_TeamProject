package project.group4;

import java.util.ArrayList;

public class Schedule
{
  private String p1;
  private String p2;
  private String p3a;
  private String p3b;
  private String p4;
  private String spare;
  private int spareIndex;
  private ArrayList<String> schedule;

  public Schedule(String p1In, String p2In, String p3aIn, String p3bIn, String p4In)
  {
    this.p1 = p1In;
    this.p2 = p2In;
    this.p3a = p3aIn;
    this.p3b = p3bIn;
    this.p4 = p4In;
    schedule = new ArrayList<String>(5);
    schedule.add(p1);
    schedule.add(p2);
    schedule.add(p3a);
    schedule.add(p3b);
    schedule.add(p4);
    spareIndex = determineSparePeriodByIndex();
    spare = convertIndexToPeriod(spareIndex);
  }

  public String getSpareByString()
  {
	  return spare;
  }
  
  public int determineSparePeriodByIndex()
  {
	  int period = 0;
	  for(int index = 0; index < 5; index++){
		  if(schedule.get(index).equals("Spare")){
			  period = index;
		  } 
	  }
		  
	  return period;
  }
  
 
  public String convertIndexToPeriod(int index)
  {
	 String ind = String.valueOf(index);
	 String answer = "";
	  switch (ind) {
      	case "0":
    	  answer  = "P1";
          break;
      	case "1":
    	  answer = "P2";
          break;
      	case "2":
    	  answer = "P3A";
          break;
      	case "3":
    	  answer = "P3B";
          break;
      	case "4":
    	  answer = "P4";
          break;
	  }   
	  return answer;
  }
  

  public ArrayList<String> getSchedule()
  {
    return this.schedule;
  }
  
  public String toString(){
	  String result = "";
	  for(int i = 0; i < 5; i++)
		  result += schedule.get(i) + " "; 
	  
	  return result;
  }
}
