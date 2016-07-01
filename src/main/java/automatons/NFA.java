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
		Iterator<FATransition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			// if this transition belongs to the state
			if( t.getInputState().equals(state)) {
				if( t.getSymbol().equals(symbol)) {
					nxtStates.add(t.getResultState());
				}
			}
		}
		return nxtStates;
	}
	
	/**
	 * input: an NFA-null M = (Q, Alphabet, TransitionFunction, q<sub>0</sub>,F)
	 * 		  input transition function t of M
	 * 
	 * 1. initialize Q' to null-closure(q<sub>0</sub>)
	 * 2. repeat
	 * 	2.1 if there is a node X, X is element of Q' and a symbol a that's element of Alphabet 
	 * 			with no arc leaving X labeled "a", then
	 * 			2.1.1 let Y = UNION over q<sub>i</sub>, where q<sub>i</sub> is element of X of
	 * 			  	  t(q<sub>i</sub>,a)
	 * 			2.1.2 if Y /= Q', then set Q' := Q UNION {Y}
	 * 			2.1.3 add an arc from X to Y labeled a
	 * 		else done:= true
	 * 	  until done
	 * 3. the set of accepting states of DM if F' = {X, where X is element of Q' | X contains 
	 * 												 an element q<sub>i</sub> that is element of F}
	 */
	public DFA<T> constructEquivDFA( TransitionFunction<T> tf ) {
		DFA<T> dfa = new DFA<T>();
		boolean done = false;
		Set<State> Q_ = nullClosure(this.getStartState()); // init Q'
		do {
			Iterator<State> it = Q_.iterator();
			while( it.hasNext() ) {
				State currS = it.next();
				Iterator<T> alphaIt = this.alphabet.iterator();
				while( alphaIt.hasNext() ) {
					T sym = alphaIt.next();
					// if no arc from X labeled a
					if( this.getNextStates(currS, sym).size() == 0) {
						// TODO
						Set<State> Y = null; // UNION over all states if X of t(qi,a)
						// 2.1.2
						if( Y.containsAll(Q_) && Q_.containsAll(Y)) {
							// add new state {Y} and add it to Q`
							// TODO
						}
						// add an arc from X to Y labeled a
						// TODO
					}
				}
			}
		} while( !done );
		// calculate F' the set of accepting states of DM
		
		return dfa;
	}
	
	/**
	 * page 170  of textbook
	 * 
	 * @param s
	 * @return
	 */
	public Set<State> nullClosure( State s ) {
		Set<State> closureSet = new HashSet<State>();
		Set<State> prev;
		closureSet.add(s); // basis step
		prev = new HashSet<State>(closureSet);
		do {
			Iterator<State> it = closureSet.iterator();
			while( it.hasNext() ) {
				State currState = it.next();
				closureSet.addAll( getNextStates(currState, null));
			}
		} while( !prev.containsAll(closureSet));
		
		return closureSet;
	}
	
	/**
	 * Find the null-closure of a set of states, which will be union of
	 * the null-closures of each state.
	 * @param states
	 * @return
	 */
	public Set<State> nullClosure( Set<State> states ) {
		Set<State> closureSet = new HashSet<State>();
		Iterator<State> it = states.iterator();
		while( it.hasNext() ) {
			State currS = it.next();
			closureSet.addAll( nullClosure( currS ));
		}
		return closureSet;
	}
	
	/**
	 * The Input Transition Function is defined by
	 * t(q<sub>i</sub>,a) = UNION over q<sub>j</sub> that are in null-closure of q<sub>i</sub> of
	 * 						null-closure(delta(q<sub>j</sub>,a))
	 * @param transFunc
	 * @return
	 */
	public Set<FATransition<T>> getInputTransitionFunc() {
		Set<FATransition<T>> inputFunc = new TransitionFunction<T>();
		Iterator<State> closureIt;
		Iterator<State> qIt = this.Q.iterator();
		// go through all states of the NFA
		while( qIt.hasNext() ) {
			State currState = qIt.next();
			Set<State> currStateNullClose = nullClosure(currState);
			Set<State> resultStateSet = new HashSet<State>();
			// find t(qi,a) for all a, where a in Alphabet
			Iterator<T> alphaIt = this.alphabet.iterator();
			// go through all symbols in alphabet
			while( alphaIt.hasNext() ) {
				T a = alphaIt.next();
				closureIt = currStateNullClose.iterator();
				// go through all states in current's null-closure
				while( closureIt.hasNext() ) {
					State qj = closureIt.next();
					// add null-closure(transition(qj,a))
					resultStateSet.addAll(nullClosure(getNextStates(qj,a)));
				}
				// now we have all states that t(currState,a) could arrive at
				// we must create transitions for them
			}
		}
		return inputFunc;
	}
	
	/**
	 * This algorithm is not present in the textbook but is useful to include.
	 * @param w
	 */
	public void recursiveSimulation( String w ) {
		// TODO
	}
	
	
	
}
