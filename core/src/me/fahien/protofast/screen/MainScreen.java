package me.fahien.protofast.screen;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

import me.fahien.protofast.camera.MainCamera;

/**
 * The Main {@link ProtoFastScreen}
 *
 * @author Fahien
 */
public class MainScreen extends ProtoFastScreen {

	private PerspectiveCamera camera;

	// TODO remove temp variables
	private Model model;
	private ModelInstance instance;
	private ModelBatch batch;
	private Environment environment;

	public MainScreen() {
		camera = new MainCamera();
	}

	public PerspectiveCamera getCamera() {
		return camera;
	}

	@Override
	public void show() {
		super.show();
		camera.update();

		// testInit();
	}

	private void testInit() {
		batch = new ModelBatch();
		ModelBuilder modelBuilder = new ModelBuilder();
		model = modelBuilder.createBox(5f, 5f, 5f,
				new Material(ColorAttribute.createDiffuse(Color.GREEN)),
				VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
		instance = new ModelInstance(model);
		environment = new Environment();
		environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		batch.begin(camera);
		batch.render(instance, environment);
		batch.end();
	}

	@Override
	public void dispose() {
		super.dispose();
		testDispose();
	}

	private void testDispose() {
		if (batch != null) batch.dispose();
		if (model != null) model.dispose();
	}
}
