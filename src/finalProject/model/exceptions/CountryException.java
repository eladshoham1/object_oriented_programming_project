/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class CountryException extends Exception {

	public CountryException(String country) {
		super(country + " is not exist in the countries list!");
	}

}
