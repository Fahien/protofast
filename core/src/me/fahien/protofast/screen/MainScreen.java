package me.fahien.protofast.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.fahien.protofast.actor.FontActor;
import me.fahien.protofast.camera.MainCamera;
import me.fahien.protofast.game.ProtoFastGame;

/**
 * The Main {@link ProtoFastScreen}
 *
 * @author Fahien
 */
public class MainScreen extends ProtoFastScreen {
	private static final boolean DEBUG_ALL = false;
	protected static final String MODELS_DIR = "models/";
	protected static final String G3DB_EXT = ".g3db";
	private static final String TITLE_TXT = "Unknown";
	private static final String FPS_TXT = "FPS: ";
	private static final float FPS_X = 4.0f;
	private static final float FPS_Y = 8.0f;
	private static final String LOADING_TXT = " %";
	private static final String RIGHT_ARROW_TXT = ">";
	private static final String LEFT_ARROW_TXT = "<";
	private static final float ARROW_SIZE = 64.0f;

	private PerspectiveCamera camera;

	private Stage stage;
	private FontActor titleActor;
	private FontActor fpsActor;
	private FontActor loadingActor;
	private Viewport viewport;

	private Array<String> modelList;
	private int modelIndex;

	private ModelInstance instance;
	private ModelBatch batch;
	private Environment environment;

	// TODO remove test variables
	private CameraInputController cameraController;

	public MainScreen() {
		camera = new MainCamera();
		viewport = new FitViewport(WIDTH, HEIGHT);
	}

	/**
	 * Loads the model list
	 */
	public void loadModelList() {
		FileHandle[] files = Gdx.files.internal(MODELS_DIR).list();
		modelList = new Array<>(files.length);
		for(FileHandle file : files) {
			String path = file.path();
			if (path.endsWith(G3DB_EXT)) {
				modelList.add(file.nameWithoutExtension());
			}
		}
	}

	/**
	 * Returns the model list
	 */
	protected Array<String> getModelList() {
		return modelList;
	}

	/**
	 * Returns the camera
	 */
	public PerspectiveCamera getCamera() {
		return camera;
	}

	/**
	 * Returns the stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 *
	 * Returns the {@link ModelInstance}
	 */
	public ModelInstance getInstance() {
		return instance;
	}

	/**
	 * Returns the {@link ModelBatch}
	 */
	public ModelBatch getBatch() {
		return batch;
	}

	/**
	 * Returns the {@link Environment}
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * Loads the resources
	 */
	protected void loadModel(String modelName) {
		getAssetManager().load(MODELS_DIR + modelName + G3DB_EXT, Model.class);
		if (titleActor != null) titleActor.setText(modelName);
	}

	/**
	 * Initializes the entities
	 */
	protected void updateInstance(String modelName) {
		if (instance == null) {
			AssetManager assetManager = getAssetManager();
			assetManager.update();
			if (assetManager.isLoaded(MODELS_DIR + modelName + G3DB_EXT)) {
				Model model = assetManager.get(MODELS_DIR + modelName + G3DB_EXT);
				if (model != null) {
					instance = new ModelInstance(model);
				}
			}
			if (loadingActor != null) {
				loadingActor.setText((int)assetManager.getProgress() * 100 + LOADING_TXT);
			}
		}
	}

	/**
	 * Initializes the {@link ModelBatch}
	 */
	protected void initBatch() {
		batch = new ModelBatch();
	}

	/**
	 * Initializes the {@link Stage}
	 */
	protected void initStage() {
		stage = new Stage();
		stage.setViewport(viewport);
		stage.setDebugAll(DEBUG_ALL);
	}

	/**
	 * Initializes the {@link Actor}s
	 */
	protected void initActors() {
		titleActor = new FontActor(getFont(), TITLE_TXT);
		titleActor.setPosition(WIDTH / 2, HEIGHT - FPS_Y);
		titleActor.setHalign(FontActor.Halign.CENTER);

		fpsActor = new FontActor(getFont(), FPS_TXT + Gdx.graphics.getFramesPerSecond());
		fpsActor.setPosition(FPS_X, FPS_Y);

		loadingActor = new FontActor(getFont(), LOADING_TXT);
		loadingActor.setPosition(WIDTH - FPS_X, FPS_Y);
		loadingActor.setHalign(FontActor.Halign.LEFT);

		FontActor leftArrow = new FontActor(getFont(), LEFT_ARROW_TXT);
		leftArrow.setSize(ARROW_SIZE, ARROW_SIZE);
		leftArrow.setY(HEIGHT / 2 - ARROW_SIZE / 2);
		leftArrow.setHalign(FontActor.Halign.CENTER);
		leftArrow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				loadPreviousModel();
			}
		});

		FontActor rightArrow = new FontActor(getFont(), RIGHT_ARROW_TXT);
		rightArrow.setSize(ARROW_SIZE, ARROW_SIZE);
		rightArrow.setPosition(WIDTH - ARROW_SIZE, HEIGHT / 2 - ARROW_SIZE / 2);
		rightArrow.setHalign(FontActor.Halign.CENTER);
		rightArrow.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				loadNextModel();
			}
		});

		stage.addActor(leftArrow);
		stage.addActor(rightArrow);
		stage.addActor(loadingActor);
		stage.addActor(fpsActor);
		stage.addActor(titleActor);
	}

	/**
	 * Loads previous {@link Model}
	 */
	private void loadPreviousModel() {
		ProtoFastGame.logger.info("Loading previous model");
		instance = null;
		getAssetManager().unload(MODELS_DIR + modelList.get(modelIndex) + G3DB_EXT);
		modelIndex = (--modelIndex < 0) ? modelList.size - 1 : modelIndex;
		loadModel(modelList.get(modelIndex));
	}

	/**
	 * Loads next {@link Model}
	 */
	private void loadNextModel() {
		ProtoFastGame.logger.info("Loading next model");
		titleActor.setText(TITLE_TXT);
		instance = null;
		getAssetManager().unload(MODELS_DIR + modelList.get(modelIndex) + G3DB_EXT);
		modelIndex = (++modelIndex >= modelList.size) ? 0 : modelIndex;
		loadModel(modelList.get(modelIndex));
	}

	/**
	 * Initializes the {@link Environment}
	 */
	protected void initEnvironment() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
	}

	/**
	 * Initializes the controller
	 */
	private void initController() {
		cameraController = new CameraInputController(camera);
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(stage);
		multiplexer.addProcessor(cameraController);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void show() {
		super.show();
		camera.update();

		initStage();
		initActors();

		loadModelList();
		loadModel(modelList.get(modelIndex));
		updateInstance(modelList.get(modelIndex));
		initBatch();
		initEnvironment();
		initController();
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		updateInstance(modelList.get(modelIndex));
		testRender();
		test2DRender(delta);
	}

	// TODO remove test methods
	private void testRender() {
		cameraController.update();
		batch.begin(camera);
		if (instance != null) batch.render(instance, environment);
		batch.end();
	}

	private void test2DRender(float delta) {
		fpsActor.setText(FPS_TXT + Gdx.graphics.getFramesPerSecond());
		viewport.apply();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height, true);
	}

	@Override
	public void dispose() {
		super.dispose();
		if (batch != null) batch.dispose();
		if (stage != null) stage.dispose();
	}
}
