package automatons;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
 * 
 * @author Jonathon
 *
 */
public abstract class FiniteAutomaton<T> {
	
	Set<State> Q = new HashSet<State>(); // set of states in Finite Automaton
	Set<State> F = new HashSet<State>(); // final states
	Set<T> alphabet = new HashSet<T>();
	Set<Transition<T>> transitionFunction = new HashSet<Transition<T>>();
	State startState;

	public FiniteAutomaton ( FiniteAutomaton<T> f ) {
		this.Q = f.Q; this.F = f.F; 
		this.alphabet = f.alphabet;
		this.transitionFunction = f.transitionFunction;
		this.startState = f.startState;
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
	
	public void removeTransition(Transition<T> t ) {
		transitionFunction.remove(t);
	}
	
	public void addTransition(Transition<T> t) {
		transitionFunction.add(t);
	}
	
	/**
	 * Remove all transitions incident to a certain state,
	 * that is, all transition going in and all transitions leading out
	 * @param s
	 * @return
	 */
	public void removeAllTransitions( State s ) {
		Iterator<Transition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			Transition<T> t = it.next();
			if( t.getInputState().equals(s) || t.getResultState().equals(s)) {
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
	public List<Transition<T>> getArcsFrom( State s1, State s2 ) {
		List<Transition<T>> l = new ArrayList<Transition<T>>();
		Iterator<Transition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			Transition<T> t = it.next();
			if(t.getInputState().equals(s1) && t.getResultState().equals(s2)) {
				l.add(t);
			}
		}
		return l;
	}
	
	
	

}
