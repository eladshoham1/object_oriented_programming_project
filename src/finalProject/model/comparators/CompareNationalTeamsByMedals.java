/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.comparators;

import java.util.Comparator;
import java.util.Map;

import finalProject.model.NationalTeam;
import finalProject.model.enums.Medals;

public class CompareNationalTeamsByMedals implements Comparator<NationalTeam> {

	@Override
	public int compare(NationalTeam nationalTeam1, NationalTeam nationalTeam2) {
		Map<Medals, Integer> allMedalsNationalTeam1 = nationalTeam1.getAllMedals();
		Map<Medals, Integer> allMedalsNationalTeam2 = nationalTeam2.getAllMedals();

		if (allMedalsNationalTeam1.get(Medals.Gold) < allMedalsNationalTeam2.get(Medals.Gold))
			return 1;
		else if (allMedalsNationalTeam1.get(Medals.Gold) == allMedalsNationalTeam2.get(Medals.Gold)) {
			if (allMedalsNationalTeam1.get(Medals.Silver) < allMedalsNationalTeam2.get(Medals.Silver))
				return 1;
			else if (allMedalsNationalTeam1.get(Medals.Silver) == allMedalsNationalTeam2.get(Medals.Silver)) {
				if (allMedalsNationalTeam1.get(Medals.Bronze) <= allMedalsNationalTeam2.get(Medals.Bronze))
					return 1;
				else
					return -1;
			} else
				return -1;
		} else
			return -1;
	}

}
