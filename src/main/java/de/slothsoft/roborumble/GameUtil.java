package de.slothsoft.roborumble;

import java.awt.Point;

public final class GameUtil {

	public static boolean isOnMap(Map map, Point point) {
		return isOnMap(map, point.x, point.y);
	}

	public static boolean isOnMap(Map map, int x, int y) {
		return x >= 0 && y >= 0 && x < map.width && y < map.height;
	}

	public static boolean isOnWall(Map map, Point point) {
		return isOnMap(map, point.x, point.y) && map.tiles[point.x][point.y];
	}

	private GameUtil() {
		// hide me
	}
}
