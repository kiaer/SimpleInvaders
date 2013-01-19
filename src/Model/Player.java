package Model;


public class Player extends GameObjects {

	public Player(Game game, String ref, int x, int y) {
		super(ref, x, y);
	}

	public void collide(GameObjects other) {
		// if its an alien, notify the game that the player
		// is dead
		if (other instanceof Invaders) {
			// view.notifyDeath();
		}

	}
}