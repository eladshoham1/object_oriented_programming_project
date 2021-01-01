/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.io.Serializable;

import finalProject.model.enums.Countries;
import finalProject.model.exceptions.CountryException;

@SuppressWarnings("serial")
public class Stadium implements Serializable {
	private String name;
	private String location;
	private int capacity;

	public Stadium(String name, String location, int capacity) throws CountryException {
		setName(name);
		setLocation(location);
		setCapacity(capacity);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) throws CountryException {
		Countries[] allCountries = Countries.values();

		for (Countries country : allCountries) {
			if (location.equals(country.toString())) {
				this.location = location;
				return;
			}

		}

		throw new CountryException(location);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = Math.abs(capacity);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Stadium))
			return false;

		Stadium temp = (Stadium) other;

		return name.equals(temp.name) && location.equals(temp.location) && capacity == temp.capacity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + " Stadium, " + location);
		sb.append("\nCapacity: " + capacity + "\n");

		return sb.toString();
	}

}
