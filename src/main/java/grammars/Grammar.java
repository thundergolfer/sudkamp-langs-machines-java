package grammars;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents the most general grammatical formalism,
 * the Unrestricted (or Recrusively Enumerable) Grammar.
 * All other grammars can derive from this grammar, imposing extra
 * restrictions.
 * @author Jonathon
 *
 */
public class Grammar {

	// types of grammars
	public static final int UNRESTRICTED = 0;
	public static final int CONTEXT_SENSITIVE = 1;
	public static final int	CONTEXT_FREE = 2;
	public static final int REGULAR = 3;
	public static final int CNFGRAMMAR = 4;
	public static final int PROB_CONTEXT_FREE = 5;
	
	public ArrayList<Rule> rules;
	public ArrayList<String> vars;
	public ArrayList<String> terminals;
	public int type; 
	
	// default constructor. has no rules
	public Grammar() {
		type = 0;
		rules = new ArrayList<Rule>();
		vars =  new ArrayList<String>();
		terminals = new ArrayList<String>();
	}
	
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
	
	public boolean addRule( Rule rule ) {
		if( validRule(rule)) {
			rules.add(rule);
			updateVarsAndTerminals( rule );
			return true;
		} else { return false; }
	}
	
	/**
	 * Test validity of the LHS and RHS of grammar rule.
	 * In unrestricted grammar, the only invalid rule type is
	 * a rule with a null LHS.
	 * @param r ( a rule )
	 * @return true, if rule has valid form. false, otherwise
	 */
	public boolean validRule( Rule r ) {
		if( r.lhs != null && r.lhs.size() > 0 ) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/** 
	 * Whenever a new rule is added to the grammar, we want to 
	 * update the list of variables and terminals with any new grammar symbols
	 */
	public void updateVarsAndTerminals() {
		if( rules == null ) {
			vars =  new ArrayList<String>();
			terminals = new ArrayList<String>();
			return;
		}
		for( int i=0; i < rules.size(); i++ ) {
			Rule r = rules.get(i);
			// update the variables and terminals for this rule
			updateVarsAndTerminals(r);
		}
	}
	
	/**
	 * Update variable and terminal lists with a single rule's symbols,
	 * if there a new symbols
	 * @param r
	 */
	public void updateVarsAndTerminals( Rule r ) {
		// check lhs for new terminals or variables
		for( int j=0; j < r.lhs.size(); j++ ) {
			if( isVariable(r.lhs.get(j)) && !vars.contains(r.lhs.get(j))) {
				vars.add(r.lhs.get(j));
			}
			else if( isTerminal(r.lhs.get(j)) && !terminals.contains(r.lhs.get(j))) {
				terminals.add(r.lhs.get(j));
			}
		}
		// for rhs we must check that this isn't a null-rule
		if ( r.rhs != null ) {
			// check rhs for new terminals or variables
			for( int j=0; j < r.rhs.size(); j++ ) {
				if( isVariable(r.rhs.get(j)) && !vars.contains(r.rhs.get(j))) {
					vars.add(r.rhs.get(j));
				}
				else if( isTerminal(r.rhs.get(j)) && !terminals.contains(r.rhs.get(j))) {
					terminals.add(r.rhs.get(j));
				}
			}
		}
		// mains sorted lists
		Collections.sort(vars);
		Collections.sort(terminals);
	}
	
	
	/**
	 * Check if we have a variable, as they are uppercase strings.
	 * @param s
	 * @return
	 */
	public static boolean isVariable(String s) {
		for (int i=0; i < s.length(); i++)
		{
			if (Character.isAlphabetic(s.charAt(i)) && // want to allow A`
				!Character.isUpperCase(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	/** 
	 * Check if we have a terminal, as they are lowercase strings
	 * @param s
	 * @return true, if string must be a terminal. false, otherwise
	 */
	public static boolean isTerminal(String s) {
		for (int i=0; i < s.length(); i++ ) {
			
			if( !Character.isLowerCase(s.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isTerminalString( ArrayList<String> str ) {
		for( int i=0; i < str.size(); i++ ) {
			if( Grammar.isVariable(str.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Is lhs -> rhs a rule in the grammar?
	 * @param lhs
	 * @param rhs
	 * @return true, if lhs->rhs is a rule. false, otherwise
	 */
	public boolean leftDirectlyDerivesRight( ArrayList<String> lhs , ArrayList<String> rhs ) {
		for( int i=0; i < this.rules.size(); i++ ) {
			Rule r = this.rules.get(i);
			if( r.lhs.size() == lhs.size() && r.lhs.containsAll(lhs) && lhs.containsAll(r.lhs)) {
				if( r.rhs.size() == rhs.size() && r.rhs.containsAll(rhs) && rhs.containsAll(rhs)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine if this LHS combo of symbols has a rule in the grammar
	 * that derives a terminal string. 
	 * @param lhs
	 * @return
	 */
	public boolean lhsDerivesTerminalString( ArrayList<String> lhs ) {
		for( int i=0; i < this.rules.size(); i++ ) {
			if( this.rules.get(i).equals(lhs) ) {
				Rule r = this.rules.get(i);
				if( r.terminalStringRule() ) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Take the rules of the grammar and print them all
	 * one line per rule.
	 */
	public void printRules() {
		for( int i=0; i < rules.size(); i++ ) {
			Rule r = rules.get(i);
			r.printRule();
		}
	}
	
	
}
