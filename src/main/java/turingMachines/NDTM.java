package turingMachines;

import java.util.Set;

import automatons.State;
import utility.Transition;

/**
 * NOTE: A Non-Deterministic Turing Machine CANNOT EXIST on a computer
 * 		 this class merely simulates a Non-Deterministic TM on a DTM
 * 
 * Algorithm 17.4.4
 * Recursive Simulation of Nondeterministic Turing Machines
 * 
 * input: Turing Machine M = (Q, Alphabet, TapeAlphabet, delta, q<sub>0</sub>, F)
 * 		  string w, where w is element of Alphabet*
 * 		  configurations cf<sub>1</sub>,cf<sub>2</sub>,...,cf<sub>p</sub> of M
 * 		  constant c = 3 * cardinality(Q) * cardinality(TapeAlphabet)
 * 		  space bound s(n)
 * 
 * 1. found = false
 * 2. i = 1
 * while not found and i < p do (check all accepting configurations)
 * 		3.1 i := i + 1
 * 		3.2 if cf<sub>i</sub> is an accepting configuration of M
 * 			then found = Derive(cf<sub>1</sub>,cf<sub>i</sub>,c<sup>s(n)</sup>)
 * end while
 * 4. 
 * 
 * @author Jonathon
 * @param <T>
 *
 */
public class NDTM<T> implements TuringMachine {
	
	Set<State> Q; // finite set of states
	State startState; 
	Set<T> tapeAlphabet; // must contain the special 'Blank' symbol
	Set<T> alphabet;
	Set<Transition<T>> transitionFunction;
	
	public void recursiveSimulation( String w, final int c, final int spaceBoundN ) {
		boolean found = false;
		int i = 1;
		int p = 1; // should be number of configs. of M
		while( !found && i < p ) { // check all accepting configurations
			i += 1; // 3.1
			// if cf....
		}
	}

	@Override
	public boolean run(String w) {
		// TODO Auto-generated method stub
		return false;
	}

}
