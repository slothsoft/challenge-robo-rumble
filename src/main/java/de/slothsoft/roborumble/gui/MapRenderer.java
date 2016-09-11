package de.slothsoft.roborumble.gui;

import static de.slothsoft.roborumble.gui.Tile.HEIGHT_IN_PIXELS;
import static de.slothsoft.roborumble.gui.Tile.WIDTH_IN_PIXELS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import de.slothsoft.roborumble.Map;
import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.RobotInfo;

public interface MapRenderer {

	int BORDER_WIDTH = 2;

	/**
	 * Paints a map
	 *
	 * @param graphics
	 *            graphics
	 * @param array
	 *            block array
	 */

	default void paintMap(Graphics2D graphics, Map map) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, map.getWidth() * WIDTH_IN_PIXELS + 2 * BORDER_WIDTH - 1,
				map.getHeight() * HEIGHT_IN_PIXELS + 2 * BORDER_WIDTH - 1);

		paintTileArray(graphics, map.getTiles());

		for (Robot robot : map.getRobots()) {
			paintRobot(graphics, robot, map.getInfo(robot));
		}
	}

	/**
	 * Paints an entire tile array
	 *
	 * @param graphics
	 *            graphics
	 * @param tiles
	 *            tiles array
	 */

	default void paintTileArray(Graphics2D graphics, boolean[][] tiles) {
		for (int xi = 0; xi < tiles.length; xi++) {
			for (int yi = 0; yi < tiles[xi].length; yi++) {
				graphics.translate(xi * WIDTH_IN_PIXELS, yi * HEIGHT_IN_PIXELS);
				paintTile(graphics, tiles[xi][yi]);
				graphics.translate(-xi * WIDTH_IN_PIXELS, -yi * HEIGHT_IN_PIXELS);
			}
		}
	}

	default void paintRobot(Graphics2D graphics, Robot robot, RobotInfo info) {
		if (info.getRenderer() != null) {
			int x = info.getX();
			int y = info.getY();
			graphics.translate(x * WIDTH_IN_PIXELS, y * HEIGHT_IN_PIXELS);
			info.getRenderer().paint(graphics, info.getDirection());
			graphics.translate(-x * WIDTH_IN_PIXELS, -y * HEIGHT_IN_PIXELS);
		} else {
			System.err.println("No renderer found for " + robot + "!");
		}
	}

	/**
	 * Paints a single tile of the map
	 *
	 * @param graphics
	 *            graphics
	 * @param solid
	 *            if its a wall
	 */

	void paintTile(Graphics2D graphics, boolean solid);

}
