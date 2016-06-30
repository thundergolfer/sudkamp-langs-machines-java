package automatons;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Nondeterministic Finite Automaton (NFA)
 * 
 * An NFA is a quintuple M = (Q, Alphabet, Transition Function, q<sub>0</sub>, F) where,
 * Q = finite set of states, q<sub>0</sub> is the start state, F = set of final states
 * 
 * @author Jonathon
 *
 */
public class NFA<T> extends FiniteAutomaton<T> {

	public NFA(FiniteAutomaton<T> f) {
		super(f);
	}
	
	/** 
	 * For an NFA, we could possibly have multiple destination states for 
	 * a certain input state and symbol. Thus we cannot use a method to return
	 * a single "next state".
	 * @param state
	 * @param symbol
	 * @return
	 */
	public Set<State> getNextStates( State state, T symbol ) {
		Set<State> nxtStates = new HashSet<State>();
		Iterator<Transition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			Transition<T> t = it.next();
			// if this transition belongs to the state
			if( t.getInputState().equals(state)) {
				if( t.getSymbol().equals(symbol)) {
					nxtStates.add(t.getResultState());
				}
			}
		}
		return nxtStates;
	}

}
