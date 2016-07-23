package parsing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import grammars.UnrestrictedGrammar;
import grammars.Rule;
import grammars.ContextFreeGrammar;
import grammars.SentForm;
import grammars.Sentence;

/**
 * Algorithm 18.2.1
 * Breadth-First Top-Down Parser
 * 
 * input: context free grammar G = (V, Alphabet, P, S)
 * 		  string p, where p is element of Alphabet*
 * data structure: queue Q
 * 
 * 1. initialize T with root S
 *    INSERT(S, Q)
 * 2. repeat
 * 		2.1 q := REMOVE(Q) 	(node to be expanded)
 * 		2.2 i := 0 			(number of last rule used)
 * 		2.3 done := false   (Boolean indicator of expansion completion)
 * 		Let q = uAv where A is the leftmost variable in q
 * 		2.4 repeat
 * 			2.4.1 if there is no A rule numbered greater than i the done := true
 * 			2.4.2 if not done then
 * 					Let A -> w be the first A rule with number greater than i and 
 * 					let  j be the number of this rule
 * 					2.4.2.1 if uwv is not element of Alphabet* and the terminal prefix of
 * 						uwv matches a prefix of p then
 * 							2.4.2.1.1 INSERT(uwv, Q)
 * 							2.4.2.1.2 Add node uwv to T. Set a pointer from uwv to q
 * 						end if
 * 					end if
 * 			2.4.3 i := j
 * 		    until done or p = uwv
 *    until EMPTY(Q) or p = uwv
 * 3. if p = uwv then accept else reject
 *    
 * Algorithm 18.4.1
 * Breath-First Bottom-Up Parser
 * 
 * input: context-free grammar G = (V, Alphabet, P, S)
 * 		  string p, where p is element of Alphabet*
 * data structure: queue Q
 * 
 * 1. initialize T with root p
 * 	  INSERT(p, Q)
 * 2. repeat
 * 		q := REMOVE(Q)
 * 		2.1 for each rule A -> w in P do
 * 			2.2.1 for each decomposition uwv of q with v, where v is element of Alphabet* do
 * 					2.1.1.1 INSERT(uAv, Q)
 * 					2.1.1.2 Add node uAv to T. Set a pointer from uAv to q
 * 				  end for
 * 			end for
 * 	  until q = S or EMPTY(Q)
 * 3. if q = S then accept else reject
 * @author Jonathon
 *
 */
public class BreadthFirstParser {
	
	public BreadthFirstParser() {
		
	}
	
	public boolean topDownParse( String p, ContextFreeGrammar g ) {
		
		int i; boolean done;
		SentForm uwv;
		Tree t; Node currNode; Node newNode;
		Queue<Node> queue = new LinkedList<Node>();
		
		// initialise T with S
		t = new Tree(currNode = new Node("S"));
		queue.add(currNode);
		// let q = uAv, where A is the leftmost variable in q
		do {
			Node q = queue.remove();  // node to be expanded
			i = 0; 					    // number of last rule used
			done = false;			 	// boolean indicator of expansion completion
			// Let 	q = uAv where A is the leftmost variable in q
			do {
				// if there is no A rule numbered greater than i then done = true
				uwv = new SentForm();
				if( !done ) {
					// get first rule A -> w that is numbered greater than i. j is its number
					Rule r = null;
					if( !r.terminalStringRule() && terminalPrefixMatches( p, uwv )) {
						newNode = new Node(uwv);
						queue.add(newNode);
						// add node uwv to T. Set a pointer from uwv to q
					}
				}
			} while( done || p.equals("uwv"));
					
		} while( queue.isEmpty() || p.equals("uwv"));
		
		if( p.equals(uwv) ) { return true; } else { return false; }
	}
	
	public boolean bottomUpParse( Sentence p, ContextFreeGrammar g ) {
		
		Tree tree; Node currNode; Node newNode;
		SentForm uwv;
		SentForm w;
		SentForm q;
		Queue<Node> queue = new LinkedList<Node>();
		
		currNode = new Node(p);
		tree = new Tree( currNode ); // initialise tree with p
		queue.add(currNode);
		
		do {
			currNode = queue.remove();
			q = currNode.sentForm;
			// for each rule A -> w in P do
			for( int i=0; i < g.rules.size(); i++ ) {
				// for each decomp. uwv of q with v being a terminal string 
				ArrayList<SentForm> decomps = getUwvDecompositions( q, g.rules.get(i) );
				for( int j=0; j < decomps.size(); j++ ) {
					newNode = new Node(decomps.get(j));
					queue.add(newNode);
					newNode.setParent(currNode);
				}
			}
			
		} while( !(q.size()== 0 && q.get(0).equals("S")) && !queue.isEmpty());
		// ^ until q = S or queue is empty
		if( true ) { return true; } else { return false; }
	}
	
	public boolean terminalPrefixMatches( String p, ArrayList<String> sententialForm) {
		
		return false;
	}
	
	public ArrayList<SentForm> getUwvDecompositions( ArrayList<String> sentForm, Rule r ) {
		
		
		// returns all uAv decompositions you can get from A -> w and a sententialForm q
		return null;
	}
	
	/**
	 * Find the leftmost variable in a sentential form.
	 * @param sententialForm
	 * @return leftmost variable if one exists. null, otherwise
	 */
	public String getLeftMostVar( ArrayList<String> sententialForm ) {
		String leftmostVar = null;
		for( int i=0; i < sententialForm.size(); i++ ) {
			if( UnrestrictedGrammar.isVariable(sententialForm.get(i))) {
				leftmostVar = sententialForm.get(i); break;
			}
		}
		return leftmostVar;
	}
	
	public ArrayList<String> getPrefix( ArrayList<String> sentForm ) {
		ArrayList<String> prefix = new ArrayList<String>();
		for( int i=0; i < sentForm.size(); i++ ) {
			if( !UnrestrictedGrammar.isVariable(sentForm.get(i))) {
				prefix.add(sentForm.get(i));
			}
			else {
				return prefix;
			}
		}
		return prefix;
	}
	
	public ArrayList<String> getSuffix( ArrayList<String> sentForm ) {
		int leftMostVarIndex = sentForm.size();
		ArrayList<String> suffix = new ArrayList<String>();
		for( int i=0; i < sentForm.size(); i++) {
			if( UnrestrictedGrammar.isVariable(sentForm.get(i))) {
				leftMostVarIndex = i;
				break;
			}
		}
		for( int i=leftMostVarIndex+1; i < sentForm.size(); i++) {
			suffix.add(sentForm.get(i));
		}
		
		return suffix;
	}

}

class Tree {
	Node root; 
	
	public Tree() {
		this.root = null;
	}
	
	public Tree( Node root ) {
		this.root = root;
	}
	
	public Node getRoot() {
		return this.root;
	}
	
}

class Node {
	
	public Node parent;
	public SentForm sentForm;
	
	public Node( SentForm sentForm ) {
		this.parent = null;
		this.sentForm = sentForm;
	}
	
	public Node( String symbol ) {
		this.parent = null;
		this.sentForm = new SentForm(Arrays.asList(symbol));
	}
	
	public void setParent( Node parent ) {
		this.parent = parent;
	}
	
}
