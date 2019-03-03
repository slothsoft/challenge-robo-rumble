package de.slothsoft.roborumble;

/**
 * The stats of a {@link Robot}.
 *
 * @author Stef Schulz
 * @since 1.0.0
 */

public final class Stats implements Cloneable {

	static final int BASE = 7;
	public static final int HEALTH_POINTS_PER_BASE = 3;
	public static final int MAX_SUM = BASE * 4;

	private int healthPointBase = BASE;
	private int attack = BASE;
	private int defense = BASE;
	private int speed = BASE;

	public void validate() {
		final int sum = this.healthPointBase + this.attack + this.defense + this.speed;
		if (sum > MAX_SUM) throw new IllegalArgumentException("Your stats cannot be more than " + MAX_SUM + "!");
		if (this.healthPointBase < 0) throw new IllegalArgumentException("Health point base cannot be less than 0!");
		if (this.attack < 0) throw new IllegalArgumentException("Health point base cannot be less than 0!");
		if (this.defense < 0) throw new IllegalArgumentException("Health point base cannot be less than 0!");
		if (this.speed < 0) throw new IllegalArgumentException("Health point base cannot be less than 0!");
	}

	public int getAttack() {
		return this.attack;
	}

	public Stats attack(int newAttack) {
		setAttack(newAttack);
		return this;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return this.defense;
	}

	public Stats defense(int newDefense) {
		setDefense(newDefense);
		return this;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getHealthPointBase() {
		return this.healthPointBase;
	}

	public Stats healthPointBase(int newHealthPointBase) {
		setHealthPointBase(newHealthPointBase);
		return this;
	}

	public void setHealthPointBase(int healthPointBase) {
		this.healthPointBase = healthPointBase;
	}

	public int getSpeed() {
		return this.speed;
	}

	public Stats speed(int newSpeed) {
		setSpeed(newSpeed);
		return this;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	@Override
	public Stats clone() {
		try {
			return (Stats) super.clone();
		} catch (final CloneNotSupportedException e) {
			throw new InternalError(e.getMessage());
		}
	}
}
