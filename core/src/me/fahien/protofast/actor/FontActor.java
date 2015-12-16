package me.fahien.protofast.actor;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The Font {@link Actor}
 *
 * @author Fahien
 */
public class FontActor extends Actor {

	private BitmapFont font;
	private StringBuilder text;

	private float marginX;
	private float marginY;

	public FontActor(BitmapFont font, String text) {
		super();
		this.font = font;
		this.text = new StringBuilder(text);
		marginX = font.getCapHeight() / 2;
		marginY = font.getCapHeight();
	}

	/**
	 * Returns the text
	 */
	public StringBuilder getText() {
		return text;
	}

	/**
	 * Sets the text
	 */
	public void setText(String text) {
		this.text.replace(0, this.text.length(), text);
		this.text.delete(text.length(), this.text.length());
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		font.draw(batch, text, getX() + marginX, getY() + marginY);
	}
}
