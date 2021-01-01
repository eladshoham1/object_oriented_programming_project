/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.Comparator;

import finalProject.model.NationalTeam;

public class CompareNationalTeamByJump implements Comparator<NationalTeam> {

	@Override
	public int compare(NationalTeam nationalTeam1, NationalTeam nationalTeam2) {
		float averageJump1 = nationalTeam1.getAverageJump();
		float averageJump2 = nationalTeam2.getAverageJump();

		if (averageJump1 < averageJump2)
			return 1;
		else if (averageJump1 > averageJump2)
			return -1;

		return 0;
	}

}
