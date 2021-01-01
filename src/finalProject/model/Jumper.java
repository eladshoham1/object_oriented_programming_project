/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.Random;

import finalProject.model.exceptions.CountryException;
import finalProject.model.interfaces.Jumpable;

@SuppressWarnings("serial")
public class Jumper extends Athlete implements Jumpable {
	private float maxJumpHeight;
	private transient float currentJumpHeight;

	public Jumper(String name, String nationalTeam, float maxJumpHeight) throws CountryException {
		super(name, nationalTeam);

		setMaxJumpHeight(maxJumpHeight);
	}

	public float getMaxJumpHeight() {
		return maxJumpHeight;
	}

	public void setMaxJumpHeight(float maxJumpHeight) {
		this.maxJumpHeight = Math.abs(maxJumpHeight);
	}

	public float getCurrentJumpHeight() {
		return currentJumpHeight;
	}

	public void setCurrentJumpHeight(float currentJumpHeight) {
		this.currentJumpHeight = Math.abs(currentJumpHeight);
	}

	@Override
	public void jump() {
		Random rnd = new Random();
		setCurrentJumpHeight(rnd.nextFloat() * maxJumpHeight);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Jumper))
			return false;

		if (!(super.equals(other)))
			return false;

		Jumper temp = (Jumper) other;

		return maxJumpHeight == temp.maxJumpHeight && currentJumpHeight == temp.currentJumpHeight;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nMax jump height: " + maxJumpHeight);
		sb.append("\nCurrent jump height: " + currentJumpHeight);

		return sb.toString();
	}

}
