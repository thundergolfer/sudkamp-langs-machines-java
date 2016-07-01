package automatons;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TransitionFunction<T> extends HashSet<FATransition<T>> {

	private static final long serialVersionUID = 1L;

	public Set<FATransition<T>> getTransitionsFor( State s ) {
		Set<FATransition<T>> trans = new HashSet<FATransition<T>>();
		Iterator<FATransition<T>> it = this.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			if( t.getInputState().equals(s)) { trans.add(t); } 
		}
		return trans;
	}
	
	public Set<FATransition<T>> getNullTransitions() {
		Set<FATransition<T>> nulls = new HashSet<FATransition<T>>();
		Iterator<FATransition<T>> it = this.iterator();
		while( it.hasNext() ) {
			FATransition<T> t = it.next();
			if( t.getSymbol().equals(null)) { nulls.add(t); }
		}
		return nulls; 
	}

}
