package de.slothsoft.roborumble;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Map {

	private final boolean[][] tiles;
	private final int width;
	private final int height;

	private List<Robot> robots = new ArrayList<>();
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

	private int indexOf(Robot searchedRobot) {
		int index = 0;
		for (Robot robot : this.robots) {
			if (searchedRobot == robot) return index;
			index++;
		}
		throw new IllegalArgumentException("Could not find robot: " + searchedRobot);
	}

	public void addRobot(Robot robot) {
		this.robots.add(robot);
		this.robotInfos.add(RobotInfo.from(robot));
	}
}
