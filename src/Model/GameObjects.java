package Model;

import java.awt.Graphics;
import java.awt.Rectangle;

import Controller.Constants;
import View.Sprite;
import View.SpriteStore;

public class GameObjects {

	protected int x;
	protected int y;
	private Game game;
	protected Sprite sprite;
	protected double dy;
	public double dx;
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public GameObjects(String ref, int x, int y) {
		this.sprite = SpriteStore.get().getSprite(ref);
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics g) {
		sprite.draw(g, (int) x, (int) y);
	}

	public void setHorizontalMovement(double dx) {
		// System.out.println("x at start "+x);
		if (x >= 5 && x <= 750) {
			this.dx = dx;
			x += (dx);
			// System.out.println(x);
		} else if (x < 5) {
			x = 5;
		} else if (x > 750) {
			x = 750;
		}
	}

	public void invaderMove(long speed) {

		this.dx = speed;

		if (x >= 5 && x <= 750 && y > 5 && y < 600) {
			x += dx;
		} else if (x < 5) {
			x = 5;

		} else if (x > 750) {
			x = 750;

		}

	}

	public void GroupInvaders(int dx) {
		x += (dx);
		System.out.println(dx);
		for (int i = 0; i > 10; i = i + 5) {
			if (x >= 5 && x <= 750 && y > 5 && y < 600) {
				x += dx;
			} else if (x < 5) {
				x = 5;

			} else if (x > 750) {
				x = 750;
			}
		}
	}

	public boolean collided(GameObjects other) {
		me.setBounds((int) x, (int) y, sprite.getWidth(), sprite.getHeight());
		him.setBounds((int) other.x, (int) other.y, other.sprite.getWidth(),
				other.sprite.getHeight());

		return me.intersects(him);
	}

	public void doChange() {
		Constants.ALIEN_MOVE *= -1;
		y = y + 25;
	}

	public Game getView() {
		return game;
	}

	public void setView(Game view) {
		this.game = view;
	}

}
