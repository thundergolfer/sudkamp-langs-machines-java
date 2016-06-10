package automatons;

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
public class FiniteAutomaton {

}
