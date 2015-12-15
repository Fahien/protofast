package me.fahien.protofast.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.utils.Logger;

import me.fahien.protofast.screen.ProtoFastScreen;

/**
 * Proto Fast {@link Game}
 *
 * @author Fahien
 */
public class ProtoFastGame extends Game {
	private static final int LOGGER_LEVEL = Logger.DEBUG;
	/**
	 * This is the {@link Logger} which will be used throughout the whole testing
	 */
	public static final Logger logger = new Logger(ProtoFastGame.class.getSimpleName(), LOGGER_LEVEL);

	private AssetManager assetManager;

	public ProtoFastGame() {
		assetManager = new AssetManager();
	}

	/**
	 * Returns the {@link AssetManager}
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

	/**
	 * Sets properly a {@link ProtoFastScreen}
	 */
	public void setScreen(ProtoFastScreen screen) {
		super.setScreen(screen);
		screen.setInitialized(true);
	}

	@Override
	public void create() {
		Gdx.app.setLogLevel(LOGGER_LEVEL);
		logger.debug("Game initialized");
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		assetManager.dispose();
		logger.debug("Game disposed");
	}
}
