/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.ArrayList;

import finalProject.model.comparators.BubbleSort;
import finalProject.model.comparators.CompareNationalTeamByJump;
import finalProject.model.comparators.CompareNationalTeamBySpeed;
import finalProject.model.enums.Fields;
import finalProject.model.enums.Medals;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.Runable;

public class TeamTournament<T extends Athleteable> extends Tournament<T> {
	private ArrayList<NationalTeam> allNationalTeams;

	public TeamTournament(Referee judge, Stadium stadium) {
		super(judge, stadium);

		allNationalTeams = new ArrayList<NationalTeam>();
	}

	public ArrayList<NationalTeam> getAllNationalTeams() {
		return allNationalTeams;
	}

	public void addNationalTeam(NationalTeam newNationalTeam) {
		allNationalTeams.add(newNationalTeam);
	}

	@Override
	public void makeTournament(Fields type) throws TournamentParticipantsException, MedalException {
		ArrayList<NationalTeam> nationalTeamsRank = new ArrayList<NationalTeam>();
		Medals[] medals = Medals.values();
		boolean runnersTeam = type.toString().equals("Running");

		for (NationalTeam nationalTeam : allNationalTeams) {
			if (runnersTeam && nationalTeam.getAllRunners().size() > 0) {
				for (Runable runner : nationalTeam.getAllRunners())
					runner.run();

				nationalTeamsRank.add(nationalTeam);
			} else if (!runnersTeam && nationalTeam.getAllJumpers().size() > 0) {
				for (Jumpable runner : nationalTeam.getAllJumpers())
					runner.jump();

				nationalTeamsRank.add(nationalTeam);
			}
		}

		if (nationalTeamsRank.size() < 3)
			throw new TournamentParticipantsException(nationalTeamsRank.size());

		if (runnersTeam) {
			BubbleSort.bubbleSort(nationalTeamsRank, new CompareNationalTeamBySpeed());

			for (int i = 0; i < medals.length; i++) {
				for (Runable runner : nationalTeamsRank.get(i).getAllRunners())
					runner.addMedal(medals[i]);
			}
		} else {
			BubbleSort.bubbleSort(nationalTeamsRank, new CompareNationalTeamByJump());

			for (int i = 0; i < medals.length; i++) {
				for (Jumpable jumper : nationalTeamsRank.get(i).getAllJumpers())
					jumper.addMedal(medals[i]);
			}
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if (!(other instanceof TeamTournament<?>))
			return false;

		if (!super.equals(other))
			return false;

		TeamTournament<T> temp = (TeamTournament<T>) other;

		return allNationalTeams.equals(temp.allNationalTeams);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());

		for (NationalTeam nationalTeam : allNationalTeams)
			sb.append("\n" + nationalTeam);

		return sb.toString();
	}

}
