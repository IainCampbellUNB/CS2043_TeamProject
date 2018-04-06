package project.group4;

import static org.junit.Assert.*;

import org.junit.Test;

public class OnCallTeacherTest {

	//Test to see if the has been assigned method works properly
	@Test
	public void testHasBeenAssigned() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		Schedule sched2 = new Schedule("PHYS/RM103","HIST/RM101","Spare","Lunch","MATH/RM103");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		OnCallTeacher teacher2 = new OnCallTeacher("Vanessa Blantagaroo","A28",sched2);
		
		teacher1.hasBeenAssigned();
		
		assertTrue(teacher1.getHasBeenAssigned());
		assertFalse(teacher2.getHasBeenAssigned());
		
	}
	//Test to see if the getSparePeriodByIndex method works and gets the index of the spare
	@Test
	public void testGetSparePeriodByIndex() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		
		assertTrue(1 == teacher1.getSparePeriodByIndex());
		assertFalse(3 == teacher1.getSparePeriodByIndex());
	}
	//Test to see if the getSparePeriodByValue method works and gets the period of the spare
	@Test
	public void testGetSparePeriodByValue() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		
		assertTrue("P2".equals(teacher1.getSparePeriodByValue()));
		assertFalse("P3B".equals(teacher1.getSparePeriodByValue()));
	}
	
	//Test to see if the setToAbsent() method works
	@Test
	public void testSetToAbsent() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		Schedule sched2 = new Schedule("PHYS/RM103","HIST/RM101","Spare","Lunch","MATH/RM103");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		OnCallTeacher teacher2 = new OnCallTeacher("Vanessa Blantagaroo","A28",sched2);
		
		teacher1.setToAbsent();
		
		assertTrue(teacher1.getAbsentStatus());
		assertFalse(teacher2.getAbsentStatus());
	}

	//test to make sure the set, increment, and max reached weekly tally methods work properly
	@Test
	public void testWeeklyTally() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		
		//Test to make sure the setter method is working
		teacher1.setWeeklyTally("1.0");
		

		assertTrue("1.0".equals(teacher1.getWeeklyTally()));
		assertFalse(teacher1.hasReachedweeklyMax());
		
		
		//Test the increment method on a teachers who's tally has been set
		teacher1.incrementWeeklyTally();
		
		assertTrue("2.0".equals(teacher1.getWeeklyTally()));
		
		//Checks to see if the maxReached method works
		assertTrue(teacher1.hasReachedweeklyMax());
				
	}
	

	//test to make sure the set, increment, and max reached monthly tally methods work properly
	@Test
	public void testMonthlyTally() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		
		teacher1.setMonthlyTally("3.0");
		
		assertTrue("3.0".equals(teacher1.getMonthlyTally()));
		assertFalse("20.0".equals(teacher1.getMonthlyTally()));
		
		assertFalse(teacher1.hasReachedMonthlyMax());
		
		//Test the increment method on a teachers who's tally has been set
		teacher1.incrementMonthlyTally();
		
		assertTrue("4.0".equals(teacher1.getMonthlyTally()));
		assertTrue(teacher1.hasReachedMonthlyMax());
		
	}

	//test to make sure the set, increment term tally methods work properly
	@Test
	public void testTermTally() {
		Schedule sched1 = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		OnCallTeacher teacher1 = new OnCallTeacher("Paul Wigorgon","A27",sched1);
		
		teacher1.setTermTally("6.0");
		
		assertTrue("6.0".equals(teacher1.getTermTally()));
		assertFalse("9.0".equals(teacher1.getTermTally()));
		
		//Test the increment method on a teachers who's tally has been set
		teacher1.incrementTermTally();
		
		assertTrue("7.0".equals(teacher1.getTermTally()));	
		
	}

}
