package automatons;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TransitionFunction<T> extends HashSet<Transition<T>> {

	private static final long serialVersionUID = 1L;

	public Set<Transition<T>> getTransitionsFor( State s ) {
		Set<Transition<T>> trans = new HashSet<Transition<T>>();
		Iterator<Transition<T>> it = this.iterator();
		while( it.hasNext() ) {
			Transition<T> t = it.next();
			if( t.getInputState().equals(s)) { trans.add(t); } 
		}
		return trans;
	}
	
	public Set<Transition<T>> getNullTransitions() {
		Set<Transition<T>> nulls = new HashSet<Transition<T>>();
		Iterator<Transition<T>> it = this.iterator();
		while( it.hasNext() ) {
			Transition<T> t = it.next();
			if( t.getSymbol().equals(null)) { nulls.add(t); }
		}
		return nulls; 
	}
}
