package me.fahien.protofast.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;

/**
 * Proto Fast {@link Screen}
 *
 * @author Fahien
 */
public class ProtoFastScreen implements Screen {
	public static final float WIDTH = 960f;
	public static final float HEIGHT = 540f;

	private boolean initialized;
	private AssetManager assetManager;

	/**
	 * Tests whether is initialized
	 */
	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Sets initialized
	 */
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	/**
	 * Returns the {@link AssetManager}
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Sets the {@link AssetManager}
	 */
	public void setAssetManager(AssetManager assetManager) {
		this.assetManager = assetManager;
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void dispose() {
		assetManager = null;
		initialized = false;
	}
}
