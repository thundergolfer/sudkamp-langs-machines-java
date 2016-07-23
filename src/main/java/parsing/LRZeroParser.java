package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import grammars.LRZeroGrammar;
import grammars.Grammar;

/**
 * Algorithm 20.2.1
 * Parser For An LR(0) Grammar
 * 
 * input: LR(0) grammar G = (V, Alphabet, P, S)
 * 		  string p, where p is an element of Alphabet*
 * 
 * 1. u := null, v:= p
 * 2. dead-end := false
 * 3. repeat
 * 		3.1. if u is element of LR(0)-CONTEXT(A -> w) for the rule A -> w in P
 * 				where u = xw then u := xA
 * 			 else if u is a viable prefix and v /= null then shift(u,v)
 * 				else dead-end := true
 * 	  
 * 	  until u = S or dead-end 
 * if u = S then accept else reject
 * 
 * Algorithm 20.3.3
 * Parser Utilizing The Deterministic LR(0) Machine
 * 
 * input: LR(0) grammar G = ( V, Alphabet, P, S )
 * 		  string p, where p is an element of Alphabet*
 * 		  deterministic LR(0) machine of G
 * 
 * 1. u := null, v:= p
 * 2. dead-end:= false
 * 	  repeat
 * 		3.1 if delta-w-caret(q<sub>s</sub>, u) contains A -> w. where u = xw then u := xA
 *    		else if delta-w-caret(q<sub>s</sub>, u) contains an item A -> y.z and v /= null then shift( u,v)
 *    			else dead-end := true
 *    until u = S or dead-end
 * if u = S then accept else reject
 * 
 * @author Jonathon
 *
 */
public class LRZeroParser {
	
	public boolean parse( LRZeroGrammar g, List<String> p ) {
		List<String> S = new ArrayList<String>(Arrays.asList(g.getStartSymbol()));
		List<String> u = null; List<String> v = p;
		boolean deadEnd = false;
		do {
			// TODO
		} while( !u.equals(S) || !deadEnd ); // until u = S or deadEnd
		if( u.equals(S)) { return true; } else { return false; }
	}
	

}
