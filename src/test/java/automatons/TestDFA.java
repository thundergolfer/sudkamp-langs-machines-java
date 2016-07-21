package automatons;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import utility.InputUtil;

public class TestDFA {
	
	DFA<String> dfaOne;

	@Before
	public void setUpGrammars() {
		dfaOne = StringFAMaker.buildDFAWithEquivStates();
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

	
	@Test
	public void testRun() {
		String word = "abbbbbb"; // this is known to be valid for the following dfa
		List<String> input = InputUtil.wordToList(word);
		assertTrue( dfaOne.run(input) );
	}
	
	@Test
	public void testDetermineEquivStates() {
		// get a DFA with some equivelant states
		
		// method should return those equiv. states
	}
	
	@Test
	public void testDetermineEquivStatesOnMinimalDFA() {
		// get a DFA with minimal states
		
		// method should return null set
	}
	
	@Test
	public void testInitS() {
		
	}
	
	@Test
	public void testDist() {
		
	}
	
	@Test 
	public void testGetArcWithSymbol() {
		State s = dfaOne.startState;
		FATransition<String> t = dfaOne.getArcWithSymbol("a", s);
		assertNotNull( t );
		assertEquals( t.symbol, "a");
	}

}
