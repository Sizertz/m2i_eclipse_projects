package m2i.formation.java;

import java.util.Random;

public class Point {
	protected int x;
	protected int y;

	public static Point randomPoint() {
		Random rng = new Random();
		return new Point(rng.nextInt(30), rng.nextInt(30));
	}

	public Point(int x, int y) {
		initialise(x, y);
	}

	public void initialise(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public String affiche() {
		return "(" + x + "," + y + ")";
	}

	public double module() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return affiche();
	}
}
