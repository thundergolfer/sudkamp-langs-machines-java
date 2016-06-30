package grammars;

import java.util.ArrayList;

public class ContextSensitiveGrammar extends Grammar {

	// default constructor
	public ContextSensitiveGrammar() {
		type = 1; 
		rules = null;
	}
	
	/**
	 * Add a ruleList as the grammar's rule list if all rules in it pass
	 * both the restrictions of the parent grammar (unrestricted) and
	 * this grammar's restrictions.
	 */
	public boolean addRules( ArrayList<Rule> ruleList ) {
		for( int i=0; i < ruleList.size(); i++ ) {
			if( !super.validRule(ruleList.get(i)) || !validRule(ruleList.get(i)) ) {
				return false;
			}
		}
		this.rules = ruleList;
		updateVarsAndTerminals();
		return true;
	}
	
	/**
	 * Add a rule to the grammar's rule list if it passes
	 * both the restrictions of the parent grammar (unrestricted) and
	 * this grammar's restrictions.
	 */
	public boolean addRule( Rule r ) {
		if( !super.validRule(r) || !validRule(r) ) {
			return false;
		}
		rules.add(r);
		updateVarsAndTerminals();
		return true;
	}
	
	/**
	 * For a grammar rule to be valid in a context sensitive grammar, 
	 * all the restrictions of the parent grammar (unrestricted) must hold, 
	 * and the number of RHS symbols must be equal or greater than the number
	 * of LHS symbols.
	 */
	public boolean validRule( Rule r ){
		if( !super.validRule(r) ){
			return false;
		}
		// len(rhs) >= len(lhs) must hold in context-sensitive.
		else if( r.rhs.size() < r.lhs.size() ) {
			return false;
		}
		else if( r.rhs == null ) {
			return false;
		}

		return true;
	}
} // end of ContextSensitiveGrammar()
