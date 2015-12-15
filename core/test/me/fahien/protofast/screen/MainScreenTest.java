package me.fahien.protofast.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.GdxRuntimeException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static me.fahien.protofast.GdxTestRunner.logger;
import static org.junit.Assert.assertNotNull;

/**
 * The {@link MainScreen} Test Case
 *
 * @author Fahien
 */
public class MainScreenTest {

	private MainScreen screen = (MainScreen) ScreenEnumerator.MAIN.getScreen();

	@Before
	public void before() {

	}

	@Test
	public void shouldHaveACamera() {
		assertNotNull("The main screen has no camera", screen.getCamera());
	}

	@Test
	public void couldLoadResources() {
		AssetManager assetManager = new AssetManager();
		screen.setAssetManager(assetManager);
		screen.loadResources();
		assertNotNull("The car model is null", assetManager.get(MainScreen.CAR_MODEL));
	}

	@Test
	public void couldInitializeEntitiesAfterLoadResources() {
		couldLoadResources();
		screen.initEntities();
		assertNotNull("The instance is null", screen.getInstance());
	}

	@Test
	public void couldInitializeTheModelBatch() {
		try {
			screen.initBatch();
			assertNotNull(screen.getBatch());
		} catch (GdxRuntimeException e){
			logger.error("Could not initialize the Model Batch during tests: " + e.getMessage());
		}
	}

	@Test
	public void couldInitializeTheEnvironment() {
		screen.initEnvironment();
		assertNotNull("The environment is null", screen.getEnvironment());
	}

	@After
	public void after() {
		screen.dispose();
	}
}
