package me.fahien.protofast.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Logger;

import me.fahien.protofast.screen.ProtoFastScreen;
import me.fahien.protofast.screen.ScreenEnumerator;

/**
 * Proto Fast {@link Game}
 *
 * @author Fahien
 */
public class ProtoFastGame extends Game {
	private static final String logo = "Powered by\n" +
			"╔═╗┬─┐┌─┐┌┬┐┌─┐╔═╗┌─┐┌─┐┌┬┐\n" +
			"╠═╝├┬┘│ │ │ │ │╠╣ ├─┤└─┐ │ \n" +
			"╩  ┴└─└─┘ ┴ └─┘╚  ┴ ┴└─┘ ┴ ";

	private static final int LOGGER_LEVEL = Logger.DEBUG;
	private static final String SYSTEM_PATH = "system/";
	private static final String SYSTEM_FONT = SYSTEM_PATH + "font.fnt";

	public static final Logger logger = new Logger(ProtoFastGame.class.getSimpleName(), LOGGER_LEVEL);

	private AssetManager assetManager;
	private BitmapFont font;

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
	 * Returns the {@link BitmapFont}
	 */
	public BitmapFont getFont() {
		return font;
	}

	protected void loadFont() {
		assetManager.load(SYSTEM_FONT, BitmapFont.class);
		assetManager.finishLoading();
		font = assetManager.get(SYSTEM_FONT);
	}

	/**
	 * Sets the screen using {@link ScreenEnumerator}
	 */
	public void setScreen(ScreenEnumerator screenEnumerator) {
		ProtoFastScreen screen = screenEnumerator.getScreen();
		screen.setAssetManager(assetManager);
		screen.setFont(font);
		screen.setInitialized(true);
		setScreen(screen);
	}

	@Override
	public void create() {
		Gdx.app.setLogLevel(LOGGER_LEVEL);
		logger.info(logo);
		loadFont();
		setScreen(ScreenEnumerator.MAIN);
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
