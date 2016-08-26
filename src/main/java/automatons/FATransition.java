package automatons;

import java.util.Set;

import utility.Transition;

/**
 * Finite Automaton Transition. Defines a the type of transitions that comprise
 * part of a Finite Automaton's specification.
 * @author Jonathon
 *
 * @param <T>
 */
public class FATransition<T> implements Transition<T> {

	State input;
	T symbol;
	State resultState;
	Set<State> possibleResults;
	
	
	public FATransition(State input, T symbol, State output) {
		this.input = input;
		this.symbol = symbol;
		this.resultState = output;
	}
	
	public FATransition(State input, T symbol, Set<State> outputs) {
		this.input = input;
		this.symbol = symbol;
		this.possibleResults = outputs;
		this.resultState = null;
	}
	
	public State getInputState() { return input; } 
	
	public T getSymbol() { return symbol; }
	
	public String toString() {
		return input + "-[" + symbol + "]-" + resultState;
	}

	public State getResultState() {
		return resultState;
	}
	
	public Set<State> getResult() {
		return possibleResults;
	}
}
