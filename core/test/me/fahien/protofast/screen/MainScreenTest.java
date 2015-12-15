package me.fahien.protofast.screen;

import org.junit.Assert;
import org.junit.Test;

/**
 * The {@link MainScreen} Test Case
 *
 * @author Fahien
 */
public class MainScreenTest {

	private MainScreen screen = (MainScreen) ScreenEnumerator.MAIN.getScreen();

	@Test
	public void shouldHaveACamera() {
		Assert.assertNotNull("The main screen has no camera", screen.getCamera());
	}
}
