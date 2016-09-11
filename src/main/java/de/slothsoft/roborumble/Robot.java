package de.slothsoft.roborumble;

import de.slothsoft.roborumble.gui.RobotRenderer;

public interface Robot {

	default String getDisplayName() {
		return getClass().getSimpleName();
	}

	default RobotRenderer createRenderer() {
		return RobotRenderer.createDefaultRenderer();
	}

	void position(Context context);

	interface Context {

	}
}
