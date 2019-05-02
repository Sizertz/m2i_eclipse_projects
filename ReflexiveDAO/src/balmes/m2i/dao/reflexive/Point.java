package balmes.m2i.dao.reflexive;

import java.util.Random;

public class Point {
	// Attributs
	private int x;
	private int y;

	// Méthode
	public void affiche() {
		System.out.println("(" + x + ", " + y + ")");
	}

	public void initialise(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Accesseurs / Getters
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// Mutateurs / Setters
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	// Constructeurs
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Point(int x) {
		this.x = this.y = x;
	}

	public Point() {
		Random rng = new Random();
		x = rng.nextInt();
		y = rng.nextInt();
	}

	// transforme un objet en String
	@Override
	public String toString() {
		return /* super.toString() + */" (" + x + ", " + y + ")";
	}

	// module du point
	public double module() {
		return Math.sqrt(x * x + y * y);
	}

	public static double staticModule(Point p) {
		return Math.sqrt(p.getX() * p.getX() + p.getY() * p.getY());
	}

	public boolean coincide(Point p2) {
		return this.x == p2.x && this.y == p2.y;
	}

	public static boolean coincide(Point p1, Point p2) {
		return p1.x == p2.x && p1.y == p2.y;
	}

	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (getClass() == o.getClass()) {
			Point p2 = (Point) o;
			return p2.x == this.x && p2.y == this.y;
		}
		return false;
	}

	public Point symmetrique() {
		return new Point(-this.x, -this.y);
	}
}
