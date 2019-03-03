package de.slothsoft.roborumble;

import java.awt.Point;

/**
 * A bullet shot by a {@link Robot}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class Bullet implements Thing {

	private final Map map;
	private final Robot robot;
	private final Point point;
	private final Direction direction;

	public Bullet(Map map, Robot robot) {
		this.map = map;
		this.robot = robot;

		final RobotInfo info = map.getInfo(robot);
		this.point = new Point(info.getX(), info.getY());
		this.direction = info.getDirection();
	}

	@Override
	public void execute() {
		this.direction.apply(this.point);
		if (!GameUtil.isOnMap(this.map, this.point) || GameUtil.isOnWall(this.map, this.point)) {
			this.map.things.remove(this);
		}
	}

	@Override
	public void collide(Robot collidedRobot) {
		final RobotInfo info = this.map.findInfo(collidedRobot);
		if (collidedRobot != this.robot && info != null) {
			if (info != null) {
				info.setHealthPoints(info.getHealthPoints() - 1);
				if (info.healthPoints <= 0) {
					this.map.removeRobot(collidedRobot);
					this.map.things.remove(this);
				}
			}
		}
	}

	@Override
	public int getX() {
		return this.point.x;
	}

	@Override
	public int getY() {
		return this.point.y;
	}

}
