package de.slothsoft.roborumble;

import java.awt.Point;
import java.util.function.Consumer;

/**
 * A direction for moving the {@link Robot}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public enum Direction {
	DOWN(point -> point.y++),

	LEFT(point -> point.x--),

	UP(point -> point.y--),

	RIGHT(point -> point.x++),

	;

	private Consumer<Point> applier;

	private Direction(Consumer<Point> applier) {
		this.applier = applier;
	}

	public void apply(Point point) {
		this.applier.accept(point);
	}

}
