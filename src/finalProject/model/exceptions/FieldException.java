/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class FieldException extends Exception {

	public FieldException() {
		super("The field can only be Running or Jumping!");
	}

}
