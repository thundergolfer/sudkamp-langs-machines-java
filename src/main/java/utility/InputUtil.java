package utility;

import java.util.ArrayList;
import java.util.List;

public class InputUtil {

	public static List<String> wordToList( String word ) {
		List<String> charList = new ArrayList<String>();

		for (int i=0; i < word.length(); ++i) {
			charList.add( String.valueOf(word.charAt(i)));
		}
		return charList;
	}
}
