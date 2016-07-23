package grammars;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestRule {

Rule testR;
	
	@Test
	public void testStringSplitConstructor() {
		testR = new Rule("A,B", "a,bb,c");
		assertEquals( testR.lhs.size(), 2 );
		assertEquals( testR.rhs.size(), 3 );
		assertEquals( testR.lhs.get(1), "B");
		assertEquals( testR.rhs.get(2), "c");
	}
	
	@Test 
	public void testStringSplitConstructorOnEmptyStrings() {
		testR = new Rule("", "");
		assertEquals( testR.lhs.size(), 0);
		assertEquals( testR.rhs.size(), 0);
	}
	
	@Test
	public void testStringSplitConstructorOnCommas() {
		testR = new Rule(",", ",");
		assertEquals( testR.lhs.size(), 0);
		assertEquals( testR.rhs.size(), 0);
	}
	
	@Test( expected=IndexOutOfBoundsException.class)
	public void testStringSplitConstructorElementAccess() {
		testR = new Rule(",", "");
		testR.lhs.get(0);
	}

}