package de.slothsoft.roborumble.gui;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;

import de.slothsoft.roborumble.Direction;
import de.slothsoft.roborumble.Map;
import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.RobotInfo;

public class HealthPointPanel extends JComponent {

	private static final long serialVersionUID = -6297202903243792595L;
	private Map map;

	private void createControls() {
		setLayout(new FlowLayout());

		for (Entry<Robot, RobotInfo> robotEntry : new HashMap<>(this.map.getRobotInfos()).entrySet()) {
			add(new RobotControl(robotEntry.getKey(), robotEntry.getValue()));
		}
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map map) {
		this.map = map;
		removeAll();
		createControls();
	}

	class RobotControl extends JLabel {

		private static final long serialVersionUID = -1769442297405719987L;

		private final RobotInfo info;

		public RobotControl(Robot robot, RobotInfo info) {
			this.info = info;
			setToolTipText(robot.getDisplayName());
			setIcon(createIcon(info.getRenderer()));
			info.addPropertyChangeListener(RobotInfo.PROPERTY_HEALTH_POINTS, e -> refresh());
			refresh();
		}

		private Icon createIcon(RobotRenderer renderer) {
			final int size = 32;
			BufferedImage image = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
			Graphics g = image.createGraphics();
			renderer.paint(g, new RobotRenderer.Context(Direction.DOWN, size, size));
			g.dispose();
			return new ImageIcon(image);
		}

		void refresh() {
			setText(String.valueOf(this.info.getHealthPoints()));
		}

	}

}
