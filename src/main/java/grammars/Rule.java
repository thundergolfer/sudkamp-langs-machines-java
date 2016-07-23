package grammars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A derivation rule that is contained within a grammar.
 * @author Jonathon (thundergolfer)
 *
 */
public class Rule {
	
	public final ArrayList<String> lhs; // Left hand side of derivation rule
	public final ArrayList<String> rhs; // Right hand side of derivation rule
	
	// Basic constructor
	public Rule( List<String> lhs, List<String> rhs ) {
		this.lhs = (rhs == null) ? new ArrayList<String>() : new ArrayList<String>(lhs);
		this.rhs = (rhs == null) ? new ArrayList<String>() : new ArrayList<String>(rhs);
	}
	
	// null RHS rule constructor
	public Rule( ArrayList<String> lhs ) {
		this.lhs = lhs;
		this.rhs = new ArrayList<String>();
	}
	
	// string split constructor
	public Rule( String lhs, String rhs ) {
		if( lhs == null || lhs.equals("")) {
			this.lhs = new ArrayList<String>();
		} else {
			this.lhs = new ArrayList<String>(Arrays.asList(lhs.split("\\s*,\\s*")));
		}
		if( rhs == null || rhs.equals("")) {
			this.rhs = new ArrayList<String>();
		} else {
			this.rhs = new ArrayList<String>(Arrays.asList(rhs.split("\\s*,\\s*")));
		}
	}
	
	public List<String> getVars( List<String> side ) {
		ArrayList<String> vars = new ArrayList<String>();
		for( int i=0; i < side.size(); i++ ) {
			// add symbol if it is a variable and does not already
			// appear in list
			if( Grammar.isVariable(vars.get(i)) &&
						!vars.contains(vars.get(i))) {
				vars.add(vars.get(i));
			}
		}
		return vars;
	}
	
	public List<String> getLhsVars() {
		return this.getVars( this.lhs );
	}
	
	public List<String> getRhsVars() {
		return this.getVars( this.rhs );
	}
	
	public boolean terminalStringRule() {
		if( Grammar.isTerminalString(this.rhs)) {
			return true;
		} else { return false; }
	}
	
	public void printRule() {
		System.out.println(this.toString());
	}
	
	public String toString() {
		String lhsStr = "";
		String rhsStr = "";
		// build LHS and RHS strings for printing
		for( int j=0; j < this.lhs.size(); j++ ) {
			lhsStr += this.lhs.get(j);
		}
		for( int j=0; j < this.rhs.size(); j++ ) {
			rhsStr += this.rhs.get(j);
		}
		return lhsStr + " -> " + rhsStr ;
	}
}
