/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.ArrayList;

import finalProject.model.comparators.BubbleSort;
import finalProject.model.comparators.CompareAthleteByJump;
import finalProject.model.comparators.CompareAthleteBySpeed;
import finalProject.model.enums.Fields;
import finalProject.model.enums.Medals;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.Runable;

public class IndividualTournament<T extends Athleteable> extends Tournament<T> {
	private ArrayList<T> allAthletes;

	public IndividualTournament(Referee judge, Stadium stadium) {
		super(judge, stadium);

		this.allAthletes = new ArrayList<T>();
	}

	public ArrayList<T> getAllAthletes() {
		return allAthletes;
	}

	public void addAthlete(T newAthlete) {
		allAthletes.add(newAthlete);
	}

	@Override
	public void makeTournament(Fields type) throws TournamentParticipantsException, MedalException {
		if (allAthletes.size() < 3)
			throw new TournamentParticipantsException(allAthletes.size());

		boolean isRunner = type.toString().equals("Running");

		for (T athlete : allAthletes) {
			if (isRunner)
				((Runable) athlete).run();
			else
				((Jumpable) athlete).jump();
		}

		if (isRunner)
			BubbleSort.bubbleSort(allAthletes, new CompareAthleteBySpeed());
		else
			BubbleSort.bubbleSort(allAthletes, new CompareAthleteByJump());

		Medals[] medals = Medals.values();
		for (int i = 0; i < medals.length; i++)
			allAthletes.get(i).addMedal(medals[i]);
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean equals(Object other) {
		if (!(other instanceof IndividualTournament<?>))
			return false;

		if (!super.equals(other))
			return false;

		IndividualTournament<T> temp = (IndividualTournament<T>) other;

		return allAthletes.equals(temp.allAthletes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());

		for (int i = 0; i < allAthletes.size(); i++)
			sb.append("\n" + allAthletes.get(0).toString());

		return sb.toString();
	}

}
