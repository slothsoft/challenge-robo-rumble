package de.slothsoft.roborumble;

import de.slothsoft.roborumble.gui.RobotRenderer;

/**
 * A little robot that moves around on the {@link Map} and tries to survive.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public interface Robot {

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	default String getAuthor() {
		return "unknown";
	}

	default RobotRenderer createRenderer() {
		return RobotRenderer.createDefaultRenderer();
	}

	default Stats createStats() {
		return new Stats();
	}

	void execute(Context context);

	interface Context {

		boolean requestMove(Direction direction);

		void rotate(Direction direction);

		boolean requestShot();

	}
}
