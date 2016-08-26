package automatons;

public class State {
	private String id;
	private String strId;
	private boolean fState; // final state
	private boolean sState; // start state
	private boolean aState; // accept state
	
	public State( String id ) {
		this(id, false);
	}
	
	public State( String id, boolean isFinal ) {
		this.id = id;
		this.fState = isFinal;
		if( Integer.parseInt(this.id) == 0 ) { this.sState = true; }
	}
	
	public String getId() { return id; }
	
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
		return "q_" + String.valueOf(getId());
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
