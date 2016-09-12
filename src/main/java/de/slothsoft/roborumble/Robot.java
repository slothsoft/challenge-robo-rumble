package de.slothsoft.roborumble;

import de.slothsoft.roborumble.gui.RobotRenderer;

public interface Robot {

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	default RobotRenderer createRenderer() {
		return RobotRenderer.createDefaultRenderer();
	}

	void execute(Context context);

	interface Context {

		boolean requestMove(Direction direction);

		void rotate(Direction direction);

		boolean requestShot();

	}
}
