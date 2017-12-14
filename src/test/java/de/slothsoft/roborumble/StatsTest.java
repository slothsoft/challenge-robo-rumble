package de.slothsoft.roborumble;

import org.junit.Test;

public class StatsTest {

	@Test
	public void testValidate() throws Exception {
		new Stats().validate();
		new Stats().attack(Stats.BASE + 1).defense(Stats.BASE - 1).validate();
		new Stats().speed(Stats.BASE + 1).healthPointBase(Stats.BASE - 1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_AttackTooLarge() throws Exception {
		new Stats().attack(Stats.BASE + 1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_AttackSmallerZero() throws Exception {
		new Stats().attack(-1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_DefenseTooLarge() throws Exception {
		new Stats().defense(Stats.BASE + 1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_DefenseSmallerZero() throws Exception {
		new Stats().defense(-1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_SpeedTooLarge() throws Exception {
		new Stats().speed(Stats.BASE + 1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_SpeedSmallerZero() throws Exception {
		new Stats().speed(-1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_HealthPointBaseTooLarge() throws Exception {
		new Stats().healthPointBase(Stats.BASE + 1).validate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidate_HealthPointBaseSmallerZero() throws Exception {
		new Stats().healthPointBase(-1).validate();
	}
}
