/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.Random;

import finalProject.model.exceptions.CountryException;
import finalProject.model.interfaces.Jumpable;
import finalProject.model.interfaces.Runable;

@SuppressWarnings("serial")
public class HybridAthlete extends Athlete implements Runable, Jumpable {
	private float maxSpeed;
	private transient float currentSpeed;
	private float maxJumpHeight;
	private transient float currentJumpHeight;

	public HybridAthlete(String name, String nationalTeam, float maxSpeed, float maxJumpHeight)
			throws CountryException {
		super(name, nationalTeam);

		setMaxSpeed(maxSpeed);
		setMaxJumpHeight(maxJumpHeight);
	}

	public float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(float maxSpeed) {
		this.maxSpeed = Math.abs(maxSpeed);
	}

	public float getCurrentSpeed() {
		return currentSpeed;
	}

	public void setCurrentSpeed(float currentSpeed) {
		this.currentSpeed = Math.abs(currentSpeed);
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
	public void run() {
		Random rnd = new Random();
		setCurrentSpeed(rnd.nextFloat() * maxSpeed);
	}

	@Override
	public void jump() {
		Random rnd = new Random();
		setCurrentJumpHeight(rnd.nextFloat() * maxJumpHeight);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof HybridAthlete))
			return false;

		if (!(super.equals(other)))
			return false;

		HybridAthlete temp = (HybridAthlete) other;

		return maxSpeed == temp.maxSpeed && currentSpeed == temp.currentSpeed && maxJumpHeight == temp.maxJumpHeight
				&& currentJumpHeight == temp.currentJumpHeight;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nMax speed: " + maxSpeed);
		sb.append("\nCurrent speed: " + currentSpeed);
		sb.append("\nMax jump height: " + maxJumpHeight);
		sb.append("\nCurrent jump height: " + currentJumpHeight);

		return sb.toString();
	}

}
