package project.group4;

import static org.junit.Assert.*;

import org.junit.Test;

public class AbsenceTrackerTest {

	//tests the set value by index method
	@Test
	public void testSetValueByIndex() {
		AbsenceTracker absTrack = new AbsenceTracker("0.0","X","0.0","0.0","X");
		
		absTrack.setValueByIndex(1,"0.0");
		absTrack.setValueByIndex(0,"X");
		
		assertTrue("0.0".equals(absTrack.absences.get(1)));
		assertTrue("X".equals(absTrack.absences.get(0)));
		
	}


}
