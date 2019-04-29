package m2i.formation.java;

public class Point3D extends Point {
	public Point3D(int x, int y, int z) {
		super(x, y);
		this.z = z;
	}

	protected int z;

	public void initialise(int x, int y, int z) {
		super.initialise(x, y);
		this.z = z;
	}

	@Override
	public String affiche() {
		return "(" + this.x + "," + this.y + "," + this.z + ")";
	}

	@Override
	public double module() {
		return Math.sqrt(x * x + y * y + z * z);
	}

}
