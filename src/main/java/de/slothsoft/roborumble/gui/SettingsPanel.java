package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import de.slothsoft.roborumble.Robots;
import de.slothsoft.roborumble.contrib.ExampleRobot;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;

	private final RobotModel robotModel = new RobotModel();

	private JSpinner sleepTime;
	private JSpinner robotCount;
	private JSpinner mapWidth;
	private JSpinner mapHeight;
	private JSpinner tileCount;

	private Game lastGame;

	public SettingsPanel() {
		setLayout(new BorderLayout());
		createControls();
	}

	private void createControls() {
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		titleBorder.setTitleColor(Color.DARK_GRAY);

		setBorder(titleBorder);
		setLayout(new GridBagLayout());

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
		add(scrollPane, GridBagData.forPanel(0, y++).gridwidth(2));

		this.robotCount = new JSpinner();
		this.robotCount.setModel(new SpinnerNumberModel(Math.max(Robots.getRobots().size(), 2), 2, 100, 1));

		add(new JLabel("Robot count:"), GridBagData.forLabel(0, y));
		add(this.robotCount, GridBagData.forControl(1, y));
		y++;

		this.sleepTime = new JSpinner();
		this.sleepTime.setModel(new SpinnerNumberModel(100, 0, 10_000, 100));
		this.sleepTime.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		add(new JLabel("Sleep time:"), GridBagData.forLabel(0, y));
		add(this.sleepTime, GridBagData.forControl(1, y));
		y++;

		this.mapWidth = new JSpinner();
		this.mapWidth.setModel(new SpinnerNumberModel(20, 10, 100, 1));
		this.mapWidth.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		add(new JLabel("Map width:"), GridBagData.forLabel(0, y));
		add(this.mapWidth, GridBagData.forControl(1, y));
		y++;

		this.mapHeight = new JSpinner();
		this.mapHeight.setModel(new SpinnerNumberModel(15, 7, 100, 1));
		this.mapHeight.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		add(new JLabel("Map height:"), GridBagData.forLabel(0, y));
		add(this.mapHeight, GridBagData.forControl(1, y));
		y++;

		this.tileCount = new JSpinner();
		this.tileCount.setModel(new SpinnerNumberModel(10, 0, 10_000, 1));
		this.tileCount.addChangeListener(e -> this.lastGame.setSleepTime((int) this.sleepTime.getValue()));

		add(new JLabel("Tile count:"), GridBagData.forLabel(0, y));
		add(this.tileCount, GridBagData.forControl(1, y));
		y++;
	}

	public Game createGame() {
		MapGenerator generator = new MapGenerator();
		generator.setWidth((int) this.mapWidth.getValue());
		generator.setHeight((int) this.mapHeight.getValue());
		generator.setTileCount((int) this.tileCount.getValue());

		Map map = generator.generate();

		int robotCount = (int) this.robotCount.getValue();
		while (map.getRobots().size() < robotCount) {
			int count = this.robotModel.getRowCount();
			for (int i = 0; i < count; i++) {
				Robot robot = this.robotModel.createRobot(i);
				if (robot != null) {
					map.addRobot(robot);
				}
			}
			if (map.getRobots().isEmpty()) {
				// no robots selected
				for (int i = 0; i < robotCount; i++) {
					map.addRobot(new ExampleRobot());
				}
			}
		}
		this.lastGame = new Game(map);
		this.lastGame.setSleepTime((int) this.sleepTime.getValue());
		return this.lastGame;
	}
}
