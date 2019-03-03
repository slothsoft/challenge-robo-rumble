package de.slothsoft.roborumble;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.roborumble.contrib.ExampleRobot;

public class RobotsTest {

	@Test
	public void testGetDefaultPositioners() throws Exception {
		final List<Robot> result = Robots.getRobots();
		Assert.assertNotNull(result);
		Assert.assertTrue("Default robot is missing: " + result,
				result.stream().filter(i -> i instanceof ExampleRobot).count() > 0);
	}

}
