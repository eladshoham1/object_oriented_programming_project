/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.exceptions;

@SuppressWarnings("serial")
public class TournamentParticipantsException extends Exception {

	public TournamentParticipantsException(int numOfParticipants) {
		super("Tournament need at least 3 participants to begin, current participants: " + numOfParticipants);
	}

}
