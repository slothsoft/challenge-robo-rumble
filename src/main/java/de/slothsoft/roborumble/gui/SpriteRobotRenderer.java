package de.slothsoft.roborumble.gui;

import java.awt.Graphics2D;
import java.awt.Image;

import de.slothsoft.roborumble.Robot;

/**
 * A default implementation of {@link RobotRenderer} that splits a single image into
 * frames for displaying the directions and animations of a {@link Robot}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class SpriteRobotRenderer implements RobotRenderer {

	private static final long FRAME_TIME = 1000 / 24; // 24 frames per second

	private final Image spriteImage;
	private final int frames;
	private final int frameWidth;
	private final int frameHeight;

	private int currentFrame;
	private long lastTime;

	public SpriteRobotRenderer(Image spriteImage, int frames) {
		this.spriteImage = spriteImage;
		this.frames = frames;

		this.frameWidth = spriteImage.getWidth(null) / frames;
		this.frameHeight = spriteImage.getHeight(null) / 4;
	}

	@Override
	public void paint(Graphics2D graphics, Context context) {
		final long currentTime = System.currentTimeMillis();
		if (currentTime - this.lastTime >= FRAME_TIME) {
			this.currentFrame = (int) ((this.currentFrame + (currentTime - this.lastTime) / FRAME_TIME) % this.frames);
			this.lastTime = currentTime;
		}
		final int x = this.currentFrame * this.frameWidth;
		final int y = context.getDirection().ordinal() * this.frameHeight;

		graphics.drawImage(this.spriteImage, 0, 0, context.getWidth(), context.getHeight(), x, y, x + this.frameWidth,
				y + this.frameHeight, null);
	}

}
