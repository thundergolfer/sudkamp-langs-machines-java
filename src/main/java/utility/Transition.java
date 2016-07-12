package utility;

import automatons.State;

/**
 * The idea of a "transition" is present throughout more than one model 
 * of computation in computing theory. Defined here are some methods that fulfil
 * some functionality that is common to all models.
 * @author Jonathon
 *
 * @param <T>
 */
public interface Transition<T> {
	
	public State getInputState();
	
	public T getSymbol();
	
	public State getResultState();

}

/* DEVELOPER'S NOTE:
 * Why use an interface for transitions? 
 * We won't have anything that can use this generic functionality,
 * we will likely always need specific functionality.
 */
