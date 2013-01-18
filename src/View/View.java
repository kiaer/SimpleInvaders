package View;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.*;
import Controller.Controller;
import Model.Invaders;
import Model.Player;
import spaceinvaders.*;
import Model.GameObjects;

public class View extends Canvas {

	private ArrayList Objects = new ArrayList();
	private BufferStrategy strategy;
	private GameObjects ship;
	private int bgCount = 1;
	private int invaderCount = 5 * 12;
	private boolean gameRunning = true;
	private GameObjects invader;
	private GameObjects shot;
	private int startCount = 5 * 12;
	private String message = "";
	private ArrayList DeadObjects = new ArrayList();
	private boolean changeRequired = false;

	public View() {

		JFrame container = new JFrame("Mario Invaders");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);

		setBounds(0, 0, 800, 600);
		panel.add(this);
		panel.setBackground(Color.GRAY);

		setIgnoreRepaint(true);

		container.pack();
		container.setResizable(false);
		container.setVisible(true);

		container.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		addKeyListener(new Controller());

		requestFocus();

		createBufferStrategy(2);
		strategy = getBufferStrategy();

		initializeObjects();

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

			// System.out.println(changeRequired);

			if (changeRequired == true) {
				for (int i = 1; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);
					objects.doChange();

				}
				changeRequired = false;
			}

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
				if (hit(ship.getX(), checkInvaderX(ship.getX()))) {
					removeObject(rightInvader(ship.getX()));

					Controller.firePressed = false;

				} else {
					Controller.firePressed = false;
				}
				// System.out.println("fire");
			}

			// System.out.println(invaderCount);

			if (startCount != invaderCount) {
				for (int i = 1; i < Objects.size(); i++) {
					GameObjects objects = (GameObjects) Objects.get(i);

					objects.invaderMove(Constants.ALIEN_MOVE);
					// System.out.println(Constants.ALIEN_MOVE);

				}

				startCount--;
			}

			// this.revalidate();

			if (invaderCount == 0) {
				g.setColor(Color.white);
				g.drawString(message,
						(800 - g.getFontMetrics().stringWidth(message)) / 2,
						250);
				g.drawString("Gamer over!", (800 - g.getFontMetrics()
						.stringWidth("Game over!")) / 2, 300);
				// this.revalidate();
				break;
			}
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}

	}

	public GameObjects rightInvader(int x) {

		for (int i = 1; i <= Objects.size() - 1; i++) {
			GameObjects objects = (GameObjects) Objects.get(i);
			for (int j = x - 20; j <= x + 20; j++) {

				if (j == objects.getX()) {
					invader = objects;
					break;

				}

			}

		}
		return invader;

	}

	public int checkInvaderX(int x) {

		for (int i = 1; i <= Objects.size() - 1; i++) {
			GameObjects objects = (GameObjects) Objects.get(i);
			for (int j = x - 20; j <= x + 20; j++) {

				if (j == objects.getX()) {
					x = j;
					break;

				}

			}
		}
		return x;
	}

	public GameObjects removeObject(GameObjects object) {

		Objects.remove(object);

		return object;

	}

	public boolean hit(int shipX, int invaderX) {

		int hit = 0;

		for (int i = shipX - 20; i <= shipX + 20; i++) {

			if (i == invaderX) {
				hit = 1;
				break;
			}

		}

		if (hit == 1)
			return true;
		else
			return false;

	}

	public void RemoveDead(Object gameobject) {
		DeadObjects.add(gameobject);
	}

	public void changeDirection() {
		changeRequired = true;

	}
}
