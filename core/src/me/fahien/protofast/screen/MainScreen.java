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

import me.fahien.protofast.camera.MainCamera;

/**
 * The Main {@link ProtoFastScreen}
 *
 * @author Fahien
 */
public class MainScreen extends ProtoFastScreen {
	protected static final String MODELS_DIR = "models/";
	protected static final String CAR_MODEL = MODELS_DIR + "car.g3db";

	private PerspectiveCamera camera;

	private ModelInstance instance;
	private ModelBatch batch;
	private Environment environment;

	// TODO remove test variables
	private CameraInputController cameraController;

	public MainScreen() {
		camera = new MainCamera();
	}

	/**
	 * Returns the camera
	 */
	public PerspectiveCamera getCamera() {
		return camera;
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
	}

	// TODO remove test methods
	private void testRender() {
		cameraController.update();
		batch.begin(camera);
		batch.render(instance, environment);
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		if (batch != null) batch.dispose();
	}
}
