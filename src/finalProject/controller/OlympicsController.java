/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.controller;

import java.io.IOException;

import finalProject.listeners.OlympicsEventsListener;
import finalProject.listeners.OlympicsUIEventsListener;
import finalProject.model.HybridAthlete;
import finalProject.model.Jumper;
import finalProject.model.Referee;
import finalProject.model.Runner;
import finalProject.model.Stadium;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.ExistObjectException;
import finalProject.model.exceptions.FieldException;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.exceptions.ObjectNotFoundException;
import finalProject.model.exceptions.OlympicsException;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.OlympicsModelable;
import finalProject.view.OlympicsViewable;

public class OlympicsController implements OlympicsEventsListener, OlympicsUIEventsListener {
	private OlympicsModelable olympicsModel;
	private OlympicsViewable olympicsView;

	public OlympicsController(OlympicsModelable theModel, OlympicsViewable theView) {
		olympicsModel = theModel;
		olympicsView = theView;

		olympicsModel.registerListener(this);
		olympicsView.registerListener(this);
	}

	@Override
	public void getStartDateModelEvent() {
		olympicsView.getStartDateViewEvent(olympicsModel.getStartDate());
	}

	@Override
	public void getEndDateModelEvent() {
		olympicsView.getEndDateViewEvent(olympicsModel.getEndDate());
	}

	@Override
	public void olympicsResultsModelEvent() {
		olympicsView.olympicsResultsViewEvent(olympicsModel);
	}

	@Override
	public void addAthleteModelEvent(Athleteable newAthlete) {
		olympicsView.addAthleteViewEvent(newAthlete);
	}

	@Override
	public void updateAthleteModelEvent(Athleteable oldAthlete, Athleteable newAthlete) {
		olympicsView.updateAthleteViewEvent(oldAthlete, newAthlete);
	}

	@Override
	public void deleteAthleteModelEvent(Athleteable athlete) {
		olympicsView.deleteAthleteViewEvent(athlete);
	}

	@Override
	public void addRefereeModelEvent(Referee newReferee) {
		olympicsView.addRefereeViewEvent(newReferee);
	}

	@Override
	public void updateRefereeModelEvent(Referee oldReferee, Referee newReferee) {
		olympicsView.updateRefereeViewEvent(oldReferee, newReferee);
	}

	@Override
	public void deleteRefereeModelEvent(Referee referee) {
		olympicsView.deleteRefereeViewEvent(referee);
	}

	@Override
	public void addStadiumModelEvent(Stadium newStadium) {
		olympicsView.addStadiumViewEvent(newStadium);
	}

	@Override
	public void updateStadiumModelEvent(Stadium oldStadium, Stadium newStadium) {
		olympicsView.updateStadiumViewEvent(oldStadium, newStadium);
	}

	@Override
	public void deleteStadiumModelEvent(Stadium stadium) {
		olympicsView.deleteStadiumViewEvent(stadium);
	}

	@Override
	public void showAllAthletesViewEvent() {
		for (Athleteable athlete : olympicsModel.getAllAthletes())
			olympicsView.addAthleteViewEvent(athlete);
	}

	@Override
	public void showAllRefereesViewEvent() {
		for (Referee referee : olympicsModel.getAllReferees())
			olympicsView.addRefereeViewEvent(referee);
	}

	@Override
	public void showAllStadiumsViewEvent() {
		for (Stadium stadium : olympicsModel.getAllStadiums())
			olympicsView.addStadiumViewEvent(stadium);
	}

	@Override
	public void addAthleteViewEvent(String name, String nationalTeam, boolean isRunner, boolean isjumper,
			float maxSpeed, float maxJump) {
		try {
			if (isRunner && isjumper)
				olympicsModel.addAthlete(new HybridAthlete(name, nationalTeam, maxSpeed, maxJump));
			else if (isRunner)
				olympicsModel.addAthlete(new Runner(name, nationalTeam, maxSpeed));
			else
				olympicsModel.addAthlete(new Jumper(name, nationalTeam, maxJump));
		} catch (ClassNotFoundException | ExistObjectException | IOException | CountryException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void updateAthleteViewEvent(Athleteable oldAthlete, String name, String nationalTeam, boolean isRunner,
			boolean isjumper, float maxSpeed, float maxJump) {
		try {
			if (isRunner && isjumper)
				olympicsModel.updateAthlete(oldAthlete, (new HybridAthlete(name, nationalTeam, maxSpeed, maxJump)));
			else if (isRunner)
				olympicsModel.updateAthlete(oldAthlete, (new Runner(name, nationalTeam, maxSpeed)));
			else
				olympicsModel.updateAthlete(oldAthlete, (new Jumper(name, nationalTeam, maxJump)));
		} catch (CountryException | ObjectNotFoundException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void deleteAthleteViewEvent(Athleteable athlete) {
		try {
			olympicsModel.deleteAthlete(athlete);
		} catch (ObjectNotFoundException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void addRefereeViewEvent(String name, String country, String field) {
		try {
			olympicsModel.addReferee(new Referee(name, country, field));
		} catch (ExistObjectException | IOException | FieldException | CountryException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void updateRefereeViewEvent(Referee oldReferee, String newName, String newCountry, String newField) {
		try {
			olympicsModel.updateReferee(oldReferee, new Referee(newName, newCountry, newField));
		} catch (ObjectNotFoundException | IOException | FieldException | CountryException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void deleteRefereeViewEvent(Referee referee) {
		try {
			olympicsModel.deleteReferee(referee);
		} catch (ObjectNotFoundException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}

	}

	@Override
	public void addStadiumViewEvent(String name, String location, int capacity) {
		try {
			olympicsModel.addStadium(new Stadium(name, location, capacity));
		} catch (ExistObjectException | CountryException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void updateStadiumViewEvent(Stadium oldStadium, String newName, String newLocation, int newCapacity) {
		try {
			olympicsModel.updateStadium(oldStadium, new Stadium(newName, newLocation, newCapacity));
		} catch (CountryException | ObjectNotFoundException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void deleteStadiumViewEvent(Stadium stadium) {
		try {
			olympicsModel.deleteStadium(stadium);
		} catch (ObjectNotFoundException | IOException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

	@Override
	public void makeOlympicsViewEvent(int numRunningIndividualTournaments, int numJumpingIndividualTournaments,
			int numRunningTeamTournaments, int numJumpingTeamTournaments) {
		try {
			olympicsModel.makeOlymipcs(numRunningIndividualTournaments, numJumpingIndividualTournaments,
					numRunningTeamTournaments, numJumpingTeamTournaments);
		} catch (ClassNotFoundException | IOException | ExistObjectException | CountryException | OlympicsException
				| TournamentParticipantsException | MedalException e) {
			olympicsView.eceptionMessage(e.getMessage());
		}
	}

}
