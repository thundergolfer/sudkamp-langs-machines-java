package turingMachines;

import automatons.State;

public class TwoTapeConfiguration<T> implements Configuration {
	
	// The entire configuration can be encoded in O(s(n)) space
	// From the textbook, we need:
	State currMachState; // encoding of the state of the machine
	int readTapePos; // location of the tape head on the read-only tape
	int workTapePos; // location of the tape head on the work tape
	Tape<T> workTape; // first s(n) squares of work tape
	
}
