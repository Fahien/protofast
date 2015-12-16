package me.fahien.protofast.screen;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import me.fahien.protofast.actor.FontActor;
import me.fahien.protofast.camera.MainCamera;

/**
 * The Main {@link ProtoFastScreen}
 *
 * @author Fahien
 */
public class MainScreen extends ProtoFastScreen {
	protected static final String MODELS_DIR = "models/";
	protected static final String CAR_MODEL = MODELS_DIR + "racing_v0.4.003.g3db";
	private static final String FPS_TXT = "FPS: ";

	private PerspectiveCamera camera;

	private Stage stage;
	private FontActor fpsActor;
	private Viewport viewport;

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
	protected void loadResources() {
		AssetManager assetManager = getAssetManager();
		assetManager.load(CAR_MODEL, Model.class);
		assetManager.finishLoading();
	}

	/**
	 * Initializes the entities
	 */
	protected void initEntities() {
		Model model = getAssetManager().get(CAR_MODEL);
		instance = new ModelInstance(model);
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
	}

	/**
	 * Initializes the {@link Actor}s
	 */
	protected void initActors() {
		fpsActor = new FontActor(getFont(), FPS_TXT + Gdx.graphics.getFramesPerSecond());
		stage.addActor(fpsActor);
	}

	/**
	 * Initializes the {@link Environment}
	 */
	protected void initEnvironment() {
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
	}

	@Override
	public void show() {
		super.show();
		camera.update();

		initStage();
		initActors();
		loadResources();
		initEntities();
		initBatch();
		initEnvironment();
		testInit();
	}

	private void testInit() {
		cameraController = new CameraInputController(camera);
		Gdx.input.setInputProcessor(cameraController);
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		testRender();
		test2DRender(delta);
	}

	// TODO remove test methods
	private void testRender() {
		cameraController.update();
		batch.begin(camera);
		batch.render(instance, environment);
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
