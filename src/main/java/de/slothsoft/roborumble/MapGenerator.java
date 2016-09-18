package de.slothsoft.roborumble;

import java.awt.Point;
import java.util.Random;

public class MapGenerator {

	private Random rnd = new Random();
	private int width = 20;
	private int height = 15;
	private int tileCount = 30;

	public Map generate() {
		boolean[][] tiles = new boolean[this.width][this.height];
		for (int i = 0; i < this.tileCount; i++) {
			tiles[this.rnd.nextInt(this.width)][this.rnd.nextInt(this.height)] = true;
		}
		Map map = new Map(tiles);
		map.setRobotStartPointSupplier(() -> generateStartPoint(tiles));
		return map;
	}

	private Point generateStartPoint(boolean[][] tiles) {
		Point point = new Point();
		do {
			point.x = this.rnd.nextInt(this.width);
			point.y = this.rnd.nextInt(this.height);
		} while (tiles[point.x][point.y]);
		return point;
	}

	public int getHeight() {
		return this.height;
	}

	public MapGenerator height(int newHeight) {
		setHeight(newHeight);
		return this;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTileCount() {
		return this.tileCount;
	}

	public MapGenerator tileCount(int newTileCount) {
		setTileCount(newTileCount);
		return this;
	}

	public void setTileCount(int tileCount) {
		this.tileCount = tileCount;
	}

	public int getWidth() {
		return this.width;
	}

	public MapGenerator width(int newWidth) {
		setWidth(newWidth);
		return this;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
