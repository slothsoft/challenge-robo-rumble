package de.slothsoft.roborumble;

import java.awt.Point;

/**
 * Util class for managing the {@link Robot}s on the map.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

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
