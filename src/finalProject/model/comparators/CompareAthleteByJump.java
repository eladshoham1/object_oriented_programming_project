/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.Comparator;

import finalProject.model.interfaces.Jumpable;

public class CompareAthleteByJump implements Comparator<Jumpable> {

	@Override
	public int compare(Jumpable jumper1, Jumpable jumper2) {
		float currentJumpHeight1 = jumper1.getCurrentJumpHeight();
		float currentJumpHeight2 = jumper2.getCurrentJumpHeight();

		if (currentJumpHeight1 < currentJumpHeight2)
			return 1;
		else if (currentJumpHeight1 > currentJumpHeight2)
			return -1;

		return 0;
	}

}
