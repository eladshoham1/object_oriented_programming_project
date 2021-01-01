/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.Comparator;

import finalProject.model.NationalTeam;

public class CompareNationalTeamBySpeed implements Comparator<NationalTeam> {

	@Override
	public int compare(NationalTeam nationalTeam1, NationalTeam nationalTeam2) {
		float averageSpeed1 = nationalTeam1.getAverageSpeed();
		float averageSpeed2 = nationalTeam2.getAverageSpeed();

		if (averageSpeed1 < averageSpeed2)
			return 1;
		else if (averageSpeed1 > averageSpeed2)
			return -1;

		return 0;
	}

}
