package parsing;

/**
 * Algorithm 20.5.4
 * Parser for a LR(1) Grammar
 * 
 * input: LR(1) grammar G = (V, Alphabet, P, S)
 * 		  string p, where p is an element of Alphabet*
 * 		  deterministic LR(1) machine of G
 *
 * 1. Let p = zv, where z is an element of Alphabet UNION {null} and v is an element of Alphabet*
 * 	  (z is the lookahead symbol, v the remainder of the input
 * 2. u := null
 * 3. dead-end := false
 * 4. repeat
 * 		4.1 if delta-w-caret( q<sub>s</sub>, u) contains [A -> w., {z<sub>1</sub>,...,Z<sub>n</sub>}
 * 			   where u = xw and z = z<sub>i</sub> for some 1 <= i <= n then u := xA
 * 			else if z /= null and delta-w-caret(q<sub>s</sub>, u) contains an item A -> p.zq then 
 * 				(shift and obtain new lookahead symbol)
 * 				4.1.1 u:= uz
 * 				4.1.2 Let v = zv` where z is an element of Alphabet UNION {null} and v` is an element of Alphabet*
 * 				4.1.3 v:= v`
 * 			end if
 * 			else dead-end := true
 * 	  until u = S or dead-end
 * if u = S then accept else reject
 * 
 * 
 * @author Jonathon
 *
 */
public class LROneParser {

}
