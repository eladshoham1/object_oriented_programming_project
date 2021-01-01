/*
	Names:
	Elad Shoham - 206001752
	Oz Amzaleg - 313554958
*/

package finalProject.model;

import java.util.Random;

import finalProject.model.exceptions.CountryException;
import finalProject.model.interfaces.Runable;

@SuppressWarnings("serial")
public class Runner extends Athlete implements Runable {
	private float maxSpeed;
	private transient float currentSpeed;

	public Runner(String name, String nationalTeam, float maxSpeed) throws CountryException {
		super(name, nationalTeam);

		setMaxSpeed(maxSpeed);
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

	@Override
	public void run() {
		Random rnd = new Random();
		setCurrentSpeed(rnd.nextFloat() * maxSpeed);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Runner))
			return false;

		if (!(super.equals(other)))
			return false;

		Runner temp = (Runner) other;

		return maxSpeed == temp.maxSpeed && currentSpeed == temp.currentSpeed;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("\nMax speed: " + maxSpeed);
		sb.append("\nCurrent speed: " + currentSpeed);

		return sb.toString();
	}

}
