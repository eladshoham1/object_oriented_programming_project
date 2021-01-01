/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.Comparator;

import finalProject.model.interfaces.Runable;

public class CompareAthleteBySpeed implements Comparator<Runable> {

	@Override
	public int compare(Runable runner1, Runable runner2) {
		float currentSpeed1 = runner1.getCurrentSpeed();
		float currentSpeed2 = runner2.getCurrentSpeed();

		if (currentSpeed1 < currentSpeed2)
			return 1;
		else if (currentSpeed1 > currentSpeed2)
			return -1;

		return 0;
	}

}