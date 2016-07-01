package automatons;

import utility.Transition;

public class FATransition<T> implements Transition {

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
}
