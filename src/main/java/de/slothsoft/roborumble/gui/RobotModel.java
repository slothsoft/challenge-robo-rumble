package de.slothsoft.roborumble.gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.Robots;

public class RobotModel extends AbstractTableModel {

	private static final long serialVersionUID = 6708900198232721648L;

	private static final String[] COLUMNS = { "Use", "Display Name", "Class" };
	public static final int COLUMN_SELECTED = 0;
	public static final int COLUMN_DISPLAY_NAME = 1;
	public static final int COLUMN_CLASS = 2;

	private List<Row> rows = new ArrayList<>();

	public RobotModel() {
		for (Robot robot : Robots.getStonePositioners()) {
			this.rows.add(new Row(robot.getDisplayName(), robot.getClass()));
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Row row = this.rows.get(rowIndex);
		switch (columnIndex) {
		case COLUMN_SELECTED:
			return row.selected;
		case COLUMN_DISPLAY_NAME:
			return row.displayName;
		case COLUMN_CLASS:
			return row.robotClass.getSimpleName();
		default:
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
		case COLUMN_SELECTED:
			return Boolean.class;
		case COLUMN_DISPLAY_NAME:
		case COLUMN_CLASS:
			return String.class;
		default:
			return super.getColumnClass(columnIndex);
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case COLUMN_SELECTED:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		Row row = this.rows.get(rowIndex);
		switch (columnIndex) {
		case COLUMN_SELECTED:
			row.selected = ((Boolean) aValue).booleanValue();
			break;
		default:
		}
	}

	public Robot createRobot(int rowIndex) {
		Row row = this.rows.get(rowIndex);
		try {
			return row.selected ? row.robotClass.newInstance() : null;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new IllegalArgumentException("Cannot create instance of " + row.robotClass, e);
		}
	}

	/*
	 * 
	 */

	static class Row {

		String displayName;
		Class<? extends Robot> robotClass;
		boolean selected = true;

		public Row(String displayName, Class<? extends Robot> robotClass) {
			this.displayName = displayName;
			this.robotClass = robotClass;
		}

	}

}
