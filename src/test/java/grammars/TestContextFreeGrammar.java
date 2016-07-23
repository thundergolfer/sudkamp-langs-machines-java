package grammars;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import data.grammars.ContextFreeExamples;

public class TestContextFreeGrammar {

	ContextFreeGrammar chainRuleG;
	
	@Before
	public void setUpGrammars() {
		chainRuleG = ContextFreeExamples.buildCFGWithChainRules();
	}
	
	@Test
	public void testChainA() {
		Set<String> chainVars = chainRuleG.chain("A");
		assertTrue( chainVars.containsAll(Arrays.asList("B","A","C")));
		chainVars = chainRuleG.chain("C");
		assertTrue( chainVars.contains("C"));
		assertFalse( chainVars.contains("A"));
		
	}
	
	@Test
	public void testRemoveChainRules() {
		chainRuleG.removeChainRules();
		System.out.println(chainRuleG);
		assertEquals( chainRuleG.rules.size(), 6);
		System.out.println(chainRuleG.vars);
		assertFalse( chainRuleG.vars.contains("C"));
	}

}
