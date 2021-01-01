/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;

import finalProject.model.enums.Countries;
import finalProject.model.enums.Medals;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.ExistObjectException;
import finalProject.model.exceptions.ObjectNotFoundException;
import finalProject.model.interfaces.Athleteable;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.Runable;

import java.util.HashMap;

public class NationalTeam {
	private String name;
	private ArrayList<Athleteable> allAthletes;
	private Map<Medals, Integer> allMedals;

	public NationalTeam(String name) throws CountryException, ClassNotFoundException, IOException {
		setName(name);
		readAthleteablesFromFiles();
		setAllMedals();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws CountryException {
		Countries[] allCountries = Countries.values();

		for (Countries country : allCountries) {
			if (name.equals(country.toString())) {
				this.name = name;
				return;
			}

		}

		throw new CountryException(name);
	}

	public ArrayList<Athleteable> getAllAthletes() {
		return allAthletes;
	}

	public int getNumOfAthletes() {
		return allAthletes.size();
	}

	public Map<Medals, Integer> getAllMedals() {
		setAllMedals();

		for (Athleteable athlete : allAthletes) {
			for (Map.Entry<Medals, Integer> medal : athlete.getAllMedals().entrySet())
				allMedals.put(medal.getKey(), allMedals.get(medal.getKey()) + medal.getValue());
		}

		return allMedals;
	}

	public void setAllMedals() {
		allMedals = new HashMap<Medals, Integer>();

		Medals[] medals = Medals.values();
		for (int i = 0; i < medals.length; i++)
			allMedals.put(medals[i], 0);
	}

	public ArrayList<Runable> getAllRunners() {
		ArrayList<Runable> allRunners = new ArrayList<Runable>();

		for (Athleteable athlete : allAthletes) {
			if (athlete instanceof Runable)
				allRunners.add((Runable) athlete);
		}

		return allRunners;
	}

	public ArrayList<Jumpable> getAllJumpers() {
		ArrayList<Jumpable> allJumpers = new ArrayList<Jumpable>();

		for (Athleteable athlete : allAthletes) {
			if (athlete instanceof Jumpable)
				allJumpers.add((Jumpable) athlete);
		}

		return allJumpers;
	}

	public float getAverageSpeed() {
		ArrayList<Runable> allRunners = getAllRunners();

		if (allRunners.size() == 0)
			return 0;

		float sumSpeed = 0.0f;

		for (Runable runner : allRunners)
			sumSpeed += runner.getCurrentSpeed();

		return sumSpeed / allRunners.size();
	}

	public float getAverageJump() {
		ArrayList<Jumpable> allJumpers = getAllJumpers();

		if (allJumpers.size() == 0)
			return 0;

		float sumSpeed = 0.0f;

		for (Jumpable jumper : allJumpers)
			sumSpeed += jumper.getCurrentJumpHeight();

		return sumSpeed / allJumpers.size();
	}

	public void addAthlete(Athleteable newAthlete) throws ExistObjectException, IOException {
		for (Athleteable athlete : allAthletes) {
			if (newAthlete.equals(athlete))
				throw new ExistObjectException(newAthlete);
		}

		allAthletes.add(newAthlete);
	}

	public void updateAthlete(Athleteable oldAthlete, Athleteable newAthlete)
			throws ObjectNotFoundException, IOException {
		for (int i = 0; i < allAthletes.size(); i++) {
			if (oldAthlete.equals(allAthletes.get(i))) {
				allAthletes.set(i, newAthlete);
				return;
			}
		}

		throw new ObjectNotFoundException(oldAthlete);
	}

	public void deleteAthlete(Athleteable athlete) throws ObjectNotFoundException, IOException {
		if (!(allAthletes.remove(athlete)))
			throw new ObjectNotFoundException(athlete);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof NationalTeam))
			return false;

		NationalTeam temp = (NationalTeam) other;

		return name.equals(temp.name) && allAthletes.equals(temp.allAthletes);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + ":\n");

		sb.append("All Athletes: \n");
		for (Athleteable athlete : allAthletes)
			sb.append(athlete.toString());

		Medals[] medals = Medals.values();

		sb.append("All Medals: \n");
		for (int i = 0; i < medals.length; i++)
			sb.append("\n" + medals[i].toString() + ": " + getAllMedals().get(medals[i]));

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private void readAthleteablesFromFiles() throws ClassNotFoundException, IOException {
		allAthletes = new ArrayList<Athleteable>();
		FileInputStream file = new FileInputStream("Athletes.txt");

		if (file.available() != 0) {
			ObjectInputStream inFile = new ObjectInputStream(file);

			for (Athleteable athlete : (ArrayList<Athleteable>) inFile.readObject()) {
				if (name.equals(athlete.getCountry())) {
					athlete.setAllMedals();
					allAthletes.add(athlete);
				}
			}

			inFile.close();
		}
	}

}
