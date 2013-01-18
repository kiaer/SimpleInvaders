package Model;

import View.View;

public class Player extends GameObjects {

	private View view;

	public Player(View view, String ref, int x, int y) {
		super(ref, x, y);

		this.view = view;
	}

	public void collide(GameObjects other) {
		// if its an alien, notify the game that the player
		// is dead
		if (other instanceof Invaders) {
			// view.notifyDeath();
		}

	}
}