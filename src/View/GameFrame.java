package View;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Constants;
import Controller.Controller;
import Model.Game;

@SuppressWarnings("serial")
public class GameFrame extends Canvas {

	public GameFrame() {
		JFrame container = new JFrame("Simple Invaders");

		JPanel panel = (JPanel) container.getContentPane();
		panel.setPreferredSize(new Dimension(Constants.GRID_HEIGHT, Constants.GRID_WIDTH));
		panel.setLayout(null);

		setBounds(0, 0, Constants.GRID_HEIGHT, Constants.GRID_WIDTH);
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
		Game.strategy = getBufferStrategy();
	}
}
