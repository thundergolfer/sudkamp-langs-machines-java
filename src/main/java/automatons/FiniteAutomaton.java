package automatons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * 
 * @author Jonathon
 * @param <T> the generic is used for the symbol (alphabet) type.
 */
public abstract class FiniteAutomaton<T> {
	
	Set<State> Q = new HashSet<State>(); // set of states in Finite Automaton
	Set<State> F = new HashSet<State>(); // final states
	Set<T> alphabet = new HashSet<T>();
	Set<FATransition<T>> transitionFunction = new HashSet<FATransition<T>>();
	State startState;
	
	public FiniteAutomaton () {
		this.Q = new HashSet<State>();
		this.F = new HashSet<State>();
		this.alphabet = new HashSet<T>();
		this.startState = null;
	}

	public FiniteAutomaton ( FiniteAutomaton<T> f ) {
		this.Q = f.Q; this.F = f.F; 
		this.alphabet = f.alphabet;
		this.transitionFunction = f.transitionFunction;
		this.startState = f.startState;
	}
	
	// start state is always q0
	@SuppressWarnings("unchecked")
	public FiniteAutomaton( int[] finalStates, Object ... args) {
		if( args.length % 3 != 0 ) { 
			throw new IllegalArgumentException("Pass 3 arguments for each transition");
		}
		for( int i=0; i < args.length; i+=3 ) {
			// get input state's id num 
			State input = new State(String.valueOf((int)args[i]));
			T symbol = (T)args[i+1];
			this.alphabet.add(symbol);
			State result = new State( String.valueOf((int)args[i+2]));
			FATransition<T> t = new FATransition<T>( input, symbol, result);
			this.Q.add(input); this.Q.add(result);
			this.transitionFunction.add(t);
		}
		// mark final states and start state
		this.startState = null;
		State[] states = Q.toArray(new State[this.Q.size()]);
		for( int i=1; i < states.length; ++i ) {
			if( Integer.parseInt(states[i].getId()) == 0 ) {
				this.startState = states[i];
			}
			for( int j=0; j < finalStates.length; ++j ) { // inefficient looping
				if( finalStates[j] == Integer.parseInt(states[i].getId())) { 
					states[i].setFinalState(true);
					this.F.add(states[i]);
				}
			}
		}
		// check for start state
		if( this.startState == null) { 
			throw new IllegalArgumentException("No start state specified.");
		}
	}
	
	
	public State getStartState() { return startState; }
	public void setStartState( State sState ) { this.startState = sState; }
	public Set<State> getFinalStates() { return F; }
	public Set<State> getStates() { return Q; }
	
	public Set<State> getOtherStates( State s ) {
		Set<State> otherStates = new HashSet<State>(Q);
		boolean removed = otherStates.remove(s);
		if( removed ) { return otherStates; }
		return null;
	}
	
	public Set<State> getNonFinalStates() {
		Set<State> nonFinals = new HashSet<State>(Q);
		nonFinals.removeAll(F);	
		return nonFinals;
	}
	
	public State getANonFinalState() {
		Set<State> nonFinals = getNonFinalStates();
		return getRandomState(nonFinals);
	}
	
	public State getRandomState( Set<State> states ) {
		int randState = new Random().nextInt(states.size() + 1);
		State s = states.toArray(new State[states.size()])[randState];
		return s;
	}
	
	public Set<State> getNonStartStates() {
		Set<State> nonStart = new HashSet<State>(Q);
		nonStart.remove(startState);
		return nonStart;
	}
	
	public State getANonStartState() {
		Set<State> nonStart = getNonStartStates();
		return getRandomState(nonStart);
		
	}
	
	public Set<State> getIntersection( Set<State> s1, Set<State> s2 ) {
		Set<State> intersection = new HashSet<State>(s1);
		intersection.retainAll(s2);
		return intersection;
	}
	
	
	public void addToAlphabet(T symbol) { alphabet.add(symbol); }
	
	public void removeFromAlphabet(T symbol) { alphabet.remove(symbol); }
	
	public void addToStates( State state ) { Q.add(state); }
	
	public void removeState( State state ) { Q.remove(state); }
	
	public void removeTransition(FATransition<T> t ) {
		transitionFunction.remove(t);
	}
	
	public void addTransition(FATransition<T> t) {
		transitionFunction.add(t);
	}
	
	/**
	 * Remove all transitions incident to a certain state,
	 * that is, all transition going in and all transitions leading out
	 * @param s
	 * @return
	 */
	public void removeAllTransitions( State s ) {
		Iterator<FATransition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			if( t.getInputState().equals(s) || t.getResult().equals(s)) {
				transitionFunction.remove(t);
			}
		}
	}
	
	public Set<State> getAcceptStates() {
		return F;
	}
	
	/**
	 * If there is an arc from state1 to state2 in the transition
	 * function, then include this transition arc in the return set.
	 * @param s1
	 * @param s2
	 * @return
	 */
	public List<FATransition<T>> getArcsFrom( State s1, State s2 ) {
		List<FATransition<T>> l = new ArrayList<FATransition<T>>();
		Iterator<FATransition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			if(t.getInputState().equals(s1) && t.getResult().equals(s2)) {
				l.add(t);
			}
		}
		return l;
	}
	
	/**
	 * Given a symbol x, does a certain state have transitions using this symbol?
	 * @param symbol
	 * @param s
	 * @return
	 */
	public List<FATransition<T>> getArcsWithSymbol( T symbol, State s ) {
		List<FATransition<T>> list = new ArrayList<FATransition<T>>();
		Set<FATransition<T>> transitions = this.transitionFunction;
		Iterator<FATransition<T>> transIt = transitions.iterator();
		while( transIt.hasNext() ) {
			FATransition<T> t = transIt.next();
			if( t.symbol.equals(symbol)) {
				list.add(t);
			}
		}
		return list;
	}
	
	
	

}
