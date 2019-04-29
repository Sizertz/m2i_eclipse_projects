package m2i.formation.java;

public class Principale {

	public static void main(String[] args) {
		Point p3d = new Point3D(4, 5, 6);
		if (p3d instanceof Point3D)
			((Point3D) p3d).initialise(12, 16, 6);
		System.out.println(p3d.affiche());

		Forme forme = new Forme();
		forme.addPoint(new Point(2, 5));
		forme.addPoint(p3d);
		forme.addPoint(new Point3D(4, 5, 6));
		for (int i = 0; i < 100; i++) {
			forme.addPoint(Point.randomPoint());
		}

		System.out.println("forme= " + forme);
		System.out.println("Point de module min dans forme: " + forme.minModule());
		forme.sort();
		System.out.println("Tri des points selon le module: " + forme);
	}

}
