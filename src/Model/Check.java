package Model;

@SuppressWarnings("serial")
public class Check extends Game {

	
	public static GameObjects rightInvader(int x) {

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

	public static int checkInvaderX(int x) {

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
	
	public static boolean hit(int shipX, int invaderX) {

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
	
}
