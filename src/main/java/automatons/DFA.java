package automatons;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import utility.Transition;

/**
 * Algorithm 5.7.2
 * Determination of Equivalent States of DFA
 * 
 * input: DFA M = (Q, Alphabet, TransitionFunctoin, q<sub>0</sub>, F)
 * 
 * 1. (Initialization)
 * 	  for every pair of states q<sub>i</sub> and q<sub>j</sub>, i < j, do
 * 		1.1 D[i,j] := 0
 * 		1.2 S[i,j] := null set
 * 	  end for
 * 2. for every pair i, j, i < j,  if one of q<sub>i</sub> or q<sub>j</sub> is
 * 	  and accepting state and the other is not an accepting state, then set D[i,j] := 1
 * 3. for every pair i, j, i < j, with D[i,j] = 0, do
 * 		3.1 if there exists an a which is element of Alphabet such that delta(q<sub>i</sub>, a) = q<sub>m</sub>,
 * 		delta(q<sub>j</sub>, a) = q<sub>n</sub> and D[m,n] = 1 or D[n,m] = 1, then DIST(i,j)
 * 		
 * 		3.2 else for each a that is element of Alphabet, do: Let delta(q<sub>i</sub>, a) = q<sub>m</sub>, 
 * 			delta(q<sub>j</sub>m a) = q<sub>n</sub>
 * 				if m < n and [i,j] /= [m,n], then add [i,j] to S[m,n]
 * 			else if m > n and [i,j] /= [n,m], then add [i,j] to S[n,m]
 *    end for
 *    
 *    DIST(i,j);
 *    begin 
 *    	D[i,j] := 1
 *    	for all [m,n] that are elements of S[i,j], DIST(m,n)
 *    end
 * 
 * @author Jonathon
 *
 */
public class DFA<T> extends FiniteAutomaton<T> {

	public DFA() { super(); }
	
	public DFA(FiniteAutomaton<T> f) {
		super(f);
	}
	
	// TODO: Centralise the checking of Final states and Start states. This should be the repsonsibility
	// of the DFA and not the states, as this is closer to the textbook
	
	public boolean run( List<T> input ) {
		return run( input, false );
	}
	
