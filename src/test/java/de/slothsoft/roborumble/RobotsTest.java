package de.slothsoft.roborumble;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.roborumble.Robot;
import de.slothsoft.roborumble.Robots;
import de.slothsoft.roborumble.contrib.ExampleRobot;
import de.slothsoft.roborumble.contrib1.A;
import de.slothsoft.roborumble.contrib1.B;
import de.slothsoft.roborumble.contrib1.C;
import de.slothsoft.roborumble.contrib2.Apple;
import de.slothsoft.roborumble.contrib2.Banana;
import de.slothsoft.roborumble.contrib2.Orange;
import de.slothsoft.roborumble.contrib2.Pear;
import de.slothsoft.roborumble.contrib3.One;
import de.slothsoft.roborumble.contrib3.Three;
import de.slothsoft.roborumble.contrib3.Two;

public class RobotsTest {

	@Test
	public void testGetClasses() throws Exception {
		List<Class<?>> result = Robots.getClasses("de.slothsoft.roborumble.contrib1");
		Assert.assertEquals(Arrays.asList(A.class, B.class, C.class), result);
	}

	@Test
	public void testGetClassesUnknownPackage() throws Exception {
		List<Class<?>> result = Robots.getClasses("de.slothsoft.unknown");
		Assert.assertEquals(new ArrayList<>(), result);
	}

	@Test
	public void testGetPositioners() throws Exception {
		List<Robot> result = Robots.getStonePositioners(Apple.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Banana(), new Orange(), new Pear()), result);
	}

	@Test
	public void testGetPositionersIgnoreAbstractClasses() throws Exception {
		List<Robot> result = Robots.getStonePositioners(One.class.getPackage());
		Assert.assertEquals(Arrays.asList(new Three(), new Two()), result);
	}

	@Test
	public void testGetDefaultPositioners() throws Exception {
		List<Robot> result = Robots.getStonePositioners();
		Assert.assertTrue("The example is missing: " + result, result.contains(new ExampleRobot()));
	}

}
