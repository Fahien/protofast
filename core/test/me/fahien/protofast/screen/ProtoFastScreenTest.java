package me.fahien.protofast.screen;

import com.badlogic.gdx.assets.AssetManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.fahien.protofast.GdxTestRunner;

/**
 * {@link ProtoFastScreen} Test Case
 *
 * @author Fahien
 */
@RunWith(GdxTestRunner.class)
public class ProtoFastScreenTest {

	private ProtoFastScreen screen;

	@Before
	public void before() {
		screen = new ProtoFastScreen();
	}

	@Test
	public void shouldNotBeInitialized() {
		Assert.assertFalse("The screen is initialized", screen.isInitialized());
	}

	@Test
	public void couldInitializeAScreen() {
		screen.setInitialized(true);
		screen.setAssetManager(new AssetManager());
	}

	@After
	public void after() {
		screen.dispose();
		Assert.assertFalse("The screen is still initialized", screen.isInitialized());
		Assert.assertNull("The asset manager is not null", screen.getAssetManager());
	}
}
