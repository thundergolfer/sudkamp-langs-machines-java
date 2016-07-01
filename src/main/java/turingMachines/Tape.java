package turingMachines;

public interface Tape<T> {

	public T getCurrentSymbol();
	
	public boolean moveTape( Direction d );
}
