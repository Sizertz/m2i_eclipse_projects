package m2i.formation.java;

import java.util.ArrayList;

public class Principale {

	public static void main(String[] args) {
		Point p1 = new Point();
		p1.affiche();
		p1.initialise(12, 9);
		System.out.print("p1 ");
		p1.affiche();
		Point p2;
		p2 = new Point(5);
		System.out.print("p2 ");
		p2.affiche();

		// println et hashcode
		System.out.println("println(p1): " + p1);
		System.out.println("println(p2): " + p2);
		// adresse de p2
		System.out.println(Integer.toHexString(p2.hashCode()));
		System.out.println(Integer.toHexString(System.identityHashCode(p2)));

		// module
		System.out.println("Module de p1 :" + p1.module());
		System.out.println("Module de p2 :" + Point.staticModule(p2));

		// coincidence
		System.out.println("p1 coincide avecc p2 = " + p1.coincide(p2) + " (" + Point.coincide(p1, p2) + ")");

		// symmetrique
		Point p3 = p1.symmetrique();
		System.out.println("p3 symmetrique de p1: " + p3);

		// Arraylists
		ArrayList<Point> lstPoints = new ArrayList<Point>();
		lstPoints.add(p1);
		lstPoints.add(p2);
		lstPoints.add(p3);

		for (Point point : lstPoints) {
			point.affiche();
		}
		System.out.println(lstPoints);
		
	}

}
