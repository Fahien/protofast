package me.fahien.protofast.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * {@link ProtoFastGame} Test Case
 *
 * @author Fahien
 */
@RunWith(GdxTestRunner.class)
public class ProtoFastGameTest {
	private static final String TEST_DIR = "test/";
	private static final String TEST_ASSET = TEST_DIR + "badlogic.jpg";

	private ProtoFastGame game;

	@Before
	public void before() {
		game = new ProtoFastGame();
	}

	@Test
	public void coudlGetTheAssetManager() {
		Assert.assertNotNull("The game has no asset manager", game.getAssetManager());
	}

	@Test
	public void couldLoadAnAsset() {
		AssetManager assetManager = game.getAssetManager();
		assetManager.load(TEST_ASSET, Texture.class);
		assetManager.finishLoading();
		Assert.assertNotNull("Could not get test asset: " + TEST_ASSET, assetManager.get(TEST_ASSET));
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
			Assert.assertNotNull("Could not get an asset: " + file.path(), assetManager.get(file.path(), Texture.class));
		}
	}

	@After
	public void after() {
		game.dispose();
	}
}
