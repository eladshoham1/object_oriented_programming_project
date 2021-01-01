/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.listeners;

import finalProject.model.Referee;
import finalProject.model.Stadium;
import finalProject.model.interfaces.Athleteable;

public interface OlympicsEventsListener {

	void getStartDateModelEvent();

	void getEndDateModelEvent();

	void addAthleteModelEvent(Athleteable newAthlete);

	void updateAthleteModelEvent(Athleteable oldAthlete, Athleteable newAthlete);

	void deleteAthleteModelEvent(Athleteable athlete);

	void addRefereeModelEvent(Referee newReferee);

	void updateRefereeModelEvent(Referee oldReferee, Referee newReferee);

	void deleteRefereeModelEvent(Referee referee);

	void addStadiumModelEvent(Stadium newStadium);

	void updateStadiumModelEvent(Stadium oldStadium, Stadium newStadium);

	void deleteStadiumModelEvent(Stadium stadium);

	void olympicsResultsModelEvent();

}
