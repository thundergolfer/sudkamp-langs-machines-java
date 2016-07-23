package data.grammars;

import java.util.ArrayList;
import java.util.Arrays;

import grammars.Grammar;
import grammars.Rule;
import grammars.UnrestrictedGrammar;

public class UnrestrictedGrammarExamples {
	private static int numGrammars = 2;
	
	public Grammar exampleContextFree() {
		int randSelection = 0;
		return exampleGrammar( randSelection );
	}
	
	public static Grammar exampleGrammar( int selection ) {
		
		Grammar g = null;
		switch( selection ) {	
		case 0:
			g = buildGrammar1();
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
	
	/** A simple unrestricted grammar with rules
	 * 
	 * S  -> A
	 * A  -> aA | aa | Bb
	 * B  -> bbb | null
	 * Bb -> C
	 * C  -> cc | ca 
	 * 
	 * @return a context free grammar for the language
	 */
	private static UnrestrictedGrammar buildGrammar1() {
		UnrestrictedGrammar g = new UnrestrictedGrammar();
		ArrayList<Rule> rules = new ArrayList<Rule>();
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("S")),
				   new ArrayList<String>(Arrays.asList("A"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("A")),
				   new ArrayList<String>(Arrays.asList("a","A"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("A")),
				   new ArrayList<String>(Arrays.asList("a","a"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("A")),
				   new ArrayList<String>(Arrays.asList("B","b"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("B")),
				   new ArrayList<String>(Arrays.asList("b","b","b"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("B")),null));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("B","b")),
				   new ArrayList<String>(Arrays.asList("C"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("C")),
				   new ArrayList<String>(Arrays.asList("c","c"))));
		rules.add( new Rule( new ArrayList<String>(Arrays.asList("C")),
				   new ArrayList<String>(Arrays.asList("c","a"))));
		g.addRules(rules);
		return g;
	}
	
	private Grammar buildGrammar2() {
		return null;
	}
	
	private Grammar buildGrammar3() {
		return null;
	}
	
	private Grammar buildGrammar4() {
		return null;
	}
}
