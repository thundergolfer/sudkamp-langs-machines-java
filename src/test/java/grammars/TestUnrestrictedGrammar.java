package grammars;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import data.grammars.UnrestrictedGrammarExamples;


public class TestUnrestrictedGrammar {
	
	Grammar g; Grammar g2;
	
	@Before
	public void setup() {
		g = new UnrestrictedGrammar(); // reset grammar before each test
		g2 = UnrestrictedGrammarExamples.exampleGrammar(0);
	}

	@Test
	public void testValidRule() {
		Rule invalidR = new Rule( null, new ArrayList<String>(Arrays.asList("W","Z")));
		Rule validR   = new Rule( new ArrayList<String>(Arrays.asList("W")), 
								  new ArrayList<String>(Arrays.asList("a","s")));
		Rule validR2  = new Rule( new ArrayList<String>(Arrays.asList("W")), null );
		assertFalse( g.validRule(invalidR));
		assertTrue(  g.validRule(validR));
		assertTrue(  g.validRule(validR2));
		
	}
	
	/**
	 * Grammar should not allow a rule of the form 
	 * null -> X, where X is a combo of variables and terminals
	 */
	@Test
	public void testRejectNullLhs() {
		Rule r = new Rule( new ArrayList<String>() , new ArrayList<String>() ); // test completely null rule
		// test only null lhs
		Rule r2 = new Rule( null, new ArrayList<String>(Arrays.asList("W","Z")));
		assertFalse( g.addRule(r));
		assertFalse( g.addRule(r2));
	}
	
	/**
	 * Grammar (unrestricted) should accept all the rules in the test,
	 * as they have non-null left hand sides
	 */
	@Test
	public void testAcceptValidRules() {
		Rule unrestrictedRule = new Rule( new ArrayList<String>(Arrays.asList("A","a","A","B")),
										  new ArrayList<String>(Arrays.asList("b","b","A","C")));
		Rule contextSensRule =  new Rule( new ArrayList<String>(Arrays.asList("A","a","A")),
				  						  new ArrayList<String>(Arrays.asList("b","b","A","C")));
		Rule contextFreeRule = new Rule( new ArrayList<String>(Arrays.asList("A")),
				  						  new ArrayList<String>(Arrays.asList("b","b","A","C")));
		Rule regularRule     = new Rule( new ArrayList<String>(Arrays.asList("A")),
				  						 new ArrayList<String>(Arrays.asList("b","C")));
		Rule nullRHSRule     = new Rule( new ArrayList<String>(Arrays.asList("A","B")), null );
		// try adding these rules in turn
		assertTrue( g.addRule( unrestrictedRule ));
		assertTrue( g.addRule( contextSensRule  ));
		assertTrue( g.addRule( contextFreeRule  ));
		assertTrue( g.addRule( regularRule      ));
		assertTrue( g.addRule( nullRHSRule      ));
	}
	
	/**
	 * Test that Grammar class correctly updates its 
	 * list of variables and terminals when a new rule is added
	 */
	@Test 
	public void testUpdateVarsAndTerminals() {
		// add a rule that has variables and terminals not 
		// already in the grammar
		g2.addRule( new Rule( new ArrayList<String>(Arrays.asList("Z")),
					 		  new ArrayList<String>(Arrays.asList("z","Z"))));
		assertTrue( g2.getTerminals().contains("z") && !g2.getTerminals().contains("Z"));
		assertTrue( g2.getVars().contains("Z") && !g2.getVars().contains("z"));
	}
	
	@Test
	public void testIsVariable() {
		assertTrue(  Grammar.isVariable("S"));
		assertTrue(  Grammar.isVariable("SSSSS"));
		assertFalse( Grammar.isVariable("s"));
		assertFalse( Grammar.isVariable("tttt"));
	}
	
	@Test
	public void testIsTerminal() {
		assertTrue(  Grammar.isTerminal("x"));
		assertTrue(  Grammar.isTerminal("xxxxx"));
		assertFalse( Grammar.isTerminal("X"));
		assertFalse( Grammar.isTerminal("XXXXXX"));
	}
}
