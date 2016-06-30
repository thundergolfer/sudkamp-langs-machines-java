package grammars;

import java.util.ArrayList;
import java.util.List;

/**
 * Class represents a SENTENTIAL FORM. 
 * A Sentential Form is a collection of terminal and variable symbols from a grammar,
 * and a Sentential Form is in a certain grammar if there is a way to derive that form
 * from the start symbol (S) by the application of a finite number of rules.
 * @author Jonathon
 *
 */
public class SentForm extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SentForm(List<String> asList) {
		super(asList);
	}

	public SentForm() {
		super();
	}
	
}
