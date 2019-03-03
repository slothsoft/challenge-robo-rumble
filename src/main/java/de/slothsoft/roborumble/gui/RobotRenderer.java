package de.slothsoft.roborumble.gui;

import java.awt.Graphics2D;
import java.io.InputStream;

import javax.imageio.ImageIO;

import de.slothsoft.roborumble.Direction;
import de.slothsoft.roborumble.Robot;

/**
 * A class that can render a single {@link Robot} to a {@link Graphics2D}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public interface RobotRenderer {

	static RobotRenderer createDefaultRenderer() {
		try (InputStream input = RobotRenderer.class.getResourceAsStream("robot_sprite.png");) {
			return new SpriteRobotRenderer(ImageIO.read(input), 2);
		} catch (final Exception e) {
			throw new RuntimeException("Robot sprite is missing!", e);
		}
	}

	void paint(Graphics2D graphics, Context context);

	public class Context {

		private final Direction direction;
		private final int width;
		private final int height;

		public Context(Direction direction) {
			this(direction, Tile.WIDTH_IN_PIXELS, Tile.HEIGHT_IN_PIXELS);
		}

		public Context(Direction direction, int width, int height) {
			this.direction = direction;
			this.width = width;
			this.height = height;
		}

		public Direction getDirection() {
			return this.direction;
		}

		public int getWidth() {
			return this.width;
		}

		public int getHeight() {
			return this.height;
		}

	}
}
