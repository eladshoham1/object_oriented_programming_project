/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.view;

import java.time.LocalDateTime;

import finalProject.controller.OlympicsController;
import finalProject.model.Referee;
import finalProject.model.Stadium;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.OlympicsModelable;

public interface OlympicsViewable {

	void registerListener(OlympicsController newListener);

	void getStartDateViewEvent(LocalDateTime startDate);

	void getEndDateViewEvent(LocalDateTime endDate);

	void addAthleteViewEvent(Athleteable newAthlete);

	void updateAthleteViewEvent(Athleteable oldAthlete, Athleteable newAthlete);

	void deleteAthleteViewEvent(Athleteable athlete);

	void addRefereeViewEvent(Referee newReferee);

	void updateRefereeViewEvent(Referee oldReferee, Referee newReferee);

	void deleteRefereeViewEvent(Referee referee);

	void addStadiumViewEvent(Stadium newStadium);

	void updateStadiumViewEvent(Stadium oldStadium, Stadium newStadium);

	void deleteStadiumViewEvent(Stadium stadium);

	void olympicsResultsViewEvent(OlympicsModelable olympicsResult);

	void eceptionMessage(String msg);

}
