package de.slothsoft.roborumble.gui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.slothsoft.roborumble.Robot;

/**
 * The model that shows the scores of the {@link Robot}'s.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public class HighScoreModel extends AbstractTableModel {

	private static final long serialVersionUID = 6708900198232721648L;

	private static final String[] COLUMNS = {"Robot", "Author", "#"};
	public static final int COLUMN_ROBOT = 0;
	public static final int COLUMN_AUTHOR = 1;
	public static final int COLUMN_VICTORIES = 2;

	private final List<Row> rows = new ArrayList<>();

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		final Row row = this.rows.get(rowIndex);
		switch (columnIndex) {
			case COLUMN_ROBOT :
				return row.robot;
			case COLUMN_AUTHOR :
				return row.author;
			case COLUMN_VICTORIES :
				return row.victories;
			default :
				return null;
		}
	}

	@Override
	public int getRowCount() {
		return this.rows.size();
	}

	@Override
	public int getColumnCount() {
		return COLUMNS.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMNS[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
			case COLUMN_ROBOT :
			case COLUMN_AUTHOR :
				return String.class;
			case COLUMN_VICTORIES :
				return String.class;
			default :
				return super.getColumnClass(columnIndex);
		}
	}

	public void propagateWinner(Robot winner) {
		final Row winnerRow = this.rows.stream().filter(r -> r.robot.equals(winner.getDisplayName())).findFirst()
				.orElse(new Row(winner.getDisplayName(), winner.getAuthor()));
		winnerRow.victories++;
		if (!this.rows.contains(winnerRow)) {
			this.rows.add(winnerRow);
		}
		this.rows.sort(Comparator.<Row>comparingInt(e -> e.victories).reversed());
		fireTableDataChanged();
	}

	/*
	 *
	 */

	static class Row {

		String robot;
		String author;
		int victories;

		public Row(String robot, String author) {
			this.robot = robot;
			this.author = author;
		}

	}

}
