package grammars;

import java.util.ArrayList;

public class RegularGrammar extends ContextFreeGrammar {
	// default constructor
	public RegularGrammar() {
		type = 3; 
		rules = null;
	}
	
	/**
	 * Add a ruleList as the grammar's rule list if all rules in it pass both the 
	 * restrictions of the parent grammars (unrestricted,context-sens, and context-free)
	 * and this grammar's restrictions.
	 */
	public boolean addRules( ArrayList<Rule> ruleList ) {
		for( int i=0; i < ruleList.size(); i++ ) {
			if( !super.validRule(ruleList.get(i)) ) {
				return false;
			}
			if( !validRule(ruleList.get(i)) ) {
				return false;
			}
		}
		this.rules = ruleList;
		return true;
	}
	
	/**
	 * Add a rule to the grammar's rule list if it passe both the
	 * restrictions of the parent grammars (unrestricted, context-sens, and context-free)
	 * and this grammar's restrictions.
	 */
	public boolean addRule( Rule r ) {
		if( !super.validRule(r) ) {
			return false;
		}
		else if( !validRule(r) ) {
			return false;
		}
		rules.add(r);
		return true;
	}
	
	/**
	 * For a grammar rule to be valid in a context-free grammar, 
	 * all the restrictions of the parent grammars must hold, and the restriction
	 * of the regular grammar must hold. A rule in a regular grammar is restricted to
	 * rules of the form: 
	 * 
	 * 1. A -> a,
	 * 2. A -> aB, or
	 * 3. A -> null, where "a" is a terminal in the grammar and "B" is a variable of the grammar
	 * 
	 */
	public boolean validRule( Rule r ){
		if( !super.validRule(r) ){
			// rule has failed restrictions of parent grammars
			return false;
		}
		// 3. rhs is null
		if( r.rhs == null ) {
			return true;
		}
		// 2. rhs is a terminal followed by a variable
		else if( r.rhs.size() == 2 && isTerminal(r.rhs.get(0)) && 
								 isVariable(r.rhs.get(1))) {
			return true;
		}
		// 1. rhs is a single terminal
		else if( r.rhs.size() == 1 && isTerminal(r.rhs.get(0))) {
			return true;
		}
		// rule is not one of the 3 valid forms
		return false;
	 } // end of validRule()
	
} // end of RegularGrammar()
