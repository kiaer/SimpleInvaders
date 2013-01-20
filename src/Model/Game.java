package Model;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import View.GameFrame;

import Controller.Constants;
import Controller.Controller;

@SuppressWarnings("serial")
public class Game extends Canvas {

	protected static ArrayList<GameObjects> Objects = new ArrayList<GameObjects>();
	private ArrayList<Object> DeadObjects = new ArrayList<Object>();
	public static BufferStrategy strategy;
	protected static GameObjects invader;
	private GameObjects ship;
	private int invaderCount = 5 * 12;
	private int startCount = 5 * 12;
	private boolean gameRunning = true;
	private boolean changeRequired = false;

	public Game() {

		new GameFrame();
		while (true) {

			initializeObjects();
			loop();
			
			invaderCount = 5 * 12;
			startCount = 5 * 12;
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

	public void initializeObjects() {

		ship = new Player(this, "sprites/ship.gif", 600, 550);

		Objects.add(ship);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 12; j++) {
				invader = new Invaders(this, "sprites/alien.gif",
						100 + (j * 50), 10 + (i * 50));
				Objects.add(invader);
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

			ship.setHorizontalMovement(0);

			if ((Controller.rightPressed) && (!Controller.leftPressed)) {
				ship.setHorizontalMovement(Constants.MOVE_SPEED);

			} else if ((Controller.leftPressed) && (!Controller.rightPressed)) {
				ship.setHorizontalMovement(-(Constants.MOVE_SPEED));

			}
			if (Controller.firePressed) {
				if (Check.hit(ship.getX(), Check.checkInvaderX(ship.getX()))) {
					removeObject(Check.rightInvader(ship.getX()));

					Controller.firePressed = false;

				} else {
					Controller.firePressed = false;
				}
				
			}

			
			if (changeRequired == true) {
				

				for (int i = 1; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.doChange();

				}
				changeRequired = false;
			}

			if (startCount != invaderCount) {
				for (int i = 1; i <= Objects.size() - 1; i++) {
					GameObjects objects = (GameObjects) Objects.get(i);

					objects.GroupInvaders(Constants.ALIEN_MOVE);
					objects.invaderMove(Constants.ALIEN_MOVE);

				}

				startCount--;
			}

			// this.revalidate();

		
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
}
