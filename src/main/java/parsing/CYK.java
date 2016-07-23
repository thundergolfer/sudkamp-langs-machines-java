package parsing;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import grammars.Grammar;
import grammars.CNFGrammar;
import grammars.Rule;

/**
 * Algorithm 4.6.1
 * CYK Algorithm
 * 
 * input: context-free-grammar G = (V, Alphabet, P, S)
 * 		  string u = x<sub>1</sub>x<sub>2</sub>...x<sub>n</sub>, where x is element of Alphabet*
 * 
 * 1. initialize all X<sub>i,j</sub> to EMPTY
 * 2. for i = 1 to n
 * 		for each variable A, if there is a rule A -> x<sub>i</sub> then
 * 	    X<sub>i,i</sub> := X<sub>i,i</sub> UNION {A}
 * 3. for step = 2 to n 
 * 		3.1 for i = 1 to n - step + 1
 * 			3.1.1  for k = 1 to i + step - 2
 * 						if there are variables B that are elements of X<sub>i,k</sub>,
 * 						C that are elements of X<sub>k+1,i+step-1</sub>, and
 * 						and rule A -> BC, then X<sub>i,i+step-1</sub> := X<sub>i,i+step-1</sub> UNION {A}
 * 4. u is element of L(G) if S is element of X<sub>1,n</sub>
 * 
 * @author Jonathon
 *
 */
public class CYK {
	
	// TODO: Clean out all the for loops in this algorithm
	@SuppressWarnings("unchecked")
	public boolean testMembership( CNFGrammar g, List<String> u ) {
		
		int N = length(u);
		Set<String>[][] X = (Set<String>[][]) new Set[N][N]; // initialise all Xij to empty 
		
		// NOTE: our arrays and lists use 0-based indexing instead of pseudocode's 1-based
		// 		 for loops still follow pseudocode
		for( int i=1; i <= N; i++ ) { // for i=1 to n 
			for( String A: g.vars ) {
				for( Rule r: g.rules ) {
					if( r.lhs != null && r.lhs.get(0).equals(A)) { // found A -> x rule 
						// if there is a A -> x_i for an x_i part of u, then
						if( r.rhs != null && r.rhs.size() == 1 && r.rhs.get(0) == u.get(i-1)) {
							X[i-1][i-1].add(A); // Xii := Xii U {A} 
						}
					}
				}
			}
		}
		for( int step=2; step <= N; ++step ) {
			for( int i=1; i <= N-step+1; ++i) {
				for( int k=i; k <= i+step-2; ++k) {
					List<String> Bvars = new ArrayList<String>( X[i-1][k-1] );
					List<String> Cvars = new ArrayList<String>( X[k][i+step-1-1] );
					for( String B: Bvars ) {
						for( String C: Cvars ) {
							// is there a rule A -> BC in grammar 
							for( Rule r: g.rules ) {
								if( r.rhs.size() == 2 && r.rhs.get(0).equals(B) &&
									r.rhs.get(1).equals(C)) {
									String A = r.lhs.get(0); // found A -> BC 
									X[i-1][i+step-1-1].add(A);
								}
							}
						}
					}
				}
			}
		}
		return X[0][N-1].contains(Grammar.startSymbol()) ? true: false; // true, if S is in X1,n
	}
	
	/**
	 * The CYK Algorithm can be modified to produce derivations of strings 
	 * in L(G), that is, to be a parser. This algorithm accomplishes it by 
	 * recording the justifications for the addition of variables in to the sets X<sub>i,j</sub>
	 * @param g
	 * @param u
	 * @return the justification table
	 */
	public String[][][] parse( CNFGrammar g, List<String> u ) {
		// TODO
		return null;
	}
	
	public int length( List<String> u ) {
		return u.size();
	}
}
