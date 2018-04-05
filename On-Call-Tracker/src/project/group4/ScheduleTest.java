package project.group4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ScheduleTest {
	
	//Test to see if the method returns the correct value
	@Test
	public void testGetSpareByStringTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertEquals("P2",sched.getSpareByString());
		
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testGetSpareByStringFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertFalse("P3B".equals(sched.getSpareByString()));
		
	}

	//Test to see if the method returns the correct value
	@Test
	public void testGetSkillTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
		String skill = sched.getSkill();
		assertEquals("MATH",skill);
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testGetSkillFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
		String skill = sched.getSkill();
		assertFalse("BIOL".equals(skill));
	}

	//Test to see if the method returns the correct value
	@Test
	public void testIsLunchPeriodTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue(sched.isLunchPeriod(2));
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testIsLunchPeriodFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertFalse(sched.isLunchPeriod(4));
	}
	
	//Test to see if the method returns the correct value
	@Test
	public void testIsSparePeriodTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue(sched.isSparePeriod(1));
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testIsSparePeriodFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertFalse(sched.isSparePeriod(3));
	}

	//Test to see if the method returns the correct value
	@Test
	public void testGetSubjectTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue("BIOL".equals(sched.getSubject(0)));
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testGetSubjectFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertFalse("BIOL".equals(sched.getSubject(4)));
	}
	
	//Test to see what happens when you try to find the subject of a spare or lunch 
	@Test
	public void testGetSubjectSpareOrLunch() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue("Spare".equals(sched.getSubject(1)));
		assertTrue("Lunch".equals(sched.getSubject(2)));
	}
	
	//Test to see if the method returns the correct value
	@Test
	public void testGetRoomNumberTrue() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue("RM102".equals(sched.getRoomNumber(3)));
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testGetRoomNumberFalse() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertFalse("RM102".equals(sched.getRoomNumber(0)));
	}
	
	//Test to see what happens when you try to find the Room of a spare or lunch 
	@Test
	public void testGetRoomSpareOrLunch() {
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		int spareIndex = sched.determineSparePeriodByIndex();
		sched.convertIndexToPeriod(spareIndex);
		sched.determineSkill();
		
		assertTrue("Spare".equals(sched.getRoomNumber(1)));
		assertTrue("Lunch".equals(sched.getRoomNumber(2)));
	}

	//Test to see if the method returns the correct value
	@Test
	public void testDetermineSparePeriodByIndexTrue() {
		
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
	    assertTrue(1 == sched.determineSparePeriodByIndex());
	}
	
	//Test to make sure that the method returns only the correct value
	@Test
	public void testDetermineSparePeriodByIndexFalse() {
		
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
		int spareIndex = sched.determineSparePeriodByIndex();
	    assertFalse(5 ==spareIndex);
	}
	
	//Test to make sure that the method returns the correct value
	@Test
	public void testConvertIndexToPeriodTrue() {
		
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
		String period = sched.convertIndexToPeriod(3);
		assertTrue("P3B".equals(period));
	}
	
	//Test to make sure that the method does not just agree with any value
	@Test
	public void testConvertIndexToPeriodFalse() {
		
		Schedule sched = new Schedule("BIOL/RM103","Spare","Lunch","MATH/RM102","MATH/RM104");
		
		String period = sched.convertIndexToPeriod(4);
		assertFalse("P3B".equals(period));
	}

}
