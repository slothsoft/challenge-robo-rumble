package de.slothsoft.roborumble.gui;

import static de.slothsoft.roborumble.gui.Tile.HEIGHT_IN_PIXELS;
import static de.slothsoft.roborumble.gui.Tile.WIDTH_IN_PIXELS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;

import de.slothsoft.roborumble.Bullet;
import de.slothsoft.roborumble.Map;
import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.RobotInfo;
import de.slothsoft.roborumble.Thing;

/**
 * A class that is able to paint a {@link Map} on a {@link Graphics2D}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public interface MapRenderer {

	int BORDER_WIDTH = 2;

	/**
	 * Paints a map
	 *
	 * @param graphics graphics
	 * @param array block array
	 */

	default void paintMap(Graphics2D graphics, Map map) {
		graphics.setStroke(new BasicStroke(BORDER_WIDTH));
		graphics.setColor(Color.DARK_GRAY);
		graphics.drawRect(-BORDER_WIDTH, -BORDER_WIDTH, map.getWidth() * WIDTH_IN_PIXELS + 2 * BORDER_WIDTH - 1,
				map.getHeight() * HEIGHT_IN_PIXELS + 2 * BORDER_WIDTH - 1);

		paintTileArray(graphics, map.getTiles());

		for (final Entry<Robot, RobotInfo> robotEntry : new HashSet<>(map.getRobotInfos().entrySet())) {
			paintRobot(graphics, robotEntry.getKey(), robotEntry.getValue());
		}
		for (final Thing thing : new ArrayList<>(map.getThings())) {
			if (thing != null) {
				// FIXME don't know why that happens, but it does
				paintThing(graphics, thing);
			}
		}
	}

	default void paintThing(Graphics2D graphics, Thing thing) {
		if (thing instanceof Bullet) {
			paintBullet(graphics, (Bullet) thing);
		} else
			throw new IllegalArgumentException("Cannot draw " + thing);
	}

	default void paintBullet(Graphics2D graphics, Bullet bullet) {
		final int x = bullet.getX();
		final int y = bullet.getY();
		final int third = WIDTH_IN_PIXELS / 3;
		graphics.translate(x * WIDTH_IN_PIXELS, y * HEIGHT_IN_PIXELS);
		graphics.setColor(Color.RED);
		graphics.fillOval(third, third, third, third);
		graphics.translate(-x * WIDTH_IN_PIXELS, -y * HEIGHT_IN_PIXELS);
	}

	/**
	 * Paints an entire tile array
	 *
	 * @param graphics graphics
	 * @param tiles tiles array
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
		if (info != null && info.getRenderer() != null) {
			final int x = info.getX();
			final int y = info.getY();
			graphics.translate(x * WIDTH_IN_PIXELS, y * HEIGHT_IN_PIXELS);
			info.getRenderer().paint(graphics, new RobotRenderer.Context(info.getDirection()));
			graphics.translate(-x * WIDTH_IN_PIXELS, -y * HEIGHT_IN_PIXELS);
		} else {
			System.err.println("No renderer found for " + robot + "!");
		}
	}

	/**
	 * Paints a single tile of the map
	 *
	 * @param graphics graphics
	 * @param solid if its a wall
	 */

	void paintTile(Graphics2D graphics, boolean solid);

}
