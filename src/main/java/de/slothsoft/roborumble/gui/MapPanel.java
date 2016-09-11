package de.slothsoft.roborumble.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JComponent;

import de.slothsoft.roborumble.Direction;
import de.slothsoft.roborumble.Map;
import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.RobotInfo;

public class MapPanel extends JComponent {

	private static final long serialVersionUID = -4607180702570402004L;
	private static final long REPAINT_IN_MS = 1000 / 24; // 24 frames per second

	private Map map;
	private MapRenderer renderer = new DefaultMapRenderer();
	private BufferStrategy bufferStrategy;

	private int width;
	private int height;
	private double ratio;

	public MapPanel() {
		this(null);
	}

	public MapPanel(Map map) {
		setMap(map);
		setDoubleBuffered(true);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Direction[] directions = Direction.values();
				for (Robot robot : map.getRobots()) {
					RobotInfo info = map.getInfo(robot);
					info.direction(directions[(info.getDirection().ordinal() + 1) % directions.length]);
				}
				repaint();
			}
		});
	}

	@Override
	public void paint(Graphics graphics) {
		if (getBackground() != null) {
			graphics.setColor(getBackground());
			graphics.fillRect(0, 0, getWidth(), getHeight());
		}
		if (this.map != null) {
			this.ratio = (float) Math.min((double) getWidth() / this.width, (double) getHeight() / this.height);
			((Graphics2D) graphics).scale(this.ratio, this.ratio);
			((Graphics2D) graphics).translate(Tile.WIDTH_IN_PIXELS, Tile.HEIGHT_IN_PIXELS);
			this.renderer.paintMap((Graphics2D) graphics, this.map);
		}
		repaint(REPAINT_IN_MS);
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map map) {
		this.map = map;
		this.width = map == null ? 0 : map.getWidth() * Tile.WIDTH_IN_PIXELS + 2 * Tile.WIDTH_IN_PIXELS;
		this.height = map == null ? 0 : map.getHeight() * Tile.HEIGHT_IN_PIXELS + 2 * Tile.HEIGHT_IN_PIXELS;
		setPreferredSize(new Dimension(this.width, this.height));
		repaint();
	}

	public double getRatio() {
		return this.ratio;
	}

	public int getPreferredWidth() {
		return this.width;
	}

	public int getPreferredHeight() {
		return this.height;
	}
}
