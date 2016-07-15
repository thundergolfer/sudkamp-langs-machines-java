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
}
