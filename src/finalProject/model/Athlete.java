/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.Map;
import java.util.HashMap;

import finalProject.model.enums.Medals;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.MedalException;
import finalProject.model.interfaces.Athleteable;

@SuppressWarnings("serial")
public abstract class Athlete extends Person implements Athleteable {
	protected transient Map<Medals, Integer> allMedals;

	public Athlete(String name, String country) throws CountryException {
		super(name, country);
		setAllMedals();
	}

	public Map<Medals, Integer> getAllMedals() {
		return allMedals;
	}

	public void setAllMedals() {
		allMedals = new HashMap<Medals, Integer>();
		Medals[] medals = Medals.values();

		for (Medals medal : medals)
			allMedals.put(medal, 0);
	}

	@Override
	public void addMedal(Medals medalType) throws MedalException {
		Medals[] medals = Medals.values();

		for (Medals medal : medals) {
			if (medal.equals(medalType)) {
				allMedals.put(medalType, allMedals.get(medalType) + 1);
				return;
			}
		}

		throw new MedalException();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Athlete))
			return false;

		if (!(super.equals(other)))
			return false;

		Athlete temp = (Athlete) other;

		return allMedals.equals(temp.allMedals);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());

		for (Map.Entry<Medals, Integer> medal : allMedals.entrySet())
			sb.append("\n" + medal.getKey() + ": " + medal.getValue());

		return sb.toString();
	}

}
