package de.slothsoft.roborumble;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Map {

	final boolean[][] tiles;
	final int width;
	final int height;

	private Supplier<Point> robotStartPointSupplier = () -> new Point();

	List<Thing> things = new ArrayList<>();
	java.util.Map<Robot, RobotInfo> robots = Collections.synchronizedMap(new HashMap<Robot, RobotInfo>());

	public Map(int width, int height) {
		this(new boolean[width][height]);
	}

	public Map(boolean[][] tiles) {
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
	}

	public int getHeight() {
		return this.height;
	}

	public int getWidth() {
		return this.width;
	}

	public boolean[][] getTiles() {
		return this.tiles.clone();
	}

	public Collection<Robot> getRobots() {
		return Collections.unmodifiableCollection(this.robots.keySet());
	}

	public java.util.Map<Robot, RobotInfo> getRobotInfos() {
		return Collections.unmodifiableMap(this.robots);
	}

	public RobotInfo getInfo(Robot robot) {
		return Objects.requireNonNull(this.robots.get(robot));
	}

	public RobotInfo findInfo(Robot robot) {
		return this.robots.get(robot);
	}

	public List<Thing> getThings() {
		return Collections.unmodifiableList(this.things);
	}

	public void addRobot(Robot robot) {
		Point position = this.robotStartPointSupplier.get();
		this.robots.put(robot, RobotInfo.from(robot).x(position.x).y(position.y));
	}

	public void removeRobot(Robot robot) {
		this.robots.remove(robot);
	}

	public Supplier<Point> getRobotStartPointSupplier() {
		return this.robotStartPointSupplier;
	}

	public Map robotStartPointSupplier(Supplier<Point> newRobotStartPointSupplier) {
		setRobotStartPointSupplier(newRobotStartPointSupplier);
		return this;
	}

	public void setRobotStartPointSupplier(Supplier<Point> robotStartPointSupplier) {
		this.robotStartPointSupplier = Objects.requireNonNull(robotStartPointSupplier);
	}

}
