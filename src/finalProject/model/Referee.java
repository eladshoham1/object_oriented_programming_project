/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import finalProject.model.enums.Fields;
import finalProject.model.exceptions.CountryException;
import finalProject.model.exceptions.FieldException;

@SuppressWarnings("serial")
public class Referee extends Person {
	private String field;

	public Referee(String name, String country, String field) throws FieldException, CountryException {
		super(name, country);
		setField(field);
	}

	public String getField() {
		return field;
	}

	public void setField(String field) throws FieldException {
		Fields[] allFields = Fields.values();

		for (Fields tempField : allFields) {
			if (field.equals(tempField.toString())) {
				this.field = field;
				return;
			}
		}

		throw new FieldException();
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Referee))
			return false;

		if (!(super.equals(other)))
			return false;

		Referee temp = (Referee) other;

		return field.equals(temp.field);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nField: " + field);

		return sb.toString();
	}

}
