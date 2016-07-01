package utility;

import automatons.State;

public interface Transition<T> {
	
	public State getInputState();
	
	public T getSymbol();
	
	public State getResultState();

}
