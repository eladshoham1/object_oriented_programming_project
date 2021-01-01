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
public abstract class Person implements Serializable {
	protected String name;
	protected String country;

	public Person(String name, String country) throws CountryException {
		setName(name);
		setCountry(country);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) throws CountryException {
		Countries[] allCountries = Countries.values();

		for (Countries tempCountry : allCountries) {
			if (country.equals(tempCountry.toString())) {
				this.country = country;
				return;
			}

		}

		throw new CountryException(country);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Person))
			return false;

		Person temp = (Person) other;

		return name.equals(temp.name) && country.equals(temp.country);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name);
		sb.append("\nCountry: " + country);

		return sb.toString();
	}
}
