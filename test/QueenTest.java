package test;

import static org.junit.Assert.assertEquals;


import model.Queen;

import org.junit.Test;


public class QueenTest {

	@Test
	public void testQueen_no_threating() {
	
		model.Queen queenA = new Queen(3, 7);
		model.Queen queenB = new Queen(5, 8);
		
		boolean threat = queenA.threatens(queenB);
		
		assertEquals("Queen threatens though.", false, threat);
	}
	
	@Test
	public void test_threating_horizontale() {
		model.Queen queenA = new Queen(3, 7);
		model.Queen queenB = new Queen(8, 7);
		
		boolean threat = queenA.threatens(queenB);
		
		assertEquals("Queens are not threatened horizontale.", true, threat);
	}
	
	@Test
	public void test_threating_verticale() {
		model.Queen queenA = new Queen(3, 7);
		model.Queen queenB = new Queen(3, 5);
		
		boolean threat = queenA.threatens(queenB);
		
		assertEquals("Queens are not threatened verticale.", true, threat);
	}
	
	@Test
	public void test_threating_bottomleft_topright() {
		model.Queen queenA = new Queen(3, 6);
		model.Queen queenB = new Queen(5, 4);
		
		boolean threat = queenA.threatens(queenB);
		
		assertEquals("Queens are not threatened from bottomleft to topright.", true, threat);
	}
	
	
	@Test
	public void test_threating_bottomright_topleft() {
		model.Queen queenA = new Queen(2, 4);
		model.Queen queenB = new Queen(4, 6);
		
		boolean threat = queenA.threatens(queenB);
		
		assertEquals("Queens are not threatened from bottomright to topleft.", true, threat);
	}
	
	@Test
	public void test_validCoordinate01() {
		String regex = "83,48";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is not valid, but should be.", true, valid);
	}
	@Test
	public void test_validCoordinate02() {
		String regex = "-32,3";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is not valid, but should be.", true, valid);
	}
	@Test
	public void test_validCoordinate03() {
		String regex = "15,-70";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is not valid, but should be.", true, valid);
		
	}
	@Test
	public void test_validCoordinate04() {
		String regex = "-79,-38";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is not valid, but should be.", true, valid);
		
	}
	@Test
	public void test_validCoordinate05() {
		String regex = "9-79,-38";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate06() {
		String regex = "-79,-38,67";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate07() {
		String regex = "6557";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate08() {
		String regex = "53,";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate09() {
		String regex = ",-10";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate10() {
		String regex = ",3";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate11() {
		String regex = "a,6";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate12() {
		String regex = "7,a";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate13() {
		String regex = "dhs";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate14() {
		String regex = ",-";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate15() {
		String regex = "-";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate16() {
		String regex = ",";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}
	@Test
	public void test_validCoordinate17() {
		String regex = "";
		
		boolean valid = Queen.isvalidcoordinate(regex);
		
		assertEquals("This coordinate is valid, but should not be.", false, valid);
		
	}

}
