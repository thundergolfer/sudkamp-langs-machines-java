package grammars;

import java.util.ArrayList;
import java.util.List;

public class CNFGrammar extends ContextFreeGrammar {
	
	public CNFGrammar() {
		type = 4; 
		rules = new ArrayList<Rule>();
	}
	
	/**
	 * Any ContextFreeGrammar can be transformed into an 
	 * equivelant grammar in CNF. This constructor takes a 
	 * Context-free grammar, and converts the grammar into a new
	 * set of CNF rules.
	 * 
	 * @param grammar
	 */
	public CNFGrammar( ContextFreeGrammar grammar ) {
		// TODO
	}
	
	public CNFGrammar( CNFGrammar grammar ) {
		type = 4;
		rules = grammar.rules;
	}
	
	/**
	 * Add a ruleList as the grammar's rule list if all rules in it pass
	 * both the restrictions of the Context-free grammar, and all rules 
	 * or in Chomsky Normal Form
	 */
	public boolean addRules( ArrayList<Rule> ruleList ) {
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
	 * both the restrictions of the Context-free grammar, and the rule is
	 * in Chomsky Normal Form.
	 */
	public boolean addRule( Rule r ) {
		if( !validRule(r) ) {
			return false;
		}
		rules.add(r);
		return true;
	}
	
	/**
	 * For a grammar rule to be valid in a context-free grammar, 
	 * all the restrictions of the context-free grammar must hold,
	 * and the rule must be in one of three forms.
	 * 
	 * 1. A -> BC,
	 * 2. A -> a,
	 * 3. S -> null, where B,C are non-start variables (V - {S}), 
	 * and a is a terminal.
	 * 
	 * @param r,  a rule
	 * @return true, if rule is in CNF. false, otherwise
	 */
	public boolean validRule( Rule r ){
		if( !super.validRule(r) ){
			return false;
		}
		// 3. rhs is null
		if( r.rhs == null || r.rhs.size() == 0) { return true; }
		// 2. rhs is a terminal (a)
		else if( r.rhs.size() == 1 && Grammar.isTerminal(r.rhs.get(0))) {
			return true;
		}
		// 1. rhs is 2 variables (BC)
		else if( r.rhs.size() == 2 && Grammar.isVariable(r.rhs.get(0))
				  && Grammar.isVariable(r.rhs.get(1))) { 
			return true;
		}
		// rule is not in one of the 3 CNF forms
		return false;
	}
	
	/**
	 * Convert a context-free rule list to an equivelant rule list which is 
	 * in CNF. 
	 * 
	 * Note: It is assumed there are:
	 * - No null-rules other than S (start symbol) -> null
	 * - no chain rules
	 * - no useless symbols **TODO: build method to remove useless symbols
	 * - a non-recursive start symbol
	 * @param cfRules
	 * @return
	 */
	private List<Rule> convertCFToCNF( ContextFreeGrammar g ) {
		
		ContextFreeGrammar cfg = new ContextFreeGrammar(g);
		cfg.removeRecursiveStartSymbol();
		cfg.removeNullRules();
		cfg.removeChainRules();
		cfg.removeUselessSymbols();
		
		// grammar now prepared for CNF conversion
		
		// convert grammar to CNF
		// TODO
		
		return null;
	}
	
} // end of CNFGrammar()
