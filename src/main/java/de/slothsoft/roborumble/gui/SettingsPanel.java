package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import de.slothsoft.roborumble.Game;
import de.slothsoft.roborumble.Map;
import de.slothsoft.roborumble.MapGenerator;
import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.contrib.ExampleRobot;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final Component settingsPanel;
	private final RobotModel robotModel = new RobotModel();

	private JSpinner sleepTime;

	private Game lastGame;

	public SettingsPanel() {
		setLayout(new BorderLayout());
		this.settingsPanel = createSettings();
		add(this.settingsPanel, BorderLayout.CENTER);
	}

	private Component createSettings() {
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		titleBorder.setTitleColor(Color.DARK_GRAY);

		JPanel parent = new JPanel();
		parent.setBorder(titleBorder);
		parent.setLayout(new GridBagLayout());

		int y = 0;
		JTable table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setModel(this.robotModel);
		table.getColumnModel().getColumn(RobotModel.COLUMN_SELECTED).setMaxWidth(40);
		table.getColumnModel().getColumn(RobotModel.COLUMN_DISPLAY_NAME).setPreferredWidth(200);
		table.getColumnModel().getColumn(RobotModel.COLUMN_DISPLAY_NAME).setMaxWidth(200);
		table.getColumnModel().getColumn(RobotModel.COLUMN_CLASS).setPreferredWidth(200);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(250, 100));
		parent.add(scrollPane, GridBagData.forPanel(0, y++).gridwidth(2));

		this.sleepTime = new JSpinner();
		this.sleepTime.setModel(new SpinnerNumberModel(100, 0, 10_000, 100));
		this.sleepTime.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		parent.add(new JLabel("Sleep time:"), GridBagData.forLabel(0, y));
		parent.add(this.sleepTime, GridBagData.forControl(1, y));
		y++;

		return parent;
	}

	public Game createGame() {
		Map map = new MapGenerator().generate();

		while (map.getRobots().size() < 7) {
			int count = this.robotModel.getRowCount();
			for (int i = 0; i < count; i++) {
				Robot robot = this.robotModel.createRobot(i);
				if (robot != null) {
					map.addRobot(robot);
				}
			}
			if (map.getRobots().isEmpty()) {
				// no robots selected
				for (int i = 0; i < 7; i++) {
					map.addRobot(new ExampleRobot());
				}
			}
		}
		this.lastGame = new Game(map);
		this.lastGame.setSleepTime((int) this.sleepTime.getValue());
		return this.lastGame;
	}

	public boolean isShowSettings() {
		return this.settingsPanel.isVisible();
	}

	public SettingsPanel showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setVisible(showSettings);
	}

}
