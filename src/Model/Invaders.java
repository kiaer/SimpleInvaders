package Model;


public class Invaders extends GameObjects {

	private Game game;

	public Invaders(Game game, String ref, int x, int y) {
		super(ref, x, y);

		this.game = game;

	}

	public void invaderMove(long speed) {
		
		if (x < 10) {
			x = 5;
			game.changeDirection();

		}
		if (x > 750) {
			x = 750;
			game.changeDirection();
		}

	// proceed with normal move

		// super.changeDirection();
	}

}
