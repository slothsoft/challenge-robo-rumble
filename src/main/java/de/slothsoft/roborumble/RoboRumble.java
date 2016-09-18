package de.slothsoft.roborumble;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.slothsoft.roborumble.gui.RobotRumbleFrame;

public class RoboRumble {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}

	private static void createAndShowGui() {
		RobotRumbleFrame robotRumble = new RobotRumbleFrame();
		robotRumble.start();
		robotRumble.pack();
	}

}
