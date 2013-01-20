package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import View.GameFrame;

import Controller.Constants;
import Controller.Controller;

//Class to run the game. Contains gameloops and objects. 
//Calls every other class for methods. 
//The game is mainly played through this class. 
//This class also alters everything in the model.

@SuppressWarnings("serial")
public class Game extends Canvas {

	protected static ArrayList<GameObjects> Objects = new ArrayList<GameObjects>();
	protected ArrayList<GameObjects> Invader = new ArrayList<GameObjects>();
	private ArrayList<Object> DeadObjects = new ArrayList<Object>();
	public static BufferStrategy strategy;
	protected static GameObjects invader;
	private GameObjects ship;
	private int invaderCount = 10 * 3;
	private boolean gameRunning = true;
	private boolean changeRequired = false;
	private double lastFire;

	public Game() {

		new GameFrame();
		while (true) {

			initializeObjects();
			loop();

			invaderCount = 10 * 3;

			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

	public void initializeObjects() {

		ship = new Player(this, "sprites/ship.gif", 600, 550);

		Objects.add(ship);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 9; j++) {
				invader = new Invaders(this, "sprites/alien.png",
						50 + (j * 50), 10 + (i * 50));
				Objects.add(invader);
				Invader.add(invader);
			}
		}

	}

	public void loop() {

		while (gameRunning) {

			Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
			g.setColor(Color.black);
			g.fillRect(0, 0, 800, 600);

			for (int i = 0; i < Objects.size(); i++) {
				GameObjects objects = (GameObjects) Objects.get(i);
				objects.draw(g);
			}

			g.dispose();
			strategy.show();

			if (invaderCount != Objects.size() - 1) {
				invaderCount--;

			}

			for (int p = 1; p < Objects.size(); p++) {
				if (Objects.get(p).getY() <= Objects.get(0).getY()) {
					break;

				}
			}

			ship.setHorizontalMovement(0);

			if ((Controller.rightPressed) && (!Controller.leftPressed)) {
				ship.setHorizontalMovement(Constants.MOVE_SPEED);

			} else if ((Controller.leftPressed) && (!Controller.rightPressed)) {
				ship.setHorizontalMovement(-(Constants.MOVE_SPEED));

			}
			if (Controller.firePressed) {

				fire();
				Controller.firePressed = false;

			}

			if (changeRequired) {

				for (int i = 1; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.doChange();

				}
				changeRequired = false;
			}

			if (invaderCount == 0) {

				Objects.removeAll(Objects);

				break;

			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

	public GameObjects removeObject(GameObjects object) {

		Objects.remove(object);

		return object;

	}

	public void RemoveDead(Object gameobject) {
		DeadObjects.add(gameobject);
	}

	public void changeDirection() {
		changeRequired = true;

	}

	public void fire() {
		if (System.currentTimeMillis() - lastFire < 300) {
			return;
		}
		lastFire = System.currentTimeMillis();
		if (Check.hit(ship.getX(), Check.checkInvaderX(ship.getX()))) {
			removeObject(Check.rightInvader(ship.getX()));
			for (int i = 1; i <= Objects.size() - 1; i++) {
				GameObjects objects = (GameObjects) Objects.get(i);

				objects.GroupInvaders(Constants.ALIEN_MOVE);
				objects.invaderMove(Constants.ALIEN_MOVE);

			}
		} else {

			for (int i = 1; i <= Objects.size() - 1; i++) {
				GameObjects objects = (GameObjects) Objects.get(i);

				objects.GroupInvaders(Constants.ALIEN_MOVE);
				objects.invaderMove(Constants.ALIEN_MOVE);

			}

			Controller.firePressed = false;
		}
	}
}
