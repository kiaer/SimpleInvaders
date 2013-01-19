package Controller;

import java.awt.event.*;

public class Controller extends KeyAdapter {
	
	public static boolean leftPressed = false;
	public static boolean rightPressed = false;
	public static boolean firePressed = false;
	public static boolean waitingForKeyPress = true;
	
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
			System.out.println(rightPressed);
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = true;
			System.out.println(firePressed);
		}else{
			firePressed=false;
		}
	} 
		
		
	public void keyReleased(KeyEvent e) {			
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = false;
		}
	}

	public void keyTyped(KeyEvent e) {
		// if we hit escape, then quit the game
		if (e.getKeyChar() == 27) {
			System.exit(0);
		}
	}
	
	public boolean isWaitingForKeyPress() {
		return waitingForKeyPress;
	}
	
	@SuppressWarnings("static-access")
	public void setWaitingForKeyPress(boolean waitingForKeyPress) {
		this.waitingForKeyPress = waitingForKeyPress;
	}
	
}
