package automatons;

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
	State result;
	
	public FATransition(State input, T symbol, State output) {
		this.input = input;
		this.symbol = symbol;
		this.result = output;
	}
	
	public State getInputState() { return input; } 
	
	public T getSymbol() { return symbol; }
	
	public State getResultState() { return result; }
	
	public String toString() {
		return input + "-[" + symbol + "]-" + result;
	}
}
