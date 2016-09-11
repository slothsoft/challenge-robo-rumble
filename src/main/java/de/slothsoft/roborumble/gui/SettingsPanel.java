package de.slothsoft.roborumble.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import de.slothsoft.roborumble.Game;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -2165255329208901685L;
	private static final Insets INSETS = new Insets(5, 5, 5, 5);

	private final Game game;

	private final Component settingsPanel;

	public SettingsPanel(Game game) {
		this.game = game;

		this.settingsPanel = createSettings();

		setLayout(new BorderLayout());
		add(this.settingsPanel, BorderLayout.CENTER);
	}

	private Component createSettings() {
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		titleBorder.setTitleColor(Color.DARK_GRAY);

		JPanel parent = new JPanel();
		parent.setBorder(titleBorder);
		parent.setLayout(new GridBagLayout());

		int y = 0;
//		JComboBox<Robot> stonePositioner = new JComboBox<>();
//		stonePositioner.setModel(new DefaultComboBoxModel<>(new Vector<>(Robots.getStonePositioners())));
//		stonePositioner.setRenderer(new DisplayableListCellRenderer<Robot>(p -> p.getDisplayName()));
//		stonePositioner.addActionListener(e -> this.game.setStonePositioner((Robot) stonePositioner.getSelectedItem()));
//		stonePositioner.setSelectedItem(this.game.getStonePositioner());
//
//		parent.add(createLabel("Positioner"), createLabelConstraints(0, y));
//		parent.add(stonePositioner, createControlConstraints(1, y));
		y++;

		return parent;
	}

	private Component createLabel(String text) {
		JLabel label = new JLabel(text + ':');
		label.setForeground(Color.WHITE);
		return label;
	}

	private static GridBagConstraints createLabelConstraints(int x, int y) {
		return new GridBagConstraints(x, y, 1, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, INSETS,
				0, 0);
	}

	private static GridBagConstraints createControlConstraints(int x, int y) {
		return new GridBagConstraints(x, y, 1, 1, 1.0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				INSETS, 0, 0);
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
