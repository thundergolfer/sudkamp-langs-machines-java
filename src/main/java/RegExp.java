import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import automatons.FiniteAutomaton;
import automatons.NFA;
import automatons.State;
import automatons.FATransition;

/**
 * Regular expressions are a way of defining Regular Sets. Regular Sets are defined recursively:
 * 
 * i)  Basis: empty, {null}, {a}, for every "a" that is in Alphabet, are regular sets over Alphabet
 * ii) Recursive Step: Let X and Y be regular sets over Alphabet. The sets:
 * 			X UNION Y
 * 			XY, and 
 * 			X*
 * 	   are regular sets over Alphabet
 * iii) Closure: X is a regular set over Alphabet on if it can be obtained from the basis elements by
 * 		a finite number of applications of the recursive step.
 * 
 * @author Jonathon
 */
public class RegExp {

	// We can't extend regex.Pattern as it is final, so I'll wrap it.
	Pattern pattern;
	
	public RegExp( String regex ) {
		compile( regex );
	}
	
	public boolean compile( String regex ) {
		boolean valid = true;
		// If needed, check the regex string for invalid format
		
		if( valid ) {
			try {
				pattern = Pattern.compile(regex);
				return true;
			} catch( PatternSyntaxException e ) {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Construction of a Regular Expression from a Finite Automaton
	 * 
	 * input: state diagram G of a finite automaton with one accepting state
	 * 
	 * Let q<sub>0</sub> be the start state and q<sub>t</sub> the accepting state of G.
	 * 1. repeat
	 * 		1.1 choose a node q<sub>i</sub> that is neither q<sub>0</sub> nor q<sub>t</sub>
	 * 		1.2 delete the node q<sub>i</sub> from G according to the following procedure:
	 * 			1.2.1 for every j,k not equal to i (this includes j = k) do
	 * 				i)   if w<sub>j,i</sub> /= null, w<sub>i,k</sub> /= null and w<sub>i,i</sub> = null, then
	 * 						add an arc from node j  to node k labeled w<sub>j,i</sub>w<sub>i,k</sub>
	 * 				ii)  if w<sub>j,i</sub> /= null, w<sub>i,k</sub> /= null and w<sub>i,i</sub> /= null then
	 * 						add an arc from node q<sub>j</sub> to node q<sub>k</sub> labeled
	 * 					    w<sub>j,i</sub>(w<sub>i,i</sub>)*w<sub>i,k</sub>
	 * 				iii) if nodes q<sub>j</sub> and q<sub>k</sub> have arcs labeled w<sub>1</sub>, w<sub>2</sub>,...,w<sub>s</sub>
	 * 					 connecting them then
	 * 						replace the arcs by a single arc labeled w<sub>1</sub>UNION w<sub>2</sub> UNION...UNION  w<sub>s</sub>
	 * 			
	 * 			1.2.2 remove the node q<sub>i</sub> and all arcs incident to it in G
	 * 	  until the only nodes in G are q<sub>0</sub> and q<sub>t</sub>
	 * 
	 * 2. determine the expression accepted by G
	 */
	public static RegExp constructFromFiniteAutomaton( NFA<String> finiteA ) {
		FiniteAutomaton<String> fa = finiteA;
		RegExp regex = null;
		Set<State> G = fa.getStates();
		State currState;
		// Finite Automaton must have only one accepting state;
		if( fa.getFinalStates().size() > 1 ) { return null; }
		
		State startState = fa.getStartState(); 
		Set<State> finalStates = fa.getFinalStates();
		State fState = fa.getRandomState(finalStates); // only one state to choose
		
		do {
			// 1.1 choose a non-start and non-final state
			Set<State> nonStart = fa.getNonStartStates();
			Set<State> nonFinal = fa.getNonFinalStates();
			State nonStartNonFinal = fa.getRandomState(fa.getIntersection(nonStart, nonFinal));
			currState = nonStartNonFinal;
			// delete this state from G according to following procedure:
			Set<State> otherStates = fa.getOtherStates(nonStartNonFinal);
			State[] others = otherStates.toArray(new State[otherStates.size()]);
			// for every j,k not equal to current state (including j=k)
			for( int j=0; j < others.length; j++ ) {
				for( int k=j; k < others.length; k++ ) {
					// i = currState, Arc is synonym for Transition
					List<FATransition<String>> jiArcs = fa.getArcsFrom(others[j], currState);
					List<FATransition<String>> jkArcs = fa.getArcsFrom(others[j], others[k]);
					List<FATransition<String>> ikArcs = fa.getArcsFrom(currState, others[k]);
					List<FATransition<String>> iiArcs = fa.getArcsFrom(currState, currState);
					FATransition<String> ji; FATransition<String> ik; FATransition<String> ii; FATransition<String> jk;
					if( jiArcs.size() > 0 && ikArcs.size() > 0 && iiArcs.size() == 0 ) {
						// add arc from j -> k labeled WjiWik  <<< how do you label it?
						ji = jiArcs.get(0); ik = ikArcs.get(0);
						fa.addTransition( new FATransition<String>(others[j], 
																 ji.getSymbol()+ik.getSymbol(),
																 others[k]));
					}
					if( jiArcs.size() > 0 && ikArcs.size() > 0 && iiArcs.size() > 0 ) {
						// add arc from j -> k labeled Wji(Wii)*Wik
						ji = jiArcs.get(0); ii = iiArcs.get(0); ik = ikArcs.get(0);
						fa.addTransition( new FATransition<String>(others[j], 
								 								 ji.getSymbol()+"("+ii.getSymbol()+")*"+ik.getSymbol(),
								 								 others[k]));
					}
					if( jkArcs.size() > 0) {
						// replace with single arc from j -> k labelled by a 
						// concatenation of all transition symbols
						String newSymbol = "";
						for( int l=0; l < jkArcs.size(); l++ ) {
							jk = jkArcs.get(l);
							newSymbol += jk.getSymbol();
							fa.removeTransition(jk);
						}
						fa.addTransition(new FATransition<String>(others[j], newSymbol, others[k]));
					}
				}
			}
			// remove current state and all arcs incident to it in G
			fa.removeAllTransitions(currState);
			fa.removeState(currState);
		} while( G.size() != 2 && G.contains(startState) && G.contains(fState)); 
		// until the only nodes in G are start and final states 
		
		// 2. Determine the expression accepted by G
		
		return regex;
	}
	
	public String toString() {
		return this.pattern.toString();
	}
}
