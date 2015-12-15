package me.fahien.protofast.screen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.fahien.protofast.GdxTestRunner;

/**
 * Proto Fast {@link ScreenEnumerator} Test Case
 *
 * @author Fahien
 */
@RunWith(GdxTestRunner.class)
public class ScreenEnumeratorTest {

	@Test
	public void couldGetTheMainScreen() {
		Assert.assertNotNull("The main screen is null", ScreenEnumerator.MAIN.getScreen());
	}

	@Test
	public void couldGetTheInfoScreen() {
		Assert.assertNotNull("The info screen is null", ScreenEnumerator.INFO.getScreen());
	}
}
