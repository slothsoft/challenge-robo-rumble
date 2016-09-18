package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import de.slothsoft.roborumble.Game;
import de.slothsoft.roborumble.Robot;

public class RobotRumbleFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final SettingsPanel settingsPanel = new SettingsPanel();
	private final MapPanel mapPanel = new MapPanel();
	private final HealthPointPanel healthPointPanel = new HealthPointPanel();

	private Game game;

	public RobotRumbleFrame() {
		setTitle("Robot Rumble");
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.mapPanel, BorderLayout.CENTER);
		panel.add(this.healthPointPanel, BorderLayout.SOUTH);

		add(panel, BorderLayout.CENTER);
		add(this.settingsPanel, BorderLayout.WEST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1000, 600));
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
	}

	public void start() {
		createMainPanel();
		setVisible(true);
		this.game = this.settingsPanel.createGame();
		this.mapPanel.setMap(this.game.getMap());
		this.healthPointPanel.setMap(this.game.getMap());
		this.game.onFinish(this::gameFinished);
		this.game.start();
		doLayout();
	}

	private void gameFinished(Robot winner) {
		System.out.println(
				winner == null ? "Game finished. There were no survivors." : "Game finished. " + winner + " won.");
		start();
	}

	public boolean isShowSettings() {
		return this.settingsPanel.isShowSettings();
	}

	public RobotRumbleFrame showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setShowSettings(showSettings);
	}
}
