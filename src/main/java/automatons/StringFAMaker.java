package automatons;

import java.util.List;
import java.util.Set;

import utility.InputUtil;

public class StringFAMaker extends FAMaker<String>{

	final int NUM_DFAS = 2;
	final int NUM_NFAS = 2; 
	
	public DFA<String> buildDFA() {
		int whichDFA = (int)(Math.random() * ((NUM_DFAS) + 1));
		return buildDFA(whichDFA);
	}
	
	public DFA<String> buildDFA( int choice ) {
		return null;
	}
	
	public NFA<String> buildNFA() {
		int whichDFA = (int)(Math.random() * ((NUM_DFAS) + 1));
		return buildNFA(whichDFA);
	}
	
	public NFA<String> buildNFA( int choice ) {
		return null;
	}
	
	public static DFA<String> buildDFAWithEquivStates() {
		int[] finalStates = {4,5};
		DFA<String> dfa = new DFA<String>( finalStates, 0,"a",1,	0,"b",2,
														1,"b",4,	1,"a",3,
														2,"a",3,	2,"b",5,
														3,"a",3,	3,"b",3,
														4,"a",4,	4,"b",4,
														5,"a",5,	5,"b",5);
		return dfa;
	}
	
	public static void main( String[] args) {
		String word = "abbbbbb";
		DFA<String> dfaWithEquiv = buildDFAWithEquivStates();
		List<String> input = InputUtil.wordToList(word);
		boolean result = dfaWithEquiv.run(input, true);
		if(result) { System.out.println(word + " is valid!"); }
		else { System.out.println(word + " is invalid. failed!"); }
		
		Set<State> equivStates = dfaWithEquiv.determineEquivStates();
		System.out.println(equivStates);
	}
}
