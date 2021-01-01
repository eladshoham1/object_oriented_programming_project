/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import finalProject.model.enums.Fields;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.interfaces.Athleteable;

public abstract class Tournament<T extends Athleteable> {
	protected Referee Referee;
	protected Stadium stadium;

	public Tournament(Referee Referee, Stadium stadium) {
		this.Referee = Referee;
		this.stadium = stadium;
	}

	public Referee getReferee() {
		return Referee;
	}

	public void setReferee(Referee Referee) {
		this.Referee = Referee;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	public abstract void makeTournament(Fields type) throws TournamentParticipantsException, MedalException;

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Tournament))
			return false;

		Tournament<?> temp = (Tournament<?>) other;

		return Referee.equals(temp.Referee) && stadium.equals(temp.stadium);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Tournament details: \n");
		sb.append(Referee.toString());
		sb.append("\n" + stadium.toString());

		return sb.toString();
	}

}