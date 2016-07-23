package grammars;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import data.grammars.ContextFreeExamples;

public class TestContextFreeGrammar {

	ContextFreeGrammar chainRuleG;
	ContextFreeGrammar nullableVarG;
	ContextFreeGrammar recurStartSymbolG;
	ContextFreeGrammar varsThatDeriveTerminalStrings;
	
	@Before
	public void setUpGrammars() {
		chainRuleG = ContextFreeExamples.buildCFGWithChainRules();
		nullableVarG = ContextFreeExamples.buildCFGWithNullableVars();
		recurStartSymbolG = ContextFreeExamples.buildCFGWithRecursiveStartSymbol();
		varsThatDeriveTerminalStrings = ContextFreeExamples.buildCFGWithVarsThatDontDeriveTerminalStrings();
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
	
	@Test
	public void testRemoveRecursiveStartSymbol() {
		recurStartSymbolG.removeRecursiveStartSymbol();
		assertTrue( recurStartSymbolG.vars.contains(Grammar.getAltStartSymbol()));
		for( Rule r: recurStartSymbolG.rules ) {
			if( r.lhs.get(0).equals(Grammar.getAltStartSymbol())) {
				assertTrue( r.rhs.get(0).equals(Grammar.getStartSymbol()));
			}
		}
	}
	
	@Test
	public void testRemoveRecursiveStartSymbolWhenNoRecursiveStartSymbolExists() {
		chainRuleG.removeRecursiveStartSymbol();
		assertFalse( chainRuleG.vars.contains(Grammar.getAltStartSymbol()));
	}
	
	@Test
	public void testConstructSetOfVarsThatDeriveTerminalString() {
		Set<String> vars = varsThatDeriveTerminalStrings.constructSetOfVarsThatDeriveTerminalStrings();
		assertTrue( vars.containsAll(Arrays.asList("B","F","A","S","E")));
		assertFalse( vars.contains("C")); assertFalse( vars.contains("D"));
	}

}
