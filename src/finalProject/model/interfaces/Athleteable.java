/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.interfaces;

import java.util.Map;

import finalProject.model.enums.Medals;
import finalProject.model.exceptions.MedalException;

public interface Athleteable {

	String getName();

	String getCountry();

	Map<Medals, Integer> getAllMedals();

	void setAllMedals();

	void addMedal(Medals medalType) throws MedalException;

}
