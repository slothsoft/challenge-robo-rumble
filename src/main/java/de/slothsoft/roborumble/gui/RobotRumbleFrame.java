package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.slothsoft.roborumble.Game;

public class RobotRumbleFrame extends JFrame {

	private static final long serialVersionUID = -2165255329208901685L;

	private final Game game = new Game();
	private final SettingsPanel settingsPanel = new SettingsPanel(this.game);
	private final MapPanel gamePanel = new MapPanel(this.game.getMap());

	public RobotRumbleFrame() {
		setTitle("Robot Rumble");
	}

	private void createMainPanel() {
		setLayout(new BorderLayout());
		add(this.settingsPanel, BorderLayout.WEST);
		add(this.gamePanel, BorderLayout.CENTER);

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		centerWindow();
	}

	private void centerWindow() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		Dimension windowSize = getSize();
		setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);
	}

	public void start() {
		createMainPanel();
		setVisible(true);
		this.gamePanel.repaint();
		this.game.start();
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
