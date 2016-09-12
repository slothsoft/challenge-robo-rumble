package de.slothsoft.roborumble.contrib;

import java.util.Random;

import de.slothsoft.roborumble.Direction;

public class ExampleRobot extends AbstractRobot {

	private static final Random RANDOM = new Random(7L);
	private static final String[] NAMES = { "Albert", "Bert", "Charles", "Daniel", "Emil", "Francis", "Gert", "Hans",
			"Ike", "James", "Klaus" };
	private static final Direction[] DIRECTIONS = Direction.values();

	@Override
	public String getDisplayName() {
		return NAMES[RANDOM.nextInt(NAMES.length)] + " (Example)";
	}

	@Override
	public void execute(Context context) {
		context.requestMove(DIRECTIONS[RANDOM.nextInt(DIRECTIONS.length)]);
		context.requestShot();
	}

}
