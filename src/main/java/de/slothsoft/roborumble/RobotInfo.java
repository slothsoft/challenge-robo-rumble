package de.slothsoft.roborumble;

import java.util.Objects;

import de.slothsoft.roborumble.gui.RobotRenderer;

public class RobotInfo {

	public static RobotInfo from(Robot robot) {
		return new RobotInfo().renderer(robot.createRenderer());
	}

	private int x;
	private int y;
	private RobotRenderer renderer; // TODO: remove renderer
	private Direction direction = Direction.DOWN;

	public int getX() {
		return this.x;
	}

	RobotInfo x(int newX) {
		setX(newX);
		return this;
	}

	void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	RobotInfo y(int newY) {
		setY(newY);
		return this;
	}

	void setY(int y) {
		this.y = y;
	}

	public RobotRenderer getRenderer() {
		return this.renderer;
	}

	RobotInfo renderer(RobotRenderer newRenderer) {
		setRenderer(newRenderer);
		return this;
	}

	void setRenderer(RobotRenderer renderer) {
		this.renderer = renderer;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public RobotInfo direction(Direction newDirection) {
		setDirection(newDirection);
		return this;
	}

	void setDirection(Direction direction) {
		this.direction = Objects.requireNonNull(direction);
	}

}