	// start state is always q0
	@SuppressWarnings("unchecked")
	public DFA( int[] finalStates, Object ... args) {
		if( args.length % 3 != 0 ) { 
			throw new IllegalArgumentException("Pass 3 arguments for each transition");
		}
		for( int i=0; i < args.length; i+=3 ) {
			// get input state's id num 
			State input = new State((int)args[i]);
			T symbol = (T)args[i+1];
			this.alphabet.add(symbol);
			State result = new State( (int)args[i+2]);
			FATransition<T> t = new FATransition<T>( input, symbol, result);
			this.Q.add(input); this.Q.add(result);
			this.transitionFunction.add(t);
		}
		// mark final states and start state
		this.startState = null;
		State[] states = Q.toArray(new State[this.Q.size()]);
		for( int i=1; i < states.length; ++i ) {
			if( states[i].getId() == 0 ) {
				this.startState = states[i];
			}
			for( int j=0; j < finalStates.length; ++j ) { // inefficient looping
				if( finalStates[j] == states[i].getId()) { 
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
	
	public boolean run( List<T> input, boolean trace ) {
		State currState = this.getStartState();
		State nextState;
		T currSymbol;
		for( int i=0; i < input.size(); ++i) {
			currSymbol = input.get(i);
			nextState = getNextState( currState, currSymbol );
			if( nextState == null ) { 
				if(trace) { System.out.println("q_"+currState.getId()+" has no transition for ["+currSymbol+"].\n Input rejected."); }
				return false; 
			}
			else { 
				if(trace) { System.out.println( "q_"+currState.getId()+" -["+currSymbol+"]-> "+"q_"+nextState.getId()); }  
				currState = nextState; 
			}
		}
		if( currState.isFinalState() ) { 
			if(trace) { System.out.println("q_"+currState.getId() + " is final state and input is consumed.\nInput accepted."); }
			return true; 
		} 
		else { 
			if(trace) { System.out.println("Input is consumed but " + "q_"+currState.getId() + " is not final state.\nInput rejected."); }
			return false;
		}
	}
	
	
	/**
	 * Returns the single destination state for an input state and symbol,
	 * or null if a destination state does not exist. ie. no arc exists
	 * @param state
	 * @param symbol
	 * @return
	 */
	public State getNextState( State state, T symbol ) {
		Iterator<FATransition<T>> it = transitionFunction.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			// if this transition belongs to the state
			if( t.getInputState().equals(state)) {
				if( t.getSymbol().equals(symbol)) {
					return t.getResultState();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * input: DFA M = (Q,Alphabet,TransitionFunction,q<sub>0</sub>,F)
	 * 
	 * 1. (Initialization) 
	 * for every pair of states q<sub>i</sub> and q<sub>j</sub>, i < j, do
	 * 		1.1 D[i,j] := 0
	 * 		1.2 S[i,j] := empty
	 * end for
	 * 2. for every pair i,j, i < j, if one of q<sub>i</sub> or q<sub>j</sub> is an 
	 * 	  an accepting state and the other is not an accepting state, then set D[i,j] := 1
	 * 3. for every pair i,j, i < j, with D[i,j] = 0, do
	 * 		3.1 if there exists an a, where a is in alphabet, such that 
	 * 			transition(q<sub>i</sub>,a) = q<sub>m</sub>,
	 * 			transition(q<sub>j</sub>,a) = q<sub>n</sub>, and
	 * 				D[m,n] = 1 or D[n,m] = 1, then DIST(i,j)
	 * 		3.2 else for each a, a is element of alphabet, do: let 
	 * 			t(q<sub>i</sub>,a) = q<sub>m</sub> and
	 * 			t(q<sub>j</sub>,a) = q<sub>n</sub>
	 * 				if m < n and [i,j] /= [m,n], then add [i,j] to S[m,n]
	 * 			else if m > n and [i,j] /= [n,m], then add [i,j] to S[n,m]
	 * 	  end for
	 * 
	 * DIST(i,j)
	 * begin
	 * 		D[i,j] := 1
	 * 		for all [m,n] element of S[i,j], DIST(m,n)
	 * end
	 * @param dfa
	 * @return
	 */
	public Set<State> determineEquivStates() {
		
		HashSet<State> equivStates = new HashSet<State>();
		HashSet<State> states = (HashSet<State>) this.getStates();
		State[] statesArray = states.toArray(new State[states.size()]);
		boolean[][] D = new boolean[states.size()][states.size()];
		ArrayList<ArrayList<Set<Point>>> S = new ArrayList<ArrayList<Set<Point>>>(); // a mess right here
		// Initialization
		// for every pair of states i,j, i < j 
		for( int i=0; i < statesArray.length; i++) {
			for( int j=i+1; j < statesArray.length; j++) {
				D[i][j] = false;
			}
		} // unnessecary??
		S = this.initS();
		// 2
		for( int i=0; i < statesArray.length; i++ ) {
			for( int j=i+1; j < statesArray.length; j++ ) {
				if( (statesArray[i].isFinalState() && !statesArray[j].isFinalState()) || 
				    (!statesArray[i].isFinalState() && statesArray[j].isFinalState())) {
					D[i][j] = true;
				}
			}
		}
		// 3
		for( int i=0; i < statesArray.length; i++ ) {
			for( int j=i+1; j < statesArray.length; j++ ) {
				if( !D[i][j] ) { // if D[i,j] = 0
					// IF
					State qi = statesArray[i]; State qj = statesArray[j];
					// if there exists an a, where a is in alphabet, such that 
					// transition(q<sub>i</sub>,a) = q<sub>m</sub>,transition(q<sub>j</sub>,a) = q<sub>n</sub>,
					// and D[m,n] = 1 or D[n,m] = 1, then DIST(i,j)
					Iterator<T> alphaIt = this.alphabet.iterator();
					while( alphaIt.hasNext() ) {
						T symbol = alphaIt.next();
						// do qi and qj share a transition with this symbol?
						FATransition<T> qiSymbolArc = getArcWithSymbol( symbol, qi );
						FATransition<T> qjSymbolArc = getArcWithSymbol( symbol, qj );
						// try every combination of transitions
						if( qiSymbolArc != null & qjSymbolArc != null ) {
								State qm = qiSymbolArc.getResultState();
								State qn = qjSymbolArc.getResultState();
								int qmIndex = getStateIndex(qm, statesArray);
								int qnIndex = getStateIndex(qn, statesArray);
								assert( qnIndex >= 0 && qmIndex >= 0 );
								if( D[qmIndex][qnIndex] == true || D[qnIndex][qmIndex] == true ) {
									dist(i,j, D, S);
								}
						}
					}
					// ELSE for each a, a is element of alphabet, do: let t(q<sub>i</sub>,a) = q<sub>m</sub> and
					// t(q<sub>j</sub>,a) = q<sub>n</sub> 
					//		if m < n and [i,j] /= [m,n], then add [i,j] to S[m,n]
					alphaIt = this.alphabet.iterator();
					while( alphaIt.hasNext() ) {
						T symbol = alphaIt.next();
						FATransition<T> qiSymbolArc = getArcWithSymbol( symbol, qi);
						FATransition<T> qjSymbolArc = getArcWithSymbol( symbol, qj);
						if( qiSymbolArc != null && qjSymbolArc != null ) {
							State qm = qiSymbolArc.getResultState();
							State qn = qjSymbolArc.getResultState();
							int qmIndex = getStateIndex(qm, statesArray);
							int qnIndex = getStateIndex(qn, statesArray);
							if( qmIndex < qnIndex && (i!=qmIndex || j!=qnIndex)) {
								S.get(qmIndex).get(qnIndex).add(new Point(i,j)); // add (i,j) to S[m,n]
							}
							else if( qmIndex > qnIndex && (i!=qnIndex || j!=qmIndex)) {
								S.get(qnIndex).get(qmIndex).add(new Point(i,j));
							}
						}
					}
				}
			}
		}	
		// if D[i][j] = 0 then add states i and j to equivStates list
		for( int i=0; i < states.size(); ++i) {
			for( int j=0; j < states.size(); ++j) {
				if( D[i][j] == false && i < j ) {
					equivStates.add(statesArray[i]);
					equivStates.add(statesArray[j]);
				}
			}
		}
		// TODO: equivStates is a set BUT still adds multiples of the same state
		// TODO: this function adds all states to the set when only 
		return equivStates;
	}
	
	public static int getStateIndex( State s, State[] states ) {
		for( int i=0; i < states.length; i++ ) {
			if( s.equals(states[i])) { return i; }
		}
		return -1;
	}
	
	/**
	 * A DFA will have at maximum one transition per state and symbol combo.
	 * We can use the general getArcsWithSymbol method and just return the first
	 * transition in the list if it exists.
	 * 
	 * @param symbol
	 * @param s
	 * @return
	 */
	public FATransition<T> getArcWithSymbol( T symbol, State s ) {
		List<FATransition<T>> list = getArcsWithSymbol( symbol, s);
		if( list.size() > 0 ) { return list.get(0); }
		else { return null; }
	}
	
	private ArrayList<ArrayList<Set<Point>>> initS() {
		int numStates = this.getStates().size();
		ArrayList<ArrayList<Set<Point>>> S = new ArrayList<ArrayList<Set<Point>>>();
		for( int i=0; i < numStates; i++ ) {
			ArrayList<Set<Point>> row = new ArrayList<Set<Point>>();
			S.add(row);
			for( int j=0; j < numStates; j++ ) {
				row.add(null);
				if( i < j ) { // want to maintain normal indexing
					row.add( new HashSet<Point>() );
				}
			}
		}
		return S;
	}
	
	/**
	 * DIST(i,j)
	 * begin
	 * 		D[i,j] := 1
	 * 		for all [m,n] element of S[i,j], DIST(m,n)
	 * end
	 * 
	 * Note: D and S added as parameters as they are local to the calling 
	 * 		 method and not fields of the DFA object.
	 * @param i
	 * @param j
	 * @param D
	 * @param S
	 */
	private static void dist(int i, int j, boolean[][] D, ArrayList<ArrayList<Set<Point>>> S) {
		D[i][j] = true;
		// for all [m,n] element of S[i,j], DIST(m,n)
		Set<Point> points = S.get(i).get(j);
		Iterator<Point> it = points.iterator();
		while( it.hasNext() ) {
			Point p = it.next();
			dist( p.x, p.y, D, S);
		}
	}
}
