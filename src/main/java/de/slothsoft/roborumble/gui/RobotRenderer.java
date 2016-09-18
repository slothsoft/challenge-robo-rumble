package de.slothsoft.roborumble.gui;

import java.awt.Graphics;
import java.io.InputStream;

import javax.imageio.ImageIO;

import de.slothsoft.roborumble.Direction;

public interface RobotRenderer {

	static RobotRenderer createDefaultRenderer() {
		try (InputStream input = RobotRenderer.class.getResourceAsStream("robot_sprite.png");) {
			return new SpriteRobotRenderer(ImageIO.read(input), 2);
		} catch (Exception e) {
			throw new RuntimeException("Robot sprite is missing!", e);
		}
	}

	void paint(Graphics graphics, Context context);

	public class Context {

		private Direction direction;
		private int width;
		private int height;

		public Context(Direction direction) {
			this(direction, Tile.WIDTH_IN_PIXELS, Tile.HEIGHT_IN_PIXELS);
		}

		public Context(Direction direction, int width, int height) {
			this.direction = direction;
			this.width = width;
			this.height = height;
		}

		public Direction getDirection() {
			return direction;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

	}
}
