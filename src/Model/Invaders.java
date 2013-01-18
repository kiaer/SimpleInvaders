package Model;

import View.View;

public class Invaders extends GameObjects {

	private View view;

	public Invaders(View view, String ref, int x, int y) {
		super(ref, x, y);

		this.view = view;

	}

	public void invaderMove() {
		
		
		
		if (x <= 10) {

			view.changeDirection();

		}
		if (x >= 750) {

			view.changeDirection();
		}

		// proceed with normal move

		// super.changeDirection();
	}

}
