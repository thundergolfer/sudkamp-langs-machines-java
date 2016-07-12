package parsing;

import grammars.ContextFreeGrammar;
import grammars.Rule;

public class LLkParser {
	
	/**
	 * Algorithm 19.4.1
	 * input: context-free grammar G = (V, Alphabet, P, S)
	 * 
	 * 1. for each a that is in Alphabet do F`(a) := {a}
	 * 2. for each A that is in V do F(a) := { {null}     if A -> null is rule in P
	 * 										 { empty set  otherwise
	 * 3. repeat 
	 * 	3.1 for each A that is in V do F`(A) := F(A)
	 * 	3.2 for each rule A -> u<sub>1</sub>u<sub>2</sub>...u<sub>n</sub> with n > 0 do
	 * 			F(A) := F(A) UNION 
	 * 					trunc<sub>k</sub>(F`(u<sub>1</sub>)F`(u<sub>2</sub>)...F`(u<sub>n</sub>))
	 *    until F(A) = F`(A) for all A in V 
	 * 4. FIRST<sub>k</sub>(A) = F(A)
	 * 
	 * @param g
	 */
	public void FIRSTk( ContextFreeGrammar g ) {
		// TODO 
	}
	
	/**
	 * Algorithm 19.5.1
	 * 
	 * input: context-free grammar G = (V, Alphabet, P, S)
	 * 		  FIRSTk(A) for every A in V
	 * 
	 * 1. FL(S) := {null}
	 * 2. for each A in V - {S} do FL(A) := empty 
	 * 3. repeat
	 * 	3.1 for each A in V do FL`(A) := FL(A)
	 * 	3.2 for each rule A -> w  = u<sub>1</sub>u<sub>2</sub>...u<sub>n</sub> with
	 * 		w NOT a terminal string do
	 * 		3.2.1 L := FL`(A)
	 * 		3.2.2 if u<sub>n</sub> in V then FL(u<sub>n</sub>) := FL(u<sub>n</sub>) UNION L
	 * 		3.2.3 for i := n - 1 to 1 do
	 * 			3.2.3.1 L := trunc<sub>k</sub>(FIRST<sub>k</sub>(u<sub>i+1</sub>)L)
	 * 			3.2.3.2 if u<sub>i</sub> in V then FL(u<sub>i</sub>) := FL(u<sub>i</sub>) UNION L
	 *    until FL(A) = FL`(A) for every A in V
	 * 4. FOLLOW<sub>k</sub>(A) := FL(A)
	 * 
	 * @param g
	 */
	public void FOLLOWk( ContextFreeGrammar g ) { // + FIRSTk(A) for every A in V 
		// TODO
	}
	
	/**
	 * Definition 19.1.1 i)
	 * The *lookahead set* of the variable A, LA(A), is defined by
	 * LA(A) = {x | S *-> uAv *-> ux, where ux in Alphabet* }
	 * @param A
	 * @return
	 */
	public String LA( String A ) {
		// TODO
		String x = null;
		return x;
	}
	
	/**
	 * Definition 19.1.1 ii)
	 * For each rule A -> w in P, the *lookahead  set* of the rule A -> w
	 * is defined by :
	 * LA(A -> w) = {x | wv *-> x where x in Alphabet* and S *-> uAv }
	 * @param r
	 * @return
	 */
	public String LA( Rule r ) {
		// TODO
		String x = null;
		return x;
	}
	
	/**
	 * Definition 19.1.2 ii)
	 * The length-k lookahead set of the variable A is the set
	 * LAk(A) = trunc_k(LA(A))
	 * 
	 * @param A
	 * @return
	 */
	public String LAk( String A ) {
		return trunc_k( LA(A) );
	}
	
	/**
	 * Definition 19.1.2 iii)
	 * The length-k lookahead set of the rule A -> w is the set
	 * LAk(A -> w) = trunc_k(LA(A -> w))
	 * 
	 * @param r
	 * @return
	 */
	public String LAk( Rule r ) {
		return trunc_k(LA(r)); // r = A -> w
	}
	
	/**
	 * Definition 19.1.2 i)
	 * trunc<sub>k</sub> is a function from *P*(Alphabet*) to *P*(Alphabet*) defined by
	 * trunc<sub>k</sub>(X) = {u | u in X with length(u) <= k or
	 * uv in X with length(u) = k }
	 * for all X in *P*(Alphabet*)
	 * @param X
	 * @return
	 */
	public String trunc_k( final String X ) {
		// TODO
		String u = null;
		return u;
	}
	
}
