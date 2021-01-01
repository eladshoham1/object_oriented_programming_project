/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import finalProject.controller.OlympicsController;
import finalProject.listeners.OlympicsEventsListener;
import finalProject.model.comparators.BubbleSort;
import finalProject.model.comparators.CompareNationalTeamsByMedals;
import finalProject.model.enums.Countries;
import finalProject.model.enums.Fields;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.ExistObjectException;
import finalProject.model.exceptions.MedalException;
import finalProject.model.exceptions.TournamentParticipantsException;
import finalProject.model.exceptions.ObjectNotFoundException;
import finalProject.model.exceptions.OlympicsException;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.OlympicsModelable;
import finalProject.model.interfaces.Runable;

public class Olympics implements OlympicsModelable {
	private final int DAYS_TIME = 16;

	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private ArrayList<Referee> allReferees;
	private ArrayList<Stadium> allStadiums;
	private ArrayList<NationalTeam> allNationalTeams;
	private ArrayList<Tournament<Athleteable>> allTournaments;

	private ArrayList<OlympicsEventsListener> allListeners;

	public Olympics() throws ClassNotFoundException, IOException, CountryException {
		setStartDate();
		setEndDate();
		allReferees = new ArrayList<Referee>();
		allTournaments = new ArrayList<Tournament<Athleteable>>();
		allStadiums = new ArrayList<Stadium>();

		readRefereesFromFiles();
		readStadiumsFromFiles();
		setAllNationalTeams();

		allListeners = new ArrayList<OlympicsEventsListener>();
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate() {
		this.startDate = LocalDateTime.now();
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate() {
		this.endDate = startDate.plusDays(DAYS_TIME);
	}

	public ArrayList<NationalTeam> getAllNationalTeams() {
		return allNationalTeams;
	}

	public int getNumOfNationalTeams() {
		return allNationalTeams.size();
	}

	public ArrayList<Referee> getAllReferees() {
		return allReferees;
	}

	public int getNumOfReferees() {
		return allReferees.size();
	}

	public ArrayList<Stadium> getAllStadiums() {
		return allStadiums;
	}

	public int getNumOfStadiums() {
		return allStadiums.size();
	}

	public ArrayList<Tournament<Athleteable>> getAllTournaments() {
		return allTournaments;
	}

	public int getNumOfTournaments() {
		return allTournaments.size();
	}

	public ArrayList<Athleteable> getAllAthletes() {
		ArrayList<Athleteable> allAthletes = new ArrayList<Athleteable>();
		for (NationalTeam team : allNationalTeams) {
			for (Athleteable athlete : team.getAllAthletes())
				allAthletes.add(athlete);
		}

		return allAthletes;
	}

	public int getNumOfAthletes() {
		return getAllAthletes().size();
	}

	public void addAthlete(Athleteable newAthlete) throws ExistObjectException, IOException {
		NationalTeam team = null;
		
		for (NationalTeam nationalTeam : allNationalTeams) {
			if (newAthlete.getCountry().equals(nationalTeam.getName()))
				team = nationalTeam;
		}
		
		if (team != null) {
			team.addAthlete(newAthlete);
			saveAthletesToFiles();
			fireAddAthleteEvent(newAthlete);
		}
	}

	public void updateAthlete(Athleteable oldAthlete, Athleteable newAthlete)
			throws ObjectNotFoundException, IOException {
		for (NationalTeam nationalTeam : allNationalTeams) {
			if (oldAthlete.getCountry().equals(nationalTeam.getName())) {
				nationalTeam.updateAthlete(oldAthlete, newAthlete);
				saveAthletesToFiles();
				fireUpdateAthleteEvent(oldAthlete, newAthlete);
				return;
			}
		}

		throw new ObjectNotFoundException(oldAthlete);
	}

	public void deleteAthlete(Athleteable athlete) throws ObjectNotFoundException, IOException {
		for (NationalTeam nationalTeam : allNationalTeams) {
			if (athlete.getCountry().equals(nationalTeam.getName())) {
				nationalTeam.deleteAthlete(athlete);
				saveAthletesToFiles();
				fireDeleteAthleteEvent(athlete);
				return;
			}
		}

		throw new ObjectNotFoundException(athlete);
	}

	public void addReferee(Referee newReferee) throws ExistObjectException, IOException {
		for (Referee referee : allReferees) {
			if (newReferee.equals(referee))
				throw new ExistObjectException("The referee already exists!");
		}

		allReferees.add(newReferee);
		saveRefereesToFiles();
		fireAddRefereeEvent(newReferee);
	}

	public void updateReferee(Referee oldReferee, Referee newReferee) throws ObjectNotFoundException, IOException {
		for (int i = 0; i < allReferees.size(); i++) {
			if (oldReferee.equals(allReferees.get(i))) {
				allReferees.set(i, newReferee);
				saveRefereesToFiles();
				fireUpdateRefereeEvent(oldReferee, newReferee);
				return;
			}
		}

		throw new ObjectNotFoundException(oldReferee);
	}

	public void deleteReferee(Referee referee) throws ObjectNotFoundException, IOException {
		if (allReferees.remove(referee)) {
			saveRefereesToFiles();
			fireDeleteRefereeEvent(referee);
			return;
		}

		throw new ObjectNotFoundException(referee);
	}

	public void addStadium(Stadium newStadium) throws ExistObjectException, IOException {
		for (Stadium stadium : allStadiums) {
			if (newStadium.equals(stadium))
				throw new ExistObjectException(newStadium);
		}

		allStadiums.add(newStadium);
		saveStadiumsToFiles();
		fireAddStadiumEvent(newStadium);
	}

	public void updateStadium(Stadium oldStadium, Stadium newStadium) throws ObjectNotFoundException, IOException {
		for (int i = 0; i < allStadiums.size(); i++) {
			if (oldStadium.equals(allStadiums.get(i))) {
				allStadiums.set(i, newStadium);
				saveStadiumsToFiles();
				fireUpdateStadiumEvent(oldStadium, newStadium);
				return;
			}
		}

		throw new ObjectNotFoundException(oldStadium);
	}

	public void deleteStadium(Stadium stadium) throws ObjectNotFoundException, IOException {
		if (allStadiums.remove(stadium)) {
			saveStadiumsToFiles();
			fireDeleteStadiumEvent(stadium);
			return;
		}

		throw new ObjectNotFoundException(stadium);
	}

	public void makeOlymipcs(int numRunningIndividualTournaments, int numJumpingIndividualTournaments,
			int numRunningTeamTournaments, int numJumpingTeamTournaments)
			throws FileNotFoundException, ClassNotFoundException, IOException, ExistObjectException, CountryException,
			OlympicsException, TournamentParticipantsException, MedalException {
		setStartDate();
		setEndDate();
		allTournaments = new ArrayList<Tournament<Athleteable>>();
		setAllNationalTeams();

		for (int i = 0; i < Math.abs(numRunningIndividualTournaments); i++)
			individualTournament(Fields.Running);

		for (int i = 0; i < Math.abs(numJumpingIndividualTournaments); i++)
			individualTournament(Fields.Jumping);

		for (int i = 0; i < Math.abs(numRunningTeamTournaments); i++)
			teamTournament(Fields.Running);

		for (int i = 0; i < Math.abs(numJumpingTeamTournaments); i++)
			teamTournament(Fields.Jumping);

		BubbleSort.bubbleSort(allNationalTeams, new CompareNationalTeamsByMedals());
		fireOlympicsResultsEvent();
	}

	@Override
	public void registerListener(OlympicsController newListener) {
		allListeners.add(newListener);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Olympics))
			return false;

		Olympics temp = (Olympics) other;

		return startDate.equals(temp.startDate) && endDate.equals(temp.endDate)
				&& allTournaments.equals(temp.allTournaments) && allReferees.equals(temp.allReferees)
				&& allStadiums.equals(temp.allStadiums) && allListeners.equals(temp.allListeners);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start: " + startDate);
		sb.append("\nEnd: " + endDate);

		sb.append("\nAll Tournaments: \n");
		for (int i = 0; i < allTournaments.size(); i++)
			sb.append(allTournaments.get(i).toString());

		sb.append("\nAll National Teams: \n");
		for (int i = 0; i < allNationalTeams.size(); i++)
			sb.append(allNationalTeams.get(i).toString());

		sb.append("\nAll Referees: \n");
		for (int i = 0; i < allReferees.size(); i++)
			sb.append(allReferees.get(i).toString());

		sb.append("\nAll Stadiums: \n");
		for (int i = 0; i < allStadiums.size(); i++)
			sb.append(allStadiums.get(i).toString());

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private void readRefereesFromFiles() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("Referees.txt");

		if (file.available() != 0) {
			ObjectInputStream inFile = new ObjectInputStream(file);
			allReferees = (ArrayList<Referee>) inFile.readObject();
			inFile.close();
		}
	}

