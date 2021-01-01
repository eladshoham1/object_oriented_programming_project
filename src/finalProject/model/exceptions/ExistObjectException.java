/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class ExistObjectException extends Exception {

	public ExistObjectException(Object object) {
		super("The " + object.getClass().getSimpleName() + " already exists!");
	}

}