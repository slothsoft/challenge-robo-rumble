package de.slothsoft.roborumble;

import java.util.List;

import de.slothsoft.challenger.core.Contributions;
import de.slothsoft.roborumble.contrib.ExampleRobot;

/**
 * Util class for getting and managing {@link Robot}s (hence the name)
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public final class Robots {

	/**
	 * Returns all instances of {@link Robot}s in the <code>contrib</code> package.
	 *
	 * @return a list of robots
	 */

	public static List<Robot> getRobots() {
		return Contributions.fetchImplementations(ExampleRobot.class.getPackage(), Robot.class);
	}

	private Robots() {
		// hide me
	}
}