	@SuppressWarnings("unchecked")
	private void readStadiumsFromFiles() throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream("Stadiums.txt");

		if (file.available() != 0) {
			ObjectInputStream inFile = new ObjectInputStream(file);
			allStadiums = (ArrayList<Stadium>) inFile.readObject();
			inFile.close();
		}
	}

	private void saveAthletesToFiles() throws IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Athletes.txt"));
		outFile.writeObject(getAllAthletes());
		outFile.close();
	}

	private void saveRefereesToFiles() throws IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Referees.txt"));
		outFile.writeObject(allReferees);
		outFile.close();
	}

	private void saveStadiumsToFiles() throws IOException {
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("Stadiums.txt"));
		outFile.writeObject(allStadiums);
		outFile.close();
	}

	private void addAllCountries() throws ClassNotFoundException, CountryException, IOException {
		Countries[] allCountries = Countries.values();

		for (Countries country : allCountries) {
			allNationalTeams.add(new NationalTeam(country.toString()));
		}
	}

	private void setAllNationalTeams()
			throws FileNotFoundException, ClassNotFoundException, IOException, CountryException {
		allNationalTeams = new ArrayList<NationalTeam>();
		addAllCountries();
	}

	private void individualTournament(Fields type)
			throws OlympicsException, TournamentParticipantsException, MedalException {
		if (allStadiums.size() == 0)
			throw new OlympicsException(Stadium.class.getSimpleName());

		Random rnd = new Random();
		Stadium stadium = allStadiums.get(rnd.nextInt(allStadiums.size()));
		Referee referee = getRefereeForTournament(type);

		IndividualTournament<Athleteable> tournament = new IndividualTournament<Athleteable>(referee, stadium);

		if (type.toString().equals("Running")) {
			for (Athleteable athlete : getAllAthletes()) {
				if (athlete instanceof Runable)
					tournament.addAthlete((Runable) athlete);
			}
		} else {
			for (Athleteable athlete : getAllAthletes()) {
				if (athlete instanceof Jumpable)
					tournament.addAthlete((Jumpable) athlete);
			}
		}

		tournament.makeTournament(type);
		allTournaments.add(tournament);
	}

	private void teamTournament(Fields type) throws OlympicsException, TournamentParticipantsException, MedalException {
		if (allStadiums.size() == 0)
			throw new OlympicsException(Stadium.class.getSimpleName());

		Random rnd = new Random();
		Stadium stadium = allStadiums.get(rnd.nextInt(allStadiums.size()));
		Referee referee = getRefereeForTournament(type);

		TeamTournament<Athleteable> tournament = new TeamTournament<Athleteable>(referee, stadium);

		for (NationalTeam nationalTeam : allNationalTeams)
			tournament.addNationalTeam(nationalTeam);

		tournament.makeTournament(type);

		allTournaments.add(tournament);
	}

	private Referee getRefereeForTournament(Fields type) throws OlympicsException {
		Random rnd = new Random();
		ArrayList<Referee> referees = new ArrayList<Referee>();

		for (Referee tempReferee : allReferees) {
			if (tempReferee.getField().equals(type.toString()))
				referees.add(tempReferee);
		}

		if (referees.size() > 0)
			return referees.get(rnd.nextInt(referees.size()));

		throw new OlympicsException(type + " " + Referee.class.getSimpleName());
	}

	private void fireAddAthleteEvent(Athleteable newAthlete) {
		for (OlympicsEventsListener l : allListeners)
			l.addAthleteModelEvent(newAthlete);
	}

	private void fireUpdateAthleteEvent(Athleteable oldAthlete, Athleteable newAthlete) {
		for (OlympicsEventsListener l : allListeners)
			l.updateAthleteModelEvent(oldAthlete, newAthlete);
	}

	private void fireDeleteAthleteEvent(Athleteable athlete) {
		for (OlympicsEventsListener l : allListeners)
			l.deleteAthleteModelEvent(athlete);
	}

	private void fireAddRefereeEvent(Referee newReferee) {
		for (OlympicsEventsListener l : allListeners)
			l.addRefereeModelEvent(newReferee);
	}

	private void fireUpdateRefereeEvent(Referee oldReferee, Referee newReferee) {
		for (OlympicsEventsListener l : allListeners)
			l.updateRefereeModelEvent(oldReferee, newReferee);
	}

	private void fireDeleteRefereeEvent(Referee referee) {
		for (OlympicsEventsListener l : allListeners)
			l.deleteRefereeModelEvent(referee);
	}

	private void fireAddStadiumEvent(Stadium newStadium) {
		for (OlympicsEventsListener l : allListeners)
			l.addStadiumModelEvent(newStadium);
	}

	private void fireUpdateStadiumEvent(Stadium oldStadium, Stadium newStadium) {
		for (OlympicsEventsListener l : allListeners)
			l.updateStadiumModelEvent(oldStadium, newStadium);
	}

	private void fireDeleteStadiumEvent(Stadium stadium) {
		for (OlympicsEventsListener l : allListeners)
			l.deleteStadiumModelEvent(stadium);
	}

	private void fireOlympicsResultsEvent() {
		for (OlympicsEventsListener l : allListeners)
			l.olympicsResultsModelEvent();
	}

}
