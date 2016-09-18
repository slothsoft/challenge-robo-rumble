package de.slothsoft.roborumble.contrib;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.InputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import de.slothsoft.roborumble.Direction;
import de.slothsoft.roborumble.gui.RobotRenderer;
import de.slothsoft.roborumble.gui.SpriteRobotRenderer;

public class ExampleRobot extends AbstractRobot {

	private static final Random RANDOM = new Random(7L);
	private static final String[] NAMES = { "Albert", "Bert", "Charles", "Daniel", "Emil", "Francis", "Gert", "Hans",
			"Ike", "James", "Klaus", "Lars", "Manfred", "Norbert", "Olaf", "Paul", "Quentin", "Ralf", "Sven", "Tony",
			"Ulf", "Viktor", "Wolfgang", "Xerox", "Yens", "Zack" };
	private static final Direction[] DIRECTIONS = Direction.values();
	private static final Color EYE_COLOR = new Color(0, 255, 237);

	private String displayName = NAMES[RANDOM.nextInt(NAMES.length)] + " (Example)";

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public RobotRenderer createRenderer() {
		try (InputStream input = RobotRenderer.class.getResourceAsStream("robot_sprite.png");) {
			return new SpriteRobotRenderer(changeEyeColor(ImageIO.read(input)), 2);
		} catch (Exception e) {
			throw new RuntimeException("Robot sprite is missing!", e);
		}
	}

	private Image changeEyeColor(BufferedImage image) {
		int width = image.getWidth();
		int height = image.getHeight();
		WritableRaster raster = image.getRaster();
		Color randomColor = createRandomColor();

		int[] color = new int[4];
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				raster.getPixel(x, y, color);
				if (color[0] == EYE_COLOR.getRed() && color[1] == EYE_COLOR.getGreen()
						&& color[2] == EYE_COLOR.getBlue()) {
					color[0] = randomColor.getRed();
					color[1] = randomColor.getGreen();
					color[2] = randomColor.getBlue();
					raster.setPixel(x, y, color);
				}
			}
		}
		return image;
	}

	private Color createRandomColor() {
		Random rnd = new Random(this.displayName.hashCode());
		int rgb = rnd.nextInt(3);
		float r = rnd.nextFloat() / 2f + (rgb == 0 ? 0f : 0.5f);
		float g = rnd.nextFloat() / 2f + (rgb == 1 ? 0f : 0.5f);
		float b = rnd.nextFloat() / 2f + (rgb == 2 ? 0f : 0.5f);
		return new Color(r, g, b);
	}

	@Override
	public void execute(Context context) {
		for (int i = 0; i < 4; i++) {
			if (context.requestMove(DIRECTIONS[RANDOM.nextInt(DIRECTIONS.length)])) {
				context.requestShot();
				break;
			}
		}
	}

}
