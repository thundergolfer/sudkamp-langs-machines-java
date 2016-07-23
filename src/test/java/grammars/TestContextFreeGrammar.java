package grammars;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import data.grammars.ContextFreeExamples;

public class TestContextFreeGrammar {

	ContextFreeGrammar chainRuleG;
	ContextFreeGrammar nullableVarG;
	
	@Before
	public void setUpGrammars() {
		chainRuleG = ContextFreeExamples.buildCFGWithChainRules();
		nullableVarG = ContextFreeExamples.buildCFGWithNullableVars();
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
		assertEquals( chainRuleG.rules.size(), 6);
		assertFalse( chainRuleG.vars.contains("C"));
	}
	
	@Test
	public void testConstructSetOfNullableVars() {
		Set<String> nullables = nullableVarG.nullableVarsSet();
		assertTrue( nullables.containsAll(Arrays.asList("A","S","C")));
		assertFalse( nullables.contains("B"));
	}

}
