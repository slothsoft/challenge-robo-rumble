package de.slothsoft.roborumble;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class Map {

	final boolean[][] tiles;
	final int width;
	final int height;

	private Supplier<Point> robotStartPointSupplier = () -> new Point();

	List<Thing> things = new ArrayList<>();
	List<Robot> robots = new ArrayList<>();
	private List<RobotInfo> robotInfos = new ArrayList<>();

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

	public List<Robot> getRobots() {
		return Collections.unmodifiableList(this.robots);
	}

	public RobotInfo getInfo(Robot robot) {
		return this.robotInfos.get(indexOf(robot));
	}

	public RobotInfo findInfo(Robot robot) {
		int index = indexOf(robot);
		return index == -1 ? null : this.robotInfos.get(index);
	}

	private int indexOf(Robot searchedRobot) {
		int index = 0;
		for (Robot robot : this.robots) {
			if (searchedRobot == robot) return index;
			index++;
		}
		return -1;
	}

	public List<Thing> getThings() {
		return Collections.unmodifiableList(this.things);
	}

	public void addRobot(Robot robot) {
		Point position = this.robotStartPointSupplier.get();
		this.robots.add(robot);
		this.robotInfos.add(RobotInfo.from(robot).x(position.x).y(position.y));
	}

	public void removeRobot(Robot robot) {
		int index = indexOf(robot);
		if (index >= 0) {
			this.robots.remove(index);
			this.robotInfos.remove(index);
		}
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
