package automatons;

public class FAMaker<T> {

	final int NUM_DFAS = 2;
	final int NUM_NFAS = 2; 
	
	public DFA<T> buildDFA() {
		int whichDFA = (int)(Math.random() * ((NUM_DFAS) + 1));
		return buildDFA(whichDFA);
	}
	
	public DFA<T> buildDFA( int choice ) {
		return null;
	}
	
	public NFA<T> buildNFA() {
		int whichDFA = (int)(Math.random() * ((NUM_DFAS) + 1));
		return buildNFA(whichDFA);
	}
	
	public NFA<T> buildNFA( int choice ) {
		return null;
	}
	
	public DFA<T> buildDFAWithEquivStates() {
		
		return null;
	}
}
