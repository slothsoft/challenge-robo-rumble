package de.slothsoft.roborumble;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.slothsoft.roborumble.gui.RobotRumbleFrame;

/**
 * The start class.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class RoboRumble {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (final Exception ex) {
			System.err.println(ex.getMessage());
		}
		SwingUtilities.invokeLater(() -> createAndShowGui());
	}

	private static void createAndShowGui() {
		final RobotRumbleFrame robotRumble = new RobotRumbleFrame();
		robotRumble.start();
		robotRumble.pack();
	}

}
