/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.listeners;

import finalProject.model.Referee;
import finalProject.model.Stadium;
import finalProject.model.interfaces.Athleteable;

public interface OlympicsUIEventsListener {

	void showAllAthletesViewEvent();

	void showAllRefereesViewEvent();

	void showAllStadiumsViewEvent();

	void addAthleteViewEvent(String name, String nationalTeam, boolean isRunner, boolean isjumper, float maxSpeed,
			float maxJump);

	void updateAthleteViewEvent(Athleteable oldAthlete, String newName, String newNationalTeam, boolean isRunner,
			boolean isjumper, float maxSpeed, float maxJump);

	void deleteAthleteViewEvent(Athleteable athlete);

	void addRefereeViewEvent(String name, String country, String field);

	void updateRefereeViewEvent(Referee oldReferee, String newName, String newCountry, String newField);

	void deleteRefereeViewEvent(Referee referee);

	void addStadiumViewEvent(String name, String location, int capacity);

	void updateStadiumViewEvent(Stadium oldStadium, String newName, String newLocation, int newCapacity);

	void deleteStadiumViewEvent(Stadium stadium);

	void makeOlympicsViewEvent(int numRunningIndividualTournaments, int numJumpingIndividualTournaments,
			int numRunningTeamTournaments, int numJumpingTeamTournaments);

}
