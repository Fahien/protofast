package me.fahien.protofast.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.fahien.protofast.GdxTestRunner;
import me.fahien.protofast.screen.InfoScreen;
import me.fahien.protofast.screen.MainScreen;
import me.fahien.protofast.screen.ProtoFastScreen;
import me.fahien.protofast.screen.ScreenEnumerator;

/**
 * {@link ProtoFastGame} Test Case
 *
 * @author Fahien
 */
@RunWith(GdxTestRunner.class)
public class ProtoFastGameTest {
	private static final String TEST_DIR = "test/";
	private static final String TEST_ASSET = TEST_DIR + "badlogic.jpg";

	private ProtoFastGame game;

	@Before
	public void before() {
		game = new ProtoFastGame();
	}

	@Test
	public void coudlGetTheAssetManager() {
		Assert.assertNotNull("The game has no asset manager", game.getAssetManager());
	}

	@Test
	public void couldLoadAnAsset() {
		AssetManager assetManager = game.getAssetManager();
		assetManager.load(TEST_ASSET, Texture.class);
		assetManager.finishLoading();
		Assert.assertNotNull("Could not get test asset: " + TEST_ASSET, assetManager.get(TEST_ASSET));
	}

	@Test
	public void couldLoadAnAssetDirectory() {
		AssetManager assetManager = game.getAssetManager();
		FileHandle[] files = Gdx.files.local(TEST_DIR).list();
		for(FileHandle file : files) {
			assetManager.load(file.path(), Texture.class);
		}
		assetManager.finishLoading();
		for(FileHandle file : files) {
			Assert.assertNotNull("Could not get an asset: " + file.path(), assetManager.get(file.path(), Texture.class));
		}
	}

	@Test
	public void couldInjectDependenciesInScreens() {
		for (ScreenEnumerator screenEnum : ScreenEnumerator.values()) {
			ProtoFastScreen screen = screenEnum.getScreen();
			try {
				game.setScreen(screen);
			} catch (GdxRuntimeException e) {
				// Ignore
			}
			Assert.assertTrue(screen.isInitialized());
			Assert.assertEquals("The screens are not equals", screen, game.getScreen());
		}
	}

	@Test
	public void shouldDisposeProperlyAScreenOnChangingIt() {
		MainScreen mainScreen = (MainScreen) ScreenEnumerator.MAIN.getScreen();
		game.setScreen(mainScreen);
		Assert.assertTrue("The screen is not initialized", mainScreen.isInitialized());
		InfoScreen infoScreen = (InfoScreen) ScreenEnumerator.INFO.getScreen();
		game.setScreen(infoScreen);
		Assert.assertFalse("The screen is not disposed properly", mainScreen.isInitialized());
	}

	@Test
	public void shouldShowTheMainScreenAfterCreate() {
		game.create();
		Assert.assertEquals("The game is not showing the main screen", ScreenEnumerator.MAIN.getScreen(), game.getScreen());
	}

	@After
	public void after() {
		game.dispose();
	}
}
