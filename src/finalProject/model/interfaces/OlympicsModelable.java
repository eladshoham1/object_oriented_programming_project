/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import finalProject.controller.OlympicsController;
import finalProject.model.NationalTeam;
import finalProject.model.Referee;
import finalProject.model.Stadium;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.ExistObjectException;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.exceptions.ObjectNotFoundException;
import finalProject.model.exceptions.OlympicsException;

public interface OlympicsModelable {

	void registerListener(OlympicsController newListener);

	LocalDateTime getStartDate();

	LocalDateTime getEndDate();

	ArrayList<NationalTeam> getAllNationalTeams();

	ArrayList<Athleteable> getAllAthletes();

	ArrayList<Referee> getAllReferees();

	int getNumOfReferees();

	ArrayList<Stadium> getAllStadiums();

	int getNumOfStadiums();

	int getNumOfTournaments();

	int getNumOfAthletes();

	void addAthlete(Athleteable newAthlete)
			throws ExistObjectException, IOException, ClassNotFoundException, CountryException;

	void updateAthlete(Athleteable oldAthlete, Athleteable newAthlete) throws ObjectNotFoundException, IOException;

	void deleteAthlete(Athleteable athlete) throws ObjectNotFoundException, IOException;

	void addReferee(Referee newReferee) throws ExistObjectException, IOException;

	void updateReferee(Referee oldReferee, Referee newReferee) throws ObjectNotFoundException, IOException;

	void deleteReferee(Referee referee) throws ObjectNotFoundException, IOException;

	void addStadium(Stadium newStadium) throws ExistObjectException, IOException;

	void updateStadium(Stadium oldStadium, Stadium newStadium) throws ObjectNotFoundException, IOException;

	void deleteStadium(Stadium stadium) throws ObjectNotFoundException, IOException;

	void makeOlymipcs(int numRunningIndividualTournaments, int numJumpingIndividualTournaments,
			int numRunningTeamTournaments, int numJumpingTeamTournaments)
			throws FileNotFoundException, ClassNotFoundException, IOException, ExistObjectException, CountryException,
			OlympicsException, TournamentParticipantsException, MedalException;

}
