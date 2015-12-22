package me.fahien.protofast.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.GdxRuntimeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import me.fahien.protofast.GdxTestRunner;
import me.fahien.protofast.screen.ProtoFastScreen;
import me.fahien.protofast.screen.ScreenEnumerator;

import static me.fahien.protofast.GdxTestRunner.logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * {@link ProtoFastGame} Test Case
 *
 * @author Fahien
 */
@RunWith(GdxTestRunner.class)
public class ProtoFastGameTest {
	private static final String TEST_DIR = "test/";
	private static final String TEST_ASSET = TEST_DIR + "badlogic.jpg";
	private static final String MODELS_DIR = "models/";
	private static final String PLAYER_MODEL = MODELS_DIR + "player.g3db";

	private ProtoFastGame game;

	@Before
	public void before() {
		game = new ProtoFastGame();
	}

	@Test
	public void couldGetTheAssetManager() {
		assertNotNull("The game has no asset manager", game.getAssetManager());
	}

	@Test
	public void couldLoadAnAsset() {
		AssetManager assetManager = game.getAssetManager();
		assetManager.load(TEST_ASSET, Texture.class);
		assetManager.finishLoading();
		assertNotNull("Could not get test asset: " + TEST_ASSET, assetManager.get(TEST_ASSET));
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
			assertNotNull("Could not get an asset: " + file.path(), assetManager.get(file.path(), Texture.class));
		}
	}

	@Test
	public void couldInjectDependenciesInScreens() {
		game.loadFont();
		for (ScreenEnumerator screenEnum : ScreenEnumerator.values()) {
			try {
				game.setScreen(screenEnum);
			} catch (GdxRuntimeException|IllegalArgumentException e) {
				logger.error("Could not initialize the Model Batch during tests: " + e.getMessage());
			}
			ProtoFastScreen screen = screenEnum.getScreen();
			assertTrue("The screen is not initialized", screen.isInitialized());
			assertNotNull("The screen has no asset manager", screen.getAssetManager());
			assertNotNull("The screen has no font", screen.getFont());
			assertEquals("The screens are not equals", screen, game.getScreen());
		}
	}

	@Test
	public void shouldDisposeProperlyAScreenOnChangingIt() {
		ProtoFastScreen mainScreen = ScreenEnumerator.MAIN.getScreen();
		try {
			game.setScreen(ScreenEnumerator.MAIN);
		} catch (GdxRuntimeException|IllegalArgumentException e) {
			logger.error("Could not initialize the Model Batch during tests: " + e.getMessage());
		}
		assertTrue("The screen is not initialized", mainScreen.isInitialized());
		try {
			game.setScreen(ScreenEnumerator.INFO);
		} catch (GdxRuntimeException|IllegalArgumentException e) {
			logger.error("Could not initialize the Model Batch during tests: " + e.getMessage());
		}
		assertFalse("The screen is not disposed properly", mainScreen.isInitialized());
	}

	@Test
	public void shouldShowTheMainScreenAfterCreate() {
		try {
			game.create();
		} catch (GdxRuntimeException|IllegalArgumentException e) {
			logger.error("Could not initialize the Model Batch during tests: " + e.getMessage());
		}
		assertEquals("The game is not showing the main screen",
				ScreenEnumerator.MAIN.getScreen(),
				game.getScreen());
	}

	@Test
	public void couldLoadTheCarModel() {
		AssetManager assetManager = game.getAssetManager();
		assetManager.load(PLAYER_MODEL, Model.class);
		assetManager.finishLoading();
		assertNotNull("Could not get car model: " + PLAYER_MODEL, assetManager.get(PLAYER_MODEL));
	}

	@Test
	public void couldLoadAndGetTheFont() {
		game.loadFont();
		assertNotNull("Could not get the font", game.getFont());
	}

	@After
	public void after() {
		game.dispose();
	}
}
