package me.fahien.protofast.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;

import org.junit.After;
import org.junit.Assert;
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
	protected static final String MODEL = "track_cone_v0.4.010";

	private MainScreen screen = (MainScreen) ScreenEnumerator.MAIN.getScreen();

	@Before
	public void before() {}

	@Test
	public void shouldHaveACamera() {
		assertNotNull("The main screen has no camera", screen.getCamera());
	}

	@Test
	public void couldInitializeTheStage() {
		try {
			screen.initStage();
			assertNotNull("The main screen has no stage", screen.getStage());
		} catch (IllegalArgumentException e) {
			logger.error("Could not initialize the stage during tests: " + e.getMessage());
		}
	}

	@Test
	public void couldLoadResources() {
		AssetManager assetManager = new AssetManager();
		screen.setAssetManager(assetManager);
		screen.loadModel(MODEL);
		while (assetManager.getProgress() < 1.0f) {
			assetManager.update();
		}
		assertNotNull("The car model is null", assetManager.get(MainScreen.MODELS_DIR + MODEL + MainScreen.G3DB_EXT));
	}

	@Test
	public void couldInitializeInstanceAfterLoadResources() {
		couldLoadResources();
		screen.updateInstance(MODEL);
		assertNotNull("The instance is null", screen.getInstance());
	}

	@Test
	public void couldInitializeTheBatch() {
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

	private void createTheModelList() {
		String modelList = "";
		FileHandle[] files = Gdx.files.local(MainScreen.MODELS_DIR).list();
		for (FileHandle file : files) {
			if (file.path().endsWith(MainScreen.G3DB_EXT)) {
				modelList += file.nameWithoutExtension() + "\n";
			}
		}
		FileHandle fileList = Gdx.files.local(MainScreen.MODEL_LIST);
		fileList.writeString(modelList, false);
	}

	@Test
	public void couldLoadTheListOfModels() {
		createTheModelList();
		screen.loadModelList();
		Array<String> list = screen.getModelList();
		Assert.assertNotNull("The model list is null", list);
		Assert.assertNotEquals("The model list is empty", list.size);
	}

	@After
	public void after() {
		screen.dispose();
	}
}
