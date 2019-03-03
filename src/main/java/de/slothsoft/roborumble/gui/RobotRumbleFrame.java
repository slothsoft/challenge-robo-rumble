package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.slothsoft.roborumble.Game;
import de.slothsoft.roborumble.Robot;

/**
 * The main frame of this challenge.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class RobotRumbleFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final SettingsPanel settingsPanel = new SettingsPanel();
	private final HighScorePanel highScorePanel = new HighScorePanel();
	private final MapPanel mapPanel = new MapPanel();
	private final HealthPointPanel healthPointPanel = new HealthPointPanel();

	private Game game;

	public RobotRumbleFrame() {
		setTitle("Robot Rumble");
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(this.mapPanel, BorderLayout.CENTER);
		panel.add(this.healthPointPanel, BorderLayout.SOUTH);

		add(panel, BorderLayout.CENTER);
		add(this.settingsPanel, BorderLayout.WEST);
		add(this.highScorePanel, BorderLayout.EAST);

		final JButton restartButton = new JButton("Restart");
		restartButton.addActionListener(e -> restart());
		this.settingsPanel.add(restartButton, GridBagData.forControl(1, 10));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(1200, 600));
		setSize(getPreferredSize());
		setLocationRelativeTo(null);
	}

	public void restart() {
		stop();
		start();
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
		if (winner == null) {
			System.out.println("Game finished. There were no survivors.");
		} else {
			System.out.println("Game finished. " + winner + " won.");
			this.highScorePanel.propagateWinner(winner);
		}
		start();
	}

	public void stop() {
		this.game.stopGame();
		this.game = null;
	}

	public boolean isShowSettings() {
		return this.settingsPanel.isVisible();
	}

	public RobotRumbleFrame showSettings(boolean showSettings) {
		setShowSettings(showSettings);
		return this;
	}

	public void setShowSettings(boolean showSettings) {
		this.settingsPanel.setVisible(showSettings);
	}
}
