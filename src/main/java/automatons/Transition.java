package automatons;

public class Transition<T> {

	State input;
	T symbol;
	State result;
	
	public Transition(State input, T symbol, State output) {
		this.input = input;
		this.symbol = symbol;
		this.result = output;
	}
	
	public State getInputState() { return input; } 
	
	public T getSymbol() { return symbol; }
	
	public State getResultState() { return result; }
}
