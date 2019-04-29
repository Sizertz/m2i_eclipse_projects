package m2i.formation.java;

import java.util.ArrayList;

public class Forme {
	private ArrayList<Point> points;

	public Forme() {
		points = new ArrayList<Point>();
	}

	public void addPoint(Point p) {
		points.add(p);
	}

	@Override
	public String toString() {
		return "Forme [points=" + points + "]";
	}

	public Point minModule() {
		if (points.size() == 0) {
			return null;
		}
		Point pMin = points.get(0);
		for (Point point : points) {
			if (point.module() < pMin.module()) {
				pMin = point;
			}
		}
		return pMin;
	}

	public int minModuleIndex(int debut) {
		if (points.size() == 0 || debut < 0)
			return -1;
		int indexMin = debut;
		for (int i = debut + 1; i < points.size(); i++) {
			if (points.get(i).module() < points.get(indexMin).module()) {
				indexMin = i;
			}
		}
		return indexMin;
	}

	public void sort() {
		for (int i = 0; i < points.size(); i++) {
			int minIndex = minModuleIndex(i);
			permutePoints(i, minIndex);
		}
	}

	private void permutePoints(int i, int j) {
		if (i < 0 || i >= points.size() || j < 0 || j >= points.size())
			return;
		Point temp = points.get(i);
		points.set(i, points.get(j));
		points.set(j, temp);

	}
}
