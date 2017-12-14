package de.slothsoft.roborumble;

import java.util.Objects;

import de.slothsoft.roborumble.gui.RobotRenderer;

public class RobotInfo extends Bean {

	public static final String PROPERTY_HEALTH_POINTS = "healthPoints";

	public static RobotInfo from(Robot robot) {
		Stats stats = robot.createStats();
		stats.validate();
		int healthPoints = stats.getHealthPointBase() * Stats.HEALTH_POINTS_PER_BASE;
		return new RobotInfo().renderer(robot.createRenderer()).healthPoints(healthPoints).maxHealthPoints(healthPoints)
				.attack(stats.getAttack()).defense(stats.getDefense()).speed(stats.getSpeed());
	}

	private int x;
	private int y;
	private RobotRenderer renderer;
	private Direction direction = Direction.DOWN;

	int healthPoints;
	private int maxHealthPoints;
	private int attack;
	private int defense;
	private int speed;

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

	public int getHealthPoints() {
		return this.healthPoints;
	}

	RobotInfo healthPoints(int newHealthPoints) {
		setHealthPoints(newHealthPoints);
		return this;
	}

	void setHealthPoints(int healthPoints) {
		int oldHealthPoints = this.healthPoints;
		this.healthPoints = healthPoints;
		this.propertyChangeSupport.firePropertyChange(PROPERTY_HEALTH_POINTS, oldHealthPoints, healthPoints);
	}

	public int getAttack() {
		return this.attack;
	}

	RobotInfo attack(int newAttack) {
		setAttack(newAttack);
		return this;
	}

	void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return this.defense;
	}

	RobotInfo defense(int newDefense) {
		setDefense(newDefense);
		return this;
	}

	void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMaxHealthPoints() {
		return this.maxHealthPoints;
	}

	RobotInfo maxHealthPoints(int newMaxHealthPoints) {
		setMaxHealthPoints(newMaxHealthPoints);
		return this;
	}

	void setMaxHealthPoints(int maxHealthPoints) {
		this.maxHealthPoints = maxHealthPoints;
	}

	public int getSpeed() {
		return this.speed;
	}

	RobotInfo speed(int newSpeed) {
		setSpeed(newSpeed);
		return this;
	}

	void setSpeed(int speed) {
		this.speed = speed;
	}

}
