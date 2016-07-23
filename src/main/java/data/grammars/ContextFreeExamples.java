package data.grammars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grammars.ContextFreeGrammar;
import grammars.Rule;

public class ContextFreeExamples {
	
	private static int numGrammars = 2;
	
	public ContextFreeGrammar exampleContextFree() {
		int randSelection = 0;
		return exampleContextFree( randSelection );
	}
	
	public static ContextFreeGrammar exampleContextFree( int selection ) {
		
		ContextFreeGrammar g = new ContextFreeGrammar();
		switch( selection ) {	
		case 0:
			g = buildCFGrammar1();
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		default:
			g = null;
			break;
		}
		
		return g;
	}
	
	/** A very simple context free grammar. Produces the language
	 * {aa,aaa,aaaa,aaaaa,aaaaaa,aaaaaaa,...}
	 * @return a context free grammar for the language
	 */
	private static ContextFreeGrammar buildCFGrammar1() {
		ContextFreeGrammar g = new ContextFreeGrammar();
		List<Rule> rules = new ArrayList<Rule>();
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("S")),
				   new ArrayList<String>(Arrays.asList("A"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("A")),
				   new ArrayList<String>(Arrays.asList("a","A"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("A")),
				   new ArrayList<String>(Arrays.asList("a","a"))));
		g.addRules(rules);
		return g;
	}
	
	public static ContextFreeGrammar  buildCFGWithChainRules() {
		ContextFreeGrammar g = new ContextFreeGrammar();
		List<Rule> rules = new ArrayList<Rule>();
		rules.add( new Rule("A", "a")); rules.add( new Rule("A","a,A"));
		rules.add( new Rule("A", "B")); rules.add( new Rule("B","b,B"));
		rules.add( new Rule("B", "b")); rules.add( new Rule("B", "C"));
		g.addRules(rules);
		return g;
	}
	
	public static ContextFreeGrammar buildCFGWithNullableVars() {
		ContextFreeGrammar g = new ContextFreeGrammar();
		List<Rule> rules = new ArrayList<Rule>();
		rules.add( new Rule("S", "A,C,A")); rules.add( new Rule("A","a,A,a"));
		rules.add( new Rule("A", "B")); rules.add( new Rule("A","C"));
		rules.add( new Rule("B", "b,B")); rules.add( new Rule("B","b"));
		rules.add( new Rule("C", "c,C")); rules.add( new Rule("C", null));
		g.addRules(rules);
		return g;
	}
	
	private ContextFreeGrammar buildCFGrammar2() {
		return null;
	}
	
	private ContextFreeGrammar buildCFGrammar3() {
		return null;
	}
	
	private ContextFreeGrammar buildCFGrammar4() {
		return null;
	}
}
