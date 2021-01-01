/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class OlympicsException extends Exception {

	public OlympicsException(String objectName) {
		super("There is no " + objectName + " in the olyimpcs");
	}

}
