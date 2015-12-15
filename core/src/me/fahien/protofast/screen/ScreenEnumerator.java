package me.fahien.protofast.screen;

/**
 * The {@link ProtoFastScreen} Enumerator
 *
 * @author Fahien
 */
public enum ScreenEnumerator {
	MAIN(new MainScreen()), INFO(new InfoScreen());

	private ProtoFastScreen screen;

	ScreenEnumerator(ProtoFastScreen screen) {
		this.screen = screen;
	}

	/**
	 * Returns the screen
	 */
	public ProtoFastScreen getScreen() {
		return screen;
	}
}
