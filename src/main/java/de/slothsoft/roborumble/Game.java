package de.slothsoft.roborumble;

public class Game {

	private final Thread thread = new Thread(this::run);

	private final Map map = new MapGenerator().generate();
	private boolean stop = true;
	private long sleepTime = 100L;

	/**
	 * Starts the current game
	 */

	public void start() {
		this.stop = false;
		this.thread.start();
	}

	private void run() {
		while (!this.stop) {
			sleep();
		}
	}

	private void sleep() {
		try {
			Thread.sleep(this.sleepTime);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Stops the current game
	 */

	public void stopGame() {
		this.stop = true;
	}

	public Map getMap() {
		return this.map;
	}

}
