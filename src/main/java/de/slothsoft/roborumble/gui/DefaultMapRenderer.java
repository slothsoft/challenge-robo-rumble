package de.slothsoft.roborumble.gui;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class DefaultMapRenderer implements MapRenderer {

	private final Image floorImage;
	private final Image wallImage;

	public DefaultMapRenderer() {
		this.floorImage = fetchImage("floor.png");
		this.wallImage = fetchImage("wall.png");
	}

	private Image fetchImage(String imageName) {
		try (InputStream input = getClass().getResourceAsStream(imageName);) {
			return ImageIO.read(input);
		} catch (Exception e) {
			throw new RuntimeException("Image " + imageName + " is missing!", e);
		}
	}

	@Override
	public void paintTile(Graphics2D graphics, boolean solid) {
		graphics.drawImage(solid ? this.wallImage : this.floorImage, 0, 0, Tile.WIDTH_IN_PIXELS, Tile.HEIGHT_IN_PIXELS,
				0, 0, this.wallImage.getWidth(null), this.wallImage.getHeight(null), null);
	}
}
