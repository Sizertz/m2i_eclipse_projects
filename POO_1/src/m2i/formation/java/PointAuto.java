package m2i.formation.java;

public class PointAuto {
	// Attributs
	private int x, y;

	// Constructeurs
	public PointAuto(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public PointAuto(int x) {
		super();
		this.x = x;
		this.y = x;
	}

	// getters & setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	// transforme l'objet en string
	@Override
	public String toString() {
		return "PointAuto [x=" + x + ", y=" + y + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this.hashCode() != obj.hashCode())
			return false;
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PointAuto other = (PointAuto) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
