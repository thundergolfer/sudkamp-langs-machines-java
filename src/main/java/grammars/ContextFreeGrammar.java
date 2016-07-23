package grammars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ContextFreeGrammar extends ContextSensitiveGrammar {
	
	// default constructor
	public ContextFreeGrammar() {
		type = 2; 
		rules = null;
	}
	
	public ContextFreeGrammar( ContextFreeGrammar g ) {
		this.rules = new ArrayList<Rule>(g.rules);
		this.vars  = new LinkedHashSet<String>(g.vars);
		this.terminals = new LinkedHashSet<String>(g.terminals);		
	}
	
	/**
	 * Add a ruleList as the grammar's rule list if all rules in it pass
	 * both the restrictions of the parent grammars (unrestricted and context-sens)
	 * and this grammar's restrictions.
	 */
	public boolean addRules( List<Rule> ruleList ) {
		for( int i=0; i < ruleList.size(); i++ ) {
			if( !validRule(ruleList.get(i)) ) {
				return false;
			}
		}
		this.rules = ruleList;
		updateVarsAndTerminals();
		return true;
	}
	
	/**
	 * Add a rule to the grammar's rule list if it passes
	 * both the restrictions of the parent grammars (unrestricted and context-sens)
	 * and this grammar's restrictions.
	 */
	public boolean addRule( Rule r ) {
		if( !validRule(r) ) {
			return false;
		}
		rules.add(r);
		updateVarsAndTerminals(r);
		return true;
	}
	
	/**
	 * For a grammar rule to be valid in a context-free grammar, 
	 * all the restrictions of the parent grammars must hold, and the restriction
	 * of the context-free grammar must hold. The restriction is that the lhs must 
	 * consist of a single non-terminal (variable). There are no restrictions on the rhs
	 * 
	 */
	public boolean validRule( Rule r ){
		if( !super.validRule(r) ){
			return false;
		}
		// lhs must be a single non-terminal
		if( r.lhs.size() != 1 || !Grammar.isVariable(r.lhs.get(0)))
		{
			return false;
		}
	
		return true;
	}
	
	public boolean leftDerivesRight( List<String> lhs, List<String> rhs ) {
		
		// for each rule in the grammar 
		for( int i=0; i < rules.size(); i++ ) {
			Rule r = rules.get(i);
			if( r.lhs.equals(lhs) && r.rhs.equals(rhs)) {
				// matching rule found. left does derive the right in this grammar
				return true;
			}
		}
		// no match found
		return false;
	}
	
	// page 116
	public List<Rule> removeUselessSymbols( ) {
		
		// TODO
		return null;
	}
	
	// page 105
	public List<Rule> removeRecursiveStartSymbol( ) {
		
		boolean hasRecurStartRule = false;
		for( int i=0; i < this.rules.size(); i++ ) {
			// found a start rule
			if( rules.get(i).lhs.get(0).compareTo("S") == 0) {
				// is the start rule recursive
				Rule startRule = rules.get(i);
				if( startRule.rhs.contains("S")) {
					// found a recursive start rule
					hasRecurStartRule = true;
					break;
				}
			}
		}
		if( hasRecurStartRule ) {
			// create a new variable S' and add rule S' -> S 
			// this rule removes starting recursion from the grammar
			// TODO: MAKE SURE S' Variable doesn't already exist in GRAMMAR
			List<String> startLHS = new ArrayList<String>();
			List<String> startRHS = new ArrayList<String>();
			// 
			startLHS.add("S'");
			startRHS.add("S");
			Rule newStartRule = new Rule( startLHS, startRHS );
			rules.add(0, newStartRule);
			
		}
		return rules;
	}
	
	// page 106
	// NOTE:  this method assumes the start symbol S is non-recursive
	/**
	 * 
	 * @param this.rules
	 * @return
	 */
	public List<Rule> removeNullRules() {
		// TODO
		List<String> nullableVars = nullableVarsSet(); // set of nullable vars for the grammar
		
		// if a rule's RHS contains nullable vars, we need to generate all possible combinations of that contained
		// variable set, each combination needs to be added as a rule to the grammar.
		// eg:
		// 
		// If B is a nullable var then, a contracting grammar with the rule
		// 
		// A -> BABa requires 4 rules for the noncontracting grammar equivelant
		// 
		// A -> BABa
		// A -> ABa
		// A -> BAa
		// A -> Aa
		
		// To remove null-rules from grammar
		// 1. determine set of nullable variables
		
		// 2. Add rules in which occurences of the nullable variables are ommitted
		
		// 3. delete the null-rules
		
		return null;
	}
	
	// page 108
	/**
	 * Algorithm for building the set of 'nullable' variables in a Context-Free
	 * grammar. If the set of 'nullable' variables is empty, the grammar is said to 
	 * be "noncontracting".
	 * @param this.rules
	 * @return
	 */
	public List<String> nullableVarsSet() {
		
		ArrayList<String> varSet = new ArrayList<String>();
		// set of null variables, initially empty
		ArrayList<String> nullableVars = new ArrayList<String>();
		ArrayList<String> prevVars;
		
		// build set of all vars in rule list
		for( int i=0 ; i < this.rules.size(); i++ ) {
			if( !varSet.contains(this.rules.get(i).lhs.get(0))) {
				varSet.add(this.rules.get(i).lhs.get(0));
			}
		}
		
		// add all variables in a rule of the form A -> null in cfRule list
		for( int i=0; i < this.rules.size(); i++ ) {
			Rule r = this.rules.get(i);
			if( r.rhs == null ) {
				// check set does not already contain variable
				if( !nullableVars.contains(r.lhs.get(0))) {
					nullableVars.add(r.lhs.get(0));
				}
			}
		}
		do {
			prevVars = new ArrayList<String>(nullableVars);
			
			// for each variable in the grammar
			for( int i=0; i < varSet.size(); i++ ) {
				// add variable M if there is a rule M -> w, where
				// w consists entirely of variables already present in prevVars
				for( int j=0; j < this.rules.size(); j++ ) {
					if( this.rules.get(j).lhs.contains(varSet.get(i))) {
						// found a rule for variable M
						// now check RHS (w)
						if ( prevVars.containsAll(this.rules.get(j).rhs)) {
							// add var if not already in nullableVars set
							if( !nullableVars.contains(this.rules.get(j).lhs.get(0))) {
								nullableVars.add(this.rules.get(j).lhs.get(0));
							}
						}
					}
				}
			}
		} while( !nullableVars.equals(prevVars));
		
		return nullableVars;
	}
	
	public List<Rule> getTerminalRulesFor( String var ) {
		List<Rule> varsRules = new ArrayList<Rule>();
		for( Rule r : this.rules ) {
			if( r.lhs.get(0).equals(var) && r.terminalStringRule() ) {
				varsRules.add(r);
			}
		}
		return varsRules;
	}
	 
	// page 11
	// NOTE: This method expects an essentially noncontracting context-free grammar
	public boolean removeChainRules() {
		//TODO
		List<Rule> newRules = new ArrayList<Rule>();
		// for each rule in this.rules
		for( String A : this.vars ) {
			// for a rule A -> w to be in the chain-rule free grammar, there must exist
			// a variable B, and a string w that satisfy
			//
			// 1. B is an element of chain(lhsVar)
			// 2. B -> w is a rule in the grammar
			// 3. w is not a variable
			// Find 1. 
			Set<String> chainVar = chain(A);
			for( String B: chainVar ) { // Go through all B in CHAIN
				// get all  B -> w rules, where w is not a variable 
				List<Rule> newARules = new ArrayList<Rule>();
				for( Rule r: this.rules ) {
					if( r.lhs.get(0).equals(B) && (r.rhs.size() > 1 || r.terminalStringRule() )) {
						newARules.add(r);
					}
				}
				for( Rule r: newARules ) {
					List<String> w = r.rhs;
					newRules.add( new Rule( new ArrayList<String>(Arrays.asList(A)), w)); // add A -> w
				}
			}
		} 
		this.rules = newRules;
		updateVarsAndTerminals();
		return true;
	}
	
	// page 114 
	public Set<String> chain( String A ) { // CHAIN(A)
		Set<String> chainA = new LinkedHashSet<String>();
		Set<String> prev  = new LinkedHashSet<String>();
		Set<String> newVars = new LinkedHashSet<String>();
		
		if( !this.vars.contains(A) ) { // Check A is in grammar
			System.out.println(A + " not a variable in grammar.");
			return chainA;
		}
		
		chainA.add(A);
		// prev = empty
		do {
			newVars = new LinkedHashSet<String>( chainA ); newVars.removeAll( prev ); // NEW := CHAIN(A) - PREV
			prev = new LinkedHashSet<String>( chainA ); // PREV := CHAIN(A)
			// for each variable B in newVars ( NEW ) 
			for( String var : newVars ) {
				// for each rule B -> C union {C} with chainA
				for( Rule r : this.rules ) {
					if( r.lhs.get(0).equals(var)) {
						if( r.rhs.size() == 1 && Grammar.isVariable(r.rhs.get(0))) {
								chainA.add(r.rhs.get(0)); // CHAIN(A) := CHAIN(A) U {C}
						}
					}
				}
			}
		} while( !chainA.equals(prev) );
		
		return chainA;
	}


} // end of ContextFreeGrammar()
