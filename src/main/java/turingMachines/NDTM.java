package turingMachines;

import java.util.List;
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
	
	public boolean recursiveSimulation( String w, List<Configuration> configs, final int c, final int s_n ) {
		boolean found = false;
		int i = 1;
		int p = configs.size(); // should be number of configs. of M
		while( !found && i < p ) { // check all accepting configurations
			i += 1; // 3.1
			if( isAcceptingConfig( configs.get(i))) {
				found = derive( configs.get(1), configs.get(i), configs,(int)Math.pow(c, s_n), p); // <<< could be problem with int
			}
		}
		if( found ) { return true; } else { return false; }
	}

	@Override
	public boolean run(String w) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isAcceptingConfig( Configuration cf ) {
		return true;
		// TODO
	}
	
	public boolean derive( Configuration cfs, Configuration cfe, List<Configuration> configs, int k, int p ) {
		// TODO
		boolean derive = false; int i;
		if( k == 0 && (cfs.equals(cfe))) { derive = true; }
		if( k == 1 && directlyDerives(cfs,cfe)) { derive = true; }
		if( k > 1 ) { // then do
			i = 1;
			while( !derive && i < p ) { // check all intermediate configurations
				i += 1;
				derive = derive(cfs,configs.get(i), configs, (int)Math.ceil(k/2),p) &&
						 derive(configs.get(i), cfe, configs, (int)Math.floor(k/2),p);
			}
		}
		return derive;
	}
	
	/**
	 * if we need to check that cfs |- cfe 
	 * This method is doing all the work I think in the algorithm to 
	 * relate the two configurations.
	 * @param cfs
	 * @param cfe
	 * @return
	 */
	public boolean directlyDerives( Configuration cfs, Configuration cfe ) {
		// TODO
		return false;
	}
}
