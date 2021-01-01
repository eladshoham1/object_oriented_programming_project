/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class ObjectNotFoundException extends Exception {

	public ObjectNotFoundException(Object object) {
		super(object.getClass().getSimpleName() + " not found");
	}

}
