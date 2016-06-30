package automatons;

public class State {
	private boolean fState = false; // final state
	private boolean sState = false; // start state
	private boolean aState = false; // accept state
	
	public boolean isFinalState() { return fState; }
	public void setFinalState( boolean finalState ) {
		this.fState = finalState;
	}
	public boolean isAcceptState() { return aState; }
	public void setAcceptState( boolean acceptState ) {
		this.aState = acceptState;
	}
}
