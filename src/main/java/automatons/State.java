package automatons;

public class State {
	private int id;
	private boolean fState; // final state
	private boolean sState; // start state
	private boolean aState; // accept state
	
	public State( int id ) {
		this(id, false);
	}
	
	public State( int id, boolean isFinal ) {
		this.id = id;
		this.fState = isFinal;
		if( this.id == 0 ) { this.sState = true; }
	}
	
	public int getId() { return id; }
	
	public boolean isFinalState() { return fState; }
	public void setFinalState( boolean finalState ) {
		this.fState = finalState;
	}
	public boolean isAcceptState() { return aState; }
	public void setAcceptState( boolean acceptState ) {
		this.aState = acceptState;
	}
	
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	
	@Override
	public boolean equals(Object other){
	    if (other == null) return false;
	    if (other == this) return true;
	    if (!(other instanceof State))return false;
	    State otherState = (State)other;
	    if( this.id == otherState.id ) return true;
	    return false;
	}
}
