package de.slothsoft.roborumble;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class Game {

	private final Thread thread = new Thread(this::run);

	private final Map map = new MapGenerator().generate();
	private boolean stop = true;
	private long sleepTime = 100L;
	private Consumer<Robot> onFinish = winner -> System.out.println(winner == null
			? "Game finished. There were no survivors." : "Game finished. " + winner.getDisplayName() + " won.");

	/**
	 * Starts the current game
	 */

	public void start() {
		this.stop = false;
		this.thread.start();
	}

	private void run() {
		while (!this.stop) {
			sleep();
		}
	}

	private void sleep() {
		try {
			for (Robot robot : new ArrayList<>(this.map.robots)) {
				robot.execute(new StonePositionerContext(this.map, robot));
			}
			for (Thing thing : new ArrayList<>(this.map.things)) {
				thing.execute();
			}
			detectCollisions();
			if (this.map.robots.size() < 2) {
				finishGame();
			}
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void detectCollisions() {
		for (Robot robot : new ArrayList<>(this.map.robots)) {
			RobotInfo info = this.map.findInfo(robot);
			if (info == null) {
				continue;
			}
			for (Thing thing : new ArrayList<>(this.map.things)) {
				if (thing.getX() == info.getX() && thing.getY() == info.getY()) {
					thing.collide(robot);
				}
				if (!this.map.robots.contains(robot)) {
					// the robot was removed, so don't detect anything any more
					break;
				}
			}
		}
	}

	private void finishGame() {
		stopGame();
		this.onFinish.accept(this.map.robots.isEmpty() ? null : this.map.robots.get(0));
	}

	/**
	 * Stops the current game
	 */

	public void stopGame() {
		this.stop = true;
	}

	public Map getMap() {
		return this.map;
	}

	public Consumer<Robot> getOnFinish() {
		return this.onFinish;
	}

	public Game onFinish(Consumer<Robot> newOnFinish) {
		setOnFinish(newOnFinish);
		return this;
	}

	public void setOnFinish(Consumer<Robot> onFinish) {
		this.onFinish = Objects.requireNonNull(onFinish);
	}

	class StonePositionerContext implements Robot.Context {

		private final Map map;
		private final Robot robot;
		private final RobotInfo info;
		private final Point point = new Point();

		private boolean alreadyMoved;
		private boolean alreadyShot;

		public StonePositionerContext(Map map, Robot robot) {
			this.map = map;
			this.robot = robot;
			this.info = map.getInfo(robot);
		}

		@Override
		public boolean requestMove(Direction direction) {
			if (this.alreadyMoved) return false;
			this.point.x = this.info.getX();
			this.point.y = this.info.getY();
			direction.apply(this.point);
			if (!GameUtil.isOnMap(this.map, this.point)) return false;
			if (this.map.tiles[this.point.x][this.point.y]) return false;
			this.info.setX(this.point.x);
			this.info.setY(this.point.y);
			rotate(direction);
			this.alreadyMoved = true;
			return true;
		}

		@Override
		public void rotate(Direction direction) {
			this.info.setDirection(direction);
		}

		@Override
		public boolean requestShot() {
			if (this.alreadyShot) return false;
			this.map.things.add(new Bullet(this.map, this.robot));
			this.alreadyShot = true;
			return true;
		}

	}
}
