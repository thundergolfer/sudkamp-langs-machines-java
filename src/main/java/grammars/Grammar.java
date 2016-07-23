package grammars;

import java.util.List;
import java.util.Set;

public interface Grammar {
	
	public Set<String> getVars();
	
	public Set<String> getTerminals();
	
	public List<Rule> getRules();

	public boolean addRules( List<Rule> ruleList );
	
	public boolean addRule( Rule r );
	
	public boolean validRule( Rule r );
	
	public void updateVarsAndTerminals();
	
	public void updateVarsAndTerminals( Rule r );
	
	/**
	 * Check if we have a variable, as they are uppercase strings.
	 * @param s
	 * @return
	 */
	public static boolean isVariable( String s ) {
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
	
	public static boolean isTerminalString( List<String> ls ) {
		for( int i=0; i < ls.size(); i++ ) {
			if( Grammar.isVariable(ls.get(i))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean leftDirectlyDerivesRight( List<String> lhs, List<String> rhs );
	
	public boolean lhsDerivesTerminalString( List<String> lhs );
	
	public void printRules();
	
	public void printVars(); 
	
	public void printTerminals();
	
	public static String getAltStartSymbol() {
		return "S`";
	}
	
	public static String getStartSymbol() {
		return "S";
	}
	
	
}
