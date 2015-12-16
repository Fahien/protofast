package me.fahien.protofast.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.fahien.protofast.GdxTestRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
		assertFalse("The screen is initialized", screen.isInitialized());
	}

	@Test
	public void couldInitializeAScreen() {
		screen.setInitialized(true);
		screen.setAssetManager(new AssetManager());
		screen.setFont(new BitmapFont());
		assertTrue("The screen is not initialized", screen.isInitialized());
		assertNotNull("The asset manager is null", screen.getAssetManager());
		assertNotNull("The font is null", screen.getFont());
	}

	@After
	public void after() {
		screen.dispose();
		assertFalse("The screen is still initialized", screen.isInitialized());
		assertNull("The asset manager is not null", screen.getAssetManager());
		assertNull("The font is not null", screen.getFont());
	}
}
