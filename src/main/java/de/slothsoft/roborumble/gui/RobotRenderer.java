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

	void paint(Graphics graphics, Direction direction);
}
